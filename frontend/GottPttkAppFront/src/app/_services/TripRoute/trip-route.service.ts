import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {ResponseTripRoute} from '../../_responseModels/ResponseTripRoute/response-trip-route';
import {TripRoute} from '../../_models/TripRoute/trip-route';
import {Observable} from 'rxjs';

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

  getRoutesForTrip(tripId: string): Observable<TripRoute[]> {
    const url = `${this.tripRouteUrl}/trip/${tripId}`;
    return this.http.get<ResponseTripRoute[]>(url).pipe(
      map(respTripRoutes => respTripRoutes.map(respTripRoute => new TripRoute(respTripRoute.numer,
        respTripRoute.indeks, respTripRoute.data, 'Zdef.', null, null, null,
        respTripRoute.powtozona ? 'Tak' : 'Nie', null, respTripRoute.trasa)))
    );
  }

  deleteTripRoute(tripId: number) {
    const url = `${this.tripRouteUrl}/${tripId}`;
    return this.http.delete(url);
  }

  addRouteToTripOnDate(tripId: number, routeId: number, routeDate: string): Observable<ResponseTripRoute> {
    const url = `${this.tripRouteUrl}`;
    const payload = {date: routeDate, route: routeId, trip: tripId};
    return this.http.post<ResponseTripRoute>(url, payload, this.httpOptions);
  }
}
