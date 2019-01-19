import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationComponent} from "./authentication/authentication.component";
import {TasksComponent} from "./tasks/tasks.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {NuevaPasswordComponent} from "./nueva-password/nueva-password.component";
import {UpdPasswordComponent} from "./upd-password/upd-password.component";
import {FormDatosIdenComponent} from "./form-datos-iden/form-datos-iden.component";

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
    },
    {
        path: 'cambio/password',
        component: NuevaPasswordComponent
    },
    {
        path: 'recuperar/password/validacion',
        component: UpdPasswordComponent
    },
    {
        path: 'recuperar/password/validar/datos-identificacion',
        component: FormDatosIdenComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
