import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';

import { MedicoRoutingModule } from './medico-routing.module';
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
    MedicoRoutingModule
  ],
  declarations: [
    c.MedicoRootComponent,
    c.AtenderComponent,
    c.ChatComponent,
    c.FichaComponent,
    c.HistorialComponent,
    c.ConversatorioComponent
  ],
  providers: []
})
export class MedicoModule { }
