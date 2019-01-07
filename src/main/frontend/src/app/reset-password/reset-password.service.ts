import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private http: HttpClient, private cookie: CookieService) {}

    getCorreo(ruc) {
        const headers = new HttpHeaders().set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token'));
        return this.http.get('/api/contacto/obtenerCorreoUsuario/'+ruc, {headers});
    }

    enviarCorreoRecuperacion(ruc) {
        const headers = new HttpHeaders().set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token'));
        return this.http.get('/api/contacto/correoRecuperacion/'+ruc, {headers});
    }
}
