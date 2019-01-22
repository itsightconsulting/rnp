import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationComponent} from "./authentication/authentication.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {UpdPasswordComponent} from "./upd-password/upd-password.component";
import {FormDatosIdenComponent} from "./form-datos-iden/form-datos-iden.component";
import {BuscarEmpresaComponent} from "./buscar-empresa/buscar-empresa.component";

const routes: Routes = [
    {
        path: '',
        component: AuthenticationComponent,
        children: [
            {path: 'login', component: AuthenticationComponent}
         ]
    },
    {
        path: 'recuperar/password',
        component: ResetPasswordComponent
    },
    {
        path: 'recuperar/usuario/busqueda',
        component: BuscarEmpresaComponent
    },
    {
        path: 'recuperar/password/validar/datos-identificacion',
        component: FormDatosIdenComponent
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
