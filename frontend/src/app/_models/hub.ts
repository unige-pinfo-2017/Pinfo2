import { Device } from './device';
import { DeviceType } from './deviceType';
export class Hub extends Device {
    typeMachine = DeviceType.Hub;
    name = 'Hub';
    consommation: number;
    constructor(idd: number){
        super(idd);
    }
}
