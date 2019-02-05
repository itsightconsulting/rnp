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
  failCaptcha: boolean = false;
  errorMessage: string;
  activeOpsCambio: boolean = false;

  constructor(private resetPasswordService: ResetPasswordService, private cookie: CookieService) { }

  ngOnInit() {
    this.resetPasswordService.getCaptcha().subscribe((res: any)=>{
        if(res.flag){
            const d = res.d;
            this.cookie.set('cap_code', d.answer, 0, '/');
            document.querySelector('#ImgCaptcha').setAttribute('src', "data:image/png;base64," + d.b64image.substr(0, d.b64image.length - 2))
        }
    });
  }

  enviarCorreoRecuperacion(evt){
      const btn = evt.target;
      btn.textContent = "Cargando... Por favor espere...";
      if(!btn.classList.contains('disabled')) {
          btn.classList.add('disabled');
          this.ruc = document.querySelector('#Ruc');
          this.resetPasswordService.enviarCorreoRecuperacion(this.ruc.value).subscribe((res : any) => {
              if (res.flag) {
                  alert("El correo ha sido enviado satisfactoriamente, revise su bandeja");
              } else {
                  alert("Ha ocurrido un error en la validación. Intentelo nuevamente más tarde");
              }
          });
      }
  }

    refreshCaptcha(fail?){
        if(fail != undefined) this.failCaptcha = true;
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
        if(r.valid) {
            if (r.controls.CodeCaptcha.value == this.cookie.get('cap_code') && !btn.hasAttribute('disabled')) {
                btn.setAttribute('disabled','disabled');
                this.resetPasswordService.getOpciones(r.controls.Ruc.value).subscribe((d: any) => {
                        if (d.flag) {
                            this.activeOpsCambio = true;
                            this.cookie.set('ruc_prov', r.controls.Ruc.value, 0, '/');
                            document.querySelectorAll('label.group-rbt').forEach(v => v.classList.remove('hidden'));
                            for(let i=0; i<Object.values(d.d).length; i++){
                                Object.values(d.d)[i] == "NO" ? document.querySelectorAll('label.group-rbt')[i].classList.add('hidden') : "";
                            }
                            this.cookie.set('my_opcs_recover_password', JSON.stringify(d.d), 0, '/');
                        } else {
                            this.refreshCaptcha();
                            this.errorMessage = d.d;
                            this.failCaptcha = false;
                            r.controls.CodeCaptcha.setErrors({'incorrect': true});
                        }
                    },
                    err => {
                        this.errorMessage = err.status + ": "+err.statusText;
                        btn.removeAttribute('disabled');
                    },
                    ()=>{
                        setTimeout(()=>this.errorMessage = "", 4500);
                    }
                )
            }else {
                this.refreshCaptcha(true);
                setTimeout(()=>this.failCaptcha = false, 3000);
                r.controls.CodeCaptcha.setErrors({'incorrect': true});
            }
        } else{
            for(let i in r.controls){
                r.controls[i].markAsTouched();
            }
        }
    }

}
