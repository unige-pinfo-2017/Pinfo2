import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../_models/index';

import {dashconConfig} from 'dashcon-config';

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        let headers = this.jwtHeader();
        return this.http.get(dashconConfig.mockApiUrlUsers, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    getById(id: number) {
        let headers = this.jwtHeader();
        return this.http.get(dashconConfig.mockApiUrlUsers +'/' + id, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    create(user: User) {
        let headers = this.jwtHeader();
        headers.append('Content-Type', 'application/json');
		return this.http.post(dashconConfig.restServerApiUrl + 'register', user, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    update(user: User) {
        let headers = this.jwtHeader();
        return this.http.put(dashconConfig.mockApiUrlUsers + '/' + user.id, user, this.jwtResponse(headers)).map((response: Response) => response.json());
    }

    delete(id: number) {
        let headers = this.jwtHeader();
        return this.http.delete(dashconConfig.mockApiUrlUsers + '/' + id, this.jwtResponse(headers)).map((response: Response) => response.json());
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
