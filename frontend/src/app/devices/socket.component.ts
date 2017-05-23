import { Component } from '@angular/core' ;
import { ActivatedRoute } from '@angular/router' ;
import { Socket } from "../_models/socket";
import { DeviceService } from "../_services/devices.service";
import { SidebarComponent } from "../sidebar.component";

@Component({
    selector: 'socket',
    templateUrl: 'socket.component.html',
    styleUrls: ['devices.component.css'],
    providers: [DeviceService]
})

export class SocketComponent {
    private socket: Socket;
    private socketId: number;

    setSocket(id: number): void {
      this.deviceService.getValues().subscribe(devices => {
      devices.forEach(element => {
            if (element.name === 'socket' && element.id === id) {
                this.socket = element;
            }
      });
    });
    }

    ngOnInit(): void {
        this.setSocket(this.socketId);
    }

    constructor(private deviceService: DeviceService, private route: ActivatedRoute) {
        this.route.params.subscribe(
        params => this.socketId = params['id']
    );
  }
}