import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MaterializeModule } from 'angular2-materialize';
import { DataTablesModule } from 'angular-datatables';
import { Angular2FontawesomeModule } from 'angular2-fontawesome/angular2-fontawesome'
import { CookieModule } from 'ngx-cookie';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { StompService } from 'ng2-stomp-service';

import { AdminModule } from './admin/admin.module';
import { EnfermeraModule } from './enfermera/enfermera.module';
import { PacienteModule } from './paciente/paciente.module';
import { MedicoModule } from './medico/medico.module';
import { LoginModule } from './login';
import { MyStompService } from './stompService/';

@NgModule({
  imports: [
    BrowserModule,
    MaterializeModule,
    DataTablesModule,
    Angular2FontawesomeModule,
    CookieModule.forRoot(),
    LoginModule,
    AdminModule,
    EnfermeraModule,
    PacienteModule,
    MedicoModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent
  ],
  providers: [StompService, MyStompService],
  bootstrap: [AppComponent]
})
export class AppModule { }
