import { Device, DeviceType } from './index';
export class Hub extends Device {
    typeMachine = DeviceType.Hub;
    name = 'Hub';
    consommation: number;
    constructor(idd: number){
        super(idd);
    }
}
