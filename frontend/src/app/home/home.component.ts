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
    selectedWs: Workstation;
    UTILISATEURS: Hero[] = [
      {property: "Default" ,status: "Connected", workspace: 10, name:'Fred',},
      {property: "Default" ,status: "Disconnected", workspace: 3,  name:'Max'},
      {property: "Default" ,status: "Disconnected", workspace: 27, name:'Thomas'},
      {property: "Default" ,status: "Connected", workspace: 44, name:'Nico'},
      {property: "Default" ,status: "Connected", workspace: 67, name:'Vinc'},
      {property: "Default" ,status: "Disconnected", workspace: 99, name:'Beni'},
      {property: "Default" ,status: "Disconnected", workspace: 66, name:'Rodé'},
    ];
    users = this.UTILISATEURS;
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
    }

    whichRole(){
      return localStorage.getItem('Role');
    }
}

export class Hero  {
  status : string;
  name : string;
  workspace : number;
  property : string;
}

