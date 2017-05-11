import { Device } from './device';
export class Socket extends Device {
    name = 'Socket';

    constructor(idd: number){
        super(idd);
    }
}