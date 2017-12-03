import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';

import { PacienteRoutingModule } from './paciente-routing.module';
import * as c from './componentes';

import { ChatModule } from './../chat';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    MaterializeModule,
    CookieModule.forChild(),
    ChatModule,
    PacienteRoutingModule
  ],
  declarations: [
    c.PacienteRootComponent,
    c.SolicitarComponent,
    c.ChatComponent,
    c.ConversatorioComponent
  ],
  providers: []
})
export class PacienteModule { }
