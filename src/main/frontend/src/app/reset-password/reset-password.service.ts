import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private http: HttpClient) {}


    getOpciones(ruc){
        return this.http.get('/api/proveedor/recuperar-pass/sc/obt/opciones/'+ruc);
    }

    enviarCorreoRecuperacion(ruc) {
        return this.http.get('/api/contacto/correoRecuperacion/'+ruc, {});
    }

    getCaptcha(){
        return this.http.get('/api/captcha/init', {});
    }

    refreshCaptcha(){
        return this.http.get('/api/captcha/refresh', {});
    }
}
