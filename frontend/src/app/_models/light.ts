import { Device } from './device';

export class Light extends Device {
    name = 'light';
    constructor(idd: string){
        super(idd);
    }
}
