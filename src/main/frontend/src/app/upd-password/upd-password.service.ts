import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UpdPasswordService{

    constructor(private http: HttpClient){

    }

    checkCodigoVerificacion(preCorreoObj){
        const headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: preCorreoObj
            });
        return this.http.post('/api/proveedor/recuperar-pass/sc/enviar/correo', params, {headers});
    }
}
