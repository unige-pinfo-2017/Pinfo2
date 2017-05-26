import { Component } from '@angular/core' ;
import { Location } from "@angular/common";
import { Hub } from '../_models/hub' ;
import { Device } from '../_models/device' ;
import { PlotComponent } from '../Plot/plot.component';
import { DeviceService } from "../_services/devices.service";
import { ActivatedRoute, Params } from '@angular/router' ;
import { Socket } from "../_models/socket";

@Component({
    selector: 'hub',
    templateUrl: 'hub.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [DeviceService]
})

export class HubComponent {
    private hub: Hub;
    private hubId: number;
    private socketsId: number[];
    sockets = new Array<Socket>();

    setHub(id: number): void {
      this.deviceService.getValues().subscribe(devices => {
      devices.forEach(element => {
            if (element.name === 'hub' && element.id === id) {
                this.hub = element;
                this.socketsId = element.link;
            }
      });
      devices.forEach(element => {
            if(element.name === "socket") {
                this.sockets.push(element);
            }
      });
    });
        
    }

    ngOnInit(): void {
        //this.setHub(this.hubId);
        this.route.params
      .switchMap((params: Params) => this.deviceService.getDevice(+params['id']))
      .subscribe(hub => {this.hub = hub});
    }
    constructor(private deviceService: DeviceService, private route: ActivatedRoute, private loc: Location) { }
        
  

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