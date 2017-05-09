import { Device } from './device';
import { DeviceType } from './deviceType';
export class Socket extends Device {
    typeMachine = DeviceType.Socket;
    name = 'Socket';

    constructor(idd: number){
        super(idd);
    }
}