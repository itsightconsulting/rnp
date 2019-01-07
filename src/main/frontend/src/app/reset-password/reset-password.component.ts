import { Component, OnInit } from '@angular/core';
import {ResetPasswordService} from "./reset-password.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  constructor(private resetPasswordService: ResetPasswordService) { }

  ngOnInit() {
  }

  obtenerCorreo(evt){
      const ruc = document.querySelector('#Ruc').value;
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

}
