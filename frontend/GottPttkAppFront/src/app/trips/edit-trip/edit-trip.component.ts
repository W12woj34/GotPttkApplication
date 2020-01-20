import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import {Location} from '@angular/common';
import {TripService} from "../../_services/Trip/trip.service";
import {Trip} from "../../_models/Trip/trip";
import {MountainGroupService} from "../../_services/MountainGroup/mountain-group.service";
import {TripRouteService} from "../../_services/TripRoute/trip-route.service";
import {TripRoute} from "../../_models/TripRoute/trip-route";
import {RouteService} from "../../_services/Route/route.service";
import {YesNoDialogComponent} from "../../dialogs/yes-no-dialog/yes-no-dialog.component";
import {AddRouteDialogComponent} from "../../dialogs/add-route-dialog/add-route-dialog.component";
import {MountainGroup} from "../../_models/MountainGroup/mountain-group";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-edit-trip',
  templateUrl: './edit-trip.component.html',
  styleUrls: ['./edit-trip.component.css'],
  animations: [trigger('fadeInOut', [
    state('void', style({
      opacity: 0
    })),
    transition('void <=> *', animate(100)),
  ]),]
})
export class EditTripComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location,
              private route: ActivatedRoute,
              private tripService: TripService,
              private mountainGroupService: MountainGroupService,
              private tripRouteService: TripRouteService,
              private routeService: RouteService) {
  }

  showSpinner = false;

  startDate = '';
  endDate = '';
  mountainGroup = '';
  totalPoints;

  dataSource;
  numberOfDataSourceRows: number = 0;
  allRoutes: TripRoute[] = [];
  displayedColumns: string[] = ['index', 'date', 'category', 'start_point', 'end_point', 'is_back', 'is_repeated', 'points', 'actions'];

  id = this.route.snapshot.paramMap.get('id');

  all_mnt_groups : MountainGroup[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.showSpinner = true;
    this.getTripDetails();
    this.getRoutes();
  }

  getTripDetails() {
    this.tripService.getTrip(this.id).subscribe(trip => {
      this.startDate = trip.begin_date;
      this.endDate = trip.end_date;
      this.getMountainGroups(trip);
      this.getPoints(trip);
    })
  }


  getRoutes() {
    this.tripRouteService.getRoutesForTrip(this.id).subscribe(routes => {
      if(routes.length == 0) {
        this.mountainGroupService.getAllMountainGroups().subscribe(groups => {
          this.all_mnt_groups = groups;
        });
        this.showSpinner = false;
      } else this.getRoutesDetails(routes);
    })
  }

  getRoutesDetails(routes: TripRoute[]) {
    routes.forEach(route => {
      this.numberOfDataSourceRows++;
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
        this.allRoutes = routes;
        this.allRoutes.sort(function(obj1,obj2 ){
          return obj1.index - obj2.index;
        });
        this.dataSource = new MatTableDataSource<TripRoute>(this.allRoutes);
        this.dataSource.paginator = this.paginator;
        this.showSpinner = false;
      }
    })
  }

  getMountainGroups(trip: Trip) {
    this.mountainGroupService.getMountainGroupsForTrip(trip.id).subscribe(mountain_group => {
      let array_of_names = [];
      mountain_group.forEach(mnt_group => {
        array_of_names.push(mnt_group.name);
      });
      trip.mnt_groups = array_of_names.join(', ');
      this.mountainGroup = trip.mnt_groups;
    });
  }

  getPoints(trip: Trip) {
    this.tripService.getPointsForTrip(trip.id).subscribe(points => {
      trip.sugg_score = points;
      this.totalPoints = trip.sugg_score;
    });
  }

  openDeleteDialog(id: number) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Czy na pewno chcesz usunąć tą trasę?',
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(YesNoDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if (result == 'yes') {
        this.deleteRoute(id)
      }
    })
  }

  deleteRoute(id: number) {
    this.tripRouteService.deleteTripRoute(id).subscribe();

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Trasa została usunięta.',
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(() => {
      window.location.reload();
    })
  }

  openAddRouteDialog() {
    const dialogConfig = new MatDialogConfig();

    if (this.numberOfDataSourceRows != 0) {
      dialogConfig.data = {
        lastRouteID: this.allRoutes[this.allRoutes.length-1].route_id,
        lastRouteDate: this.allRoutes[this.allRoutes.length-1].date,
        tripID: this.id,
      };
      dialogConfig.panelClass = 'custom-dialog-background';

      const dialogRef = this.dialog.open(AddRouteDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(result => {
        if(result == 'Added') window.location.reload();
      })
    } else {
      dialogConfig.data = {
        tripGroup: this.mountainGroup,
        tripID: this.id,
      };
      dialogConfig.panelClass = 'custom-dialog-background';

      const dialogRef = this.dialog.open(AddRouteDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(result => {
        if(result == 'Added') window.location.reload();
      })
    }
  }

  submitTrip() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Pomyślnie edytowano wycieczkę w książeczce'
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(() => {
      this.router.navigate(['/manageTrips']);
    })
  }

  goBack(): void {
    this.location.back();
  }

}
