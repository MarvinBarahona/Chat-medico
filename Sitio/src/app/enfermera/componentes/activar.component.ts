import { Component, OnInit, OnDestroy, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { User } from './../../login/';

import { MaterializeDirective, MaterializeAction } from "angular2-materialize";

@Component({
  templateUrl: './activar.component.html',
  styles: [`
    .modal{
      height: 300px;
      width: 500px;
    }
  `]
})

export class ActivarComponent implements OnInit, OnDestroy {
  users: User[];
  id: number;
  sub: any;
  user: User;

  modalActivar = new EventEmitter<string | MaterializeAction>();

  constructor(private router: Router, private stompService: MyStompService) {
    this.users = [];

    this.id = this.stompService.getUser().id;
  }

  ngOnInit() {
    setTimeout(()=>{
      this.sub = this.stompService.getStomp().subscribe('/topic/getUsersResponse/' + this.id, (users: User[]) => {
        users = users.filter((user: User)=>{
          return (user.role == "paciente" || user.role == "medico");
        });

        this.users = users;
      });

      this.stompService.sendWithUser("/app/getUsers/" + this.id, "Recuperar");
    }, 2000);
  }

  guardar(){
    this.user.active = true;
    this.stompService.sendWithUser("/app/saveUser/" + this.id, this.user);
    this.closeModal();
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

  openModal(user: User) {
    this.user = user;
    this.modalActivar.emit({ action: "modal", params: ['open'] });
  }

  closeModal() {
    this.modalActivar.emit({ action: "modal", params: ['close'] });
  }


}
