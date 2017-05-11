import { Component } from '@angular/core' ;
import { ActivatedRoute } from '@angular/router' ;
import { Light } from '../_models/light';
import { LightService } from "../_services/light.service";

@Component({
    selector: 'light',
    templateUrl: 'light.component.html',
    styleUrls: ['devices.component.css'],
})

export class LightComponent {
    private myLight: Light;
    private lightId: number;

    constructor(private route: ActivatedRoute) {
        this.route.params.subscribe(
        params => this.lightId = params['id']
    );
    console.log(this.lightId);
  }

}