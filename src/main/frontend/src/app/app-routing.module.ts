import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationComponent} from "./authentication/authentication.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {UpdPasswordComponent} from "./upd-password/upd-password.component";
import {FormDatosIdenComponent} from "./form-datos-iden/form-datos-iden.component";
import {BuscarEmpresaComponent} from "./buscar-empresa/buscar-empresa.component";
import {ResetUserComponent} from "./reset-user/reset-user.component";
import {ListadoOpcionComponent} from "./listado-opcion/listado-opcion.component";
import {ErrorApiComponent} from "./error-api/error-api.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {ValidacionSsoComponent} from "./validacion-sso/validacion-sso.component";
import {UpdCorreoComponent} from "./upd-correo/upd-correo.component";

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
        path: 'recuperar/usuario',
        component: ResetUserComponent
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
        path: 'recuperar/password/opcs',
        component: ListadoOpcionComponent
    },
    {
        path: 'informativo',
        component: ErrorApiComponent
    },
    {
        path: 'validacion/via/sunat/:itscur/:itsnekot',
        component: ValidacionSsoComponent,
    },
    {
        path: 'actualizacion/correo',
        component: UpdCorreoComponent,
    },
    {
        path: '**',
        component: NotFoundComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
