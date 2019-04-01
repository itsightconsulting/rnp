import {Component, OnInit} from '@angular/core';
import {default as Hashids} from "hashids";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-validacion-sso',
  templateUrl: './validacion-sso.component.html',
  styleUrls: ['./validacion-sso.component.css']
})
export class ValidacionSsoComponent implements OnInit {

  urlParts: string[];
  ruc: string;
  maxTime: string;

  constructor(private readonly cookie: CookieService) { }

  ngOnInit() {
      this.validarTknRuc();
  }

  validarTknRuc(){
    this.urlParts = window.location.href.split("/");
    const lenUrl = this.urlParts.length;
    this.ruc = this.urlParts[lenUrl-2];
    this.maxTime = this.urlParts[lenUrl-1];
    const hashids = new Hashids("its_sunat_sso", 32);
    const rucHash = hashids.decode(this.ruc)[0];
    const maxTime = hashids.decode(this.maxTime)[0];
    if(maxTime>0){
        const maxTimeYear = new Date(maxTime).getFullYear();
        const todayYear = new Date().getFullYear();
        if(rucHash>0 && String(rucHash).length === 11 && maxTime>0 && maxTimeYear === todayYear) {
            this.cookie.set('its_cur', String(rucHash),  0.00083, '/');
            window.location.href = document.querySelector('base').href+"actualizacion/correo";
        } else {
            window.location.href = document.querySelector('base').href+"login";
        }
    }
  }
}
