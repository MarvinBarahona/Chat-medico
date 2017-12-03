import { Injectable } from '@angular/core';
import { StompService } from 'ng2-stomp-service';
import { CookieService } from 'ngx-cookie';

import { User } from './../login/clases';
import { Message } from './';

@Injectable()
export class MyStompService{

  constructor(private stomp: StompService, private cookieService: CookieService){
    //Configuración del cliente stomp.
    this.stomp.configure({
      host: 'http://localhost:8080/hello',
      debug: true,
      queue: { 'init': false }
    });

    this.stomp.startConnect().then(() => {
      this.stomp.done('init');
    });
  }

  getStomp(){
    return this.stomp;
  }

  getUser(){
	let _user = this.cookieService.getObject('user');
    let user = new User;
    user.id = _user['id'];
    user.name= _user['name'];
    user.officeName = _user['officeName'];
    user.schema = _user['schema'];
    user.role = _user['role'];

    return user;
  }

  send(url: string, message: any){
    this.stomp.send(url, message);
  }

  sendWithUser(url: string, message: any){
    this.stomp.send(url, new Message(this.getUser(), message));
  }
}
