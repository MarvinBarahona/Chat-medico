import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';

import { ChatMessage } from './../clases/';

declare var $: any;

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  host: {'(window:beforeunload)':'abandonar()'},
  styles: [`
    #chat{
      height: 350px;
      overflow-y: scroll;
    }

    #chat div{
      border-radius: 25px;
      background-color: #F0F0F0;
      margin-bottom: 10px;
    }
  `]
})

export class ChatComponent implements OnInit, OnDestroy {
  id: number;
  myName: string;
  message: string;
  messages: ChatMessage[];
  sub: any;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
    this.messages = [];
    this.myName = this.stompService.getUser().name;
  }

  ngOnInit() {
    setTimeout(()=>{
      this.sub = this.stompService.getStomp().subscribe('/topic/chat/'+this.id, (message: ChatMessage) => {
		this.messages.push(message);
		setTimeout(()=>{
			$('#chat').scrollTop($('#chat').prop("scrollHeight"));
		}, 100);
      });

      this.stompService.sendWithUser("/app/joinChat/"+this.id, "Unirse");
    }, 1000);
  }

  ngOnDestroy(){
	  this.sub.unsubscribe();
	  this.stompService.sendWithUser('/app/leaveChat/'+this.id, "Abandonar");
  }

  enviar(){
    if(this.message != null && this.message != ""){
      this.stompService.sendWithUser("/app/chat/"+this.id, this.message);
      this.message = "";
    }
  }
  
  abandonar(){
	this.stompService.sendWithUser('/app/leaveChat/'+this.id, "Abandonar");
  }
}
