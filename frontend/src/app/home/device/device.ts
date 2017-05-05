import { Workstation } from '../workstation';
import { DeviceType } from "./deviceType";
export class Device {
    id: number;
    typeMachine: DeviceType;
    assignedWs: Workstation;

    constructor(idd: number, ttype: DeviceType){
        this.id = idd;
        this.typeMachine = ttype;
    }
}