import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    constructor(private http: HttpClient, private cookie: CookieService) {}

    instanceApiToken(){
        if(!this.cookie.check('rnp_api_token')){
            console.log('Angular app is now running');
            const headers = new HttpHeaders().set('Authorization',"Basic " + btoa('rnp_osce' + ":" + 'itsight19@13')).set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            const params = new HttpParams({
                fromObject : {
                    'grant_type' : 'client_credentials',
                }});
            this.http.post('/api/oauth/token', params,{headers}).subscribe((d: any)=>this.cookie.set('rnp_api_token', d.access_token));
        }
    }

    getTask() {
        return this.http.get('/api/tasks');
    }

    consultar() {
        return this.http.get('/api/listar');
    }
}
