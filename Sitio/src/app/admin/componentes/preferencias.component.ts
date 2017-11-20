import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './preferencias.component.html',
  styles: []
})

export class PreferenciasComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
