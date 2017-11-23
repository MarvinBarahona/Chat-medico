import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import * as c from './componentes';
import { AppAuthGuard } from './../login'

const routes: Routes = [
  {
    path: 'paciente',
    component: c.PacienteRootComponent,
    children: [
      {
        path: '',
        redirectTo: '/paciente/chats',
        pathMatch: 'full'
      },
      {
        path: 'chats',
        component: c.SolicitarComponent,
        canActivate: [AppAuthGuard],
        data: {role: "paciente"}
      },
      {
        path: 'chats/:id',
        component: c.ChatComponent,
        canActivate: [AppAuthGuard],
        data: {role: "paciente"}
      },
      {
        path: 'conversatorios',
        component: c.ConversatoriosComponent,
        canActivate: [AppAuthGuard],
        data: {role: "paciente"}
      },
      {
        path: 'conversatorios/:id',
        component: c.ConversatorioComponent,
        canActivate: [AppAuthGuard],
        data: {role: "paciente"}
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [AppAuthGuard]
})
export class PacienteRoutingModule { }
