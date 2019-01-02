import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TasksComponent } from './tasks/tasks.component';
import { TasksAddComponent } from './tasks/tasks-add/tasks-add.component';
import { TasksListComponent } from './tasks/tasks-list/tasks-list.component';
import {TaskService} from "./tasks/task.service";
import {HttpClient, HttpClientModule, HttpHeaders} from "@angular/common/http";
import { AuthenticationComponent } from './authentication/authentication.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {CookieService} from "ngx-cookie-service";

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    TasksAddComponent,
    TasksListComponent,
    AuthenticationComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [TaskService, HttpClient, {
      provide: APP_INITIALIZER,
      useFactory: (ts: TaskService) => function(){
            ts.instanceApiToken();
      },
      deps: [TaskService],
      multi: true
  }, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
