import { Component, OnInit } from '@angular/core';
import { User } from '../_models/index';
import { UserService } from '../_services/index';
import { Device } from "./device/device";
import { DEVICES } from "./device/mock-devices";
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
    workstats: Workstation[];
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
    selectedTitle() : String {
        return '';
    }

    ngOnInit() {
        this.devices = DEVICES
    }
}
