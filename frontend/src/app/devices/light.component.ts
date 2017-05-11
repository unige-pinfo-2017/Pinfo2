import { Component, OnInit, Input } from '@angular/core' ;
import { Light } from '../_models/light';

@Component({
    selector: 'light',
    templateUrl: 'light.component.html',
    styleUrls: ['devices.component.css']
})

export class LightComponent implements OnInit{
    @Input() selectedLight: Light;

    ngOnInit() {
    }

    constructor() { }
}