import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { DatepickerOptions } from 'ng2-datepicker';

import { MyStompService } from './../../stompService/';
import { Patient } from './../../clases/';

declare var $: any;

@Component({
  templateUrl: './nuevo-paciente-masivo.component.html',
  styles: []
})

export class NuevoPacienteMasivoComponent implements OnInit, OnDestroy {
  patients: Patient[];
  id: number;
  subscription: any;
  message: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.patients = [];
    this.id = this.stompService.getUser().id;
  }

  ngOnInit() {
    setTimeout(() => {
      this.subscription = this.stompService.getStomp().subscribe('/topic/savePatientsResponse/' + this.id, (patients: Patient[]) => {
        this.router.navigate(["/admin/usuarios"]);
      });
    }, 1000);
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  cargar(event: any) {
    let reader = new FileReader();
    let file = event.target.files[0];

    if(file.name.endsWith(".csv")){
      // Cada vez que se carga una imagen, visualizarla y asignarla al objeto catálogo.
      reader.onload = (e: any) => {
        let src = e.target.result;
        let data = $.csv.toObjects(src);

        this.patients = [];
        data.forEach((d)=>{
          let patient = new Patient;
          patient.name = d["name"];
          patient.code = d["code"];
          patient.birthday = new Date(d["birthdayStr"]);
          this.patients.push(patient)
        });
      };

      reader.readAsText(file, 'ISO-8859-1');
    }

    if(file.name.endsWith(".json")){
      // Cada vez que se carga una imagen, visualizarla y asignarla al objeto catálogo.
      reader.onload = (e: any) => {
        let src = e.target.result;
        let data = JSON.parse(src);

        this.patients = [];
        data.forEach((d)=>{
          let patient = new Patient;
          patient.name = d["name"];
          patient.code = d["code"];
          patient.birthday = new Date(d["birthdayStr"]);
          this.patients.push(patient)
        });
      };

      reader.readAsText(file, 'ISO-8859-1');
    }
  }

  registrar(){
    this.message = "Creando...";

    // Mandar mensaje al bus.
    this.stompService.sendWithUser('/app/savePatients/'+this.id, this.patients);
  }

}
