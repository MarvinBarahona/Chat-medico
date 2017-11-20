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
        redirectTo: '/admin/preferencias',
        pathMatch: 'full'
      },
      {
        path: 'preferencias',
        component: c.PreferenciasComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'conversatorios',
        component: c.ConversatoriosComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'usuarios',
        component: c.UsuariosComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'usuarios/nuevoMasivo',
        component: c.NuevoUsuarioMasivoComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'usuarios/nuevoIndividual',
        component: c.NuevoUsuarioIndividualComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'usuarios/:id',
        component: c.UsuarioComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
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
