import { Component, OnInit } from '@angular/core';
import { User } from '../_models/index';
import { UserService } from '../_services/index';
import { WsComp } from './wscomp.component';
import { CComp } from './ccomp.component';
import { Device } from "./device";
import { DEVICES } from "./mock-devices";
import { Workstation } from "./workstation";

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html',
})

export class HomeComponent implements OnInit{
    title = 'Home';
    currentUser: User;
    selectedDevice: Device;
    selectedWs: Workstation;
    devices: Device[];
    wscomp: WsComp;
    ccomp: CComp;
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.ccomp = new CComp();
        this.wscomp = new WsComp();
    }
    selectedTitle() : String {
        return '';
    }

    ngOnInit() {
        this.devices = DEVICES
    }
}
