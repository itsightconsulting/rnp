import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {Utilitarios} from "../utilitarios";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  btnSubmit: any;
  objJson: any;
  verificacion = true;
  msgLogin = "";
  opcRecuperacion = 1;
  msgInformacionUsuario = "";
  timesRequestIframe = 0;
  flagSinUsuario = false;
  activeSegForm= false;
  iframeReceiver: any;
  msgNueUsu=false;

  constructor(private readonly authenticationService: AuthenticationService, private readonly utilitarios: Utilitarios) {
  }

  ngOnInit() {
      if(this.utilitarios.$urlParam("ops") != null){
        setTimeout(()=>{document.getElementById('btnNoInitSesion').click();}, 100);
      }
  }

  submit(frm, $event){
      this.btnSubmit = Array.from($event.target)[--Array.from($event.target).length];
      this.objJson = new Object();
      frm._directives.forEach((x,i) => {
          this.objJson[Object.keys(frm.value)[i].substr(0,1).toLocaleLowerCase() + Object.keys(frm.value)[i].substr(1)] = frm.value[Object.keys(frm.value)[i]];
      });
      if(frm.valid) {
          if (!this.btnSubmit.classList.contains('disabled')) {
              this.btnSubmit.classList.add('disabled');
              this.authenticationService.authProcess(this.objJson).subscribe(
                  (d: any) => {
                      if (d.flag) {
                          this.overlayActive();
                          this.iframeReceiver = document.getElementById('IframeReceiver');
                          this.iframeReceiver.contentWindow.postMessage(`${frm.controls.Ruc.value}|${frm.controls.Clave.value}`,
                                                                     'https://www.rnp.gob.pe/login.asp');
                          //this.iframeCheckLogged() is fired after login on rnp's irame
                          this.validacionIframeFallo(d);
                      } else {
                          this.verificacion = d.flag;
                          this.msgLogin = d.d;
                      }
                  }, ()=>{}
                  , ()=>{
                      this.btnSubmit.classList.remove('disabled');
                  }
              );
          }
      } else{
          if(frm.controls.Clave.invalid){
              frm.controls.Clave.markAsTouched();
          }

          if(frm.controls.Ruc.invalid){
              frm.controls.Ruc.markAsTouched();
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

  irOpcionRecuperacion(){
      if(this.opcRecuperacion === 1){
          window.location.href = document.querySelector('base').href+"recuperar/password";
      } else{
          this.activeSegForm=false;
          this.flagSinUsuario = true;
      }
  }

  showMsgUsuario(){
      this.msgInformacionUsuario = "Estimado proveedor, para personas naturales o jurídicas que son nacionales o extranjeros domiciliados en el Perú," +
       " su usuario RNP es el número de RUC proporcionado por la Superintendencia Nacional de Aduanas y de Administración Tributaria (SUNAT)";
       setTimeout(()=>{this.msgInformacionUsuario = ""}, 12000);
  }

  iframeCheckLogged(){
      if(this.timesRequestIframe>0){
          window.location.href = "https://www.rnp.gob.pe/tramites/tramites.asp";
      }
      this.timesRequestIframe++;
  }

  overlayActive(){
      try {
          document.getElementById("overlay").style.display = "block";
      }catch (ex) {
          console.log(ex);
      }
  }

  validacionIframeFallo(d){
      setTimeout(()=>{
          this.verificacion = !d.flag;
          this.msgLogin = "El servicio de login no se encuentra disponible. Intentelo nuevamente más tarde o comuníquese al teléfono: 6135555 Anexo 5000";
          document.getElementById("overlay").style.display = "none";
      }, 5000)
  }

  redirectRecUsu(){
      window.location.href = document.querySelector('base').href+"recuperar/usuario";
  }

  backLogin(){
      window.location.href = document.querySelector('base').href+"login";
  }
}
