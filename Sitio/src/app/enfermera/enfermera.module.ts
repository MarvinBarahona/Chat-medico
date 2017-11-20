import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { CookieModule } from 'ngx-cookie';
import { MaterializeModule } from 'angular2-materialize';

import { EnfermeraRoutingModule } from './enfermera-routing.module';
import * as c from './componentes';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    MaterializeModule,
    CookieModule.forChild(),
    EnfermeraRoutingModule
  ],
  declarations: [
    c.EnfermeraRootComponent,
    c.ActivarComponent
  ],
  providers: []
})
export class EnfermeraModule { }
