import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import 'rxjs/add/operator/filter';

declare var $ : any;

@Component({
  templateUrl: './medico-root.component.html',
})

export class  MedicoRootComponent implements OnInit {
  chateando: boolean;

  constructor(private router: Router) { }
  ngOnInit() {
	  
	$("#toogle_menu").sideNav({closeOnClick: true});
	
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .subscribe((event)=>{
        this.chateando = this.router.url.includes("chats/");
      });
  }
}
