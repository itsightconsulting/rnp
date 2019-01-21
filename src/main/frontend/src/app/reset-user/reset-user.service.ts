import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private http: HttpClient) {}

    getCaptcha(){
        return this.http.get('/api/captcha/init', {});
    }

    refreshCaptcha(){
        return this.http.get('/api/captcha/refresh', {});
    }
}
