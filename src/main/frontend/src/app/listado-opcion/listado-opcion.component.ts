import {Component, Input, OnInit, Output} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ListadoOpcionService} from "./listado-opcion.service";
import { Representante} from "./representante";

@Component({
  selector: 'app-listado-opcion',
  templateUrl: './listado-opcion.component.html',
  styleUrls: ['./listado-opcion.component.css']
})
export class ListadoOpcionComponent implements OnInit {

  existsLstRepre: boolean;
  opcs: string[];
  opcElegida = 1;
  mailRepElegido = "1";
  err: string;
  @Input()
  ruc: string = this.cookie.check('ruc_prov') ? this.cookie.get('ruc_prov') : "";
  lstRepresentante: any[];
  repSelected: any;
  valMsgNoRecuperado = false;
  hrefRouteValidacion = "recuperar/password/validacion";
  passCaptcha = "ok";

  constructor(private cookie: CookieService, private listadoOpcService: ListadoOpcionService) {
      this.opcs = [
          "Correo electrónico de contacto",
          "Correo electrónico de representante",
          "Clave SOL-SUNAT",
          "Datos de identificación"
      ];
  }

  ngOnInit() {
      this.existsLstRepre = false;
      setTimeout(()=>{
          const opcs = this.cookie.get('my_opcs_recover_password');
          if(opcs != undefined && this.isJsonString(opcs)){
              const obj = JSON.parse(opcs);
              const opcsValues =  Object.keys(obj).map(key=>obj[key]);
              for(var i=0; i<opcsValues.length; i++){
                  if(opcsValues[i] == "NO") {
                      document.querySelectorAll('label.group-rbt')[i].classList.add('hidden');
                  }
              }
          }
          document.querySelector('#OpcsPass').classList.toggle('hidden');
      }, 1);
  }

  obtenerCorreo(btn){
      const ruc = this.cookie.get('ruc_prov');
      this.listadoOpcService.getCorreoByRuc(ruc).subscribe(
          (x: any)=> {
          if(x.flag){
              this.cookie.set('email_prov', x.d, 0, '/');
              this.cookie.set('checkCaptcha', this.passCaptcha,  0.00083, '/');
              window.location.href = document.querySelector('base').href+this.hrefRouteValidacion;
          }else{
              btn.classList.remove('disabled');
              this.err = x.d;
          }
      },err=>{
          this.err = `${err.status}: ${err.statusText}`;
          btn.classList.remove('disabled');
      }, ()=>{
              setTimeout(()=>this.err = "", 6000);
      });
  }

  obtenerCorreoRepresentantes(btn){
      const ruc = this.cookie.get('ruc_prov');
      this.listadoOpcService.getCorreoRepresentantesByRuc(ruc).subscribe(
          (x: any)=> {
              if(x.flag){
                if(x.d.length==1){
                    this.cookie.set('email_prov', x.d[0].correoRepresentante, 0, '/');
                    this.cookie.set('checkCaptcha', this.passCaptcha,  0.083, '/');
                    window.location.href = document.querySelector('base').href+this.hrefRouteValidacion;
                }else{
                    this.existsLstRepre = true;
                    this.lstRepresentante = Array.from(new Set(x.d.map(v=>JSON.stringify(v)))).map((v: any)=>JSON.parse(v)).map(v=>
                        new Representante(
                            null,
                            null,
                            null,
                            v.correoRepresentante,
                            v.correoFormateado));
                }
              }else{
                  this.err = x.d;
              }
          },err=>{
              this.err = `${err.status}: ${err.statusText}`;
          }, ()=>{
              btn.classList.remove('disabled');
              setTimeout(()=>this.err = "", 6000);
          });
  }

  enviarOpcion(evt){
    const btn = evt.target;
    if(!btn.classList.contains('disabled')){
        btn.classList.add('disabled');
        if(this.opcElegida == 1){
            console.log(1);
            this.obtenerCorreo(btn);
        }

        if(this.opcElegida == 2){
            this.obtenerCorreoRepresentantes(btn);
        }

        if(this.opcElegida == 3){
            window.location.href = 'https://200.123.25.107/auth-sunatsol/externaluserauth?opt=loginExt';
        }

        if(this.opcElegida == 4){
            window.location.href = document.querySelector('base').href+'recuperar/password/validar/datos-identificacion';
        }
    }
  }

  elegirCorreoRep(){
      if(this.mailRepElegido == "1"){
        this.repSelected = document.querySelector('input.rbt-reps');
        this.cookie.set('email_prov', this.repSelected.getAttribute('data-correo-rep'), 0, '/');
      }else{
          this.cookie.set('email_prov', this.mailRepElegido, 0, '/');
      }
      this.cookie.set('checkCaptcha', this.passCaptcha,  0.083, '/');
      window.location.href = document.querySelector('base').href+this.hrefRouteValidacion;
  }

  msgNoRecuperado() {
    this.valMsgNoRecuperado = true;
  }

  isJsonString(str) {
    try {
        const json = JSON.parse(str);
        return (typeof json === 'object');
    } catch (e) {
        return false;
    }
  }
}
