import { Component} from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';

@Component({
  templateUrl: './solicitar.component.html',
  styles: []
})

export class SolicitarComponent{
  constructor(private router: Router, private stompService: MyStompService) {}

  solicitar(){
    let user = this.stompService.getUser();
    this.stompService.sendWithUser("/app/addChat/"+user.id,user.name);
    this.router.navigate(['/paciente/chats/'+user.id]);
  }
}
