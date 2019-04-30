import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {FormDatosIdenService} from "../form-datos-iden/form-datos-iden.service";

@Component({
  selector: 'app-upd-correo',
  templateUrl: './upd-correo.component.html',
  styleUrls: ['./upd-correo.component.css']
})
export class UpdCorreoComponent implements OnInit {

  ruc: string = this.cookie.get('its_cur');
  err2: string;

  constructor(private readonly cookie: CookieService, private readonly datosIdenService: FormDatosIdenService) { }

  ngOnInit() {
      this.validacionRucToken();
  }

  validacionRucToken(){
    if(isNaN(Number(this.ruc))){
        window.location.href = document.querySelector('base').href+"login";
    }
    if(String(this.ruc).length !== 11){
        window.location.href = document.querySelector('base').href+"login";
    }
  }

  actualizarCorreo(r, evt){
      const btn = evt.target;
      if(r.valid){
          btn.setAttribute('disabled', 'disabled');
          const obj: any = new Object();
          obj.ruc = this.ruc;
          obj.correo = r.controls.CorreoEle.value;
          this.datosIdenService.actualizarCorreoProveedor(obj).subscribe((res: any)=>{
              if(res.flag){
                  this.cookie.deleteAll("/recuperar/password/validacion");
                  this.cookie.set('email_prov',  r.controls.CorreoEle.value, 0, '/');
                  this.cookie.set('checkCaptcha', "ok",  0.00083, '/');
                  window.location.href = document.querySelector('base').href+"recuperar/password/validacion";
              }else{
                  this.err2 = res.d;
              }
          }, err=>{
              console.log(err);
              this.err2 = `${err.status}: ${err.statusText}`;
          },()=>{
              btn.removeAttribute('disabled');
              setTimeout(()=>this.err2 = "", 10000);
          })
      }
  }

  removeSpaces(e){
      e.control.setValue(e.value.trim());
  }
}
