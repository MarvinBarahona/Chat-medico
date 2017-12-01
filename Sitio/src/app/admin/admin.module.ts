import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';
import { NgDatepickerModule } from 'ng2-datepicker';

import { AdminRoutingModule } from './admin-routing.module';
import * as c from './componentes';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    MaterializeModule,
    NgDatepickerModule,
    CookieModule.forChild(),
    AdminRoutingModule
  ],
  declarations: [
    c.AdminRootComponent,
    c.ConversatoriosComponent,
    c.UsuarioComponent,
    c.UsuariosComponent,
    c.NuevoUsuarioMasivoComponent,
    c.NuevoUsuarioIndividualComponent
  ],
  providers: []
})
export class AdminModule { }
