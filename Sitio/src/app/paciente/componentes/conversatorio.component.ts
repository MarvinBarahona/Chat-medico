import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { Chat } from './../../chat/';

@Component({
  templateUrl: './conversatorio.component.html',
  styles: []
})

export class ConversatorioComponent implements OnInit {
  id: number;
  chat: Chat;
  subscriptions: any[];

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
    this.chat = new Chat;
    this.subscriptions = [];
  }

  ngOnInit() {
    setTimeout(() => {
      let sub = this.stompService.getStomp().subscribe("/topic/getChatResponse/" + this.id, (chat: Chat) => {
        this.chat = chat;
        sub.unsubscribe();
      });

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/addChat/' + this.schema, (chat: Chat) => {
        if (chat.id == this.id) this.chat = chat;
      }));

      this.subscriptions.push(this.stompService.getStomp().subscribe('/topic/removeChat/' + this.schema, (chat: Chat) => {
        if (chat.id == this.id) this.chat = new Chat;
      }));

      this.stompService.sendWithUser('/app/getChat/' + this.id, "Iniciar");
    }, 1000);
  }

  ngOnDestroy() {
    this.subscriptions.forEach((sub) => { sub.unsubscribe(); });
  }
}
