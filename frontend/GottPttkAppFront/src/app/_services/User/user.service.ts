import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {ResponseUser} from "../../_responseModels/ResponseUser/response-user";
import {User} from "../../_models/User/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private usersUrl = 'http://localhost:8080/users';

  getUserInfo(user_id: string) : Observable<User>{
    const url = `${this.usersUrl}/${user_id}`;
    return this.http.get<ResponseUser>(url).pipe(
      map (resp_user => new User(resp_user.id,resp_user.imie,resp_user.nazwisko,''))
    );
  }
}
