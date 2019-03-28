import { Component, OnInit } from '@angular/core';
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
  tkn: string;

  constructor(private cookie: CookieService) { }

  ngOnInit() {
      this.validarTknRuc();
  }

  validarTknRuc(){
    this.urlParts = window.location.href.split("/");
    const lenUrl = this.urlParts.length;
    this.tkn = this.urlParts[lenUrl-1];
    this.ruc = this.urlParts[lenUrl-2];
    const hashids = new Hashids("its_sunat_sso", 32);
    if(hashids.decode(this.ruc.substr(0,32)).length > 0 && hashids.decode(this.ruc.substr(32)).length > 0) {
        this.cookie.set('its_netok', this.tkn);
        this.cookie.set('its_cur', this.ruc);
        window.location.href = document.querySelector('base').href+"actualizacion/correo";
    } else {
        window.location.href = document.querySelector('base').href+"login";
    }
  }
}
