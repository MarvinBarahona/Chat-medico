import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { CookieService } from 'ngx-cookie';

import { MyStompService } from './../../stompService/';
import { User, Login } from './../clases';

declare var Materialize: any;

@Component({
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit, OnDestroy {
  login: Login;
  errorMessage: string;
  message: string;
  id: number;
  subscriptions: any[];

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private stompService: MyStompService
  ) {
    this.login = new Login();
    this.id = (new Date).getMilliseconds();
    this.subscriptions = [];    
    this.cookieService.removeAll();
  }

  ngOnInit() {
    setTimeout(()=>{
      this.subscriptions.push(
        this.stompService.getStomp().subscribe('/topic/loginResponse/'+this.id,(user: User) => {
          this.cookieService.putObject("user", user);
          this.router.navigate(["/"+user.role]);
      }));

      this.subscriptions.push(
        this.stompService.getStomp().subscribe('/topic/loginResponse/error/'+this.id,(e: string) => {
          this.message = null;
          this.errorMessage = e;
      }));
    }, 1000)
  }

  ngOnDestroy(){
    this.subscriptions.forEach((sub)=>{sub.unsubscribe();});
  }

  logueo() {
    this.errorMessage = null;
    this.message = "Iniciando sesión...";

    // Mandar mensaje al bus.
    this.stompService.send('/app/login/'+this.id, this.login);
  }


}
