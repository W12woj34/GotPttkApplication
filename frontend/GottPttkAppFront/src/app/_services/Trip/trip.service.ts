import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ResponseTrip} from '../../_responseModels/ResponseTrip/response-trip';
import {Trip} from '../../_models/Trip/trip';
import {map} from 'rxjs/operators';
import {SendVerifyTrips} from '../../_sendModels/sendVerifyTrips/send-verify-trips';
import {VerifyTrip} from '../../_models/VerifyTrip/verify-trip';

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

  getTripsForUserOfStatus(userId: number, tripStatus: number): Observable<Trip[]> {
    const url = `${this.tripsUrl}/tourist/${userId}/${tripStatus}`;
    return this.http.get<ResponseTrip[]>(url).pipe(
      map(respTrips => respTrips.map(respTrip => new Trip(respTrip.numer, respTrip.dataRozpoczecia,
        respTrip.dataZakonczenia, null, this.statusAsText(respTrip.status),
        null, respTrip.odznaka, null)))
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

  getPointsForTrip(tripId: number) {
    const url = `${this.tripsUrl}/points/${tripId}`;
    return this.http.get<number>(url);
  }

  getTripsForUser(userId: number): Observable<Trip[]> {
    const url = `${this.tripsUrl}/tourist/${userId}`;
    return this.http.get<ResponseTrip[]>(url).pipe(
      map(respTrips => respTrips.map(respTrip => new Trip(respTrip.numer, respTrip.dataRozpoczecia,
        respTrip.dataZakonczenia, null, this.statusAsText(respTrip.status),
        null, respTrip.odznaka, null)))
    );
  }

  getTrip(tripId: string): Observable<Trip> {
    const url = `${this.tripsUrl}/${tripId}`;
    return this.http.get<ResponseTrip>(url).pipe(
      map(respTrip => new Trip(respTrip.numer, respTrip.dataRozpoczecia, respTrip.dataZakonczenia,
        null, this.statusAsText(respTrip.status), null, respTrip.odznaka, null))
    );
  }

  sendTripsForVerification(tripsToSend: SendVerifyTrips): Observable<Trip[]> {
    const url = `${this.tripsUrl}/verify`;
    const payload = {ids: tripsToSend.ids};
    return this.http.put<ResponseTrip[]>(url, payload, this.httpOptions).pipe(
      map(respTrips => respTrips.map(respTrip => new Trip(respTrip.numer, respTrip.dataRozpoczecia,
        respTrip.dataZakonczenia, null, this.statusAsText(respTrip.status),
        null, respTrip.odznaka, null)))
    );
  }

  addNewTrip(badgeId: number): Observable<Trip> {
    const url = `${this.tripsUrl}`;
    const payload = {newTripGetBadge: badgeId};
    return this.http.post<ResponseTrip>(url, payload, this.httpOptions).pipe(
      map(respTrip => new Trip(respTrip.numer, respTrip.dataRozpoczecia, respTrip.dataZakonczenia,
        null, this.statusAsText(respTrip.status), null, respTrip.odznaka, null))
    );
  }

  getTripsForVerification(leaderId: string): Observable<VerifyTrip[]> {
    const url = `${this.tripsUrl}/leader/${leaderId}/3`;
    return this.http.get<ResponseTrip[]>(url).pipe(
      map(respTrips => respTrips.map(respTrip => new VerifyTrip(respTrip.numer, respTrip.dataRozpoczecia,
        respTrip.dataZakonczenia, null, this.statusAsText(respTrip.status), null,
        respTrip.odznaka, null, null, null, null)))
    );
  }

  setStatusForTrip(tripId: number, tripStatus: number) {
    const url = `${this.tripsUrl}/status/${tripId}`;
    const payload = {status: tripStatus};
    return this.http.put<ResponseTrip>(url, payload, this.httpOptions);
  }

  deleteTrip(tripId: number) {
    const url = `${this.tripsUrl}/${tripId}`;
    return this.http.delete(url);
  }
}
