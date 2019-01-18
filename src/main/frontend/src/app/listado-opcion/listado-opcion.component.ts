import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import {ListadoOpcionService} from "./listado-opcion.service";

@Component({
  selector: 'app-listado-opcion',
  templateUrl: './listado-opcion.component.html',
  styleUrls: ['./listado-opcion.component.css']
})
export class ListadoOpcionComponent implements OnInit {
  private opcs: string[];
  private opcElegida: number = 1;
  private err: string;
  constructor(private cookie: CookieService, private listadoOpcService: ListadoOpcionService) {
      this.opcs = [
          "Correo electrónico de contacto",
          "Correo electrónico de representante",
          "Clave SOL-SUNAT",
          "Datos de identificación"
        ];
  }

  ngOnInit() {
  }

  obtenerCorreo(btn){
      let ruc = this.cookie.get('ruc_prov');
      this.listadoOpcService.getCorreoByRuc(ruc).subscribe(
          (x: any)=> {
          if(x.flag){
              console.log('success:',x.d);
              this.cookie.set('email_prov', x.d);
              window.location.href = '/recuperar/password/validacion';
          }else{
              btn.classList.remove('disabled');
              this.err = x.d;
          }
      },err=>{
          this.err = err.status + ": "+err.statusText;
          btn.classList.remove('disabled');
      }, ()=>{
              setTimeout(()=>this.err = "", 4000);
      });
  }

  obtenerCorreoRepresentantes(btn){
      let ruc = this.cookie.get('ruc_prov');
      this.listadoOpcService.getCorreoRepresentantesByRuc(ruc).subscribe(
          (x: any)=> {
              console.log(x);
              if(x.flag){
                if(x.d.length==1){
                    this.cookie.set('email_prov', x.d[0].correoRepresentante);
                    //window.location.href = '/recuperar/password/validacion';
                }else{
                }
              }else{
                  this.err = x.d;
              }
          },err=>{
              this.err = err.status + ": "+err.statusText;
              btn.classList.remove('disabled');
          }, ()=>{
              setTimeout(()=>this.err = "", 4000);
          });
  }

  enviarOpcion(evt){
    let btn = evt.target;
    if(!btn.classList.contains('disabled')){
        btn.classList.add('disabled');
        if(this.opcElegida == 1){
            this.obtenerCorreo(btn);
        }

        if(this.opcElegida == 2){
            this.obtenerCorreoRepresentantes(btn);
        }
    }
  }


}
