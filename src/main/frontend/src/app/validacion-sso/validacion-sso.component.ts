import {Component, OnInit} from '@angular/core';
import {default as Hashids} from "hashids";
import {CookieService} from "ngx-cookie-service";
import {ValidacionSsoService} from "./validacion-sso.service";

@Component({
  selector: 'app-validacion-sso',
  templateUrl: './validacion-sso.component.html',
  styleUrls: ['./validacion-sso.component.css']
})
export class ValidacionSsoComponent implements OnInit {

  urlParts: string[];
  sunatToken: string;
  maxTime: string;
  ip: string;
  initRuc: string;

  constructor(private readonly validacionSsoService: ValidacionSsoService, private readonly cookie: CookieService) { }

  ngOnInit() {
      this.validarTknRuc();
  }

  validarTknRuc(){

    this.urlParts = window.location.href.split("/");
    const lenUrl = this.urlParts.length;
    this.sunatToken = this.urlParts[lenUrl-3];
    this.maxTime = this.urlParts[lenUrl-2];
    this.ip = this.urlParts[lenUrl-1];
    this.initRuc = this.cookie.get('ruc_prov');

      //
    const hashids = new Hashids("its_sunat_sso", 32);
    const rucHash = JSON.parse(atob(this.sunatToken.split(".")[1])).userdata.numRUC;
    const maxTimeHash = this.maxTime;
    const maxTime = hashids.decode(this.maxTime)[0];
    const ipCliente = atob(""+this.ip);

    if(rucHash != this.initRuc){
        const msg = btoa("Estimado usuario, hemos recibido su solicitud para recuperar su clave RNP, " +
            "a través de la opción Clave SOL-SUNAT; sin embargo, advertimos que intenta hacerlo con un RUC diferente " +
            "con el que inició el procedimiento. En ese sentido, hemos procedido a interrumpir su solicitud. " +
            "Inicie nuevamente el procedimiento.");
        window.location.href = document.querySelector('base').href+"informativo/"+msg;
        return;
    }

    if(maxTime>0){
        const maxTimeYear = new Date(maxTime).getFullYear();
        const todayYear = new Date().getFullYear();
        const itsEmitCheck = this.cookie.check('its_emit');
        if(itsEmitCheck){
            const itsEmit = this.cookie.get('its_emit');
            if(itsEmit === maxTimeHash){
                window.location.href = document.querySelector('base').href+"login";
            }
        }

        this.cookie.set('its_emit', maxTimeHash, 0,'/');
        if(rucHash>0 && String(rucHash).length === 11 && maxTime>0 && maxTimeYear === todayYear && ipCliente) {
            this.validacionSsoService.auditoriaActualizacionFromSsoSunat({
                ipCliente: ipCliente,
                ruc: rucHash
            }).subscribe((d: any) => {
                    this.cookie.set('its_cur', String(rucHash),  0.00083, '/');
                    window.location.href = document.querySelector('base').href+"actualizacion/correo";
                },
                err => {
                    window.location.href = document.querySelector('base').href+"login";
                },
                ()=>{}
            );

        } else {
            window.location.href = document.querySelector('base').href+"login";
        }
    } else {
        window.location.href = document.querySelector('base').href+"login";
    }
  }
}
