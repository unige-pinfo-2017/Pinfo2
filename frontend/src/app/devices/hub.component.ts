import { Component } from '@angular/core' ;
import { Hub } from '../_models/hub' ;
import { Device } from '../_models/device' ;
import { PlotComponent } from '../Plot/plot.component';
import { DeviceService } from "../_services/devices.service";
import { ActivatedRoute } from '@angular/router' ;

@Component({
    selector: 'hub',
    templateUrl: 'hub.component.html',
    styleUrls: ['devices.component.css'],
    providers: [DeviceService]
})

export class HubComponent {
    private hub: Hub;
    private hubId: number;

    setHub(id: number): void {
      this.deviceService.getValues().subscribe(devices => {
      devices.forEach(element => {
            if (element.name === 'hub' && element.id === id) {
                this.hub = element;
            }
      });
    });
    }

    ngOnInit(): void {
        this.setHub(this.hubId);
    }
    constructor(private deviceService: DeviceService, private route: ActivatedRoute) {
        this.route.params.subscribe(
        params => this.hubId = params['id']
    );
  }
}