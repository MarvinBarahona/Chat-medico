import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { User } from './../../login/';

@Component({
  templateUrl: './usuarios.component.html',
  styles: []
})

export class UsuariosComponent implements OnInit, OnDestroy {
  users: User[];
  id: number;
  sub: any;

  constructor(private router: Router, private stompService: MyStompService) {
    this.users = [];

    this.id = this.stompService.getUser().id;
  }

  ngOnInit() {
    setTimeout(()=>{
      this.sub = this.stompService.getStomp().subscribe('/topic/getUsersResponse/' + this.id, (users: User[]) => {
        this.users = users;
      });

      this.stompService.sendWithUser("/app/getUsers/" + this.id, "Recuperar");
    }, 2000);
  }

  guardar(user: User){
    this.stompService.sendWithUser("/app/saveUser/" + this.id, user);
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

}
