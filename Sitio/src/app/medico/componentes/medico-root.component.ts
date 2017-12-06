import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import 'rxjs/add/operator/filter';

declare var $: any;

@Component({
  templateUrl: './medico-root.component.html',
})

export class MedicoRootComponent implements OnInit {

  constructor(private router: Router) { }
  ngOnInit() {

    $("#toogle_menu").sideNav({ closeOnClick: true });
  }
}
