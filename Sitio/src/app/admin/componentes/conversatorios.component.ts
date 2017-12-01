import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { DatepickerOptions } from 'ng2-datepicker';

import { MyStompService } from './../../stompService/';
import { Conference } from './../../chat/';

declare var $: any;

@Component({
  templateUrl: './conversatorios.component.html',
  styles: []
})

export class ConversatoriosComponent implements OnInit, OnDestroy {
  newConference: Conference;
  conferences: Conference[];
  dpOptions: DatepickerOptions = {};

  subscriptions: any[];
  id: number;
  schema: string;
  time: string;

  constructor(private router: Router, private stompService: MyStompService) {
    this.dpOptions = {
      minYear: 2017,
      maxYear: 2025,
      displayFormat: 'DD/MM/YYYY',
      barTitleFormat: 'MMMM YYYY'
    }

    this.newConference = new Conference;
	  this.newConference.date = new Date;

    this.subscriptions = [];
    this.id = this.stompService.getUser().id;
    this.schema = this.stompService.getUser().schema;
  }

  ngOnInit() {
    setTimeout(() => {
      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/getConferencesResponse/' + this.id, (conferences: Conference[]) => {
        this.conferences = conferences;
        setTimeout(()=>{
          $('input.timepicker').timepicker({
            timeFormat: 'HH:mm',
            change: (time: Date) => { this.time = time.getHours() + ":00";},
            interval: 60,
            minHour: 7,
            maxHour: 21
          });
        }, 500);        
      }));

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/addConference/' + this.schema, (conference: Conference) => {
        this.conferences.push(conference);
        this.newConference = new Conference;
        this.newConference.date = new Date;
        this.time = "";
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

  guardar(){
    let i = this.time.indexOf(":");
    let t = this.time.slice(0, i);
    this.newConference.date.setHours(Number.parseInt(t));
    this.stompService.sendWithUser("/app/newConference/"+this.id, this.newConference);
  }

  eliminar(conference: Conference){
    this.stompService.sendWithUser("/app/removeConference/"+this.id, conference);
  }

  ngOnDestroy(){
    this.subscriptions.forEach((sub)=>{sub.unsubscribe();});
  }
}
