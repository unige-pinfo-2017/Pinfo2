import { Device } from './device';
export class Hub extends Device {
    name = 'hub';
    socketId: number[];

    constructor(idd: number){
        super(idd);
    }

    setSockets(socks: number[]) {
        this.socketId = socks;
    }
}
