import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient) {}

    authProcess(credentials) {
/*
        const headers = new HttpHeaders().set('Content-Type', 'application/json');
*/
        return this.http.post('/api/clave/validaUsuario', credentials/*, {headers}*/);
    }
}
