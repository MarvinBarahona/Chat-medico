import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { CookieService } from 'ngx-cookie';
import { StompService } from 'ng2-stomp-service';

import { User, Login } from './../clases';

declare var Materialize: any;

@Component({
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
  login: Login;
  errorMessage: string;
  message: string;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cookieService: CookieService,
    private stomp: StompService
  ) {
    this.login = new Login();
	
	this.id = (new Date).getMilliseconds();

    // Configuración del cliente stomp.
    this.stomp.configure({
      host: 'http://localhost:8080/hello',
      debug: false,
      queue: { 'init': false }
    });
  }

  ngOnInit() {
    // Luego de conectarse.
    this.stomp.startConnect().then(() => {
      this.stomp.done('init');

      // Suscribirse a la respuesta exitosa.
      this.stomp.subscribe('/topic/loginResponse/'+this.id,
        (user: User) => {
          this.cookieService.putObject("user", user);
		  this.stomp.disconnect();
          this.router.navigate(["/"+user.role]);
        }
      );

      // Suscribirse a la respuesta fallida
      this.stomp.subscribe('/topic/loginResponse/error/'+this.id,
        (e: string) => {
          this.message = null;
          this.errorMessage = e;
        }
      );
    });
  }

  logueo() {
    this.errorMessage = null;
    this.message = "Iniciando sesión...";

    // Mandar mensaje al bus.
    this.stomp.send('/app/login/'+this.id, this.login);
  }
}
