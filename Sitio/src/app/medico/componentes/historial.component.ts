import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'historial',
  templateUrl: './historial.component.html',
  styles: []
})

export class HistorialComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
