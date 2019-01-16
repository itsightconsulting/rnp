import {Component, OnInit} from '@angular/core';
import {UpdPasswordService} from "./upd-password.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-upd-password',
  templateUrl: './upd-password.component.html',
  styleUrls: ['./upd-password.component.css']
})
export class UpdPasswordComponent implements OnInit {
  preCorreo: any;
    ruc: string = this.cookie.get('ruc_prov');
    correo: string = this.cookie.get('email_prov');
    correoFormatter: string = this.correo.slice(0,4)+ '*'.repeat(this.correo.indexOf('@')-4)+ this.correo.slice(this.correo.indexOf('@'));
    initFormActive: boolean = true;
    err: string = "";
  constructor(private updPasswordService: UpdPasswordService, private cookie: CookieService) {
  }

  ngOnInit() {}

  enviarCorreoCodVerificacion(evt){
      const btn = evt.target;
      btn.setAttribute('disabled', 'disabled');
      btn.textContent = 'Cargando...';
      this.preCorreo = {};
      this.preCorreo.correo = this.cookie.get('email_prov');
      this.preCorreo.ruc = this.cookie.get('ruc_prov');
      this.preCorreo.ipCliente = "127.0.0.1";
    this.updPasswordService.checkCodigoVerificacion(this.preCorreo).subscribe((res: any) =>{
            console.log(res);
            if(res.flag){
                this.initFormActive = false;
            }else{
                this.err = res.d;
            }
    },err=>{
            console.log(err);
    },
        ()=>{
            btn.removeAttr('disabled');
        });
  }

}
