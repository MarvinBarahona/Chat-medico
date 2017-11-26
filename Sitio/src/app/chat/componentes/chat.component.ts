import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';

import { ChatMessage } from './../clases/';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styles: []
})

export class ChatComponent implements OnInit, OnDestroy {
  id: number;
  message: string;
  messages: ChatMessage[];
  sub: any;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
    this.messages = [];
  }

  ngOnInit() {
    setTimeout(()=>{
      this.sub = this.stompService.getStomp().subscribe('/topic/chat/'+this.id, (message: ChatMessage) => {
        this.messages.push(message);
      });

      this.stompService.sendWithUser("/app/joinChat/"+this.id, "Unirse");
    }, 1000);    
  }

  ngOnDestroy(){
	  this.sub.unsubscribe();
  }

  enviar(){
    this.stompService.sendWithUser("/app/chat/"+this.id, this.message);
  }
}
