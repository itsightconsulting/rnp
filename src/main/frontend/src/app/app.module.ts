import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TasksComponent} from './tasks/tasks.component';
import {TasksAddComponent} from './tasks/tasks-add/tasks-add.component';
import {TasksListComponent} from './tasks/tasks-list/tasks-list.component';
import {HttpClient, HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthenticationComponent} from './authentication/authentication.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {CookieService} from "ngx-cookie-service";
import {NuevaPasswordComponent} from './nueva-password/nueva-password.component';
import {OnStartUpComponent} from './on-start-up/on-start-up.component';
import {OnStartUpService} from "./on-start-up/on-start-up.service";
import {HttpRequestInterceptor} from "./http-request-interceptor.component";
import {FormsModule} from "@angular/forms";
import { ListadoOpcionComponent } from './listado-opcion/listado-opcion.component';
import { UpdPasswordComponent } from './upd-password/upd-password.component';
import {EqualValidator} from "./upd-password/equal-validator.directive";
import { FormDatosIdenComponent } from './form-datos-iden/form-datos-iden.component';
import {DateValidator} from "./form-datos-iden/date-validator.directive";
import { ResetUserComponent } from './reset-user/reset-user.component';

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    TasksAddComponent,
    TasksListComponent,
    AuthenticationComponent,
    ResetPasswordComponent,
    NuevaPasswordComponent,
    OnStartUpComponent,
    ListadoOpcionComponent,
    UpdPasswordComponent,
    EqualValidator,
    DateValidator,
    FormDatosIdenComponent,
    ResetUserComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [OnStartUpService, HttpClient, {
      provide: APP_INITIALIZER,
      useFactory: (osu: OnStartUpService) => function(){
            osu.instanceApiToken();
      },
      deps: [OnStartUpService],
      multi: true
  }, CookieService,
      {
          provide: HTTP_INTERCEPTORS,
          useClass: HttpRequestInterceptor,
          multi: true
      }],
  bootstrap: [AppComponent]
})
export class AppModule { }
