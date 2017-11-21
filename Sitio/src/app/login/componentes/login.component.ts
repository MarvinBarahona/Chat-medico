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

    this.stomp.configure({
      host: 'http://localhost:8080/hello',
      debug: false,
      queue: { 'init': false }
    });
  }

  // Método: logueo
  // Objetivo: Permite al usuario iniciar sesión
  logueo() {
    this.errorMessage = undefined;
    this.message = "Iniciando sesión...";

    this.stomp.send('/app/login', this.login);
  }

  ngOnInit() {
    // Captura el siguiente URL
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/home';

    this.stomp.startConnect().then(() => {
      this.stomp.done('init');

      //subscribe
      this.stomp.subscribe('/topic/loginResponse',
        (user: Usuario) => {
          Materialize.toast(user.nombre, 3000, 'toastSuccess');
        }
      );

      //subscribe
      this.stomp.subscribe('/topic/loginResponse/error',
        (e: string) => {
          Materialize.toast(e, 3000, 'toastError');
        }
      );
    });
  }
}
