import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './usuario.component.html',
  styles: []
})

export class UsuarioComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
