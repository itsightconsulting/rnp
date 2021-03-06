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
  correo: string = this.cookie.check('email_prov') ? this.cookie.get('email_prov'): "";
  checkByRep: string = this.cookie.check('by_rep_mail') ? this.cookie.get('by_rep_mail'): "";
  correoFormatter: string = this.correo.length === 0 ? "":this.correo.slice(0,4)+ '*'.repeat(this.correo.indexOf('@')-4)+ this.correo.slice(this.correo.indexOf('@'));
  initFormActive = true;
  err = "";
  codVerCorrecto = false;
  err2 = "";
  claveDto: any;
  scssMsg: any;
  err3 = "";
  finalScssMsg = false;
  overlayAlert: any;
  titleForm = "Correo electrónico de contacto";

  constructor(private readonly updPasswordService: UpdPasswordService, private readonly cookie: CookieService, private readonly utilitarios: Utilitarios) {
      this.fnCheckByRep();
      this.mostrarContenidoByCondiciones();
  }

  fnCheckByRep(){
      if(this.checkByRep === "1"){
          this.titleForm = "Correo electrónico del representante";
          this.cookie.delete('by_rep_mail', '/');
      }
  }

  mostrarContenidoByCondiciones(){
      const hashIds = new Hashids("its_rnp_seg_mod", 32);
      const expDateParam = this.utilitarios.$urlParam("de");
      const rucParam = this.utilitarios.$urlParam("key");
      const codParam = this.utilitarios.$urlParam("cd");
      const lm = this.utilitarios.$urlParam("lm");

      if(expDateParam === null || rucParam === null || codParam ===null || lm === null){
          if(!this.cookie.check('checkCaptcha')){
              window.location.href = document.querySelector('base').href+"recuperar/password";
          }
          return;
      }
      const rucDecode = hashIds.decode(rucParam)[0];
      const expDateDecode = hashIds.decode(expDateParam)[0];
      const codDecode = hashIds.decode(codParam)[0];
      const mailDecode = atob(""+lm);
      if(rucDecode === null || expDateDecode === null || codDecode === null || !mailDecode.includes("@")){
          window.location.href = document.querySelector('base').href+"login";
      }
      const t = new Date().getTime();
      if(String(rucDecode).length !== 11 || t > expDateDecode || String(codDecode).length !== 6) {
          window.location.href = document.querySelector('base').href+"login";
      }

      if(this.cookie.check('checkCodeVerif')){
          const cod = this.cookie.get('checkCodeVerif');
          if(cod === codDecode){//Valida si ya no ha sido usado con anterioridad(cookie insertada after validación de un código específico)
              window.location.href = document.querySelector('base').href+"login";
          }
      }

      this.cookie.set('ruc_prov', rucDecode, 0, '/');
      this.ruc = rucDecode;
      this.correo = mailDecode;
      this.correoFormatter = this.correo.length === 0 ? "":this.correo.slice(0,4)+ '*'.repeat(this.correo.indexOf('@')-4)+ this.correo.slice(this.correo.indexOf('@'));
      this.initFormActive = false;
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

    backLogin(){
        window.location.href = document.querySelector('base').href+"login";
    }
}
