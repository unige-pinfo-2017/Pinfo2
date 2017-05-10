import { Component } from '@angular/core' ;
import { Hub } from '../_models/hub' ;
import { Device } from '../_models/device' ;

@Component({
    selector: 'hub',
    templateUrl: 'hub.component.html',
    styleUrls: ['devices.component.css']
})

export class HubComponent {
    hub: Device;
}