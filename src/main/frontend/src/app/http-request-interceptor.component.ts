import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class HttpRequestInterceptor implements HttpInterceptor{

    constructor(private cookie: CookieService){
    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // Clone the request to add the new header
        if(!req.url.startsWith('/api/oauth')){
            const clonedRequest = req.clone({ headers: req.headers.set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token')) });
            return next.handle(clonedRequest);
        }
        return next.handle(req);
    }
}
