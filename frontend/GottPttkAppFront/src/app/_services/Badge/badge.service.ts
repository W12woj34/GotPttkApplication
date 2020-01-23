import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ResponseBadge} from '../../_responseModels/ResponseBadge/response-badge';
import {Badge} from '../../_models/Badge/badge';
import {ResponseBadgeInfo} from '../../_responseModels/ResponseBadgeInfo/response-badge-info';

@Injectable({
  providedIn: 'root'
})
export class BadgeService {

  constructor(private http: HttpClient) { }

  private badgesUrl = 'http://localhost:8080/getBadges';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getBadgeForUserOfStatus(userId: string, status: number): Observable<Badge[]> {
    const url = `${this.badgesUrl}/tourist/${userId}/${status}`;
    return this.http.get<ResponseBadge[]>(url).pipe(
      map (respBadges => respBadges.map(respBadge => new Badge(respBadge.dataZdobycia, respBadge.id,
        respBadge.odznaka, respBadge.punkty, respBadge.referat, respBadge.status, respBadge.turysta)))
    );
  }

  getBadgeInfoForBadgeID(badgeId: number): Observable<Badge> {
    const url = `${this.badgesUrl}/${badgeId}`;
    return this.http.get<ResponseBadge>(url).pipe(
      map (respBadge => new Badge(respBadge.dataZdobycia, respBadge.id, respBadge.odznaka, respBadge.punkty,
        respBadge.referat, respBadge.status, respBadge.turysta))
    );
  }

  getPossibleBadges(userId: string): Observable<ResponseBadgeInfo[]> {
    const url = `${this.badgesUrl}/tourist/${userId}/possible`;
    return this.http.get<ResponseBadgeInfo[]>(url);
  }

  setSelectedBadge(selectedBadge: string, touristID: string) {
    const url = `${this.badgesUrl}`;
    const payload = {badgeId: selectedBadge, touristId: touristID};
    return this.http.post(url, payload, this.httpOptions);
  }
}
