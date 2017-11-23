import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import 'rxjs/add/operator/filter';

@Component({
  templateUrl: './medico-root.component.html',
})

export class  MedicoRootComponent implements OnInit {
  chateando: boolean;

  constructor(private router: Router) { }
  ngOnInit() {
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .subscribe((event)=>{
        this.chateando = this.router.url.includes("chats/");
      });
  }
}
