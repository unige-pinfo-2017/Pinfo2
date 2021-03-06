﻿import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../_models/index';

import {labConConfig} from 'labCon-config';

@Injectable()
export class UserService {
    private restServerApiUrl = labConConfig.restServerApiUrl + 'users/';

    constructor(private http: Http) { }

    getAll(): Observable<any[]> {
        let headers = this.jwtHeader();
        return this.http.get(this.restServerApiUrl + "getAllUsers", this.jwtResponse(headers)).map((response: Response) => {
            let x = response.json();
            return x || { };
        });
    }

    getById(id: number) {
        let headers = this.jwtHeader();
        return this.http.get(labConConfig.mockApiUrlUsers +'/' + id, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    create(user: User) {
        let headers = this.jwtHeader();
        headers.append('Content-Type', 'application/json');
		return this.http.post(labConConfig.restServerApiUrl + 'register', user, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    update(user: User) {
        let headers = this.jwtHeader();
        return this.http.put(labConConfig.mockApiUrlUsers + '/' + user.id, user, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    delete(id: number) {
        let headers = this.jwtHeader();
        return this.http.delete(labConConfig.mockApiUrlUsers + '/' + id, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    // private helper methods
    private jwtHeader(){
        // create authorization header with jwt token
        let headers = new Headers();
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            headers.append( 'Authorization', 'Bearer ' + currentUser.token );
        }
        return headers;
    }
    private jwtResponse(headers : Headers) {
        return new RequestOptions({ headers: headers });
    }
}
