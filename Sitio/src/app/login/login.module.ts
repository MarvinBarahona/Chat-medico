import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { CookieModule } from 'ngx-cookie';

import { LoginRoutingModule } from './login-routing.module';
import * as c from './componentes';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule,
    CookieModule.forChild(),
    LoginRoutingModule
  ],
  declarations: [
    c.LoginComponent,
    c.HomeComponent,
    c.RegistrarComponent,
    c.NotFoundComponent,
    c.NotAllowedComponent
  ],
  providers: []
})
export class LoginModule { }
