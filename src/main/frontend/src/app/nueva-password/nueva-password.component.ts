import { Component, OnInit } from '@angular/core';
import {NuevaPasswordService} from "./nueva-password.service";

@Component({
  selector: 'app-nueva-password',
  templateUrl: './nueva-password.component.html',
  styleUrls: ['./nueva-password.component.css']
})
export class NuevaPasswordComponent implements OnInit {

  constructor(private nuevaPasswordService : NuevaPasswordService) { }

  ngOnInit() {
      this.validarProcesoDeCambioPassword();
  }
  validarProcesoDeCambioPassword(){
      this.nuevaPasswordService.validacionCambioPassword().subscribe((res: any)=>{
        if(res.flag){
            console.log("Este usuario se encuentra en el proceso de cambio de password");
        }else
            window.location.href= "/login";
      },
      (err: any)=>{
          console.log(err);
      })
  }
}
