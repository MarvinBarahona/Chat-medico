import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { DatepickerOptions } from 'ng2-datepicker';

import { MyStompService } from './../../stompService/';
import { Doctor } from './../../clases/';

@Component({
  templateUrl: './nuevo-medico-individual.component.html',
  styles: []
})

export class NuevoMedicoIndividualComponent implements OnInit, OnDestroy {
  doctor: Doctor;
  dpOptions: DatepickerOptions = {};
  id: number;
  subscription: any;
  message: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.dpOptions = {
      minYear: 1900,
      maxYear: 2025,
      displayFormat: 'DD/MM/YYYY',
      barTitleFormat: 'MMMM YYYY'
    }

    this.doctor = new Doctor;
    this.doctor.birthday = new Date("1/1/1980");

    this.id = this.stompService.getUser().id;
  }

  ngOnInit() {
    setTimeout(() => {
      this.subscription = this.stompService.getStomp().subscribe('/topic/saveDoctorResponse/' + this.id, (doctor: Doctor) => {
        this.router.navigate(["/admin/usuarios"]);
      });
    }, 1000);
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  registrar() {
    this.message = "Creando...";

    // Mandar mensaje al bus.
    this.stompService.sendWithUser('/app/saveDoctor/'+this.id, this.doctor);
  }

}
