import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MountainGroup} from "../../_models/MountainGroup/mountain-group";
import {map} from "rxjs/operators";
import {ResponseMountainGroup} from "../../_responseModels/ResponseMountainGroup/response-mountain-group";

@Injectable({
  providedIn: 'root'
})
export class MountainGroupService {

  constructor(private http: HttpClient) { }

  private groupsUrl = 'http://localhost:8080/groups';
  getMountainGroupsForTrip(trip_id: number) : Observable<MountainGroup[]> {
    const url = `${this.groupsUrl}/trip/${trip_id}`;
    return this.http.get<ResponseMountainGroup[]>(url).pipe(
      map (resp_mnt_group => resp_mnt_group.map(resp_mnt_group => new MountainGroup(resp_mnt_group.nazwa)))
    );
  }
}
