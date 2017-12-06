import { Component, OnInit, EventEmitter, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MaterializeDirective, MaterializeAction } from "angular2-materialize";

import { MyStompService } from './../../stompService/';

import { Consultation, Patient, Doctor, Diagnostic } from './../../clases/';

@Component({
  templateUrl: './chat.component.html',
  styles: []
})

export class ChatComponent implements OnInit, OnDestroy{
  id: number;
  modalFicha = new EventEmitter<string | MaterializeAction>();
  consultation: Consultation;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
    this.consultation = new Consultation;
	  this.consultation.patient = new Patient;
    this.consultation.patient.id = this.id;
    this.consultation.doctor = new Doctor;
	  this.consultation.date = new Date;
    this.consultation.diagnostic = new Diagnostic;
    this.consultation.doctor.id = this.stompService.getUser().id;
  }

  ngOnInit(){
    setTimeout(()=>{
      this.stompService.sendWithUser('/app/startChat/'+this.id, "Iniciar");
    },1000);
  }

  terminar(){
    this.openFicha();
    this.stompService.sendWithUser('/app/endChat/'+this.id, "Terminar");
  }

  openFicha() {
    this.modalFicha.emit({ action: "modal", params: ['open'] });
  }

  closeFicha() {
    this.modalFicha.emit({ action: "modal", params: ['close'] });
  }

  ngOnDestroy(){
    this.closeFicha();
  }
}
