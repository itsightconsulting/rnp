import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class OnStartUpService{

    constructor(private http: HttpClient, private cookie: CookieService){
    }

    instanceApiToken(){
        const headers = new HttpHeaders().set('Authorization', 'Bearer facda666-ee85-4423-abc9-8f9df8cb2a59');
        this.http.get('http://127.0.0.1:8080/api/v1/seg/d/a', {headers}).subscribe((res: any)=>{
            console.log(res);
        });

        if(!this.cookie.check('rnp_api_token')) {
            const headers = new HttpHeaders().set('Authorization', "Basic " + btoa('rnp_osce' + ":" + 'itsight19@13')).set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            const params =
                new HttpParams({
                    fromObject: {
                        'grant_type': 'client_credentials',
                    }
                });
            this.http.post('/api/oauth/token', params, {headers}).subscribe((d: any) => this.cookie.set('rnp_api_token', d.access_token, 0, '/'));
        }
    }

    instanceApiTokenByOldCookie(){
        const headers = new HttpHeaders().set('Authorization', "Basic " + btoa('rnp_osce' + ":" + 'itsight19@13')).set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: {
                    'grant_type': 'client_credentials',
                }
            });
        return this.http.post('/api/oauth/token', params, {headers});
    }
}
