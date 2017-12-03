import { Component, OnInit, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MaterializeDirective, MaterializeAction } from "angular2-materialize";

import { MyStompService } from './../../stompService/';

@Component({
  templateUrl: './chat.component.html',
  styles: []
})

export class ChatComponent implements OnInit{
  id: number;
  modalFicha = new EventEmitter<string | MaterializeAction>();

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
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
}
