import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { CookieService } from 'ngx-cookie';
import { StompService } from 'ng2-stomp-service';

import { Usuario, Login } from './../clases';

declare var Materialize: any;

@Component({
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
  login: Login;
  returnUrl: string;
  errorMessage: string;
  message: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cookieService: CookieService,
    private stomp: StompService
  ) {
    this.login = new Login();

    // Configuración del cliente stomp.
    this.stomp.configure({
      host: 'http://localhost:8080/hello',
      debug: false,
      queue: { 'init': false }
    });
  }

  ngOnInit() {
    // Captura el siguiente URL
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/home';

    // Luego de conectarse.
    this.stomp.startConnect().then(() => {
      this.stomp.done('init');

      // Suscribirse a la respuesta exitosa.
      this.stomp.subscribe('/topic/loginResponse',
        (user: Usuario) => {
          Materialize.toast(user.nombre, 3000, 'toastSuccess');
        }
      );

      // Suscribirse a la respuesta fallida
      this.stomp.subscribe('/topic/loginResponse/error',
        (e: string) => {
          Materialize.toast(e, 3000, 'toastError');
        }
      );
    });
  }

  logueo() {
    this.errorMessage = undefined;
    this.message = "Iniciando sesión...";

    // Mandar mensaje al bus.
    this.stomp.send('/app/login', this.login);
  }
}
