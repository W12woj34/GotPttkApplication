import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import { Location } from '@angular/common';

export interface PeriodicElement {
  position: number;
  date: string;
  category: string;
  start_point: string;
  end_point: string;
  is_back: string;
  is_repeated: string;
  points: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, date: '02-11-2019', category: 'Zdef.', start_point: 'Przedbórze', end_point: 'Fajna Ryba', is_back: 'Nie', is_repeated: 'Nie', points: 10},
  {position: 2, date: '02-11-2019', category: 'Zdef.', start_point: 'Fajna Ryba', end_point: 'Kozłowa', is_back: 'Nie', is_repeated: 'Tak', points: 0},
  {position: 3, date: '02-11-2019', category: 'Zdef.', start_point: 'Kozłowa', end_point: 'Buczyna', is_back: 'Nie', is_repeated: 'Nie', points: 6},
  {position: 4, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
];

@Component({
  selector: 'app-add-trip',
  templateUrl: './add-trip.component.html',
  styleUrls: ['./add-trip.component.css']
})
export class AddTripComponent implements OnInit {

  startDate : string = '02-11-2019';
  endDate : string = '02-11-2019';

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location) { }

  displayedColumns: string[] = ['date', 'category', 'start_point', 'end_point', 'is_back', 'is_repeated', 'points', 'actions'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  submitTrip(){
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
