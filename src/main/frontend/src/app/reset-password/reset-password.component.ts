import { Component, OnInit } from '@angular/core';
import {ResetPasswordService} from "./reset-password.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  refCaptcha: any;
  verify : any;
  ruc: any;
  failCaptcha: boolean = false;
  errorMessage: string;

  constructor(private resetPasswordService: ResetPasswordService, private cookie: CookieService) { }

  ngOnInit() {
    this.resetPasswordService.getCaptcha().subscribe((res: any)=>{
        if(res.flag){
            const d = res.d;
            this.cookie.set('captcha_code', d.answer);
            document.querySelector('#ImgCaptcha').setAttribute('src', "data:image/png;base64," + d.b64image.substr(0, d.b64image.length - 2))
        }

    });
  }

  obtenerCorreo(evt){
      this.verify = {};
      this.verify.ruc = document.querySelector('#Ruc');
      this.verify.codCaptcha = document.querySelector('#captchaCode');
      if(this.verify.codCaptcha.value.length > 0){
          const answerCaptcha = this.cookie.get('captcha_code');
          if(this.verify.codCaptcha.value.trim() == answerCaptcha){
              this.resetPasswordService.getCorreo(this.verify.ruc).subscribe((res: any)=>{
                  if(res.flag){
                      document.querySelector('#RucSolicitante').textContent = this.verify.ruc.value;
                      document.querySelector('#MsgValidacion').classList.remove('d-none');
                      document.querySelector('#frm_1').classList.add('d-none');
                      document.querySelector('#frm_2').classList.remove('d-none');
                      const correo = res.d.mensaje;
                      document.querySelector('#CorreoSemiOculto').textContent = correo.slice(0,5) + "*******"+ correo.slice(12);
                  }else{
                      document.querySelector('#MsgValidacion').classList.remove('d-none');
                  }
              });
          }else {
              alert('El código captcha ingresado no coincide con el de la imagen');
              this.refreshCaptcha();
          }
      }else
          alert('Debe ingresar el código captcha');


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
                this.cookie.set('captcha_code', d.answer);
                this.refCaptcha.c = document.getElementById('CodeCaptcha');
                this.refCaptcha.c.value = "";
                document.querySelector('#ImgCaptcha').setAttribute('src', "data:image/png;base64," + d.b64image.substr(0, d.b64image.length - 2))
            }
        })
    }

    obtenerOpciones(r, evt){
        if(r.valid) {
            if (r.controls.CodeCaptcha.value == this.cookie.get('captcha_code'))
                this.resetPasswordService.getOpciones(r.controls.Ruc.value).subscribe((d: any) => {
                        if(d.flag)
                            console.log('success')
                        else{
                            this.refreshCaptcha();
                            this.errorMessage = d.d;
                            this.failCaptcha = this.failCaptcha ? false : !true;
                        }
                    },
                    error => {
                        console.log(error)
                    })
            else
                this.refreshCaptcha(true);
        }else{
            for(let i in r.controls){
                r.controls[i].markAsTouched();
            }
        }
    }

    numberOnly(event): boolean {
        const charCode = (event.which) ? event.which : event.keyIdentifier;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

}
