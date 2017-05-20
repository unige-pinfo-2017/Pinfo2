import { Component } from '@angular/core' ;
import { Hub } from '../_models/hub' ;
import { Device } from '../_models/device' ;
import { PlotComponent } from '../Plot/plot.component';
import { DeviceService } from "../_services/devices.service";
import { ActivatedRoute } from '@angular/router' ;
import { SidebarComponent } from "../sidebar.component";

@Component({
    selector: 'hub',
    templateUrl: 'hub.component.html',
    styleUrls: ['devices.component.css'],
    providers: [DeviceService]
})

export class HubComponent {
    private hub: Hub;
    private hubId: number;
    private socketsId: number[];

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
            params => {
                this.hubId = params['id'];
                        console.log(this.hubId);
                        console.log(params['id']);
            }
        );
  }

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
}