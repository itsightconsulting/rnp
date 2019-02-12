import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {map} from 'rxjs/operators';
import {OnStartUpService} from "./on-start-up/on-start-up.service";

@Injectable({
    providedIn: 'root'
})
export class HttpRequestInterceptor implements HttpInterceptor{

    private overlaySpans: any;
    private intervalOverlay: any;
    private msgOverlay: string = "Por favor espere";

    constructor(private cookie: CookieService, private onWakeUp: OnStartUpService){
    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // Clone the request to add the new header
        /*if(req.method == "POST") {*/
        try {
            document.getElementById("overlay").style.display = "block";
            document.querySelector('#overlay p').innerHTML = this.msgOverlay.split('').map(v => `<span>${v == ' ' ? '&nbsp' : v}</span>`).join('') + '<span>&nbsp</span><span><i class="fa fa-spinner fa-spin"></i></span>'

            let correCaminos = 0;
            let vueltasCompletas = 0;
            const lenLetters = this.msgOverlay.length + 1;
            this.overlaySpans = document.querySelectorAll('#overlay p span');
            this.intervalOverlay = setInterval(() => {
                if (correCaminos > 0)
                    this.overlaySpans[correCaminos - 1].style.color = 'white';
                if (vueltasCompletas > 0)
                    this.overlaySpans[this.overlaySpans.length - 1].style.color = 'white';
                this.overlaySpans[correCaminos].style.color = '#2be8b4';
                if (lenLetters == correCaminos) {
                    correCaminos = 0;
                    ++vueltasCompletas;
                }
                correCaminos++;
            }, 100)
        } catch (ex) {
            console.log(ex);
        }
        /*}*/
        if(!req.url.startsWith('/api/oauth')){
            const clonedRequest = req.clone({ headers: req.headers.set('Authorization', 'Bearer '+ this.cookie.get('rnp_api_token')) });
            return next.handle(clonedRequest).pipe(map((event: HttpEvent<any>) => {
                console.log(event.type);
                if(event.type == 0){
                    this.onWakeUp.instanceApiTokenByOldCookie()
                        .subscribe((d: any) => {
                                console.log(d);
                                this.cookie.set('rnp_api_token', d.access_token, 0, '/');
                                window.location.reload();
                            }, err=>{
                            window.location.href = "/informativo";
                    })
                }
                if (event instanceof HttpResponse) {
                    // do stuff with response and headers you want
                    /*if(req.method == "POST") {*/
                        window.clearInterval(this.intervalOverlay);
                        document.getElementById("overlay").style.display = "none";
                    /*}*/
                }
                return event;
            }))
        }
        return next.handle(req);
    }
}
