import { Device, DeviceType } from './index';

export class Socket extends Device {
    typeMachine = DeviceType.Socket;
    name = 'Socket';

    constructor(idd: number){
        super(idd);
    }
}