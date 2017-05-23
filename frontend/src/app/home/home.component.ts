﻿import { Component, OnInit } from '@angular/core';
import { User } from '../_models/index';
import { UserService } from '../_services/index';
import { Light } from '../_models/light';
import { Workstation } from '../_models/workstation';
import { DevicesComponent } from '../devices/devices.component';
import { PlotComponent } from '../Plot/plot.component';
import { SidebarComponent } from "../sidebar.component";

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit{
    title = 'Home';
    currentUser: User;
    selectedWs: Workstation;
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
    }
}
