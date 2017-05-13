import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Device } from '../_models/device';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class DeviceService {
    private devicesUrl = '/assets/devices.json';

    constructor(private http: Http) {
    }
    getValues(): Observable<Device[]> {
        return this.http.get(this.devicesUrl).map(this.extractValues).catch(this.handleError);
    }

    private extractValues(res: Response) {
        let body = res.json();
        return body.Device;
    }

    private handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Promise.reject(errMsg);
    }
}
