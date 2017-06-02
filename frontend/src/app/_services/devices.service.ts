import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptionsArgs } from '@angular/http';
import { Device } from '../_models/device';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {labConConfig} from 'labCon-config';

@Injectable()
export class DeviceService {
    private devicesMockUrl = '/assets/devices.json';
    private restServerApiUrl = labConConfig.restServerApiUrl + 'devices/';
    private dev: Device;
    constructor(private http: Http) {
    }
    
    getAllDevices(): Observable<any> {
        return this.http.get(this.restServerApiUrl + 'getIds').map(this.extractAllDevices).catch(this.handleError);
    }

    private extractAllDevices(res: Response) {
        let body = res.json();
        return body.deviceIds || { };
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

    getWorkstation(id: string): Observable<any>{
        return this.http.get(this.restServerApiUrl + 'getWorkstation?deviceId=' + id).map(this.extractWorkstation).catch(this.handleError);
    }

    private extractWorkstation(res: Response) {
        let body = res.json();
        return body.workstation || { };
    }

    getWorkstationSocketIds(workstationId: string): Observable<any[]>{
        return this.http.get(this.restServerApiUrl + 'getWorkstationSocketIds?workstation=' + workstationId).map(this.extractWorkstationSocketIds).catch(this.handleError);
    }

    private extractWorkstationSocketIds(res: Response) {
        let body = res.json();
        return body.socketIds || { };
    }

    /*getDevice(id: number, typeName: String): Promise<any> {
        return this.getAllDevices().toPromise().then(Device => Device.find(element => element.id === id && element.name===typeName))      
    }*/

}
