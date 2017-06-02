import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { AuthGuard } from './_guards/index';
import { PlotComponent} from './Plot/plot.component';
import { HubComponent, LightComponent, SocketComponent } from './devices/index' ;

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'hub', component: HubComponent, canActivate: [AuthGuard]},
    { path: 'light/:id', component: LightComponent, canActivate: [AuthGuard]},
    { path: 'hub/:id', component: HubComponent, canActivate: [AuthGuard]},
    { path: 'socket/:id', component: SocketComponent, canActivate: [AuthGuard]},
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
