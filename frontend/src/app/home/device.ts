import { Workstation } from './workstation';
export class Device {
    id: number;
    assignedWs: Workstation;

    constructor(idd: number){
        this.id = idd;
    }
}