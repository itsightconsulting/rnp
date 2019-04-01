import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private readonly http: HttpClient) {}


    getOpciones(ruc){
        return this.http.get('/api/fi/proveedor/recuperar-pass/sc/obt/opciones/'+ruc);
    }

    enviarCorreoRecuperacion(ruc) {
        return this.http.get('/api/fi/contacto/correoRecuperacion/'+ruc, {});
    }

    getCaptcha(){
        return this.http.get('/api/fi/captcha/init', {});
    }

    refreshCaptcha(){
        return this.http.get('/api/fi/captcha/refresh', {});
    }
}
