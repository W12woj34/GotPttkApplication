import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

export interface PeriodicElement {
  position: number;
  id: number;
  first_name: string;
  last_name: string;
  username: string;
  begin_date: string;
  end_date: string;
  mnt_group: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, id: 5, first_name: 'Jan', last_name: 'Kowalski', username: 'JKowalski123', begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie'},
  {position: 2, id: 6, first_name: 'Jan', last_name: 'Kowalski', username: 'JKowalski123', begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie'},
  {position: 3, id: 7, first_name: 'Jan', last_name: 'Kowalski', username: 'JKowalski123', begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie'},
  {position: 4, id: 8, first_name: 'Jan', last_name: 'Kowalski', username: 'JKowalski123', begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie'},
];

@Component({
  selector: 'app-verify-trips-main',
  templateUrl: './verify-trips-main.component.html',
  styleUrls: ['./verify-trips-main.component.css']
})
export class VerifyTripsMainComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private route: ActivatedRoute,
              private location: Location) { }

  displayedColumns: string[] = ['first_name', 'last_name', 'username', 'begin_date', 'end_date', 'mnt_group', 'verify_button'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

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
      this.goBack();
    })
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    if (ELEMENT_DATA.length == 0) this.openErrorDialog();
  }

  goBack(): void {
    this.location.back();
  }

}
