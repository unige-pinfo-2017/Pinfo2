import { Device } from './device';
export class Hub extends Device {
    name = 'hub';
    
    constructor(idd: string){
        super(idd);
    }
}
