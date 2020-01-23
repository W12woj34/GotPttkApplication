import {Component, OnInit, ViewChild} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import {SimpleErrorDialogComponent} from '../../dialogs/simple-error-dialog/simple-error-dialog.component';
import {TableDialogComponent} from '../../dialogs/table-dialog/table-dialog.component';
import {MatPaginator} from '@angular/material/paginator';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {Trip} from '../../_models/Trip/trip';
import {TripService} from '../../_services/Trip/trip.service';
import {MountainGroupService} from '../../_services/MountainGroup/mountain-group.service';
import {SendVerifyTrips} from '../../_sendModels/sendVerifyTrips/send-verify-trips';
import {BadgeService} from '../../_services/Badge/badge.service';

@Component({
  selector: 'app-send-trips-for-verification',
  templateUrl: './send-trips-for-verification.component.html',
  styleUrls: ['./send-trips-for-verification.component.css'],
  animations: [trigger('fadeInOut', [
    state('void', style({
      opacity: 0
    })),
    transition('void <=> *', animate(100)),
  ])]
})

export class SendTripsForVerificationComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private tripService: TripService,
              private mountainGroupService: MountainGroupService,
              private badgeService: BadgeService) {
  }

  showSpinner = false;
  dataSource;
  selection = new SelectionModel<Trip>(true, []);
  displayedColumns: string[] = ['select', 'beginDate', 'endDate', 'mntGroups', 'badgeName', 'status', 'suggScore'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  ngOnInit() {
    this.showSpinner = true;
    this.getTrips();
  }

  getTrips() {
    this.tripService.getTripsForUserOfStatus(JSON.parse(localStorage.getItem('currentUser')).id, 0)
      .subscribe(trips => {
        if (trips.length === 0) {
          this.openErrorDialog();
        } else {
          this.getMountainGroups(trips);
          this.getPoints(trips);
          this.getBadgeNames(trips);
        }
      });
  }

  sendTripsForVerification() {
    this.showSpinner = true;

    const idsTable: number[] = [];
    this.selection.selected.forEach(selectedTrip => {
      idsTable.push(selectedTrip.id);
    });
    const tripsToSend = new SendVerifyTrips(idsTable);
    this.tripService.sendTripsForVerification(tripsToSend).subscribe(successTrips => {
      this.showSpinner = false;
      if (successTrips.length === this.selection.selected.length) {
        this.openSuccessAllSentDialog();
      } else {
        this.getMountainGroups(successTrips);
        this.getPoints(successTrips);
        this.openSomeFailedDialog(successTrips);
      }
    });
  }

  getMountainGroups(trips: Trip[]) {
    trips.forEach(trip => {
      this.mountainGroupService.getMountainGroupsForTrip(trip.id).subscribe(mountainGroups => {
        const mountainGroupNames = [];
        mountainGroups.forEach(mountainGroup => {
          mountainGroupNames.push(mountainGroup.name);
        });
        trip.mntGroups = mountainGroupNames.join(', ');
      });
    });
  }

  getPoints(trips: Trip[]) {
    trips.forEach(trip => {
      this.tripService.getPointsForTrip(trip.id).subscribe(points => {
        trip.suggScore = points;
        this.checkAllLoaded(trips);
      });
    });
  }

  getBadgeNames(trips: Trip[]) {
    trips.forEach(trip => {
      this.badgeService.getBadgeInfoForBadgeID(trip.badge).subscribe(badgeInfo => {
        trip.badgeName = badgeInfo.badgeName;
      });
    });
  }

  checkAllLoaded(trips: Trip[]) {
    let allDone = true;
    trips.forEach(trip => {
      if (trip.suggScore == null) {
        allDone = false;
      }
      if (allDone) {
        this.dataSource = new MatTableDataSource<Trip>(trips);
        this.dataSource.paginator = this.paginator;
        this.showSpinner = false;
      }
    });
  }

  openSuccessAllSentDialog() {
    const dialogConfig = new MatDialogConfig();

    const succTrips: Trip[] = [];

    this.selection.selected.forEach(selectedTrip => {
      succTrips.push(new Trip(selectedTrip.id, selectedTrip.beginDate, selectedTrip.endDate, selectedTrip.mntGroups,
        'Przekazana do wer.', selectedTrip.suggScore, selectedTrip.badge, null));
    });

    dialogConfig.disableClose = true;
    dialogConfig.data = {
      dialogType: 'sentAll',
      title: 'Pomyślnie przekazano wszystkie wycieczki do weryfikacji',
      description: 'Wycieczki powinny zostać zweryfikowane przez odpowiedniego przodownika w ciągu kilku dni.',
      tableTitle: 'Lista przekazanych wycieczek:',
      dataSource: succTrips
    };

    dialogConfig.maxHeight = '550px';

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(TableDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(() => {
      window.location.reload();
    });
  }

  openSomeFailedDialog(successTrips: Trip[]) {
    const failedTrips: Trip[] = [];

    this.selection.selected.forEach(selectedTrip => {
      if (-1 === successTrips.findIndex(succTrip => succTrip.id === selectedTrip.id)) {
        failedTrips.push(selectedTrip);
      }
    });

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.data = {
      dialogType: 'errorSome',
      title: 'Nie znaleziono przodowników dla wszystkich wycieczek',
      description: 'Niektóre wycieczki nie mogą zostać przekazane, ponieważ nie znaleziono dla nich odpowiednich przodowników.',
      tableTitle: 'Lista wycieczek, dla których nie znaleziono przodowników:',
      dataSource: failedTrips,
    };

    dialogConfig.maxHeight = '550px';

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(TableDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(() => {
      window.location.reload();
    });
  }

  openErrorDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Brak niezweryfikowanych wycieczek do przekazania',
      desc: 'Wróć tu, kiedy będziesz miał nowe niezweryfikowane wycieczki.'
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(() => {
      this.router.navigate(['/dashboard']);
    });
  }

  goBack(): void {
    this.router.navigate(['../'], {relativeTo: this.route});
  }


}
