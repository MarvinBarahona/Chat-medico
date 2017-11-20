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
        redirectTo: '/paciente/solicitar',
        pathMatch: 'full'
      },
      {
        path: 'solicitar',
        component: c.SolicitarComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'chat/:id',
        component: c.ChatComponent,
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
        path: 'conversatorios/:id',
        component: c.ConversatorioComponent,
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
export class PacienteRoutingModule { }
