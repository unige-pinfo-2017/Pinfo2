import { Component, OnInit } from '@angular/core';
import { Device } from '../_models/device';
import { Light } from '../_models/light' ;
import { Hub } from '../_models/hub' ;
import { Socket } from '../_models/socket' ;
import { DeviceService } from '../_services' ;
import { HubComponent } from './hub.component' ;

@Component({
    selector: 'devices',
    templateUrl: 'devices.component.html',
    styleUrls: ['devices.component.css'],
    providers: [DeviceService]
})

export class DevicesComponent implements OnInit {
    errorMessage: any;
    devices: Device[];
    device: DevicesComponent;
    setDevices(): void {
      this.deviceService.getValues().subscribe(devices => { this.devices = devices;
                                console.log("Depuis devices.component.ts setDevices");
                                console.log(this.devices[0].id); },
            error => this.errorMessage = <any> error);
    }

    ngOnInit(): void {
        this.setDevices();
    }
    constructor(private deviceService: DeviceService) {
    }
}