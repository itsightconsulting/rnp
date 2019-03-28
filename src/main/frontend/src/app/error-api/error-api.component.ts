import { Component, OnInit } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-error-api',
  templateUrl: './error-api.component.html',
  styleUrls: ['./error-api.component.css']
})
export class ErrorApiComponent implements OnInit {

  constructor(private cookie: CookieService, private http: HttpClient) { }

  ngOnInit() {
    //this.queryDemo();
  }

  queryDemo(){

      const headers =
          new HttpHeaders()
              .set('Authorization', "Basic " + btoa(`rnp_osce:itsight19@13`))
              .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

      const params =
          new HttpParams({
              fromObject: {
                  'grant_type': 'client_credentials',
              }
          });
      this.http.post('https://200.123.25.107/segrnp-svc/api/v1/oauth/token', params, {headers}).subscribe((d: any) => {console.log(d)});

  }

}
