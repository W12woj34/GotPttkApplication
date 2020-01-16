import { Component, OnInit } from '@angular/core';
import {User} from "../_models/user";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  currentUser : User;
  constructor() { }

  ngOnInit() {
  }

  logout() {
    this.currentUser = null;
  }

  loginAsTourist () {
    this.currentUser = new User();
    this.currentUser.username = "Jan Kowalski";
    this.currentUser.type = "Turysta";
  }

  loginAsGuide () {
    this.currentUser = new User();
    this.currentUser.username = "Jan Kowalski";
    this.currentUser.type = "Przodownik";
  }

}
