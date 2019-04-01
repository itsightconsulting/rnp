import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UpdPasswordService{

    constructor(private readonly http: HttpClient){

    }

    enviarCorreoCodVer(preCorreoObj){
        const headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: preCorreoObj
            });
        return this.http.post('/api/fi/proveedor/recuperar-pass/sc/enviar/correo', params, {headers});
    }

    checkCodigoVerificacion(ruc, codVer){
        return this.http.get(`/api/fi/seg/validar/cod-verificacion/${ruc}/${codVer}`, {});
    }

    actualizarClave(updClaveDto){
        const headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: updClaveDto
            });
        return this.http.post('/api/fi/seg/actualizar/clave', params, {headers});
    }
}
