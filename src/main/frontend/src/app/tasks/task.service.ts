import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    constructor(private http: HttpClient) {}

    getTask() {
        return this.http.get('/api/tasks');
    }

    consultar() {
        return this.http.get('/api/listar');
    }
}
