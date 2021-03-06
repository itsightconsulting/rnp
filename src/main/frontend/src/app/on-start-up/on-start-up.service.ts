import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class OnStartUpService{

    oauthCliUsername = "rnp_osce";
    oauthCliPassword = "itsight19@13";

    constructor(private readonly http: HttpClient, private readonly cookie: CookieService){
    }

    instanceApiToken(){
        if(!this.cookie.check('rnp_api_token')) {
            const headers =
                            new HttpHeaders()
                                .set('Authorization', "Basic " + btoa(`${this.oauthCliUsername}:${this.oauthCliPassword}`))
                                .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

            const params =
                new HttpParams({
                    fromObject: {
                        'grant_type': 'client_credentials',
                    }
                });
            this.http.post('/api/fi/oauth/token', params, {headers}).subscribe((d: any) => this.cookie.set('rnp_api_token', d.access_token, 0, '/'));
        }
    }

    instanceApiTokenByOldCookie(){
        const headers = new HttpHeaders()
                                .set('Authorization', "Basic " + btoa(`${this.oauthCliUsername}:${this.oauthCliPassword}`))
                                .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        const params =
            new HttpParams({
                fromObject: {
                    'grant_type': 'client_credentials',
                }
            });
        return this.http.post('/api/fi/oauth/token', params, {headers});
    }
}
