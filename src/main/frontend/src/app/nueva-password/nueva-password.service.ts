import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class NuevaPasswordService {

    constructor(private http: HttpClient, private cookie: CookieService) {}

    validacionCambioPassword() {
        const rucHash = new URLSearchParams(window.location.search).get("cd");
        return this.http.get('/api/contacto/validacion/cambio-password/'+rucHash, {});
    }
}
