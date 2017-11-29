import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Conference, Chat } from './../clases/';

declare var $: any;

@Component({
  selector: 'recordatorios',
  templateUrl: './recordatorios.component.html',
  styles: []
})

export class RecordatoriosComponent implements OnInit, OnDestroy {
  conferences: Conference[];
  chats: Chat[];

  subscriptions: any[];
  id: number;
  schema: string;
  role: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.conferences = [];
    this.chats = [];

    this.subscriptions = [];
    this.id = this.stompService.getUser().id;
    this.schema = this.stompService.getUser().schema;
    this.role = this.stompService.getUser().role;
  }

  ngOnInit() {
    setTimeout(() => {
      let subConferences = this.stompService.getStomp().subscribe('/topic/getConferencesResponse/' + this.id, (conferences: Conference[]) => {
        this.conferences = conferences;
        subConferences.unsubscribe();
      });

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/addConference/' + this.schema, (conference: Conference) => {
        this.conferences.push(conference);
      }));

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/removeConference/' + this.schema, (conference: Conference) => {
        let conferenceInArray = this.conferences.find((_conference) => { return _conference.id == conference.id });
        if (conferenceInArray) {
          let i = this.conferences.indexOf(conferenceInArray);
          this.conferences.splice(i, 1);
        }
      }));

      let subChats = this.stompService.getStomp().subscribe('/topic/getStartedChatsResponse/' + this.id, (chats: Chat[]) => {
        this.chats = chats;
        subChats.unsubscribe();
      });

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/addStartedChat/' + this.schema, (chat: Chat) => {
        this.chats.push(chat);
      }));

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/removeStartedChat/' + this.schema, (chat: Chat) => {
        let chatInArray = this.chats.find((_chat) => { return _chat.id == chat.id });
        if (chatInArray) {
          let i = this.chats.indexOf(chatInArray);
          this.chats.splice(i, 1);
        }
      }));

      this.stompService.sendWithUser("/app/getConferences/" + this.id, "Conectado");
      this.stompService.sendWithUser("/app/getStartedChats/" + this.id, "Conectado");
    }, 2000);
  }

  agregar(conference: Conference){
    this.stompService.sendWithUser("/app/addChat/" + conference.id, conference.topic);
    this.router.navigate(['/'+this.role+'/conversatorios/'+conference.id]);
  }

  ngOnDestroy(){
    this.subscriptions.forEach((sub)=>{sub.unsubscribe();});
  }
}
