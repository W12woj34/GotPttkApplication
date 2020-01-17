import {Component, OnInit, ViewChild} from '@angular/core';
import { Router} from "@angular/router";
import { Location } from '@angular/common';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {YesNoDialogComponent} from "../../dialogs/yes-no-dialog/yes-no-dialog.component";

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
  {position: 5, date: '02-11-2019', category: 'Zdef.', start_point: 'Fajna Ryba', end_point: 'Kozłowa', is_back: 'Nie', is_repeated: 'Tak', points: 0},
  {position: 6, date: '02-11-2019', category: 'Zdef.', start_point: 'Kozłowa', end_point: 'Buczyna', is_back: 'Nie', is_repeated: 'Nie', points: 6},
  {position: 7, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
  {position: 8, date: '02-11-2019', category: 'Zdef.', start_point: 'Fajna Ryba', end_point: 'Kozłowa', is_back: 'Nie', is_repeated: 'Tak', points: 0},
  {position: 9, date: '02-11-2019', category: 'Zdef.', start_point: 'Kozłowa', end_point: 'Buczyna', is_back: 'Nie', is_repeated: 'Nie', points: 6},
  {position: 10, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
  {position: 11, date: '02-11-2019', category: 'Zdef.', start_point: 'Fajna Ryba', end_point: 'Kozłowa', is_back: 'Nie', is_repeated: 'Tak', points: 0},
  {position: 12, date: '02-11-2019', category: 'Zdef.', start_point: 'Kozłowa', end_point: 'Buczyna', is_back: 'Nie', is_repeated: 'Nie', points: 6},
  {position: 13, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
  {position: 14, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
  {position: 15, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
  {position: 16, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
];

@Component({
  selector: 'app-verify-trip-details',
  templateUrl: './verify-trip-details.component.html',
  styleUrls: ['./verify-trip-details.component.css']
})
export class VerifyTripDetailsComponent implements OnInit {

  constructor(private router: Router,
              private location: Location,
              private dialog: MatDialog) { }

  public pointsToSend : any = {};

  displayedColumns: string[] = ['date', 'category', 'start_point', 'end_point', 'is_back', 'is_repeated', 'points'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  verifyPositive() {
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
