import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import * as c from './componentes';
import { AppAuthGuard } from './../login'

const routes: Routes = [
  {
    path: 'admin',
    component: c.AdminRootComponent,
    children: [
      {
        path: '',
        redirectTo: '/admin/usuarios',
        pathMatch: 'full'
      },
      {
        path: 'conversatorios',
        component: c.ConversatoriosComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'usuarios',
        component: c.UsuariosComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'usuarios/nuevoMasivo',
        component: c.NuevoUsuarioMasivoComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'usuarios/nuevoIndividual',
        component: c.NuevoUsuarioIndividualComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'usuarios/:id',
        component: c.UsuarioComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [AppAuthGuard]
})
export class AdminRoutingModule { }
