import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ResponseRoute} from '../../_responseModels/ResponseRoute/response-route';
import {Observable} from 'rxjs';
import {Route} from '../../_models/Route/route';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http: HttpClient) {
  }

  private routeUrl = 'http://localhost:8080/routes';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getRouteDetails(routeId: number): Observable<Route> {
    const url = `${this.routeUrl}/${routeId}`;
    return this.http.get<ResponseRoute>(url).pipe(
      map (respRoute => new Route(respRoute.numer, respRoute.poczatkowy, respRoute.koncowy,
        respRoute.czyWGore ? 'Nie' : 'Tak', respRoute.punkty, respRoute.podgrupa, respRoute.dlugosc))
    );
  }

  getPossibleRoutes(routeId: number): Observable<Route[]> {
    const url = `${this.routeUrl}/possible/${routeId}`;
    return this.http.get<ResponseRoute[]>(url).pipe(
      map (respRoutes => respRoutes.map(respRoute => new Route(respRoute.numer, respRoute.poczatkowy,
        respRoute.koncowy, respRoute.czyWGore ? 'Nie' : 'Tak', respRoute.punkty, respRoute.podgrupa,
        respRoute.dlugosc)))
    );
  }

  getRoutesForSubgroup(subgroupId: string): Observable<Route[]> {
    const url = `${this.routeUrl}/subgroup/${subgroupId}`;
    return this.http.get<ResponseRoute[]>(url).pipe(
      map (respRoutes => respRoutes.map(respRoute => new Route(respRoute.numer, respRoute.poczatkowy,
        respRoute.koncowy, respRoute.czyWGore ? 'Nie' : 'Tak', respRoute.punkty, respRoute.podgrupa,
        respRoute.dlugosc)))
    );
  }
}
