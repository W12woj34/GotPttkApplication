import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs/operators";
import {ResponseTripRoute} from "../../_responseModels/ResponseTripRoute/response-trip-route";
import {TripRoute} from "../../_models/TripRoute/trip-route";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TripRouteService {

  constructor(private http: HttpClient) {
  }

  private tripRouteUrl = 'http://localhost:8080/tripRoutes';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getRoutesForTrip(trip_id: string) : Observable<TripRoute[]>{
    const url = `${this.tripRouteUrl}/trip/${trip_id}`;
    return this.http.get<ResponseTripRoute[]>(url).pipe(
      map(resp_trip_route => resp_trip_route.map(resp_trip_route => new TripRoute(resp_trip_route.numer,resp_trip_route.indeks,resp_trip_route.data,'Zdef.',null,null,null,resp_trip_route.powtozona ? "Tak" : "Nie",null)))
    );
  }

  deleteTripRoute(trip_id: number) {
    const url = `${this.tripRouteUrl}/${trip_id}`;
    return this.http.delete(url);
  }
}
