import { User } from './../login/clases';

export class Message{
  user: User;
  message: any;

  constructor(user: User, message: any){
    this.user = user;
    this.message = message;
  }
}
