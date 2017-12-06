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
        path: 'medicos/nuevoMasivo',
        component: c.NuevoMedicoMasivoComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'medicos/nuevoIndividual',
        component: c.NuevoMedicoIndividualComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'pacientes/nuevoMasivo',
        component: c.NuevoPacienteMasivoComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'pacientes/nuevoIndividual',
        component: c.NuevoPacienteIndividualComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'medicos/:id',
        component: c.MedicoComponent,
        canActivate: [AppAuthGuard],
        data: {role: "admin"}
      },
      {
        path: 'pacientes/:id',
        component: c.PacienteComponent,
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
