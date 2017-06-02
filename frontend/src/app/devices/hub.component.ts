import { Component, OnInit, OnDestroy, ViewChild, ComponentRef } from '@angular/core';
import { Location } from "@angular/common";
import { Hub } from '../_models/hub';
import { Device } from '../_models/device';
import { PlotComponent } from '../Plot/plot.component';
import { DeviceService } from "../_services/devices.service";
import { HubService } from "../_services/hub.service";
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Socket } from "../_models/socket";
import { SocketService } from "../_services/socket.service";
import { BaseChartDirective } from 'ng2-charts';
import { AnonymousSubscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/switchMap';





@Component({
    selector: 'hub',
    templateUrl: 'hub.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [DeviceService, SocketService, HubService]
})

export class HubComponent implements OnInit, OnDestroy{
    private hub: Hub;
    private workstationId: string;
    sockets = new Array<Socket>();

    private valuesForPlot: any;
    private dataSubscription: AnonymousSubscription;
    private timerSubscription: AnonymousSubscription;
    private valuesForPlotArrived: boolean = false;
    private plotTime: number = 1;



    private dstart = new Array<String>();
    private dend = new Array<String>();
    private tstart = new Array<String>();
    private tend = new Array<String>();
    private isLiveData: boolean;
    private times: number[];
    private stateArrayHistory;
    private valuesForPlotHistory: any;

    constructor(private deviceService: DeviceService, private route: ActivatedRoute, private loc: Location,
    private socketService: SocketService, private hubService: HubService) { }

    ngOnInit(): void {
        this.route.params
            .subscribe((params: Params) => {/*this.deviceService.getDevice(+params['id'], "hub")*/
                this.hub = new Hub(params['id']);
                this.hub.toTimestamp = this.getTimestamp();
                this.hub.fromTimestamp = this.hub.toTimestamp - 60000;
                this.deviceService.getWorkstation(this.hub.id)
                    .subscribe(workstation => {this.workstationId = workstation;
                                                this.deviceService.getWorkstationSocketIds(this.workstationId)
                                                    .subscribe(socketsId => {this.hub.link = socketsId;
                                                        this.hub.link.forEach(element => {this.sockets.push(new Socket(element))});
                                                    })
                                                });
                })
                this.refreshData();



            /*.subscribe(hub => {
                this.hub = hub;
                hub.link.forEach(id => {
                    this.deviceService.getAllDevices().toPromise()
                    .then(Device => Device.forEach(dev => {
                        if ((new String(dev.id)).valueOf() === (new String(id)).valueOf()) {
                            this.sockets.push(dev);
                        }})
                )})}); */     
    }



    public ngOnDestroy() {
        if (this.dataSubscription) {
            this.dataSubscription.unsubscribe();
        }
        if (this.timerSubscription) {
            this.timerSubscription.unsubscribe();
        }
    }

    private subscribeToData(): void {
        this.timerSubscription = Observable.timer(3000).first().subscribe(() => {
            this.hub.toTimestamp = this.getTimestamp();
            this.hub.fromTimestamp = this.hub.toTimestamp - 60000;
            this.refreshData()
        });
    }

    private refreshData(): void {
        this.dataSubscription = this.hubService.getHubLastState(this.hub.id)
            .subscribe(hub => {
                console.log(this.hub.fromTimestamp)
                console.log(this.hub.toTimestamp)
                console.log(hub);
                //this.hub.state = states[states.length - 1].isOn;
                //this.hub.consommation = states[states.length - 1].power

                this.hub.consommation = hub.power;
                if (this.plotTime == 1) {
                    this.hub.statesArray = [{power:hub.power, timestamp:this.hub.toTimestamp}];
                    /*let valuesForPlotTmp = this.parseStatesArray();
                    this.valuesForPlot= [];
                    this.valuesForPlot = valuesForPlotTmp;
                    this.valuesForPlotArrived = true;*/
                    this.valuesForPlot = this.parseStatesArray();
                    this.valuesForPlotArrived = true;
                }

                this.subscribeToData();
            });
    }

    private getTimestamp(): number {
        return +new Date();
    }
    private getHourMinutesFromTimestamp(timestamp: number) {
        let date: Date = new Date(timestamp);
        let dateString: String = date.getHours().toString();
        dateString = dateString + ":" + date.getMinutes().toString();
        return dateString;
    }

    private getHourMinutesSecondsFromTimestamp(timestamp: number) {
        let date: Date = new Date(timestamp);
        let dateString: String = date.getHours().toString();
        dateString = dateString + ":" + date.getMinutes().toString() + ":" + date.getSeconds().toString();
        return dateString;
    }

    private getDayHourMinuteFromTimeStamp(timestamp: number) {
        let date: Date = new Date(timestamp);
        let dateString: String = date.getDate().toString();
        dateString = dateString + ", " + date.getHours().toString() + ":" + date.getMinutes().toString();
        return dateString;
    }

    private getDayMonthHourMinuteFromTimeStamp(timestamp: number) {
        let date: Date = new Date(timestamp);
        let dateString: String = date.getDate().toString();
        dateString = dateString + ", " + date.getMonth() + ',  ' + date.getHours().toString() + ":" + date.getMinutes().toString();
        return dateString;
    }

    private parseStatesArray(): any {
        let data: number[] = [];
        let label: String;
        let lineChartLabels: any[] = [];
        let lineChartLabelsCorrectSyntax: any[] = [];

        if (this.valuesForPlotArrived) {
            let currentMeasure = this.hub.statesArray.pop();
            if (this.valuesForPlot.lineChartData[0].data.length < 8) {
                data = this.valuesForPlot.lineChartData[0].data;
                data.push(currentMeasure.power);

                lineChartLabelsCorrectSyntax = this.valuesForPlot.lineChartLabels;
                lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(currentMeasure.timestamp));
            }
            else {
                this.valuesForPlot.lineChartData[0].data.shift();
                data = this.valuesForPlot.lineChartData[0].data;
                data.push(currentMeasure.power);

                this.valuesForPlot.lineChartLabels.shift();
                lineChartLabelsCorrectSyntax = this.valuesForPlot.lineChartLabels;
                //lineChartLabelsCorrectSyntax = this.valuesForPlot.lineChartLabels.slice(1, this.valuesForPlot.lineChartLabels.length);
                lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(currentMeasure.timestamp));
            }

            label = "Live data consumption";

        }
        else {
            data = [];
            lineChartLabels = [];

            if (this.hub.statesArray.length > 10) {//s'il y a trop de points, on en prend que 10
                let nbTot: number = this.hub.statesArray.length;
                let ratio: number = Math.floor(nbTot / 10);
                let statesArrayToKeep: any[] = [];
                for (let i = 0; i < 10; i++) {
                    statesArrayToKeep.push(this.hub.statesArray[i * ratio]);
                }
                this.hub.statesArray = statesArrayToKeep;

            }


            for (let state of this.hub.statesArray) {
                data.push(state.power);
                lineChartLabels.push(state.timestamp);
            }

            let truc = data.shift();
            data.unshift(truc);
            let tmp = lineChartLabels.shift()
            lineChartLabels.unshift(tmp)

            for (let x of lineChartLabels) {
                if (this.plotTime == 1) {
                    lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(x));
                    label = "Live consumption";
                }
                else if (this.plotTime == 2) {
                    lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(x));
                    label = "Consumption of the last hour";
                }
                else if (this.plotTime == 3) {
                    lineChartLabelsCorrectSyntax.push(this.getDayHourMinuteFromTimeStamp(x));
                    label = "Consumption of the last day";
                }
                else if (this.plotTime == 4) {
                    lineChartLabelsCorrectSyntax.push(this.getDayHourMinuteFromTimeStamp(x));
                    label = "Consumption of the last week";
                }
            }
            let tmp2 = lineChartLabelsCorrectSyntax.shift();
            lineChartLabelsCorrectSyntax.unshift(tmp2);

        }



        let lineChartData: any[] = [{ data: data, label: label }];

        let value: any = {
            lineChartData: lineChartData,
            lineChartLabels: lineChartLabelsCorrectSyntax
        }
        return value
    }


    private staticPlot(timeBefore: number) {
        this.ngOnDestroy();
        this.valuesForPlotArrived = false;
        this.hub.toTimestamp = this.getTimestamp();
        this.hub.fromTimestamp = this.hub.toTimestamp - timeBefore;
        this.hubService.getHubStates(this.hub.id, this.hub.fromTimestamp, this.hub.toTimestamp)
            .subscribe(states => {
                this.hub.statesArray = states;
                /*let valuesForPlotTmp = this.parseStatesArray();
                this.valuesForPlot= [];
                this.valuesForPlot = valuesForPlotTmp;
                this.plot.chart.chart.config.data.labels = this.valuesForPlot.lineChartLabels;*/
                this.valuesForPlot = this.parseStatesArray();
                this.plot.chart.chart.config.data.labels = this.valuesForPlot.lineChartLabels;
            });

    }

    private historyPlot() {
        this.hubService.getHubStates(this.hub.id, this.times[0], this.times[1])
            .subscribe(states => {
                this.stateArrayHistory = states;
                this.valuesForPlotHistory = this.parseStatesArrayHistory();
                this.plot.chart.chart.config.data.labels = this.valuesForPlot.lineChartLabels;

            });
    }

    private switchPlot(event) {
        let x = new Number(this.plotTime);
        switch (x.valueOf()) {
            case 1: { //en live                
                this.refreshData();
                break;
            }
            case 2: {   //depuis derniere heure
                this.staticPlot(1000 * 3600);
                break;
            }
            case 3: {   //depuis dernier jour
                this.staticPlot(1000 * 3600 * 24);
                break;
            }
            case 4: {   // depuis derniere semaine
                this.staticPlot(1000 * 3600 * 24 * 7);
                break;
            }
        }
    }

    /*private onChange(event) {

        if (event.checked.toString() === "true") {
            this.socketService.postSocketState(this.hub.id, true).subscribe();
            this.hub.setState(true);
        }
        else {
            this.socketService.postSocketState(this.hub.id, false).subscribe();
            this.hub.setState(false);
        }
    }*/

    @ViewChild(PlotComponent) plot: PlotComponent;






    show_live() {
        let x = document.getElementById("panel-live");
        let y = document.getElementById("panel-history");

        if (x.className.indexOf("w3-hide") !== -1 && y.className.indexOf("w3-hide") === -1) {
            x.className = x.className.replace(" w3-hide", "");
            y.className += " w3-hide";
        }
    }

    show_history() {
        let x = document.getElementById("panel-live");
        let y = document.getElementById("panel-history");

        if (y.className.indexOf("w3-hide") !== -1 && x.className.indexOf("w3-hide") === -1) {
            y.className = y.className.replace(" w3-hide", "");
            x.className += " w3-hide";
        }
    }

    getHistory() {
        if (this.tstart.length !== 0 && this.tend.length !== 0 && this.dstart.length !== 0 && this.dend.length !== 0) {
            let xd = this.tstart.pop().valueOf();
            this.tstart.push(xd);
            let ad = this.dstart.pop();
            this.dstart.push(ad);
            let dtstart = Date.parse(ad.replace("00:00", xd).valueOf());

            let xf = this.tend.pop().valueOf();
            this.tend.push(xf);
            let af = this.dend.pop();
            this.dend.push(af);
            let dtend = Date.parse(af.replace("00:00", xf).valueOf());

            if (dtstart.valueOf() > dtend.valueOf()) {
                const tmp = dtstart;
                dtstart = dtend;
                dtend = tmp;
            }
            this.times = [dtstart, dtend];
            this.historyPlot();
            document.getElementById('id01').style.display = 'block';
        } else if (this.tstart.length === 0 && this.tend.length === 0 && this.dstart.length !== 0 && this.dend.length !== 0) {
            let ad = this.dstart.pop();
            this.dstart.push(ad);
            let dtstart = Date.parse(ad.valueOf());

            let af = this.dend.pop();
            this.dend.push(af);
            let dtend = Date.parse(af.valueOf());

            if (dtstart.valueOf() > dtend.valueOf()) {
                const tmp = dtstart;
                dtstart = dtend;
                dtend = tmp;
            }
            this.times = [dtstart, dtend];
            this.historyPlot();
            document.getElementById('id01').style.display = 'block';
        }

    }

    updateDate(n: number, newValue) {
        let x = new String(newValue);
        if (n === 1) {
            this.dstart.push(x.valueOf());
        } else if (n === 2) {
            this.dend.push(x.valueOf());
        } else if (n === 3) {
            this.tstart.push(x.valueOf());
        } else {
            this.tend.push(x.valueOf());
        }
    }

    private parseStatesArrayHistory(): any {
        let data: number[] = [];
        let label: String;
        let lineChartLabels: any[] = [];
        let lineChartLabelsCorrectSyntax: any[] = [];

        if (this.stateArrayHistory.length > 10) {//s'il y a trop de points, on en prend que 10
            let nbTot: number = this.stateArrayHistory.length;
            let ratio: number = Math.floor(nbTot / 10);
            let statesArrayToKeep: any[] = [];
            for (let i = 0; i < 10; i++) {
                statesArrayToKeep.push(this.stateArrayHistory[i * ratio]);
            }
            this.stateArrayHistory = statesArrayToKeep;
        }


        for (let state of this.stateArrayHistory) {
            data.push(state.power);
            lineChartLabels.push(state.timestamp);
        }

        let truc = data.shift();
        data.unshift(truc);
        let tmp = lineChartLabels.shift()
        lineChartLabels.unshift(tmp)

        for (let x of lineChartLabels) {

            lineChartLabelsCorrectSyntax.push(this.getDayMonthHourMinuteFromTimeStamp(x));
        }
        label = "Consumption from " + lineChartLabelsCorrectSyntax[0] + " to "
            + lineChartLabelsCorrectSyntax[lineChartLabelsCorrectSyntax.length - 1];
        let tmp2 = lineChartLabelsCorrectSyntax.shift();
        lineChartLabelsCorrectSyntax.unshift(tmp2);



        let lineChartData: any[] = [{ data: data, label: label }];

        let value: any = {
            lineChartData: lineChartData,
            lineChartLabels: lineChartLabelsCorrectSyntax
        }
        return value
    }

    
}