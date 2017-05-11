import { Device, DeviceType } from './index';

export class Light extends Device {
    typeMachine = DeviceType.Ampoule;
    name = 'Light';

    constructor(idd: number){
        super(idd);
    }
}
