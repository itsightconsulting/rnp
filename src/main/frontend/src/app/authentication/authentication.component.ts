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
        /*const ruc = document.getElementById('RUC').value;
        const pass = document.getElementById('Clave').value;*/
        /*const params = new Object();
        params.ruc = "aaa";
        params.clave = "bbb";*/
        const bodyCredentials = JSON.stringify({ruc: "RucEnDuro", clave: "ClaveEnDuro"});
        this.authenticationService.authProcess(bodyCredentials).subscribe(()=>{
            alert('Usted se ha autenticado satisfactoriamente...');
        });
    }

}
