import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ResetUserService {

    constructor(private http: HttpClient) {}

    getCaptcha(){
        return this.http.get('/api/captcha/init', {});
    }

    refreshCaptcha(){
        return this.http.get('/api/captcha/refresh', {});
    }
    
    enviarCorreoProvExtNoDom(obj){
        const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params = new HttpParams({
            fromObject: obj
        });
        return this.http.post('/api/proveedor/recuperar-pass/su/enviar/correo/ext-nodom', params, {headers});
    }

    enviarCorreoRepProvExtNoDom(obj){
        const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params = new HttpParams({
            fromObject: obj
        });
        return this.http.post('/api/proveedor/recuperar-pass/su/enviar/correo/rep-ext-nodom', params, {headers});
    }
}
