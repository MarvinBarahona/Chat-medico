import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import * as c from './componentes';
import { AppAuthGuard } from './../login'

const routes: Routes = [
  {
    path: 'medico',
    component: c.MedicoRootComponent,
    children: [
      {
        path: '',
        redirectTo: '/medico/atender',
        pathMatch: 'full'
      },
      {
        path: 'chats',
        component: c.AtenderComponent,
        // canActivate: [AppAuthGuard],
        // data: {politica: 143}
      },
      {
        path: 'chats/:id',
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
export class MedicoRoutingModule { }
