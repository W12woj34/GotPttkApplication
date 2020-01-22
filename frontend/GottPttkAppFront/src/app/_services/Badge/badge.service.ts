import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {ResponseBadge} from "../../_responseModels/ResponseBadge/response-badge";
import {Badge} from "../../_models/Badge/badge";
import {ResponseBadgeInfo} from "../../_responseModels/ResponseBadgeInfo/response-badge-info";

@Injectable({
  providedIn: 'root'
})
export class BadgeService {

  constructor(private http: HttpClient) { }

  private badgesUrl = 'http://localhost:8080/getBadges';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getBadgeForUserOfStatus(user_id: string, status: number) : Observable<Badge[]>{
    const url = `${this.badgesUrl}/tourist/${user_id}/${status}`;
    return this.http.get<ResponseBadge[]>(url).pipe(
      map (resp_badge => resp_badge.map(resp_badge => new Badge(resp_badge.dataZdobycia,resp_badge.id,resp_badge.odznaka,resp_badge.punkty,resp_badge.referat,resp_badge.status,resp_badge.turysta)))
    );
  }

  getBadgeInfoForBadgeID(badge_id: number) : Observable<Badge>{
    const url = `${this.badgesUrl}/${badge_id}`;
    return this.http.get<ResponseBadge>(url).pipe(
      map (resp_badge => new Badge(resp_badge.dataZdobycia,resp_badge.id,resp_badge.odznaka,resp_badge.punkty,resp_badge.referat,resp_badge.status,resp_badge.turysta))
    );
  }

  getBadgeInfo(badge_id: string) : Observable<ResponseBadgeInfo>{
    const url = `http://localhost:8080/badges/${badge_id}`;
    return this.http.get<ResponseBadgeInfo>(url);
  }

  getPossibleBadges(user_id: string) : Observable<ResponseBadgeInfo[]>{
    const url = `${this.badgesUrl}/tourist/${user_id}/possible`;
    return this.http.get<ResponseBadgeInfo[]>(url);
  }

  setSelectedBadge(selectedBadge: string, touristID: string) {
    const url = `${this.badgesUrl}`;
    const payload = {badgeId: selectedBadge, touristId: touristID};
    return this.http.post(url,payload,this.httpOptions);
  }
}
