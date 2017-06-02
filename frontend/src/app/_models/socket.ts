import { Device } from './device';
export class Socket extends Device {
    name = 'Socket';

    constructor(idd: string){
        super(idd);
    }
}