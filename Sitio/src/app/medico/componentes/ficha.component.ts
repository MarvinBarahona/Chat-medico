import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';

import { Consultation, Diagnostic, Prescription, Reference } from './../../clases/';

@Component({
  selector: 'ficha',
  templateUrl: './ficha.component.html',
  styles: []
})

export class FichaComponent{
  @Input() consultation: Consultation;
  addingPrescription: boolean = false;
  addingReference: boolean = false;

  constructor(private router: Router,  private stompService: MyStompService) {}

  addPrescription(){
    this.consultation.prescription = new Prescription;
    this.addingPrescription = true;
  }

  addReference(){
    this.consultation.reference = new Reference;
    this.addingReference = true;
  }

  cancelPrescription(){
    this.addingPrescription = false;
    this.consultation.prescription = null;
  }

  cancelReference(){
    this.addingReference = false;
    this.consultation.reference = null;
  }

  registrar(){
    // Mandar mensaje al bus.
    this.stompService.sendWithUser('/app/saveConsultation', this.consultation);
	  this.router.navigate(['/medico/atender']);
  }
}
