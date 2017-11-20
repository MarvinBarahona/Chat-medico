import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './usuarios.component.html',
  styles: []
})

export class UsuariosComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
