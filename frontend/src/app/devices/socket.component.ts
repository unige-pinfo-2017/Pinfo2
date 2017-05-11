import { Component } from '@angular/core' ;
import { ActivatedRoute } from '@angular/router' ;
import { Socket } from "../_models/socket";

@Component({
    selector: 'socket',
    templateUrl: 'socket.component.html',
    styleUrls: ['devices.component.css']
})

export class SocketComponent {
    mysocket: Socket;
    socketId: number;
    constructor(private route: ActivatedRoute) {
        this.route.params.subscribe(
        params => this.socketId = params['id']
    );
    console.log(this.socketId);
  }
}