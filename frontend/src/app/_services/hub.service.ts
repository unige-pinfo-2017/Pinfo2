import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {labConConfig} from 'labCon-config';



@Injectable()
export class HubService{
    private restServerApiUrl = labConConfig.restServerApiUrl + 'devices/hubs/';

    constructor(private http: Http) {
    }
    
    getHubStates(id: string, fromTimeStamp: number, toTimeStamp: number): Observable<any[]>{
        
        /*let params: URLSearchParams = new URLSearchParams();
        params.append('deviceId', id.toString());
        let options : RequestOptions = new RequestOptions({search: params});*/
        return this.http.get(this.restServerApiUrl+'getStates?deviceId='+id + '&from=' + fromTimeStamp.toString() + '&to=' + toTimeStamp.toString() ).map(this.extractHubStates).catch(this.handleError);
    }

    private extractHubStates(res: Response){
        let body = res.json();
        return body.hubStates || { };
    }

    getHubLastState(id: string): Observable<any>{
        
        /*let params: URLSearchParams = new URLSearchParams();
        params.append('deviceId', id.toString());
        let options : RequestOptions = new RequestOptions({search: params});*/
        return this.http.get(this.restServerApiUrl+'getLastState?deviceId='+id ).map(this.extractHubLastState).catch(this.handleError);
    }

    private extractHubLastState(res: Response){
        let body = res.json();
        return body.hub || { };
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