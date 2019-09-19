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
  ruc: string;
  maxTime: string;
  ip: string;

  constructor(private readonly validacionSsoService: ValidacionSsoService, private readonly cookie: CookieService) { }

  ngOnInit() {
      this.validarTknRuc();
  }

  validarTknRuc(){
    this.urlParts = window.location.href.split("/");
    const lenUrl = this.urlParts.length;
    this.ruc = this.urlParts[lenUrl-3];
    this.maxTime = this.urlParts[lenUrl-2];
    this.ip = this.urlParts[lenUrl-1];

    const hashids = new Hashids("its_sunat_sso", 32);
    const rucHash = hashids.decode(this.ruc)[0];
    const maxTimeHash = this.maxTime;
    const maxTime = hashids.decode(this.maxTime)[0];
    const ipCliente = atob(""+this.ip);

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
