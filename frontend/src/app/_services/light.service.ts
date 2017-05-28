import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Device } from '../_models/device';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {labConConfig} from 'labCon-config';



@Injectable()
export class LightService{
    private restServerApiUrl = labConConfig.restServerApiUrl + 'devices/lights/';

    constructor(private http: Http) {
    }


    getLightLastState(id: number): Observable<any>{
        
        /*let params: URLSearchParams = new URLSearchParams();
        params.append('deviceId', id.toString());
        let options : RequestOptions = new RequestOptions({search: params});*/
        return this.http.get(this.restServerApiUrl+'getLastState?deviceId='+id.toString()).map(this.extractLightLastState).catch(this.handleError);
    }

    private extractLightLastState(res: Response){
        let body = res.json();
        return body.light || { };
    }

    postLightState(id: number, state: boolean): Observable<any>{
        let body = JSON.stringify({  });
        /*let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });*/
        return this.http.post(this.restServerApiUrl+'setOnOrOff?deviceId='+id.toString()+'&onOrOff='+state, body).map(() =>1).catch(this.handleError);
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