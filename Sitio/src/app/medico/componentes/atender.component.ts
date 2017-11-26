import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';

import { Chat } from './../../chat/';

@Component({
  templateUrl: './atender.component.html',
  styles: []
})

export class AtenderComponent implements OnInit, OnDestroy {
  id: number;
  schema: string;
  chats: Chat[];
  subscriptions: any[];

  constructor(private router: Router, private stompService: MyStompService) {
    this.id = (new Date).getMilliseconds();
    this.subscriptions = [];
    this.schema = this.stompService.getUser().schema;
	this.chats = [];
  }

  ngOnInit() {
    setTimeout(()=>{
      let sub = this.stompService.getStomp().subscribe('/topic/getChatsResponse/'+this.id, (chats: Chat[]) => {
          this.chats = chats;
          sub.unsubscribe();
      });

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/addChat/'+this.schema, (chat: Chat) => {
        let chatInArray = this.chats.find((_chat)=>{return _chat.id == chat.id});
        if(!chatInArray){
          this.chats.push(chat);
        }
        else{
          chatInArray.state = chat.state;
        }
      }));

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/removeChat/'+this.schema, (id: string) => {
        let chatInArray = this.chats.find((_chat)=>{return _chat.id == id});
        if(chatInArray){
          let i = this.chats.indexOf(chatInArray);
          this.chats.splice(i, 1);
        }
      }));

      this.stompService.sendWithUser("/app/getChats/"+this.id, "Conectado");
    }, 1000);
  }

  ngOnDestroy(){
    this.subscriptions.forEach((sub)=>{sub.unsubscribe();});
  }
}
