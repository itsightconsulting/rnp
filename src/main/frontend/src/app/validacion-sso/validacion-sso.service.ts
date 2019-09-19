import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ValidacionSsoService{

    constructor(private readonly http: HttpClient) {}

    auditoriaActualizacionFromSsoSunat(data) {
        const headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: data
            });
        return this.http.post('/api/fi/proveedor/recuperar-pass/sc/post/sso-sunat-validacion', params, {headers});
    }
}
