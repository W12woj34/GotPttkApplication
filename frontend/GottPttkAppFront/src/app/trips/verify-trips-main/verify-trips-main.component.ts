import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {SimpleErrorDialogComponent} from '../../dialogs/simple-error-dialog/simple-error-dialog.component';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {TripService} from '../../_services/Trip/trip.service';
import {MountainGroupService} from '../../_services/MountainGroup/mountain-group.service';
import {BadgeService} from '../../_services/Badge/badge.service';
import {UserService} from '../../_services/User/user.service';
import {VerifyTrip} from '../../_models/VerifyTrip/verify-trip';

@Component({
  selector: 'app-verify-trips-main',
  templateUrl: './verify-trips-main.component.html',
  styleUrls: ['./verify-trips-main.component.css']
})
export class VerifyTripsMainComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private route: ActivatedRoute,
              private location: Location,
              private tripService: TripService,
              private mountainGroupService: MountainGroupService,
              private badgeService: BadgeService,
              private userService: UserService,
              private router: Router) {
  }

  showSpinner = false;
  dataSource;
  displayedColumns: string[] = ['firstName', 'lastName', 'username', 'beginDate', 'endDate', 'mntGroups', 'badgeName', 'verifyButton'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  openErrorDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Brak wycieczek do weryfikacji',
      desc: 'Wróć tu, kiedy będziesz miał nowe wycieczki do weryfikacji.'
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(() => {
      this.router.navigate(['/dashboard']);
    });
  }

  ngOnInit() {
    this.showSpinner = true;
    this.getTripsForVerification();
  }

  getTripsForVerification() {
    this.tripService.getTripsForVerification('2').subscribe(trips => {
      if (trips.length !== 0) {
        this.getMountainGroups(trips);
        this.getUsersInfo(trips);
        this.getBadgeNames(trips);
      } else {
        this.showSpinner = false;
        this.openErrorDialog();
      }
    });
  }

  getUsersInfo(trips: VerifyTrip[]) {
    trips.forEach(trip => {
      this.badgeService.getBadgeInfoForBadgeID(trip.badge).subscribe(badge => {
        this.userService.getUserInfo(badge.tourist).subscribe(user => {
          trip.firstName = user.firstName;
          trip.lastName = user.lastName;
          trip.username = user.id;
        });
      });
    });
  }

  getMountainGroups(trips: VerifyTrip[]) {
    trips.forEach(trip => {
      this.mountainGroupService.getMountainGroupsForTrip(trip.id).subscribe(mountainGroups => {
        const mountainGroupNames = [];
        mountainGroups.forEach(mountainGroup => {
          mountainGroupNames.push(mountainGroup.name);
        });
        trip.mntGroups = mountainGroupNames.join(', ');
        this.checkAllLoaded(trips);
      });
    });
  }

  getBadgeNames(trips: VerifyTrip[]) {
    trips.forEach(trip => {
      this.badgeService.getBadgeInfoForBadgeID(trip.badge).subscribe(badgeInfo => {
        trip.badgeName = badgeInfo.badgeName;
      });
    });
  }

  checkAllLoaded(trips: VerifyTrip[]) {
    let allDone = true;
    trips.forEach(trip => {
      if (trip.mntGroups == null) {
        allDone = false;
      }
      if (allDone) {
        this.dataSource = new MatTableDataSource<VerifyTrip>(trips);
        this.dataSource.paginator = this.paginator;
        this.showSpinner = false;
      }
    });
  }

  goBack(): void {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

}
