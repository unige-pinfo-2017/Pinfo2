import { Component, OnInit, OnDestroy } from '@angular/core' ;
import { ActivatedRoute, Params, Router } from '@angular/router' ;
import { Light } from '../_models/light';
//import { Device } from '../_models/device';
import { LightService } from '../_services/light.service';
import 'rxjs/add/operator/switchMap';
import {Observable } from 'rxjs/Rx';
import { AnonymousSubscription} from 'rxjs/Subscription';


@Component({
    selector: 'light',
    templateUrl: 'light.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [LightService]
})

export class LightComponent implements OnInit, OnDestroy {
    private myLight: Light;
    /*private powerConsumption: number;
    private lightId: number;
    private isOn: boolean;
    */
    private postsSubscription: AnonymousSubscription;
    private timerSubscription: AnonymousSubscription;

    constructor(private lightService: LightService, private route: ActivatedRoute) { };
    
     ngOnInit(): void {
         this.route.params.subscribe(params => {
            this.myLight = new Light(+params['id']); 
        });
         this.refreshData();
        /*this.deviceService.getLightLastState(this.myLight.id).subscribe(light => {this.myLight.state = light.onOffStatus;
                                                                                    this.myLight.consommation = light.power;});*/                                                               
    }

    public ngOnDestroy(): void{
        if (this.postsSubscription) {
            this.postsSubscription.unsubscribe();
        }
        if (this.timerSubscription) {
            this.timerSubscription.unsubscribe();
        }
    }

    private subscribeToData(): void{
        this.timerSubscription = Observable.timer(3000).first().subscribe(()=> this.refreshData());
    }

    private refreshData(): void{
        this.postsSubscription = this.lightService.getLightLastState(this.myLight.id)
            .subscribe(light => {this.myLight.consommation = light.power;
                                this.myLight.state = light.onOffStatus;
                                this.subscribeToData();
            });   
    }

    private onChange(event){
        console.log(event);
        console.log(event.checked.toString());
        if (event.checked.toString() === "true"){
            console.log("dans if true")
            this.lightService.postLightState(this.myLight.id, true).subscribe(()=>console.log("après post true"));
            this.myLight.setState(true);
        }
        else{
            console.log("dans if false")
            this.lightService.postLightState(this.myLight.id, false).subscribe(()=>console.log("après post false"));
            this.myLight.setState(false);
        }
    }

    /*this.route.params.switchMap(
                (params: Params) => this.deviceService.getDevice(+params['id'], "light"))
            .subscribe(light => this.myLight = light);*/

}