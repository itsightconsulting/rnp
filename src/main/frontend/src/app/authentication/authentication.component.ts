import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "./authentication.service";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

    autenticacion(){
        const ruc = document.getElementById('RUC');
        const pass = document.getElementById('Clave');
        let val = false;
        if(ruc.value.length == 11) {
            val = true;
        }else{
            alert('ruc debe tener 11 dígitos');
            val = false;
        }
        if(pass.value.length>0) {
            val = true;
        }else{
            alert('Ingrese una password');
            val = false;
        }

        if(val){
            this.authenticationService.authProcess(ruc.value, pass.value).subscribe((d) => {
                if(d.flag){
                    window.location.href = "/recuperar/password"
                }else{
                    alert('Las credenciales ingresadas son incorrectas');
                }
            });

        }
    }
}
