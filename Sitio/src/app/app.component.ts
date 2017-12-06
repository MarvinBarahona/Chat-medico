import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import 'rxjs/add/operator/filter';

import { CookieService } from 'ngx-cookie';
import { MyStompService } from './stompService/';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  usuario: string;
  office: string;

  constructor(private router: Router, private cookieService: CookieService, private stompService: MyStompService) { }

  ngOnInit() {
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .subscribe((event) => {
        let u = this.cookieService.getObject('user');
        if (u){
			this.usuario = u['name'];
			this.office = u['officeName'];
		}
        else{
			this.usuario = null;
			this.office = "Chat médico";
		}
    });
  }

  cerrarSesion() {
    this.router.navigate(["/login"]);
  }

  ngOnDestroy(){
    this.stompService.getStomp().disconnect();
  }
}
