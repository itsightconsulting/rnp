import { Component, OnInit } from '@angular/core';
import {FormDatosIdenService} from "./form-datos-iden.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-form-datos-iden',
  templateUrl: './form-datos-iden.component.html',
  styleUrls: ['./form-datos-iden.component.css']
})
export class FormDatosIdenComponent implements OnInit {

  ruc: string = this.cookie.check('ruc_prov') ? this.cookie.get('ruc_prov') : "";
  lstPais: any[];
  lstTipoDoc: any[];
  lstZonaReg: any[];
  lstTipoCon: any[];
  datosIdenDto: any;
  hoy: Date;
  dtMin: Date;
  err = "";
  err2 = "";
  scssValidacion: boolean;

  constructor(private readonly datosIdenService: FormDatosIdenService, private readonly cookie: CookieService) { }

  ngOnInit() {
      this.obtenerPais();
      this.obtenerTipoDocumeneto();
      this.obtenerZonaRegistral();
      this.obtenerTipoCondicion();
      this.hoy =new Date();
      this.dtMin = new Date(1908,12,1);
      this.scssValidacion = false;
  }

  obtenerPais(){
      this.datosIdenService.obtenerForaneas('P').subscribe((res: any)=>{
          this.lstPais = res.d;
      })
  }

  obtenerTipoDocumeneto(){
      this.datosIdenService.obtenerForaneas('D').subscribe((res: any)=>{
          this.lstTipoDoc = res.d;
      })
  }

  obtenerZonaRegistral(){
      this.datosIdenService.obtenerForaneas('Z').subscribe((res: any)=>{
          this.lstZonaReg = res.d;
      })
  }

  obtenerTipoCondicion(){
      this.datosIdenService.obtenerForaneas('C').subscribe((res: any)=>{
          this.lstTipoCon = res.d;
      })
  }

  numberOnly(event): boolean {
      const charCode = (event.which) ? event.which : event.keyIdentifier;
      if (charCode > 31 && (charCode < 48 || charCode > 57)) {
          return false;
      }
      return true;
  }

  validarDatos(r, evt){
      if(r.valid) {
          const btn = evt.target;
          btn.setAttribute('disabled', 'disabled');
          this.datosIdenDto = {};
          this.datosIdenDto.ruc = this.ruc;
          this.datosIdenDto.paisId = r.controls.Pais.value;
          this.datosIdenDto.tipoDocuId = r.controls.TipoDoc.value;
          this.datosIdenDto.desDocu = r.controls.NumeroDocumento.value;
          this.datosIdenDto.zonaRegistralId = r.controls.ZonaReg.value === "" ? 0 : r.controls.ZonaReg.value;
          this.datosIdenDto.nroPartida = r.controls.NroPartidaEle.value;
          const dt = new Date(r.controls.FechaIngreso.value);
          this.datosIdenDto.fecIngreso = new Date(new Date(dt.getTime()+1000*60*60*24));//+1 día para normalizar la fecha a la de hoy
          this.datosIdenDto.fecIngreso = new Date(this.datosIdenDto.fecIngreso.toDateString());
          this.datosIdenDto.tipoCondicionId = r.controls.TipoCon.value;
          this.datosIdenService.checkDatosIdentificacion(this.datosIdenDto).subscribe((res: any)=>{
                  if(res.flag){
                      this.scssValidacion = true;
                  }else{
                      this.err = res.d;
                  }
              },
              err=>{
                  this.err = `${err.status}: ${err.statusText}`;
              },
              ()=>{
                  btn.removeAttribute('disabled');
                  setTimeout(()=>this.err = "", 6000);
              })
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
                  this.cookie.deleteAll("recuperar/password/validacion");
                  this.cookie.set('email_prov',  r.controls.CorreoEle.value, 0, '/');
                  this.cookie.set('checkCaptcha', "ok",  0.00083, '/');
                  window.location.href = document.querySelector('base').href+'recuperar/password/validacion';
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

  backLogin(){
      window.location.href = document.querySelector('base').href+"login";
  }

  removeSpaces(e){
      e.control.setValue(e.value.trim());
  }
}
