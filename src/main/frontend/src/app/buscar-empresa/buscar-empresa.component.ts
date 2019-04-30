import { Component, OnInit } from '@angular/core';
import {BuscarEmpresaService} from "./buscar-empresa.service";

@Component({
  selector: 'app-buscar-empresa',
  templateUrl: './buscar-empresa.component.html',
  styleUrls: ['./buscar-empresa.component.css']
})
export class BuscarEmpresaComponent implements OnInit {

  lstPais: any[];
  lstCoincidencias: any[] = new Array();
  buscado = false;
  err = "";

  constructor(private readonly buscarEmpresaService: BuscarEmpresaService) { }

  ngOnInit() {
    this.obtenerPais();
  }

  obtenerPais(){
    this.buscarEmpresaService.obtenerListadoPais().subscribe((res: any)=>{
        this.lstPais = res.d;
    })
  }

  buscar(r, evt){
    const btn = evt.target.tagName === "IMG" ? evt.target.parentElement : evt.target;
    if(r.valid){
        btn.setAttribute('disabled', 'disabled');
        this.buscarEmpresaService.obtenerEmpresas({paisId: r.controls.Pais.value, tipoPersonaId: r.controls.TipoPersona.value, razonSocial: r.controls.RazonSocial.value})
                .subscribe((res: any)=>{
            this.buscado = true;
            if(res.flag){
                this.lstCoincidencias = res.d;
            }else{
                this.lstCoincidencias = new Array();
            }
        },err=>{
                    console.log(err);
                    this.err = `${err.status}: ${err.statusText}`;
        },()=>{
                    btn.removeAttribute('disabled');
                    setTimeout(()=>this.err = "", 8000);
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
