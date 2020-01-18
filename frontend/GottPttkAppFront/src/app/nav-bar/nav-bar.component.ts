import {Component, OnInit} from '@angular/core';
import {User} from "../_models/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  currentUser: User;

  constructor(private router: Router,) {
  }

  ngOnInit() {
  }

  logout() {
    this.currentUser = null;
    this.router.navigate(['/dashboard']);
  }

  loginAsTourist() {
    this.currentUser = new User();
    this.currentUser.username = "Jan Kowalski";
    this.currentUser.type = "Turysta";
  }

  loginAsGuide() {
    this.currentUser = new User();
    this.currentUser.username = "Jan Kowalski";
    this.currentUser.type = "Przodownik";
  }

}
