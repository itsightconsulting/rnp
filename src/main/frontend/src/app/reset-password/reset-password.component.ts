import { Component, OnInit } from '@angular/core';
import {ResetPasswordService} from "./reset-password.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

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
      const ruc = document.querySelector('#Ruc').value;
      const codCaptcha = document.querySelector('#captchaCode').value;
      if(codCaptcha.length > 0){
          const answerCaptcha = this.cookie.get('captcha_code');
          if(codCaptcha.trim() == answerCaptcha){
              this.resetPasswordService.getCorreo(ruc).subscribe((res: any)=>{
                  if(res.flag){
                      document.querySelector('#RucSolicitante').textContent = ruc;
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
              this.refreshCaptcha("");
          }
      }else
          alert('Debe ingresar el código captcha');


  }

  enviarCorreoRecuperacion(evt){
      const btn = evt.target;
      btn.textContent = "Cargando... Por favor espere...";
      if(!btn.classList.contains('disabled')) {
          btn.classList.add('disabled');
          const ruc = document.querySelector('#Ruc').value;
          this.resetPasswordService.enviarCorreoRecuperacion(ruc).subscribe((res : any) => {
              if (res.flag) {
                  alert("El correo ha sido enviado satisfactoriamente, revise su bandeja");
              } else {
                  alert("Ha ocurrido un error en la validación. Intentelo nuevamente más tarde");
              }
          });
      }
  }

    refreshCaptcha(evt){
        this.resetPasswordService.refreshCaptcha().subscribe((res: any)=>{
            if(res.flag){
                const d = res.d;
                this.cookie.set('captcha_code', d.answer);
                document.getElementById('captchaCode').value = "";
                document.querySelector('#ImgCaptcha').setAttribute('src', "data:image/png;base64," + d.b64image.substr(0, d.b64image.length - 2))
            }
        })
    }

}
