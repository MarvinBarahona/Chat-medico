import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'ficha',
  templateUrl: './ficha.component.html',
  styles: []
})

export class FichaComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
