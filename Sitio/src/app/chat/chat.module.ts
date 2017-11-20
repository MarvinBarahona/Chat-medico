import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';

import * as c from './componentes';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    MaterializeModule,
    CookieModule.forChild()
  ],
  declarations: [
    c.ChatComponent,
    c.RecordatoriosComponent
  ],
  exports:[
    c.ChatComponent,
    c.RecordatoriosComponent
  ],
  providers: []
})
export class ChatModule { }
