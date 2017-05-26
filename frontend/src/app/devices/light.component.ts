import { Component, OnInit } from '@angular/core' ;
import { ActivatedRoute, Params } from '@angular/router' ;
import { Light } from '../_models/light';
import { Device } from '../_models/device';
import { DeviceService } from "../_services/devices.service";
import { SidenavComponent } from "../sidenav/sidenav.component";
import 'rxjs/add/operator/switchMap';

@Component({
    selector: 'light',
    templateUrl: 'light.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [DeviceService]
})

export class LightComponent implements OnInit {
    private myLight: Light;
    private lightId: number;


    ngOnInit(): void {
        this.route.params.switchMap((params: Params) => this.deviceService.
            getDevice(+params['id'])).subscribe(light => {this.myLight = light; 
            console.log(this.myLight.id)});
        this.route.params.switchMap((params: Params) => this.setLight(+params['id'])) ;
    }
    constructor(private deviceService: DeviceService, private route: ActivatedRoute) { };

    setLight(id: number): void {
      this.deviceService.getValues().subscribe(devices => {
      devices.forEach(element => {
            if (element.name === 'light' && element.id === id) {
                this.myLight = element;
            }
      });
    });
    }

}