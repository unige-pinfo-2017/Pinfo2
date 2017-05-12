import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class DataForPlotService {

    private plotUrl = 'http://localhost:8080/labCon/testPlot';
    //private plotUrl = '/assets/values.json';
    constructor(private http: Http){
    }
    getValues(): Observable<any>{
        return this.http.get(this.plotUrl).map(this.extractValues).catch(this.handleError);
    }

    private extractValues(res: Response) {
        let body = res.json();
        return body.Values || { };
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
