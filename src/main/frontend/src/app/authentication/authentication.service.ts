import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    baseUrl = environment.baseUrl;

    constructor(private http: HttpClient) {

    }

    authProcess(objAutth: any) {
        const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params = new HttpParams({
            fromObject : objAutth
        });
        return this.http.post(this.baseUrl+'/api/seg/login', params,{headers});
    }
}
