import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MountainGroup} from '../../_models/MountainGroup/mountain-group';
import {map} from 'rxjs/operators';
import {ResponseMountainGroup} from '../../_responseModels/ResponseMountainGroup/response-mountain-group';

@Injectable({
  providedIn: 'root'
})
export class MountainGroupService {

  constructor(private http: HttpClient) { }

  private groupsUrl = 'http://localhost:8080/groups';
  getMountainGroupsForTrip(tripId: number): Observable<MountainGroup[]> {
    const url = `${this.groupsUrl}/trip/${tripId}`;
    return this.http.get<ResponseMountainGroup[]>(url).pipe(
      map (respMntGroups => respMntGroups.map(respMntGroup => new MountainGroup(respMntGroup.nazwa)))
    );
  }

  getAllMountainGroups(): Observable<MountainGroup[]> {
    const url = `${this.groupsUrl}`;
    return this.http.get<ResponseMountainGroup[]>(url).pipe(
      map (respMntGroups => respMntGroups.map(respMntGroup => new MountainGroup(respMntGroup.nazwa)))
    );
  }
}
