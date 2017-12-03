import { Component} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MyStompService } from './../../stompService/';

@Component({
  templateUrl: './chat.component.html',
  host: {'(window:beforeunload)':'ngOnDestroy()'},
  styles: []
})

export class ChatComponent{
  id: number;

  constructor(private router: Router, private route: ActivatedRoute, private stompService: MyStompService) {
    this.id = this.route.snapshot.params['id'];
  }

  ngOnDestroy(){
    this.stompService.sendWithUser('/app/removeChat/'+this.id, "Remover");
  }
}
