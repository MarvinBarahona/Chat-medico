import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './nuevo-usuario-individual.component.html',
  styles: []
})

export class NuevoUsuarioIndividualComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
