import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./authentication.service";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
  btnSubmit: any;
  constructor(private authenticationService: AuthenticationService) {
  }
  objJson: any;
  verificacion: boolean;
  msgLogin: string = "";
  opcRecuperacion: number = 1;
  msgInformacionUsuario: string = "";
  private flagSinUsuario: boolean = false;
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
                            window.location.href = "http://www.rnp.gob.pe/login.asp";
                            window.localStorage.setItem('rnp_login_obj', JSON.stringify({"status": 'ok', "usuario": frm.controls.Ruc.value, "password": frm.controls.Clave.value}));
                        } else {
                            this.verificacion = d.flag;
                            this.msgLogin = d.d;
                            this.btnSubmit.classList.remove('disabled');
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
            this.activeSegForm=false;
            this.flagSinUsuario = true;
        }
    }

    showMsgUsuario(){
        this.msgInformacionUsuario = "Estimado proveedor, para personas naturales o jurídicas que son nacionales o extranjeros domiciliados en el Perú," +
         " su usuario RNP es el número de RUC proporcionado por la Superintendencia Nacional de Aduanas y de Administración Tributaria (SUNAT)";
         setTimeout(()=>{this.msgInformacionUsuario = ""}, 12000);
    }
}
