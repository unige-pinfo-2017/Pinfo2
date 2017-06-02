import { Workstation } from './workstation';
export abstract class Device {
    id: string;
    name: string;
    state: boolean;
    link: string[];
    consommation: number;
    statesArray: any[];

    constructor(idd: string){
        this.id = idd;
    }

    getState(): boolean {
        return this.state;
    }

    setState(stat: boolean) {
        this.state = stat;
    }

    changeState() {
        if (this.state === true) {
            this.setState(false);
        } else {
            this.setState(true);
        }
    }
    fromTimestamp : number;
    toTimestamp: number
}