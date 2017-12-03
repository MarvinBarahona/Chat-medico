import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Office, Login } from './../clases';

@Component({
  templateUrl: './registrar.component.html',
  styles: []
})
export class RegistrarComponent implements OnInit, OnDestroy {
  office: Office;
  id: number;
  subscription: any;
  message: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.id = (new Date).getMilliseconds();
    this.office = new Office;
    this.office.admin = new Login;
    this.office.nurse = new Login;
  }

  ngOnInit() {
    setTimeout(()=>{
      this.subscription = this.stompService.getStomp().subscribe('/topic/createOfficeResponse/'+this.id,(office: Office) => {
          this.router.navigate(["/login"]);
      });
    }, 1000)
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  registrar() {
    this.message = "Creando...";

    this.office.schema = this.office.name.replace(/ /g,'')
    this.office.schema = this.normalizar(this.office.schema);

    // Mandar mensaje al bus.
    this.stompService.send('/app/createOffice/'+this.id, this.office);
  }

  normalizar(palabra: string): string {
    palabra = palabra.toLowerCase();
    let from = "ãàáäâèéëêìíïîòóöôùúüûÑñÇç";
    let to = "aaaaaeeeeiiiioooouuuunncc";
    let mapping = {};

    for (var i = 0, j = from.length; i < j; i++) mapping[from.charAt(i)] = to.charAt(i);

    let ret = [];

    for (var i = 0, j = palabra.length; i < j; i++) {
      let c = palabra.charAt(i);

      if (mapping.hasOwnProperty(palabra.charAt(i)))
        ret.push(mapping[c]);
      else
        ret.push(c);
    }
    return ret.join('');
  }
}
