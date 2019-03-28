import {Component, OnInit} from '@angular/core';
import {UpdPasswordService} from "./upd-password.service";
import {CookieService} from "ngx-cookie-service";
import {default as Hashids} from "hashids";
import {Utilitarios} from "../utilitarios";

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
  initFormActive = true;
  err = "";
  codVerCorrecto = false;
  err2 = "";
  claveDto: any;
  scssMsg: any;
  err3 = "";
  finalScssMsg = false;
  overlayAlert: any;

  constructor(private updPasswordService: UpdPasswordService, private cookie: CookieService, private utilitarios: Utilitarios) {
      this.mostrarContenidoByCondiciones();
  }

  mostrarContenidoByCondiciones(){
      const hashIds = new Hashids("its_rnp_seg_mod", 32);
      const expDateParam = this.utilitarios.$urlParam("de");
      const rucParam = this.utilitarios.$urlParam("key");
      const codParam = this.utilitarios.$urlParam("cd");
      if(expDateParam != null && rucParam != null && codParam != null){
        const rucDecode = hashIds.decode(rucParam)[0];
        const expDateDecode = hashIds.decode(expDateParam)[0];
        const codDecode = hashIds.decode(codParam)[0];
        if(rucDecode != null && expDateDecode != null && codDecode != null){
            const t = new Date().getTime();
            if(String(rucDecode).length == 11 && t < expDateDecode && String(codDecode).length == 6){
                if(this.cookie.check('checkCodeVerif')){
                    const cod = this.cookie.get('checkCodeVerif');
                    if(cod == codDecode){
                        window.location.href = document.querySelector('base').href+"login";
                    }
                }
                this.initFormActive = false;
            }else{
                window.location.href = document.querySelector('base').href+"login";
            }
        }else{
            window.location.href = document.querySelector('base').href+"login";
        }
      }else{
          if(!this.cookie.check('checkCaptcha')){
              window.location.href = document.querySelector('base').href+"recuperar/password";
          }
      }

      //http://127.0.0.1:4200/recuperar/password/validacion?de=jYbn8W3vaOVRrg43ymkzBN2AgMJ4BwE5&key=j7blEJK4D51N09oX80MqPMyW2dpXmn6o
  }

  ngOnInit() {}

  enviarCorreoCodVerificacion(evt){
    const hashids = new Hashids("its_rnp_seg_mod", 32);
    const expiration = hashids.encode(new Date().getTime()+7200000);

    const btn = evt.target;
    btn.setAttribute('disabled', 'disabled');
    btn.textContent = 'Cargando...';
    this.preCorreo = {};
    this.preCorreo.correo = this.cookie.get('email_prov');
    this.preCorreo.ruc = this.cookie.get('ruc_prov');
    this.preCorreo.expiration = expiration;
    this.updPasswordService.enviarCorreoCodVer(this.preCorreo).subscribe((res: any) =>{
        if(res.flag){
            this.initFormActive = false;
            this.overlayAlert = document.querySelector('#overlayAlert');
            this.overlayAlert.style.display = 'block';
            this.cookie.delete('checkCaptcha', '/');
        } else {
            this.err = res.d;
        }
    },err=>{
        console.log(err);
    },
     ()=>{
        btn.removeAttribute('disabled');
        btn.textContent = 'ENVIAR';
        setTimeout(()=>this.err = "",6000);
    });
  }

    comprobarCodVerificacion(r, evt){
        if(r.valid){
            const btn = evt.target;
            btn.setAttribute('disabled', 'disabled');
            const ruc = this.cookie.get('ruc_prov');
            const code = r.controls.CodVerificacion.value;
            this.updPasswordService.checkCodigoVerificacion(ruc, code).subscribe((res: any)=>{
                    if(res.flag){
                        this.cookie.set('cod_ver_prov', code, 0, '/');
                        this.cookie.set('checkCodeVerif', code, 0.083, '/');
                        this.codVerCorrecto = true;
                    }else{
                        this.err2 = res.d;
                    }
                },err=>{
                    console.log(err);
                    this.err2 = `${err.status}: ${err.statusText}`;
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
                    if(res.flag){
                        this.scssMsg = res.d;
                        this.finalScssMsg = true;
                    } else {
                        this.err3 = res.d;
                    }
                },
                err=>{
                    console.log(err);
                    this.err3 = `${err.status}: ${err.statusText}`;
                },
                ()=>{
                    btn.removeAttribute('disabled');
                    setTimeout(()=>this.err3 = "", 6000);
                })
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
