import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private readonly http: HttpClient) {

    }

    authProcess(objAutth: any) {
        const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params = new HttpParams({
            fromObject : objAutth
        });
        return this.http.post('/api/fi/seg/login', params,{headers});
    }
}
