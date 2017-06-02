import { Component, OnInit } from '@angular/core';
import { Device } from '../_models/device';
import { Light } from '../_models/light' ;
import { Hub } from '../_models/hub' ;
import { Socket } from '../_models/socket' ;
import { DeviceService } from '../_services' ;
import { HubComponent } from '../devices/hub.component' ;
import { LightComponent } from '../devices/light.component';
import {Routes, Router} from '@angular/router';

@Component({
    selector: 'sidenav',
    templateUrl: 'sidenav.component.html',
    styleUrls: ['sidenav.component.css'],
    providers: [DeviceService]
})

export class SidenavComponent {
    errorMessage: any;
    hubs = new Array<Hub>();
    sockets = new Array<Socket>();
    lights =  new Array<Light>();
    selectedLight: Light;
    selectedHub: Hub;
    selectedSocket: Socket;
    
    setDevices(): void {
      this.deviceService.getAllDevices().subscribe(deviceIds => {
          deviceIds.lightIds.forEach(element => {
              this.lights.push(new Light(element))
          });
          deviceIds.socketIds.forEach(element => {
              this.sockets.push(new Socket(element))
          });
          deviceIds.hubIds.forEach(element => {
              this.hubs.push(new Hub(element))
          });
    },
        error => this.errorMessage = <any> error);
    }

    ngOnInit(): void {
        this.setDevices();
    }

    constructor(private deviceService: DeviceService, private _router: Router) { }

    selectLight(light: Light) {
        this.selectedLight = light;
        this._router.navigate(["/light", light.id]);
    }

    selectHub(hub: Hub) {
        this.selectedHub = hub;
        this._router.navigate(["/hub", hub.id]);
    }

    selectSocket(socket: Socket) {
        this.selectedSocket = socket;
        this._router.navigate(["/socket", socket.id]);
    }
}