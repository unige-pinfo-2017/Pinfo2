import { Component, OnInit, OnDestroy, ViewChild, ComponentRef } from '@angular/core' ;
import { ActivatedRoute, Params, Router } from '@angular/router' ;
import { Socket } from "../_models/socket";
import { SocketService } from "../_services/socket.service";
import 'rxjs/add/operator/switchMap';
import {Observable } from 'rxjs/Rx';
import { AnonymousSubscription} from 'rxjs/Subscription';
import { BaseChartDirective } from 'ng2-charts';
import {PlotComponent} from '../Plot/plot.component'




@Component({
    selector: 'socket',
    templateUrl: 'socket.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [SocketService]
})

export class SocketComponent implements OnInit, OnDestroy{
    private mySocket: Socket;
    private valuesForPlot: any;
    private dataSubscription: AnonymousSubscription;
    private timerSubscription: AnonymousSubscription;
    private valuesForPlotArrived : boolean = false;
    private plotTime : number = 1;


    private dstart = new Array<String>();
    private dend = new Array<String>(); 
    private tstart = new Array<String>();
    private tend = new Array<String>();
    private isLiveData: boolean

    constructor(private socketService: SocketService, private route: ActivatedRoute) { }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.mySocket = new Socket(+params['id']); 
            this.mySocket.toTimestamp = this.getTimestamp();
            this.mySocket.fromTimestamp = this.mySocket.toTimestamp-60000;
             
        });
        this.refreshData();
    }

    public ngOnDestroy(){
        if (this.dataSubscription) {
        this.dataSubscription.unsubscribe();
        }
        if (this.timerSubscription) {
            this.timerSubscription.unsubscribe();
        }
    }

    private subscribeToData( ): void{
        this.timerSubscription = Observable.timer(3000).first().subscribe(()=> {
                                                                                this.mySocket.toTimestamp = this.getTimestamp();
                                                                                this.mySocket.fromTimestamp = this.mySocket.toTimestamp-60000;
                                                                                this.refreshData()});
    }

    private refreshData(): void{
        this.dataSubscription = this.socketService.getSocketStates(this.mySocket.id, this.mySocket.fromTimestamp, this.mySocket.toTimestamp)
            .subscribe(states => {
                                this.mySocket.state = states[states.length -1].isOn;
                                this.mySocket.consommation = states[states.length -1].power
                                if (this.plotTime==1){
                                    this.mySocket.statesArray = states;
                                    /*let valuesForPlotTmp = this.parseStatesArray();
                                    this.valuesForPlot= [];
                                    this.valuesForPlot = valuesForPlotTmp;
                                    this.valuesForPlotArrived = true;*/
                                    this.valuesForPlot= this.parseStatesArray();
                                    this.valuesForPlotArrived = true;
                                }
                                
                                this.subscribeToData();
            });   
    }

    

    

    private getTimestamp(): number{
        return +new Date();
    }
    private getHourMinutesFromTimestamp(timestamp: number){
        let date : Date = new Date(timestamp);
        let dateString : String = date.getHours().toString();
        dateString = dateString + ":" + date.getMinutes().toString();
        return dateString;
    }

    private getHourMinutesSecondsFromTimestamp(timestamp: number){
        let date : Date = new Date(timestamp);
        let dateString : String = date.getHours().toString();
        dateString = dateString + ":" + date.getMinutes().toString() + ":" + date.getSeconds().toString();
        return dateString;
    }

    private getDayHourMinuteFromTimeStamp(timestamp:  number){
        let date : Date = new Date(timestamp);
        let dateString : String = date.getDate().toString();
        dateString = dateString + ", " + date.getHours().toString()+ ":" + date.getMinutes().toString();
        return dateString;
    }


    private parseStatesArray(): any{
        let data : number[] = [];
        let label : String;
        let lineChartLabels : any[] = [];
        let lineChartLabelsCorrectSyntax : any[] = [];
        
        if (this.valuesForPlotArrived){
            let currentMeasure = this.mySocket.statesArray.pop();
            if (this.valuesForPlot.lineChartData[0].data.length <8){
                data = this.valuesForPlot.lineChartData[0].data;
                data.push(currentMeasure.power);

                lineChartLabelsCorrectSyntax = this.valuesForPlot.lineChartLabels;
                lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(currentMeasure.timestamp));
            }
            else{
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
        else{
            data = [];
            lineChartLabels = [];

            if (this.mySocket.statesArray.length > 10){//s'il y a trop de points, on en prend que 10
                let nbTot : number = this.mySocket.statesArray.length;
                let ratio : number = Math.floor(nbTot/10);
                let statesArrayToKeep : any[] = [];
                for (let i = 0; i<10;i++ ){
                    statesArrayToKeep.push(this.mySocket.statesArray[i*ratio]);
                }
                this.mySocket.statesArray = statesArrayToKeep;

            }
            
            
            for (let state of this.mySocket.statesArray){
                data.push(state.power);
                lineChartLabels.push(state.timestamp);
            }
            
            let truc = data.shift();
            data.unshift(truc);
            let tmp = lineChartLabels.shift()
            lineChartLabels.unshift(tmp)

            for (let x of lineChartLabels){
                if (this.plotTime == 1 ){
                    lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(x));
                    label = "Live consumption";
                }
                else if (this.plotTime == 2){
                    lineChartLabelsCorrectSyntax.push(this.getHourMinutesSecondsFromTimestamp(x));
                    label = "Consumption of the last hour";
                }
                else if (this.plotTime == 3){
                    lineChartLabelsCorrectSyntax.push(this.getDayHourMinuteFromTimeStamp(x));
                    label = "Consumption of the last day";
                }
                else if (this.plotTime == 4){
                    lineChartLabelsCorrectSyntax.push(this.getDayHourMinuteFromTimeStamp(x));
                    label = "Consumption of the last week";
                }
            }
            let tmp2 = lineChartLabelsCorrectSyntax.shift();
            lineChartLabelsCorrectSyntax.unshift(tmp2);
            
        }
            
        

        let lineChartData: any[]  = [{data: data,label:label}];
        
        let value :any = {
            lineChartData:lineChartData,
            lineChartLabels:lineChartLabelsCorrectSyntax
        }
        return value
    }


    private staticPlot(timeBefore :  number){
        this.ngOnDestroy();
        this.valuesForPlotArrived = false;
        this.mySocket.toTimestamp = this.getTimestamp();
        this.mySocket.fromTimestamp = this.mySocket.toTimestamp - timeBefore;
        this.socketService.getSocketStates(this.mySocket.id, this.mySocket.fromTimestamp, this.mySocket.toTimestamp)
            .subscribe(states => {this.mySocket.statesArray = states;
                                /*let valuesForPlotTmp = this.parseStatesArray();
                                this.valuesForPlot= [];
                                this.valuesForPlot = valuesForPlotTmp;
                                this.plot.chart.chart.config.data.labels = this.valuesForPlot.lineChartLabels;*/
                                this.valuesForPlot= this.parseStatesArray();
                                this.plot.chart.chart.config.data.labels = this.valuesForPlot.lineChartLabels;
            });
  
    }

    private historyPlot(){
        this.socketService.getSocketStates(this.mySocket.id, this.mySocket.fromTimestamp, this.mySocket.toTimestamp)
            .subscribe(states => {this.mySocket.statesArray = states;
                                this.mySocket.state = states[states.length-1].isOn
                                this.valuesForPlot= this.parseStatesArray();
                                this.valuesForPlotArrived = false;
            });  
    }

    private switchPlot(event) {
        let x = new Number(this.plotTime);
        switch(x.valueOf()){
            case 1:{ //en live                
                this.refreshData();
                break;

            }
            case 2: {   //depuis derniere heure
                this.staticPlot(1000*3600);
                break;

            }
            case 3: {   //depuis dernier jour
                this.staticPlot(1000*3600*24);
                break;

            }
            case 4: {   // depuis derniere semaine
                this.staticPlot(1000*3600*24*7);
                break;

            }
            

        }
        
    }

    private onChange(event){
        
        if (event.checked.toString() === "true"){
            this.socketService.postSocketState(this.mySocket.id, true).subscribe();
            this.mySocket.setState(true);
        }
        else{
            this.socketService.postSocketState(this.mySocket.id, false).subscribe();
            this.mySocket.setState(false);
        }
    }

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

    getHistory(): number[] {
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

        let retour = [dtstart, dtend];
        console.log(retour[0] + " " + retour[1]);

        return [dtstart, dtend];
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

}