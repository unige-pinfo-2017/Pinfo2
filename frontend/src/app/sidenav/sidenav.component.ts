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
      this.deviceService.getValues().subscribe(devices => {
      devices.forEach(element => {
            if (element.name === 'hub') {
                this.hubs.push(element);
            } else if (element.name === 'light') {
                this.lights.push(element);
            } else if (element.name === 'socket') {
                this.sockets.push(element);
            }
      });
    },
            error => this.errorMessage = <any> error);

    }

    ngOnInit(): void {
        this.setDevices();
    }
    constructor(private deviceService: DeviceService, private _router: Router) { }

    show(elem: string) {
        let x = document.getElementById(elem);
        if (x.className.indexOf("w3-show") === -1) {
            x.className += ' w3-show';
            if (elem === "li") {
                this.hide_hs();
            } else if (elem === "hu") {
                this.hide_s();
            }
        } else {
            x.className = x.className.replace(" w3-show", '');
            if (elem === "li") {
                this.hide_hs();
            } else if (elem === "hu") {
                this.hide_s();
            }
        }
    }

    hide_hs() {
        let x2 = document.getElementById('idh');
        let x3 = document.getElementById('ids');
        if (x2.className.indexOf("w3-hide") === -1) {
            x2.className += ' w3-hide';
            x3.className += ' w3-hide';
        } else {
            x2.className = x2.className.replace(" w3-hide", '');
            x3.className = x3.className.replace(" w3-hide", '');
        }
    }

    hide_s() {
        let x3 = document.getElementById('ids');
        if (x3.className.indexOf("w3-hide") === -1) {
            x3.className += ' w3-hide';
        } else {
            x3.className = x3.className.replace(" w3-hide", '');
        }
    }

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