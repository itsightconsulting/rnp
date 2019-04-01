import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";
import {Utilitarios} from "../utilitarios";

@Injectable({
    providedIn: 'root'
})
export class NuevaPasswordService {

    constructor(private readonly http: HttpClient, private readonly cookie: CookieService, private readonly utilitarios: Utilitarios) {}

    validacionCambioPassword() {
        const rucHash = this.utilitarios.$urlParam("cd");
        return this.http.get('/api/fi/contacto/validacion/cambio-password/'+rucHash, {});
    }
}
