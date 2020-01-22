import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import {YesNoDialogComponent} from "../../dialogs/yes-no-dialog/yes-no-dialog.component";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {Trip} from "../../_models/Trip/trip";
import {TripService} from "../../_services/Trip/trip.service";
import {MountainGroupService} from "../../_services/MountainGroup/mountain-group.service";
import {MatPaginator} from "@angular/material/paginator";
import {BadgeService} from "../../_services/Badge/badge.service";

@Component({
  selector: 'app-manage-trips',
  templateUrl: './manage-trips.component.html',
  styleUrls: ['./manage-trips.component.css']
})
export class ManageTripsComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location,
              private tripService: TripService,
              private mountainGroupService: MountainGroupService,
              private badgeService: BadgeService) { }

  displayedColumns: string[] = ['begin_date', 'end_date', 'mnt_groups', 'badgeName', 'status', 'score', 'buttons'];
  dataSource;
  showSpinner;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  ngOnInit() {
    this.showSpinner = true;
    this.getTrips();
  }

  getTrips() {
    this.tripService.getTripsForUser(JSON.parse(localStorage.getItem('currentUser')).id)
      .subscribe(trips => {
        if(trips.length != 0) {
          this.getMountainGroups(trips);
          this.getPoints(trips);
          this.getBadgeNames(trips);
          } else {
          this.showSpinner = false;
        }
      });
  }

  getMountainGroups(trips: Trip[]) {
    trips.forEach(trip => {
      this.mountainGroupService.getMountainGroupsForTrip(trip.id).subscribe(mountain_group => {
        let array_of_names = [];
        mountain_group.forEach(mnt_group => {
          array_of_names.push(mnt_group.name);
        });
        trip.mnt_groups = array_of_names.join(', ');
      })
    })
  }

  getPoints(trips: Trip[]) {
    trips.forEach(trip => {
      this.tripService.getPointsForTrip(trip.id).subscribe(points => {
        trip.sugg_score = points;
        this.checkAllLoaded(trips);
      })
    });
  }

  getBadgeNames(trips: Trip[]) {
    trips.forEach(trip => {
      this.badgeService.getBadgeInfoForBadgeID(trip.badge).subscribe(badgeInfo => {
        trip.badgeName = badgeInfo.badge_name;
      })
    })
  }

  checkAllLoaded(trips: Trip[]){
    let allDone = true;
    trips.forEach(trip => {
      if(trip.sugg_score == null) allDone = false;
      if(allDone) {
        this.dataSource = new MatTableDataSource<Trip>(trips);
        this.dataSource.paginator = this.paginator;
        this.showSpinner = false;
      }
    })
  }

  checkIfCanModfiy(status: string){
    return status == 'Niezweryfikowana';
  }

  openDeleteDialog(id: number) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Czy na pewno chcesz usunąć tą wycieczkę?',
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(YesNoDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if(result == 'yes') {
       this.deleteTrip(id)
      }})
  }

  deleteTrip(id: number) {
    this.showSpinner = true;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.panelClass = 'custom-dialog-background';

    this.tripService.deleteTrip(id).subscribe(result => {
      if(result == id) {
        const index = this.dataSource.data.indexOf(id);
        this.dataSource.data.splice(index, 1);
        this.dataSource._updateChangeSubscription();
        this.showSpinner = false;
        dialogConfig.data = {
          title: 'Wycieczka została usunięta.',
        };
        this.dialog.open(SimpleErrorDialogComponent, dialogConfig);
      } else {
        dialogConfig.data = {
          title: 'Wystąpił problem podczas usuwania wycieczki.',
        };
        this.dialog.open(SimpleErrorDialogComponent, dialogConfig);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  addTripAndOpenEdit(){
    this.showSpinner = true;
    this.badgeService.getBadgeForUserOfStatus(JSON.parse(localStorage.getItem('currentUser')).id,0).subscribe(badge => {
      this.tripService.addNewTrip(badge[0].id).subscribe(trip => {
        this.router.navigate(['/addTrip',trip.id]);
      });
    })
  }

}
