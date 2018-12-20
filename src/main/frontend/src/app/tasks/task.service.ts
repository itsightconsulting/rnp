import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

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
