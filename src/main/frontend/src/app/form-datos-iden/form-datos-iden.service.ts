import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class FormDatosIdenService{

    constructor(private http: HttpClient) {}

    obtenerForaneas(fk){
        switch (fk) {
            case 'P':
                return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/foranea/pais`,{});
                break;
            case 'D':
                return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/foranea/tipo-documento`,{});
                break;
            case 'Z':
                return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/foranea/zona-registral`,{});
                break;
            case 'C':
                return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/foranea/tipo-condicion`,{});
                break;
            default: break;
        }
    }

    checkDatosIdentificacion(dtsIdenDto){
        const headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: dtsIdenDto
            });
        return this.http.post('/api/fi/proveedor/recuperar-pass/sc/validar/datos-identificacion', params, {headers});
    }

    actualizarCorreoProveedor(obj: any){
        const headers = new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: obj
            });
        return this.http.put(`/api/fi/proveedor/recuperar-pass/sc/actualizar/correo/ext-nodom`, params, {headers});

    }

}
