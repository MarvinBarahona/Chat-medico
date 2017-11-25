import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';

@Component({
  templateUrl: './atender.component.html',
  styles: []
})

export class AtenderComponent implements OnInit {
  id: number;
  subscriptions: any[];

  constructor(private router: Router, private stompService: MyStompService) {
    this.id = (new Date).getMilliseconds();
    this.subscriptions = [];
  }

  ngOnInit() {
    this.subscriptions.push(
      this.stompService.getStomp().subscribe('/topic/chatsInit/'+this.id,(r:any) => {
        console.log(r);
    }));

    this.stompService.sendWithUser("/app/chats/"+this.id, "Conectado");
  } 
}
