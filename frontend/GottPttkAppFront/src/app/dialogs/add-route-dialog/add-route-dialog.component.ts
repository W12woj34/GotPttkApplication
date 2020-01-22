import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {RouteService} from "../../_services/Route/route.service";
import {MatTableDataSource} from "@angular/material/table";
import {Route} from "../../_models/Route/route";
import {SelectionModel} from "@angular/cdk/collections";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {TripRouteService} from "../../_services/TripRoute/trip-route.service";
import {MountainSubgroupService} from "../../_services/MountainSubgroup/mountain-subgroup.service";

@Component({
  selector: 'app-add-route-dialog',
  templateUrl: './add-route-dialog.component.html',
  styleUrls: ['./add-route-dialog.component.css'],
  animations: [trigger('fadeInOut', [
    state('void', style({
      opacity: 0
    })),
    transition('void <=> *', animate(100)),
  ]),]
})
export class AddRouteDialogComponent implements OnInit {

  showSpinner = false;

  minDate;

  lastRouteID: number;
  tripID: number;
  tripGroup: string;
  @Input() selectedDate;

  all_mnt_subgroups;
  mountainSubgroup;

  dataSource;
  selection = new SelectionModel<Route>(false, []);
  displayedColumns: string[] = ['select','start_point', 'end_point', 'is_back', 'mnt_subgroup', 'length', 'points'];

  constructor(
    private dialogRef: MatDialogRef<AddRouteDialogComponent>,
    private routeService: RouteService,
    private tripRouteService: TripRouteService,
    private mountainSubgroupService: MountainSubgroupService,
    @Inject(MAT_DIALOG_DATA) data) {
    this.lastRouteID = data.lastRouteID;
    this.minDate = data.lastRouteDate;
    this.tripID = data.tripID;
    this.tripGroup = data.tripGroup;
  }

  ngOnInit() {
    this.showSpinner = true;
    if(this.lastRouteID != null) {
      this.getPossibleRoutesForLastRoute()
    } else {
      this.getPossibleSubgroups();
    }
  }

  getPossibleRoutesForLastRoute(){
    this.routeService.getPossibleRoutes(this.lastRouteID).subscribe(routes => {
      this.dataSource = new MatTableDataSource<Route>(routes);
      this.showSpinner = false;
    })
  }

  getPossibleSubgroups(){
    this.mountainSubgroupService.getMountainSubgroupsForGroup(this.tripGroup).subscribe(subgroups => {
      this.all_mnt_subgroups = subgroups;
      this.mountainSubgroup = subgroups[0];
      this.showSpinner = false;
    })
  }

  onSubgroupSelect(){
    this.showSpinner = true;
    this.selection.clear();
    let subGroupID = '';
    this.all_mnt_subgroups.forEach(subgroup => {
      if(subgroup.name == this.mountainSubgroup) subGroupID = subgroup.id;
    });
    this.routeService.getRoutesForSubgroup(subGroupID).subscribe(routes => {
      this.dataSource = new MatTableDataSource<Route>(routes);
      this.showSpinner = false;
    })
  }

  addRoute(){
    this.showSpinner = true;
    const dateToSend = this.formatDate(this.selectedDate);
    this.tripRouteService.addRouteToTripOnDate(this.tripID,this.selection.selected[0].id,dateToSend).subscribe(result =>{
      if(result.data == dateToSend && result.wycieczka == this.tripID && result.trasa == this.selection.selected[0].id){
        this.dialogRef.close();
      }
    });
  }

  formatDate(date) {
    let d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }
}
