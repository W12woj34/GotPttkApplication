import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-back-bar',
  templateUrl: './back-bar.component.html',
  styleUrls: ['./back-bar.component.css']
})
export class BackBarComponent implements OnInit {

  @Input() mainComponent;

  constructor() { }

  click() {
    this.mainComponent.goBack();
  }

  ngOnInit() {
  }

}
