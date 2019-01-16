import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "./authentication.service";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
  btnSubmit: any;
  constructor(private authenticationService: AuthenticationService) { }
  credentials: any;
  objJson: any;
  verificacion: boolean;
  msgLogin: string = "";
  opcRecuperacion: number = 1;
  private activeSegForm: boolean = false;
  ngOnInit() {
    this.verificacion = true;
  }

    submit(frm, $event){
        this.btnSubmit = Array.from($event.target)[--Array.from($event.target).length];
        this.objJson = new Object();
        frm._directives.forEach((x,i) => {
            this.objJson[Object.keys(frm.value)[i].substr(0,1).toLocaleLowerCase() + Object.keys(frm.value)[i].substr(1)] = Object.values(frm.value)[i];
        });
        if(frm.valid) {
            if (!this.btnSubmit.classList.contains('disabled')) {
                this.btnSubmit.classList.add('disabled');
                this.authenticationService.authProcess(this.objJson).subscribe(
                    (d: any) => {
                        if (d.flag) {
                            //window.location.href = "/recuperar/password"
                            alert('Autenticación correcta: ' + JSON.stringify(d));
                            window.location.reload();
                        } else {
                            this.verificacion = d.flag;
                            this.msgLogin = d.d;
                            this.btnSubmit.classList.remove('disabled');
                            alert('Las credenciales ingresadas son incorrectas');
                        }
                    },
                    (error)=>{
                    this.btnSubmit.classList.remove('disabled');
                        console.log(error);
                });
            }
        }else
            for(let i in frm.controls){
                frm.controls[i].markAsTouched();
            }
    }

    log(x){
        //console.log(x);
    }

    numberOnly(event): boolean {
        const charCode = (event.which) ? event.which : event.keyIdentifier;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

    irOpcionRecuperacion(){
        if(this.opcRecuperacion == 1){
            window.location.href = "/recuperar/password";
        }else{
            window.location.href = "/recuperar/usuario";
        }
    }
}
