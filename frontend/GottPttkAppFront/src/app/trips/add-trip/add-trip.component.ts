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
import {BadgeService} from "../../_services/Badge/badge.service";

@Component({
  selector: 'app-add-trip',
  templateUrl: './add-trip.component.html',
  styleUrls: ['./add-trip.component.css'],
  animations: [trigger('fadeInOut', [
    state('void', style({
      opacity: 0
    })),
    transition('void <=> *', animate(100)),
  ]),]
})
export class AddTripComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location,
              private route: ActivatedRoute,
              private tripService: TripService,
              private mountainGroupService: MountainGroupService,
              private tripRouteService: TripRouteService,
              private routeService: RouteService,
              private badgeService: BadgeService) {
  }

  showSpinner = false;

  editedTrip : Trip = new Trip(null,null,null,null,null,null,null,null);

  dataSource;
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
      this.editedTrip = trip;
      this.getMountainGroups(this.editedTrip);
      this.getPoints(this.editedTrip);
      this.getAssociatedBadgeName(this.editedTrip);
    })
  }

  getRoutes() {
    this.tripRouteService.getRoutesForTrip(this.id).subscribe(routes => {
      if(routes.length == 0) {
        this.allRoutes = [];
        this.dataSource = new MatTableDataSource<TripRoute>();
        this.dataSource.paginator = this.paginator;
        this.mountainGroupService.getAllMountainGroups().subscribe(groups => {
          this.all_mnt_groups = groups;
        });
        this.showSpinner = false;
      } else this.getRoutesDetails(routes);
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

  getAssociatedBadgeName(editedTrip: Trip){
    this.badgeService.getBadgeInfoForBadgeID(editedTrip.badge).subscribe(badgeInfo => {
      editedTrip.badgeName = badgeInfo.badge_name;
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

  getMountainGroups(editedTrip: Trip) {
    this.mountainGroupService.getMountainGroupsForTrip(editedTrip.id).subscribe(mountain_group => {
      let array_of_names = [];
      mountain_group.forEach(mnt_group => {
        array_of_names.push(mnt_group.name);
      });
      editedTrip.mnt_groups = array_of_names.join(', ');
    });
  }

  getPoints(editedTrip: Trip) {
    this.tripService.getPointsForTrip(editedTrip.id).subscribe(points => {
      this.editedTrip.sugg_score = points;
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
    this.showSpinner = true;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.panelClass = 'custom-dialog-background';

    this.tripRouteService.deleteTripRoute(id).subscribe(result =>{
      if(result == id) {
        this.showSpinner = false;
        dialogConfig.data = {
          title: 'Trasa została usunięta pomyślnie.',
        };
        const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);
        dialogRef.afterClosed().subscribe(() => {
          this.ngOnInit();
        })
      } else{
        this.showSpinner = false;
        dialogConfig.data = {
          title: 'Wystąpił błąd podczas usuwania trasy.',
        };
        const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);
        dialogRef.afterClosed().subscribe(() => {
          this.ngOnInit();
        })
      }
    });
  }

  openAddRouteDialog() {
    const dialogConfig = new MatDialogConfig();

    if (this.allRoutes.length != 0) {
      dialogConfig.data = {
        lastRouteID: this.allRoutes[this.allRoutes.length - 1].route_id,
        lastRouteDate: this.allRoutes[this.allRoutes.length - 1].date,
        tripID: parseInt(this.id),
      };
      dialogConfig.panelClass = 'custom-dialog-background';

      const dialogRef = this.dialog.open(AddRouteDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(() => {
        this.ngOnInit();
      })
    } else {
      dialogConfig.data = {
        tripGroup: this.editedTrip.mnt_groups,
        tripID: this.id,
      };
      dialogConfig.panelClass = 'custom-dialog-background';

      const dialogRef = this.dialog.open(AddRouteDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(result => {
        if(result != 'cancel') this.ngOnInit();
      })
    }
  }

  submitTrip() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Pomyślnie dodano wycieczkę do książeczki'
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
