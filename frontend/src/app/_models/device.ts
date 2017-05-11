import { Workstation } from './workstation';
export abstract class Device {
    id: number;
    name: string;
    state: boolean;
    consommation: number;

    constructor(idd: number){
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
}