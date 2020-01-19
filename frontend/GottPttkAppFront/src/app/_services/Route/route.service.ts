import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ResponseRoute} from "../../_responseModels/ResponseRoute/response-route";
import {Observable} from "rxjs";
import {Route} from "../../_models/Route/route";
import {map} from "rxjs/operators";

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

  getRouteDetails(route_id: number) : Observable<Route>{
    const url = `${this.routeUrl}/${route_id}`;
    return this.http.get<ResponseRoute>(url).pipe(
      map (resp_route => new Route(resp_route.poczatkowy,resp_route.koncowy,resp_route.czyWGore ? "Nie" : "Tak",resp_route.punkty))
    );
  }
}
