import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Patient } from './../../clases/';

@Component({
  templateUrl: './paciente.component.html',
  styles: []
})

export class PacienteComponent implements OnInit {
  id: number;
  patient: Patient;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
  }

  ngOnInit() {
    setTimeout(() => {
      let sub = this.stompService.getStomp().subscribe('/topic/getPatientResponse/' + this.id, (patient: Patient) => {
        this.patient = patient;
        sub.unsubscribe();
      });

      this.stompService.sendWithUser('/app/getPatient/' + this.id, "Obtener");
    }, 1000);
  }
}
