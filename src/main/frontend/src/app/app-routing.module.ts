import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationComponent} from "./authentication/authentication.component";
import {TasksComponent} from "./tasks/tasks.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";

const routes: Routes = [
    {
        path: '',
        component: AuthenticationComponent,
        children: [
            {path: 'login', component: AuthenticationComponent}
         ]
    },
    {
        path: 'tasks',
        component: TasksComponent
    },
    {
        path: 'recuperar/password',
        component: ResetPasswordComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
