import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import {YesNoDialogComponent} from "../../dialogs/yes-no-dialog/yes-no-dialog.component";
import {Router} from "@angular/router";

export interface TripElement {
  position: number;
  id: number;
  begin_date: string;
  end_date: string;
  mnt_group: string;
  status: string;
  score: number;
}

const ELEMENT_DATA: TripElement[] = [
  {position: 1, id: 5, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
  {position: 2, id: 6, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
  {position: 3, id: 7, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
  {position: 4, id: 8, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
  {position: 5, id: 9, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
  {position: 6, id: 10, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
  {position: 7, id: 11, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', score: 14},
];

@Component({
  selector: 'app-manage-trips',
  templateUrl: './manage-trips.component.html',
  styleUrls: ['./manage-trips.component.css']
})
export class ManageTripsComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router) { }

  displayedColumns: string[] = [ 'begin_date', 'end_date', 'mnt_group', 'status', 'score', 'buttons'];
  dataSource = new MatTableDataSource<TripElement>(ELEMENT_DATA);

  ngOnInit() {
  }

  checkIfCanDelete(id: number){
    if(id==5) {
      this.openErrorDialog('Nie możesz usunąć tej wycieczki', 'Ta wycieczka została przesłana do weryfikacji lub jest już zweryfikowana.');
    } else {
      this.openDeleteDialog();
    }
  }

  checkIfCanEdit(id: number){
    if(id==5) {
      this.openErrorDialog('Nie możesz edytować tej wycieczki', 'Ta wycieczka została przesłana do weryfikacji lub jest już zweryfikowana.');
    } else {
      this.router.navigate(['/editTrip/:id',id])
    }
  }

  openErrorDialog(title: string, desc:string) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: title,
      desc: desc
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    this.dialog.open(SimpleErrorDialogComponent, dialogConfig);
  }

  openDeleteDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Czy na pewno chcesz usunąć tą wycieczkę?',
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(YesNoDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if(result == 'yes') {
       this.deleteTrip()
      }})
  }

  deleteTrip() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Wycieczka została usunięta.',
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    this.dialog.open(SimpleErrorDialogComponent, dialogConfig);
  }

}
