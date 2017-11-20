import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';

import { AdminRoutingModule } from './admin-routing.module';
import * as c from './componentes';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    MaterializeModule,
    CookieModule.forChild(),
    AdminRoutingModule
  ],
  declarations: [
    c.AdminRootComponent,
    c.PreferenciasComponent,
    c.ConversatoriosComponent,
    c.UsuarioComponent,
    c.UsuariosComponent,
    c.NuevoUsuarioMasivoComponent,
    c.NuevoUsuarioIndividualComponent
  ],
  providers: []
})
export class AdminModule { }
