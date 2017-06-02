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
    private title = 'Home';
    private currentUser: User;
    private status: string;
    private users: any[];

    islogged(status: boolean): string {
        if (status) return "Online";
        else return "Offline";
    }
    
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.currentUser.status = true;
        this.status = "Connected";
        if (this.currentUser.role === "admin") {
            this.userService.getAll().subscribe(users => {
                this.users = users;
                this.users.forEach(user => user.statusString = this.islogged(user.status));
            });
        }        
    }

    ngOnInit() {
    }

    addUser() {
        document.getElementById('id01').style.display = 'block';
    }
}
