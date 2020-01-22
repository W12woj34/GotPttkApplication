import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { Location } from '@angular/common';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {YesNoDialogComponent} from "../../dialogs/yes-no-dialog/yes-no-dialog.component";
import {TripRoute} from "../../_models/TripRoute/trip-route";
import {Trip} from "../../_models/Trip/trip";
import {TripService} from "../../_services/Trip/trip.service";
import {TripRouteService} from "../../_services/TripRoute/trip-route.service";
import {RouteService} from "../../_services/Route/route.service";
import {BadgeService} from "../../_services/Badge/badge.service";

@Component({
  selector: 'app-verify-trip-details',
  templateUrl: './verify-trip-details.component.html',
  styleUrls: ['./verify-trip-details.component.css']
})
export class VerifyTripDetailsComponent implements OnInit {

  constructor(private router: Router,
              private location: Location,
              private dialog: MatDialog,
              private route: ActivatedRoute,
              private tripService: TripService,
              private tripRouteService: TripRouteService,
              private routeService: RouteService,
              private badgeService: BadgeService) { }

  displayedColumns: string[] = ['date', 'category', 'start_point', 'end_point', 'is_back', 'is_repeated', 'points'];
  dataSource;
  totalPoints;
  associatedBadge;
  showSpinner = false;

  id = this.route.snapshot.paramMap.get('id');

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.showSpinner = true;
    this.getTripDetails();
    this.getRoutes();
  }

  getTripDetails() {
    this.tripService.getTrip(this.id).subscribe(trip => {
      this.getPoints(trip);
      this.getAssociatedBadgeName(trip);
    })
  }

  getRoutes() {
    this.tripRouteService.getRoutesForTrip(this.id).subscribe(routes => {
      this.getRoutesDetails(routes);
    })
  }

  getRoutesDetails(routes: TripRoute[]) {
    routes.forEach(route => {
      this.routeService.getRouteDetails(route.route_id).subscribe(route_details => {
        route.start_point = route_details.start_point;
        route.end_point = route_details.end_point;
        route.is_back = route_details.is_back;
        route.points = route_details.points;
        this.checkTableDataLoaded(routes);
      })
    })
  }

  checkTableDataLoaded(routes: TripRoute[]) {
    let allDone = true;
    routes.forEach(route => {
      if (route.points == null) allDone = false;
      if (allDone) {
        this.dataSource = new MatTableDataSource<TripRoute>(routes);
        this.dataSource.paginator = this.paginator;
        this.showSpinner = false;
      }
    })
  }

  getPoints(trip: Trip) {
    this.tripService.getPointsForTrip(trip.id).subscribe(points => {
      trip.sugg_score = points;
      this.totalPoints = trip.sugg_score;
    });
  }

  getAssociatedBadgeName(verifiedTrip: Trip){
    this.badgeService.getBadgeInfoForBadgeID(verifiedTrip.badge).subscribe(badgeInfo => {
      this.associatedBadge = badgeInfo.badge_name;
    })
  }

  verifyPositive() {
    this.tripService.setStatusForTrip(parseInt(this.id),1).subscribe();

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Wycieczka została zweryfikowana pozytywnie',
      desc: 'Czy chcesz dalej weryfikować wycieczki?',
      nolink: '/dashboard',
      yeslink: '/verifyTrips'
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(YesNoDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if(result == 'yes') {
        this.router.navigate(['/verifyTrips'])
      } else {
        this.router.navigate(['/dashboard'])      }
    })
  }

  verifyNegative() {
    this.tripService.setStatusForTrip(parseInt(this.id),2).subscribe();

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Wycieczka została zweryfikowana negatywnie',
      desc: 'Czy chcesz dalej weryfikować wycieczki?',
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(YesNoDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if(result == 'yes') {
        this.router.navigate(['/verifyTrips'])
      } else {
        this.router.navigate(['/dashboard'])      }
    })
  }

  goBack(): void {
    this.location.back();
  }

}
