import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class ListadoOpcionService{

    constructor(private http: HttpClient){

    }

    getCorreoByRuc(ruc){
        return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/correo/${ruc}`,{});
    }

    getCorreoRepresentantesByRuc(ruc){
        return this.http.get(`/api/fi/proveedor/recuperar-pass/sc/obtener/listado/correo/rep/${ruc}`,{});
    }

}
