import { Component } from '@angular/core';
import { SidenavComponent } from './sidenav/sidenav.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'Home';

  isLogged(): boolean{
    if (localStorage.getItem("currentUser")){
      return true;
    } 
    else{
        return false;
    }
  }

}
