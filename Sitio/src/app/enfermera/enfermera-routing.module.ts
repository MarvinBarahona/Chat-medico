import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import * as c from './componentes';
import { AppAuthGuard } from './../login'

const routes: Routes = [
  {
    path: 'enfermera',
    component: c.EnfermeraRootComponent,
    children: [
      {
        path: '',
        redirectTo: '/enfermera/activar',
        pathMatch: 'full'
      },
      {
        path: 'activar',
        component: c.ActivarComponent,
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
export class EnfermeraRoutingModule { }
