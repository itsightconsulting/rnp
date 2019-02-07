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

  private existsLstRepre: boolean;
  private opcs: string[];
  private opcElegida: number = 1;
  private mailRepElegido: string = "1";
  private err: string;
  @Input()
  private ruc: string = this.cookie.check('ruc_prov') ? this.cookie.get('ruc_prov') : "";
  private lstRepresentante: any[];
  private repSelected: any;
  private valMsgNoRecuperado: boolean = false;

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
              for(let i=0; i<Object.values(JSON.parse(opcs)).length; i++){
                  Object.values(JSON.parse(opcs))[i] == "NO" ? document.querySelectorAll('label.group-rbt')[i].classList.add('hidden') : "";
              }
          }
          document.querySelector('#OpcsPass').classList.toggle('hidden');
      }, 1);
  }

  obtenerCorreo(btn){
      let ruc = this.cookie.get('ruc_prov');
      this.listadoOpcService.getCorreoByRuc(ruc).subscribe(
          (x: any)=> {
          if(x.flag){
              console.log('success:',x.d);
              this.cookie.set('email_prov', x.d, 0, '/');
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
                    this.cookie.set('email_prov', x.d[0].correoRepresentante, 0, '/');
                    window.location.href = '/recuperar/password/validacion';
                }else{
                    this.existsLstRepre = true;
                    this.lstRepresentante = x.d.map(v=>
                        new Representante(
                            null,
                            null,
                            null,
                            v.correoRepresentante,
                            v.correoFormateado));
                    console.log(this.lstRepresentante);
                }
              }else{
                  this.err = x.d;
              }
          },err=>{
              this.err = err.status + ": "+err.statusText;
          }, ()=>{
              btn.classList.remove('disabled');
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

        if(this.opcElegida == 3){
            this.err = "Este servicio no se encuentra disponible en este momento. Intentar nuevamente más tarde";
            btn.classList.remove('disabled');
            setTimeout(()=>this.err = "", 5500);
        }

        if(this.opcElegida == 4){
            window.location.href = '/recuperar/password/validar/datos-identificacion';
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
      window.location.href = '/recuperar/password/validacion';
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
