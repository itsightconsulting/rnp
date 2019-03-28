import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class BuscarEmpresaService{
    constructor(private http: HttpClient){

    }

    obtenerListadoPais(){
       return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/foranea/pais`,{});
    }

    obtenerEmpresas(objQ: any){
        return this.http.get(`/api/fi/proveedor/recuperar-pass/su/obtener/listado/empresa-ext-no-dom/${objQ.razonSocial}?paisId=${objQ.paisId}&tipoPersonaId=${objQ.tipoPersonaId}`);
    }
}
