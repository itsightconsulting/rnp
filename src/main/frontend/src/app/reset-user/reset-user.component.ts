import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reset-user',
  templateUrl: './reset-user.component.html',
  styleUrls: ['./reset-user.component.css']
})
export class ResetUserComponent implements OnInit {
  private opcRecuperacion: number = 1;
  private activeInitForm: boolean = true;
  private activeOpcion1: boolean = false;
  private lastMsgActive: boolean = false;
  constructor() { }

  ngOnInit() {
  }

  mostrarMensaje(){
    this.lastMsgActive = true;
      setTimeout(() => {
          this.lastMsgActive = false;
      }, 30000);
  }

  irOpcionRecuperacion(){
    if(this.opcRecuperacion == 1){
        this.activeOpcion1 = true;
        this.activeInitForm = false;
    }
  }

  submit(){
  }

}
