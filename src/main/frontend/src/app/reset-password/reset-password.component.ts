import {Component, OnInit} from '@angular/core';
import {ResetPasswordService} from "./reset-password.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  refCaptcha: any;
  ruc: any;
  failCaptcha = false;
  errorMessage: string;
  activeOpsCambio = false;

  constructor(private readonly resetPasswordService: ResetPasswordService, private readonly cookie: CookieService) { }

  ngOnInit() {
    this.resetPasswordService.getCaptcha().subscribe((res: any)=>{
        if(res.flag){
            const d = res.d;
            this.cookie.set('cap_code', d.answer, 0, '/');
            document.querySelector('#ImgCaptcha').setAttribute('src', "data:image/png;base64," + d.b64image.substr(0, d.b64image.length - 2))
        }
    });
  }

    refreshCaptcha(fail?){
        if(fail !== undefined) this.failCaptcha = true;
        this.resetPasswordService.refreshCaptcha().subscribe((res: any)=>{
            if(res.flag){
                const d = res.d;
                this.refCaptcha = {};
                this.cookie.set('cap_code', d.answer, 0, '/');
                this.refCaptcha.c = document.getElementById('CodeCaptcha');
                this.refCaptcha.c.value = "";
                document.querySelector('#ImgCaptcha').setAttribute('src', "data:image/png;base64," + d.b64image.substr(0, d.b64image.length - 2))
            }
        })
    }

    numberOnly(event): boolean {
        const charCode = (event.which) ? event.which : event.keyIdentifier;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

    preventMultipleSubmit(btn, r){
        const captchaVal = r.controls.CodeCaptcha.value === this.cookie.get('cap_code');

        if (!captchaVal) {
            this.refreshCaptcha(true);
            setTimeout(()=>this.failCaptcha = false, 3000);
            r.controls.CodeCaptcha.setErrors({'incorrect': true});
            return;
        }

        if(captchaVal){
            btn.setAttribute('disabled','disabled');
            this.resetPasswordService.getOpciones(r.controls.Ruc.value).subscribe((d: any) => {
                    this.reducingComplexity(d, r);
            },
            err => {
                this.errorMessage = `${err.status}: ${err.statusText}`;
                btn.removeAttribute('disabled');
            },
            ()=>{
                setTimeout(()=>this.errorMessage = "", 4500);
            })
        }
    }

    reducingComplexity(d: any, r: any){
        if (d.flag) {
            this.activeOpsCambio = true;
            this.cookie.set('ruc_prov', r.controls.Ruc.value, 0, '/');
            this.ruc = r.controls.Ruc.value;
            Array.from(document.querySelectorAll('label.group-rbt')).forEach(v => v.classList.remove('hidden'));
            const objValues = Object.keys(d.d).map(key=>d.d[key]);
            for(let i=0; i<objValues.length; i++){
                if(objValues[i] === "NO") {
                    document.querySelectorAll('label.group-rbt')[i].classList.add('hidden');
                }
            }
            this.cookie.set('my_opcs_recover_password', JSON.stringify(d.d), 0, '/');
            return;
        } else {
            this.refreshCaptcha();
            this.errorMessage = d.d;
            this.failCaptcha = false;
            r.controls.CodeCaptcha.setErrors({'incorrect': true});
            return;
        }
    }
}
