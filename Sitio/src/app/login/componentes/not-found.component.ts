import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'not-found',
  templateUrl: './not-found.component.html',
  styles: [`
    #error404{
      width: 100%;
    }

    .card-panel{
      margin-top: 30px;
    }
  `]
})
export class NotFoundComponent implements OnInit {
  constructor() { }
  ngOnInit() {}
}
