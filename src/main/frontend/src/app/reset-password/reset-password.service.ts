import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private http: HttpClient, private cookie: CookieService) {}


    getOpciones(ruc){
        return this.http.get('/api/proveedor/recuperar-pass/sc/obt/opciones/'+ruc);
    }
    getCorreo(ruc) {
        return this.http.get('/api/proveedor/recuperar-pass/sc/obt/opciones/'+ruc.value);
    }

    enviarCorreoRecuperacion(ruc) {
        const headers = new HttpHeaders().set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token'));
        return this.http.get('/api/contacto/correoRecuperacion/'+ruc, {headers});
    }

    getCaptcha(){
        const headers = new HttpHeaders().set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token'));
        return this.http.get('/api/captcha/init', {headers});
    }

    refreshCaptcha(){
        const headers = new HttpHeaders().set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token'));
        return this.http.get('/api/captcha/refresh', {headers});
    }

}
