import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule, MdDatepickerModule, MdNativeDateModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChartsModule} from 'ng2-charts';
import { HomeComponentSysAdmin } from "./home/homeSystAdmin.component";

import 'hammerjs';

// used to create fake backend
/*import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';*/

import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import { AlertService, AuthenticationService, UserService, DataForPlotService, DeviceService } from './_services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { PlotComponent } from './Plot/plot.component';
import { HubComponent, LightComponent, SocketComponent } from './devices/index' ;
import { SidenavComponent } from "./sidenav/sidenav.component";

//import { dashconConfig } from '../dashcon-config';


@NgModule({
  declarations: [
    AppComponent,
      AlertComponent,
      HomeComponent,
      LoginComponent,
      RegisterComponent,
      PlotComponent,
      HubComponent,
      LightComponent,
      SocketComponent,
      SidenavComponent,
      HomeComponentSysAdmin
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    MaterialModule,
    MdDatepickerModule,
    MdNativeDateModule,
    BrowserAnimationsModule,
    ChartsModule
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService
    //DeviceService,
    // providers used to create fake backend
    /*fakeBackendProvider,
    MockBackend,
    BaseRequestOptions*/
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
