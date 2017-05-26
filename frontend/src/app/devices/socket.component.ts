import { Component } from '@angular/core' ;
import { ActivatedRoute, Params } from '@angular/router' ;
import { Socket } from "../_models/socket";
import { DeviceService } from "../_services/devices.service";
import { SidenavComponent } from "../sidenav/sidenav.component";

@Component({
    selector: 'socket',
    templateUrl: 'socket.component.html',
    styleUrls: ['../sidenav/sidenav.component.css'],
    providers: [DeviceService]
})

export class SocketComponent {
    private socket: Socket;
    private socketId: number;

    ngOnInit(): void {
        this.route.params
      .switchMap((params: Params) => this.deviceService.getDevice(+params['id']))
      .subscribe(device => this.socket = device);
    }

    constructor(private deviceService: DeviceService, private route: ActivatedRoute) { }
}