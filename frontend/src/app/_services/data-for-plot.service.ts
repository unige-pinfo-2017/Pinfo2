import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class DataForPlotService {

    private plotUrl = 'localhost:8080/testPlot';
    constructor(private http: Http){

    }
    getValues(): Promise<any[]>{
        return this.http.get(this.plotUrl).toPromise().then(response => response.json().data as any[]).catch(this.handleError);
    } 

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

}