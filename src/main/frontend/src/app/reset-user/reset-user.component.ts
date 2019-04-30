import { Component, OnInit } from '@angular/core';
import {ResetUserService} from "./reset-user.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-reset-user',
  templateUrl: './reset-user.component.html',
  styleUrls: ['./reset-user.component.css']
})
export class ResetUserComponent implements OnInit {
  opcRecuperacion = 1;
  activeInitForm = true;
  activeOpcion1 = false;
  activeOpcion2 = false;
  lastMsgActive = false;
  refCaptcha: any;
  failCaptcha = false;
  errorMessage = "";
  scssMsg: any;
  finalScssMsg = false;
  prefixImgBs = "data:image/png;base64,";

  constructor(private readonly resetUserService: ResetUserService, private readonly cookie: CookieService) { }

  ngOnInit() {
      this.resetUserService.getCaptcha().subscribe((res: any)=>{
          if(res.flag){
              const d = res.d;
              this.cookie.set('cap_code', d.answer, 0, '/');
              document.querySelector('#ImgCaptcha').setAttribute('src', this.prefixImgBs + d.b64image.substr(0, d.b64image.length - 2))
              document.querySelector('#ImgCaptcha2').setAttribute('src', this.prefixImgBs + d.b64image.substr(0, d.b64image.length - 2))
          }
      });
  }

  mostrarMensaje(){
    this.lastMsgActive = true;
      setTimeout(() => {
          this.lastMsgActive = false;
      }, 30000);
  }

  irOpcionRecuperacion(){
    if(this.opcRecuperacion === 1){
        this.activeOpcion1 = true;
        this.activeInitForm = false;
    }

    if(this.opcRecuperacion === 2){
        this.activeOpcion2 = true;
        this.activeInitForm = false;
    }

    if(this.opcRecuperacion === 3){
        window.location.href = document.querySelector('base').href+"recuperar/usuario/busqueda";
    }
  }

  refreshCaptcha(fail?, sec?){
      if(fail !== undefined) this.failCaptcha = true;
      this.resetUserService.refreshCaptcha().subscribe((res: any)=>{
          if(res.flag){
              const d = res.d;
              this.refCaptcha = {};
              this.cookie.set('cap_code', d.answer, 0, '/');
              this.refCaptcha.c = document.getElementById(`CodeCaptcha${sec !== undefined ? '2':''}`);
              this.refCaptcha.c.value = "";
              this.refCaptcha.c.focus();
              document.querySelector(`#ImgCaptcha${sec !== undefined ? '2':''}`).setAttribute('src', this.prefixImgBs + d.b64image.substr(0, d.b64image.length - 2))
          }
      })
  }

  submit(btn, r){
      if(r.valid) {
          if (r.controls.CodeCaptcha.value === this.cookie.get('cap_code') && !btn.hasAttribute('disabled')) {
              btn.setAttribute('disabled','disabled');
              this.resetUserService.enviarCorreoProvExtNoDom({correo: r.controls.CorreoEle.value}).subscribe((d: any) => {
                      if (d.flag) {
                          this.finalScssMsg = true;
                          this.scssMsg = d.d;
                          this.activeOpcion1 = false;
                      } else {
                          this.refreshCaptcha();
                          this.errorMessage = d.d;
                          this.failCaptcha = false;
                          r.controls.CodeCaptcha.setErrors({'incorrect': true});
                      }
                  },
                  err => {
                      this.errorMessage = `${err.status}: ${err.statusText}`;
                      btn.removeAttribute('disabled');
                  },
                  ()=>{
                      setTimeout(()=>this.errorMessage = "", 8000);
                  }
              )
          }else {
              this.refreshCaptcha(true);
              setTimeout(()=>this.failCaptcha = false, 3000);
              r.controls.CodeCaptcha.setErrors({'incorrect': true});
          }
      }
  }

    submit2(btn, r){
        if(r.valid) {
            if (r.controls.CodeCaptcha2.value === this.cookie.get('cap_code') && !btn.hasAttribute('disabled')) {
                btn.setAttribute('disabled','disabled');
                this.resetUserService.enviarCorreoRepProvExtNoDom({correo: r.controls.CorreoEleRepre.value}).subscribe((d: any) => {
                        if (d.flag) {
                            this.finalScssMsg = true;
                            this.scssMsg = d.d;
                            this.activeOpcion2 = false;
                        } else {
                            this.refreshCaptcha(undefined, true);
                            this.errorMessage = d.d;
                            this.failCaptcha = false;
                            r.controls.CodeCaptcha2.setErrors({'incorrect': true});
                        }
                    },
                    err => {
                        this.errorMessage = `${err.status}: ${err.statusText}`;
                        btn.removeAttribute('disabled');
                    },
                    ()=>{
                        setTimeout(()=>this.errorMessage = "", 8000);
                    }
                )
            } else {
                this.refreshCaptcha(true, true);
                setTimeout(()=>this.failCaptcha = false, 3000);
                r.controls.CodeCaptcha2.setErrors({'incorrect': true});
            }
        }
    }

    backLogin(){
        window.location.href = document.querySelector('base').href+"login";
    }

    removeSpaces(e){
        e.control.setValue(e.value.trim());
    }
}
