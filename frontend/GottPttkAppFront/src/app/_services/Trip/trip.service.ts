import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ResponseTrip} from "../../_responseModels/ResponseTrip/response-trip";
import {Trip} from "../../_models/Trip/trip";
import {map} from "rxjs/operators";
import {SendVerifyTrips} from "../../_sendModels/sendVerifyTrips/send-verify-trips";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor(private http: HttpClient) {
  }

  private tripsUrl = 'http://localhost:8080/trips';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getTripsForUserOfStatus(user_id: number, trip_status: number): Observable<Trip[]> {
    const url = `${this.tripsUrl}/tourist/${user_id}/${trip_status}`;
    return this.http.get<ResponseTrip[]>(url).pipe(
      map(resp_trip => resp_trip.map(resp_trip => new Trip(resp_trip.numer, resp_trip.dataRozpoczecia, resp_trip.dataZakonczenia, null, this.statusAsText(resp_trip.status), null)))
    );
  }

  statusAsText(status: number) {
    switch (status) {
      case 0 :
        return 'Niezweryfikowana';
      case 1 :
        return 'Zwer. pozytywnie';
      case 2 :
        return 'Zwer. negatywnie';
      case 3 :
        return 'Przekazana do wer.';
    }
  }

  getPointsForTrip(trip_id: number) {
    const url = `${this.tripsUrl}/points/${trip_id}`;
    return this.http.get<number>(url);
  }

  getTripsForUser(user_id: number): Observable<Trip[]> {
    const url = `${this.tripsUrl}/tourist/${user_id}`;
    return this.http.get<ResponseTrip[]>(url).pipe(
      map(resp_trip => resp_trip.map(resp_trip => new Trip(resp_trip.numer, resp_trip.dataRozpoczecia, resp_trip.dataZakonczenia, null, this.statusAsText(resp_trip.status), null)))
    );
  }

  sendTripsForVerification(tripsToSend: SendVerifyTrips) : Observable<Trip[]> {
    const url = `${this.tripsUrl}/verify`;
    const payload = {ids: tripsToSend.ids};
    return this.http.put<ResponseTrip[]>(url,payload,this.httpOptions).pipe(
      map(resp_trip => resp_trip.map(resp_trip => new Trip(resp_trip.numer, resp_trip.dataRozpoczecia, resp_trip.dataZakonczenia, null, this.statusAsText(resp_trip.status), null)))
    );
  }
}
