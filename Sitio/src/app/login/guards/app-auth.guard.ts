/*
*Nombre del guard: app-auth
*Dirección: /src/app/login/guards/app-auth.guard.ts
*Objetivo: No permite acceder a la información sin loguearse.
*/

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';

import { CookieService } from 'ngx-cookie';

@Injectable()
export class AppAuthGuard implements CanActivate {
  constructor(private router: Router, private cookieService: CookieService) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let res = false;
    let user = this.cookieService.getObject('user');

    // Si no hay token, redirigir al logueo.
    if (!user) {
      this.router.navigate(['/login']);
    }
    else {
      res = user['role'] == next.data['role'];
      if (!res) this.router.navigate(['/error403']);
    }

    return res;
  }
}
