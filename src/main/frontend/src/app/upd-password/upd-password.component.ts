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
    ruc: string = this.cookie.check('ruc_prov') ? this.cookie.get('ruc_prov'): "";
    correo: string = this.cookie.check('email_prov')? this.cookie.get('email_prov'): "";
    correoFormatter: string = this.correo.slice(0,4)+ '*'.repeat(this.correo.indexOf('@')-4)+ this.correo.slice(this.correo.indexOf('@'));
    initFormActive: boolean = true;
    err: string = "";
    codVerCorrecto: boolean = false;
    err2: string = "";
    claveDto: any;
    scssMsg: any;
    err3: string = "";
    finalScssMsg: boolean = false;
    overlayAlert: any;
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
    this.updPasswordService.enviarCorreoCodVer(this.preCorreo).subscribe((res: any) =>{
        if(res.flag){
            this.initFormActive = false;
            this.overlayAlert = document.querySelector('#overlayAlert');
            this.overlayAlert.style.display = 'block'
        } else {
            this.err = res.d;
        }
    },err=>{
        console.log(err);
    },
     ()=>{
        btn.removeAttribute('disabled');
        btn.textContent = 'ENVIAR';
        setTimeout(()=>this.err = "",4000);
    });
  }

    comprobarCodVerificacion(r, evt){
        if(r.valid){
            const btn = evt.target;
            btn.setAttribute('disabled', 'disabled');
            let ruc = this.preCorreo.ruc = this.cookie.get('ruc_prov');
            this.updPasswordService.checkCodigoVerificacion(ruc, r.controls.CodVerificacion.value).subscribe((res: any)=>{
                    console.log(res);
                    if(res.flag){
                        this.cookie.set('cod_ver_prov', r.controls.CodVerificacion.value, 0, '/');
                        this.codVerCorrecto = true;
                    }else{
                        this.err2 = res.d;
                    }
                },err=>{
                    console.log(err);
                    this.err2 = err.status + ": "+err.statusText;
                },
                ()=>{
                    btn.removeAttribute('disabled');
            });
        }
    }

    actualizarClave(r, evt){
        if(r.valid) {
            const btn = evt.target;
            btn.setAttribute('disabled', 'disabled');
            this.claveDto = {};
            this.claveDto.ruc = this.ruc;
            this.claveDto.correo = this.correo;
            this.claveDto.clave = r.controls.Clave.value;
            this.claveDto.codVerificacion = this.cookie.get('cod_ver_prov');
            this.updPasswordService.actualizarClave(this.claveDto).subscribe((res: any)=>{
                    console.log(res);
                    if(res.flag){
                        this.scssMsg = res.d;
                        this.finalScssMsg = true;
                    } else {
                        this.err3 = res.d;
                    }
                },
                err=>{
                    console.log(err);
                    this.err3 = err.status + ": "+err.statusText;
                },
                ()=>{
                    btn.removeAttribute('disabled');
                    setTimeout(()=>this.err3 = "", 4000);
                })
        }
    }
}
