import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { AuthGuard } from './_guards/index';
import { PlotComponent} from './Plot/plot.component';
import { DevicesComponent } from './devices/devices.component' ;
import { HubComponent, LightComponent } from './devices/index' ;

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
<<<<<<< HEAD
    { path: 'devices', component: DevicesComponent },
    { path: 'testPlot', component: PlotComponent },
    { path: 'hub', component: HubComponent},
    { path: 'light/:id', component: LightComponent},

=======

    {path: 'testPlot', component: PlotComponent},
    
>>>>>>> ecran_accueil
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
