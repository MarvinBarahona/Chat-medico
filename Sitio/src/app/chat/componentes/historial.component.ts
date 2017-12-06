import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Consultation } from './../../clases/';

@Component({
  selector: 'historial',
  templateUrl: './historial.component.html',
  styles: []
})

export class HistorialComponent implements OnInit {
  @Input() medico: boolean;
  id: number;
  consultations: Consultation[];
  consultation: Consultation;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
  }

  ngOnInit() {
    let q = this.medico ? 'Doctor' : 'Patient';

    setTimeout(() => {
      let sub = this.stompService.getStomp().subscribe('/topic/get' + q + 'HistoryResponse/' + this.id, (consultations: Consultation[]) => {
        this.consultations = consultations;
        sub.unsubscribe();
      });

      this.stompService.sendWithUser('/app/get' + q + 'History/' + this.id, "Obtener");
    }, 1000);
  }
}
