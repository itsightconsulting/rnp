import {Component, OnInit} from '@angular/core';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-error-api',
  templateUrl: './error-api.component.html',
  styleUrls: ['./error-api.component.css']
})
export class ErrorApiComponent implements OnInit {

    urlParts: string[];
    ruc: string;
    existsCustomMessage = false;
    message: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
      this.checkWildCardMessage();
  }

  checkWildCardMessage(){
      this.urlParts = window.location.href.split("/");
      const wildcardmessage = this.route.snapshot.paramMap.get("wildcardmessage");
      if(wildcardmessage){
          this.existsCustomMessage = true;
          this.message = atob(wildcardmessage);
      }
  }
}
