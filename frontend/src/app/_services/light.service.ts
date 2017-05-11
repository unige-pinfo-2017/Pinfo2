import { Injectable } from '@angular/core' ;
import { Light } from "../_models/light";
import { DevicesComponent } from "../devices/devices.component";

@Injectable()
export class LightService {
    private devComp: DevicesComponent;

    getSelectedLight(): Promise<Light> {
        return Promise.resolve(this.devComp.selectedLight);
    }
}