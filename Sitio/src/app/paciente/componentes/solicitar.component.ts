import { Component} from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';

@Component({
  templateUrl: './solicitar.component.html',
  styles: []
})

export class SolicitarComponent{
  id: number;

  constructor(private router: Router, private stompService: MyStompService) {
    this.id = (new Date).getMilliseconds();
  }

  solicitar(){
    this.stompService.sendWithUser("/app/chat/"+this.id,"Conectado");
    this.router.navigate(['/paciente/chats/'+this.id]);
  }
}
