import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {MountainSubgroup} from "../../_models/MountainSubgroup/mountain-subgroup";
import {ResponseMountainSubgroup} from "../../_responseModels/ResponseMountainSubgroup/response-mountain-subgroup";

@Injectable({
  providedIn: 'root'
})
export class MountainSubgroupService {

  constructor(private http: HttpClient) { }

  private groupsUrl = 'http://localhost:8080/subgroups';
  getMountainSubgroupsForGroup(group_id: string) : Observable<MountainSubgroup[]> {
    const url = `${this.groupsUrl}/group/${group_id}`;
    return this.http.get<ResponseMountainSubgroup[]>(url).pipe(
      map (resp_mnt_subgroup => resp_mnt_subgroup.map(resp_mnt_subgroup => new MountainSubgroup(resp_mnt_subgroup.nazwa,resp_mnt_subgroup.id,resp_mnt_subgroup.grupa)))
    );
  }
}
