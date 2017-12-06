import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import { MyStompService } from './../../stompService/';
import { User } from './../../login/';

@Component({
  templateUrl: './usuarios.component.html',
  styles: []
})

export class UsuariosComponent implements OnInit, OnDestroy {
  doctors: User[];
  patients: User[];
  id: number;
  sub: any;

  constructor(private router: Router, private stompService: MyStompService) {
    this.id = this.stompService.getUser().id;
  }

  ngOnInit() {
    setTimeout(()=>{
      this.sub = this.stompService.getStomp().subscribe('/topic/getUsersResponse/' + this.id, (users: User[]) => {
        this.doctors = users.filter((user: User)=>{ return user.role == "medico";});
        this.patients = users.filter((user: User)=>{ return user.role == "paciente";});
      });

      this.stompService.sendWithUser("/app/getUsers/" + this.id, "Recuperar");
    }, 2000);
  }

  guardar(user: User){
    this.stompService.sendWithUser("/app/saveUser", user);
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

}
