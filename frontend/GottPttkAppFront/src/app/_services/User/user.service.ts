import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ResponseUser} from '../../_responseModels/ResponseUser/response-user';
import {User} from '../../_models/User/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private usersUrl = 'http://localhost:8080/users';

  getUserInfo(userId: string): Observable<User> {
    const url = `${this.usersUrl}/${userId}`;
    return this.http.get<ResponseUser>(url).pipe(
      map (respUser => new User(respUser.id, respUser.imie, respUser.nazwisko, ''))
    );
  }
}
