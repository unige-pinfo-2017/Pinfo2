import { Component } from '@angular/core' ;
import { Hub } from '../_models/hub' ;
import { Device } from '../_models/device' ;
import { PlotComponent } from '../Plot/plot.component';

@Component({
    selector: 'hub',
    templateUrl: 'hub.component.html',
    styleUrls: ['devices.component.css']
})

export class HubComponent {
    hub: Hub;
}