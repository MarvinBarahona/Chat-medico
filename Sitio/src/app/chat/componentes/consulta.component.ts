import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

import { Consultation } from './../../clases/';

@Component({
  selector: 'consulta',
  templateUrl: './consulta.component.html',
  styles: []
})

export class ConsultaComponent{
  @Input() consultation: Consultation;

  constructor() { }
}
