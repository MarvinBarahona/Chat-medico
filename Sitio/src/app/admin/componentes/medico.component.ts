import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Doctor } from './../../clases/';

@Component({
  templateUrl: './medico.component.html',
  styles: []
})

export class MedicoComponent implements OnInit {
  id: number;
  doctor: Doctor;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
  }

  ngOnInit() {
    setTimeout(() => {
      let sub = this.stompService.getStomp().subscribe('/topic/getDoctorResponse/' + this.id, (doctor: Doctor) => {
        this.doctor = doctor;
        sub.unsubscribe();
      });

      this.stompService.sendWithUser('/app/getDoctor/' + this.id, "Obtener");
    }, 1000);
  }
}
