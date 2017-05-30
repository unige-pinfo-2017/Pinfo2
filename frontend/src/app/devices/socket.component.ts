import { Component, OnInit, OnDestroy } from '@angular/core' ;
import { ActivatedRoute, Params, Router } from '@angular/router' ;
import { Socket } from "../_models/socket";
import { SocketService } from "../_services/socket.service";
import 'rxjs/add/operator/switchMap';
import {Observable } from 'rxjs/Rx';
import { AnonymousSubscription} from 'rxjs/Subscription';


@Component({
    selector: 'socket',
    templateUrl: 'socket.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [SocketService]
})

export class SocketComponent implements OnInit, OnDestroy{
    private mySocket: Socket;
    private valuesForPlot: any;
    private postsSubscription: AnonymousSubscription;
    private timerSubscription: AnonymousSubscription;

    constructor(private socketService: SocketService, private route: ActivatedRoute) { }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.mySocket = new Socket(+params['id']); 
            this.mySocket.toTimestamp = this.getTimestamp();
            this.mySocket.fromTimestamp = this.mySocket.toTimestamp-3600*1000;
        });
        this.refreshData();
    }

    public ngOnDestroy(){
        if (this.postsSubscription) {
        this.postsSubscription.unsubscribe();
        }
        if (this.timerSubscription) {
            this.timerSubscription.unsubscribe();
        }
    }

    private subscribeToData( ): void{
        this.timerSubscription = Observable.timer(3000).first().subscribe(()=> {
                                                                                this.mySocket.toTimestamp = this.getTimestamp();
                                                                                this.mySocket.fromTimestamp = this.mySocket.toTimestamp-1000*3600;
                                                                                this.refreshData()});
    }

    private refreshData(): void{
        this.postsSubscription = this.socketService.getSocketStates(this.mySocket.id, this.mySocket.fromTimestamp, this.mySocket.toTimestamp)
            .subscribe(states => {this.mySocket.statesArray = states;
                                this.valuesForPlot= this.parseStatesArray();
                                this.subscribeToData();
            });   
    }

    getTimestamp(): number{
        return +new Date();
    }

    parseStatesArray(): any{
        let data : number[] = [];
        let label : String = 'Consumption during the last hour'
        let lineChartLabels : any[] = [];

        for (let state of this.mySocket.statesArray){
            data.push(state.power);
            lineChartLabels.push(state.timestamp.toString())
        }
        let lineChartData: any[]  = [{data: data,label:label}];

        let value :any = {
            lineChartData:lineChartData,
            lineChartLabels:lineChartLabels
        }
        return value
    }


    private onChange(event){
        console.log(event);
        console.log(event.checked.toString());
        if (event.checked.toString() === "true"){
            this.socketService.postSocketState(this.mySocket.id, true).subscribe(()=>console.log("après post true"));
            this.mySocket.setState(true);
        }
        else{
            console.log("dans if false")
            this.socketService.postSocketState(this.mySocket.id, false).subscribe(()=>console.log("après post false"));
            this.mySocket.setState(false);
        }
    }

}