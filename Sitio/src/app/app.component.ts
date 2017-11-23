import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import 'rxjs/add/operator/filter';

import { CookieService } from 'ngx-cookie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  usuario: string;

  constructor(private router: Router, private cookieService: CookieService) { }

  cerrarSesion() {
    this.cookieService.removeAll();
    this.router.navigate(["/login"]);
  }

  ngOnInit() {
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .subscribe((event) => {
        let u = this.cookieService.getObject('user');
        if (u) this.usuario = u['name'];
		else this.usuario = null;
      });
  }
}
