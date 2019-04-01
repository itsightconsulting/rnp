import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {CsHeader} from "./cs-header";

@Injectable({
    providedIn: 'root'
})
export class ResetUserService {

    constructor(private readonly http: HttpClient) {}

    getCaptcha(){
        return this.http.get('/api/fi/captcha/init', {});
    }

    refreshCaptcha(){
        return this.http.get('/api/fi/captcha/refresh', {});
    }
    
    enviarCorreoProvExtNoDom(obj){
        const csHeader = this.getHeadersParams(obj);
        const headers = csHeader.getHeaders();
        return this.http.post('/api/fi/proveedor/recuperar-pass/su/enviar/correo/ext-nodom', csHeader.getParams(), {headers});
    }

    enviarCorreoRepProvExtNoDom(obj){
        const csHeader = this.getHeadersParams(obj);
        const headers = csHeader.getHeaders();
        return this.http.post('/api/fi/proveedor/recuperar-pass/su/enviar/correo/rep-ext-nodom', csHeader.getParams(), {headers});
    }

    getHeadersParams(obj){
        const h = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const p = new HttpParams({
            fromObject: obj
        });
        return new CsHeader(h, p);
    }
}
