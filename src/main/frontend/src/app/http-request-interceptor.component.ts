import {
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpResponse
} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, throwError} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {catchError, map} from 'rxjs/operators';
import {OnStartUpService} from "./on-start-up/on-start-up.service";
import {environment} from "../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class HttpRequestInterceptor implements HttpInterceptor{

    overlaySpans: any;
    intervalOverlay: any;
    msgOverlay = "Por favor espere";
    rnpApiBsRoute: string = environment.baseUrl;
    hrefInformativo = "informativo";

    constructor(private cookie: CookieService, private onWakeUp: OnStartUpService){
    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
        this.overlayExecute();

        // Clone the request to add the new header
        if(!req.url.startsWith('/api/fi/oauth')){
            if(req.url.startsWith('/api/fi')) {
                const clonedRequest = req.clone({url: req.url.replace('/api/fi', this.rnpApiBsRoute), headers: req.headers.set('Authorization', 'Bearer ' + this.cookie.get('rnp_api_token'))});
                return next.handle(clonedRequest).pipe(map((event: HttpEvent<any>) => {
                    if (event instanceof HttpResponse) {
                        window.clearInterval(this.intervalOverlay);
                        document.getElementById("overlay").style.display = "none";
                    }
                    return event;
                }), catchError((error: HttpErrorResponse) => {
                    if (error.status != undefined && error.status === 401) {
                        console.log(error);
                        this.onWakeUp.instanceApiTokenByOldCookie()
                            .subscribe((d: any) => {
                                console.log(d);
                                this.cookie.set('rnp_api_token', d.access_token, 0, '/');
                                window.location.reload();
                            }, err => {
                                console.log(err);
                                this.cookie.set('rnp_api_token', '0', 0, '/');
                                window.location.href = document.querySelector('base').href+this.hrefInformativo;
                            })
                    }

                    if (error.status != undefined && (error.status === 504 || error.status === 0)) {
                        console.log(error);
                        this.cookie.set('rnp_api_token', '0', 0, '/');
                        window.location.href = document.querySelector('base').href+this.hrefInformativo;
                    }
                    return throwError(error);
                }))
            }
        }else{
            const clonedRequest = req.clone({ url: req.url.replace('/api/fi', this.rnpApiBsRoute)});
            return next.handle(clonedRequest).pipe(map((event: HttpEvent<any>) => {
                return event;
            }), catchError((error: HttpErrorResponse) => {
                    console.log(error);
                    this.cookie.set('rnp_api_token', '0', 0, '/');
                    window.location.href = document.querySelector('base').href+this.hrefInformativo;
                return throwError(error);
            }));
        }
        return next.handle(req)
    }

    overlayExecute(){
        /*if(req.method == "POST") {*/
        try {
            document.getElementById("overlay").style.display = "block";
            const charArray = this.msgOverlay.split('');
            const charArrayWithSpaces = charArray.map(v => {
                return `<span>${v == ' ' ? '&nbsp' : v}</span>`
            }).join('');
            const finalOverlayMessage = charArrayWithSpaces + '<span>&nbsp</span><span><e class="fa fa-spinner fa-spin"></e></span>';
            document.querySelector('#overlay p').innerHTML = finalOverlayMessage;

            let correCaminos = 0;
            let vueltasCompletas = 0;
            const lenLetters = this.msgOverlay.length + 1;
            this.overlaySpans = document.querySelectorAll('#overlay p span');
            this.intervalOverlay = setInterval(() => {
                if (correCaminos > 0) {
                    this.overlaySpans[correCaminos - 1].style.color = 'white';
                }
                if (vueltasCompletas > 0) {
                    this.overlaySpans[this.overlaySpans.length - 1].style.color = 'white';
                }
                this.overlaySpans[correCaminos].style.color = '#2be8b4';
                if (lenLetters == correCaminos) {
                    correCaminos = 0;
                    ++vueltasCompletas;
                }
                correCaminos++;
            }, 100)
        } catch (ex) {
            //console.log(ex);
        }
        /*}*/
    }
}
