import { Component, OnInit } from '@angular/core';

declare var $ : any;

@Component({
  templateUrl: './paciente-root.component.html',
})

export class  PacienteRootComponent implements OnInit {
  constructor() {}
  ngOnInit() {
    $("#toogle_menu").sideNav({ closeOnClick: true });
  }
}
