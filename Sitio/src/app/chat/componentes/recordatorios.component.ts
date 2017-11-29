import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Conference } from './../clases/';

declare var $: any;

@Component({
  selector: 'recordatorios',
  templateUrl: './recordatorios.component.html',
  styles: []
})

export class RecordatoriosComponent implements OnInit {
  conferences: Conference[];
  subscriptions: any[];
  id: number;
  schema: string;
  role: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.conferences = [];

    this.subscriptions = [];
    this.id = this.stompService.getUser().id;
    this.schema = this.stompService.getUser().schema;
    this.role = this.stompService.getUser().role;
  }

  ngOnInit() {
    setTimeout(() => {
      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/getConferencesResponse/' + this.id, (conferences: Conference[]) => {
        this.conferences = conferences;
      }));

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

      this.stompService.sendWithUser("/app/getConferences/" + this.id, "Conectado");
    }, 2000);
  }

  goToConference(id: string){
    this.router.navigate(['/'+this.role+'/chats/'+id]);
  }
}
