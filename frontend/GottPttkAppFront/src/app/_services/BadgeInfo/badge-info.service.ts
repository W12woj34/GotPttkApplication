import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ResponseBadgeInfo} from '../../_responseModels/ResponseBadgeInfo/response-badge-info';

@Injectable({
  providedIn: 'root'
})
export class BadgeInfoService {

  constructor(private http: HttpClient) { }

  private badgesUrl = 'http://localhost:8080/badges';

  getBadgeInfo(badgeId: string): Observable<ResponseBadgeInfo> {
    const url = `${this.badgesUrl}/${badgeId}`;
    return this.http.get<ResponseBadgeInfo>(url);
  }
}
