import {Component, OnInit} from '@angular/core';
import {User} from "../_models/User/user";
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
    if(localStorage.getItem('currentUser')) {
      this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUser = null;
    this.router.navigate(['/dashboard']);
  }

  loginAsTourist() {
    this.currentUser = new User(8,'Bartłomiej','Kamiński','Turysta');
    this.currentUser.id = 8;
    this.currentUser.firstName = 'Bartłomiej';
    this.currentUser.lastName = 'Kamiński';
    this.currentUser.type = "Turysta";
    localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
  }

  loginAsGuide() {
    this.currentUser = new User(2,'Antoni','Maciejewski','Przodownik');
    this.currentUser.id = 2;
    this.currentUser.firstName = 'Antoni';
    this.currentUser.lastName = 'Maciejewski';
    this.currentUser.type = "Przodownik";
    localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
  }

}
