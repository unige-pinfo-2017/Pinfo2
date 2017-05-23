import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChartsModule} from 'ng2-charts';

import 'hammerjs';

// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';

import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import { AlertService, AuthenticationService, UserService, DataForPlotService, DeviceService } from './_services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { PlotComponent } from './Plot/plot.component';
import { DevicesComponent, HubComponent, LightComponent, SocketComponent } from './devices/index' ;
import {MdButtonModule, MdCheckboxModule} from '@angular/material';
import { SidebarComponent } from "./sidebar.component";

//import { dashconConfig } from '../dashcon-config';


@NgModule({
  declarations: [
    AppComponent,
      AlertComponent,
      HomeComponent,
      LoginComponent,
      RegisterComponent,
      PlotComponent,
      DevicesComponent,
      HubComponent,
      LightComponent,
      SocketComponent,
      SidebarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    BrowserAnimationsModule,
    ChartsModule,
    MdButtonModule,
    MdCheckboxModule
  ],
  exports: [
    MdButtonModule,
    MdCheckboxModule
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,
    DeviceService,
    // providers used to create fake backend
    fakeBackendProvider,
    MockBackend,
    BaseRequestOptions
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export class PizzaPartyAppModule { }

export class MyOwnCustomMaterialModule { }
