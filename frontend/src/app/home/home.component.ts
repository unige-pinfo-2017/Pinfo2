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
      {id: 1, workspace: 10, name:'Fred',},
      {id: 2, workspace: 3,  name:'Max'},
      {id: 5, workspace: 27, name:'Thomas'},
      {id: 3, workspace: 44, name:'Nico'},
      {id: 4, workspace: 67, name:'Vinc'},
      {id: 6, workspace: 99, name:'Beni'},
      {id: 7, workspace: 66, name:'Rodé'},
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
  id : number;
  name : string;
  workspace : number;
}

