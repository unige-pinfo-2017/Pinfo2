import { Component, OnInit } from '@angular/core' ;
import { ActivatedRoute } from '@angular/router' ;
import { Light } from '../_models/light';
import { Device } from '../_models/device';
import { DeviceService } from "../_services/devices.service";
import { SidebarComponent } from "../sidebar.component";

@Component({
    selector: 'light',
    templateUrl: 'light.component.html',
    styleUrls: ['devices.component.css'],
    providers: [DeviceService]
})

export class LightComponent implements OnInit {
    private myLight: Light;
    private lightId: number;

    setLight(id: number): void {
      this.deviceService.getValues().subscribe(devices => {
      devices.forEach(element => {
            if (element.name === 'light' && element.id === id) {
                this.myLight = element;
            }
      });
    });
    }

    ngOnInit(): void {
        this.setLight(this.lightId);
    }
    constructor(private deviceService: DeviceService, private route: ActivatedRoute) {
        this.route.params.subscribe(
        params => this.lightId = params['id']
    );
  }
}