import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';
import { NgDatepickerModule } from 'ng2-datepicker';

import { AdminRoutingModule } from './admin-routing.module';
import * as c from './componentes';

import { ChatModule } from './../chat';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    MaterializeModule,
    NgDatepickerModule,
    CookieModule.forChild(),
    ChatModule,
    AdminRoutingModule
  ],
  declarations: [
    c.AdminRootComponent,
    c.ConversatoriosComponent,
    c.PacienteComponent,
    c.MedicoComponent,
    c.UsuariosComponent,
    c.NuevoPacienteMasivoComponent,
    c.NuevoMedicoMasivoComponent,
    c.NuevoPacienteIndividualComponent,
    c.NuevoMedicoIndividualComponent
  ],
  providers: []
})
export class AdminModule { }
