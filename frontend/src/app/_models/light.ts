import { Device } from './device';
import { DeviceType } from './deviceType';
export class Light extends Device {
    typeMachine = DeviceType.Ampoule;
    name = 'Light';

    constructor(idd: number){
        super(idd);
    }
}
