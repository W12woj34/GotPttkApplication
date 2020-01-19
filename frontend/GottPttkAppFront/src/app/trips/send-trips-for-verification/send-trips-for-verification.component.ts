import {Component, OnInit, ViewChild} from '@angular/core';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import {TableDialogComponent} from "../../dialogs/table-dialog/table-dialog.component";
import {MatPaginator} from "@angular/material/paginator";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Trip} from "../../_models/Trip/trip";
import {TripService} from "../../_services/Trip/trip.service";
import {MountainGroupService} from "../../_services/MountainGroup/mountain-group.service";

@Component({
  selector: 'app-send-trips-for-verification',
  templateUrl: './send-trips-for-verification.component.html',
  styleUrls: ['./send-trips-for-verification.component.css'],
  animations: [trigger('fadeInOut', [
    state('void', style({
      opacity: 0
    })),
    transition('void <=> *', animate(100)),
  ]),]
})

export class SendTripsForVerificationComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private route: ActivatedRoute,
              private location: Location,
              private tripService: TripService,
              private mountainGroupService: MountainGroupService) {
  }

  dataSource;
  selection = new SelectionModel<Trip>(true, []);
  displayedColumns: string[] = ['select', 'begin_date', 'end_date', 'mnt_groups', 'status', 'sugg_score'];

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected == numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getTrips();
  }

  getTrips() {
    this.tripService.getTripsForUserOfStatus(JSON.parse(localStorage.getItem('currentUser')).id,0)
      .subscribe(trips => {
        if(trips.length == 0){
          this.openErrorDialog();
        } else {
          this.getMountainGroups(trips);
          this.getPoints(trips);
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
      })
    });
    this.dataSource = new MatTableDataSource<Trip>(trips);
    this.dataSource.paginator = this.paginator;
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
      this.goBack();
    })

  }

  openSentDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    if (this.selection.selected.length > 3) {
      dialogConfig.data = {
        dialogType: 'errorSome',
        title: 'Nie znaleziono przodowników dla wszystkich wycieczek',
        description: 'Niektóre wycieczki nie mogą zostać przekazane, ponieważ nie znaleziono dla nich odpowiednich przodowników.',
        tableTitle: 'Lista wycieczek, dla których nie znaleziono przodowników:',
        dataSource: this.selection.selected,
      };
    } else {
      dialogConfig.data = {
        dialogType: 'sentAll',
        title: 'Pomyślnie przekazano wycieczki do weryfikacji',
        description: 'Wycieczki powinny zostać zweryfikowane przez odpowiedniego przodownika w ciągu kilku dni.',
        tableTitle: 'Lista przekazanych wycieczek:',
        dataSource: this.selection.selected,
      };
    }
    dialogConfig.maxHeight = '550px';

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(TableDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if(result == true){
        dialogConfig.data = {
          dialogType: 'sentAll',
          title: 'Pomyślnie przekazano wycieczki do weryfikacji',
          description: 'Wycieczki powinny zostać zweryfikowane przez odpowiedniego przodownika w ciągu kilku dni.',
          tableTitle: 'Lista przekazanych wycieczek:',
          dataSource: this.selection.selected,
        };

        this.dialog.open(TableDialogComponent, dialogConfig);
      }
    })
  }

  goBack(): void {
    this.location.back();
  }

}
