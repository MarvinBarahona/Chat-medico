import { User } from './../login/clases';

export class Message{
  user: User;
  object: any;

  constructor(user: User, object: any){
    this.user = user;
    this.object = object;
  }
}
