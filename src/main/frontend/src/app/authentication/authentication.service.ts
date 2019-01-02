import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient, private cookie: CookieService) {}

    authProcess(ruc, clave) {
        const headers = new HttpHeaders().set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token')).set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params = new HttpParams({
            fromObject : {
                'ruc' : ruc,
                'clave' : clave
            }});
        return this.http.post('/api/clave/validaUsuario', params,{headers});
    }
}
