import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'recordatorios',
  templateUrl: './recordatorios.component.html',
  styles: []
})

export class RecordatoriosComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
