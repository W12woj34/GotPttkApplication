import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {MountainSubgroup} from '../../_models/MountainSubgroup/mountain-subgroup';
import {ResponseMountainSubgroup} from '../../_responseModels/ResponseMountainSubgroup/response-mountain-subgroup';

@Injectable({
  providedIn: 'root'
})
export class MountainSubgroupService {

  constructor(private http: HttpClient) { }

  private groupsUrl = 'http://localhost:8080/subgroups';
  getMountainSubgroupsForGroup(groupId: string): Observable<MountainSubgroup[]> {
    const url = `${this.groupsUrl}/group/${groupId}`;
    return this.http.get<ResponseMountainSubgroup[]>(url).pipe(
      map (respMntSubgroups => respMntSubgroups.map(respMntSubgroup =>
        new MountainSubgroup(respMntSubgroup.nazwa, respMntSubgroup.id, respMntSubgroup.grupa)))
    );
  }
}
