import { User } from './../_models/user';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/index';
import { Light } from '../_models/light';
import { Workstation } from '../_models/workstation';
import { PlotComponent } from '../Plot/plot.component';
import { SidenavComponent } from "../sidenav/sidenav.component";

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit{
    title = 'Home';
    currentUser: User;
    status: string;
    users = new Array<User>();
    
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        console.log(this.currentUser.role);
        this.currentUser.status = true;
        this.status = "Connected";
        if (this.currentUser.role === "admin") {
            this.userService.getAll().subscribe(users => console.log(users.length + " " + users));
        }        
    }

    ngOnInit() {
    }
}
