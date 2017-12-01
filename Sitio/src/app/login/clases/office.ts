import { Login } from './';

export class Office{
  id: number;
  name: string;
  schema: string;
  address: string;
  description: string;
  admin: Login;
  nurse: Login;
}
