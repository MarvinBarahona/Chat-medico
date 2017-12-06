import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { DatepickerOptions } from 'ng2-datepicker';

import { MyStompService } from './../../stompService/';
import { Doctor } from './../../clases/';

declare var $: any;

@Component({
  templateUrl: './nuevo-medico-masivo.component.html',
  styles: []
})

export class NuevoMedicoMasivoComponent implements OnInit, OnDestroy {
  doctors: Doctor[];
  id: number;
  subscription: any;
  message: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.doctors = [];
    this.id = this.stompService.getUser().id;
  }

  ngOnInit() {
    setTimeout(() => {
      this.subscription = this.stompService.getStomp().subscribe('/topic/saveDoctorsResponse/' + this.id, (doctors: Doctor[]) => {
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

        this.doctors = [];
        data.forEach((d)=>{
          let doctor = new Doctor;
          doctor.name = d["name"];
          doctor.code = d["code"];
          doctor.speciality = d["speciality"];
          doctor.birthday = new Date(d["birthdayStr"]);
          this.doctors.push(doctor)
        });
      };

      reader.readAsText(file, 'ISO-8859-1');
    }

    if(file.name.endsWith(".json")){
      // Cada vez que se carga una imagen, visualizarla y asignarla al objeto catálogo.
      reader.onload = (e: any) => {
        let src = e.target.result;
        let data = JSON.parse(src);

        this.doctors = [];
        data.forEach((d)=>{
          let doctor = new Doctor;
          doctor.name = d["name"];
          doctor.code = d["code"];
          doctor.speciality = d["speciality"];
          doctor.birthday = new Date(d["birthdayStr"]);
          this.doctors.push(doctor)
        });
      };

      reader.readAsText(file, 'ISO-8859-1');
    }
  }

  registrar(){
    this.message = "Creando...";

    // Mandar mensaje al bus.
    this.stompService.sendWithUser('/app/saveDoctors/'+this.id, this.doctors);
  }

}
