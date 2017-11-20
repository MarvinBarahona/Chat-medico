import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styles: []
})

export class ChatComponent implements OnInit {
  constructor(private router: Router) { }
  ngOnInit() {
  }
}
