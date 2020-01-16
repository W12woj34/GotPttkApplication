import {Component, OnInit, ViewChild} from '@angular/core';
import {SelectionModel} from "@angular/cdk/collections";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SimpleErrorDialogComponent} from "../simple-error-dialog/simple-error-dialog.component";
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

export interface PeriodicElement {
  position: number;
  begin_date: string;
  end_date: string;
  mnt_group: string;
  status: string;
  sugg_score: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 2, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 3, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 4, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 5, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 6, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 7, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 8, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 9, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 10, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 11, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 12, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 13, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 14, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 15, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 16, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 17, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 18, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 19, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 20, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},

];

const SENT_DATA: PeriodicElement[] = [
  {position: 1, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 2, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 3, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 4, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 5, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 6, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 7, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 8, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 9, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 10, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 11, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 12, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 13, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 14, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 15, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 16, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 17, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 18, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 19, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {position: 20, begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},

];

@Component({
  selector: 'app-trip-table',
  templateUrl: './trip-table.component.html',
  styleUrls: ['./trip-table.component.css']
})
export class TripTableComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private route: ActivatedRoute,
              private location: Location) {}

  displayedColumns: string[] = ['select', 'begin_date', 'end_date', 'mnt_group', 'status', 'sugg_score'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  selection = new SelectionModel<PeriodicElement>(true, []);

  openErrorDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Brak niezweryfikowanych wycieczek do przekazania',
      desc: 'Wróć tu, kiedy będziesz miał nowe niezweryfikowane wycieczki.'
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(SimpleErrorDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      () => this.location.back(),
    )
  }

  openSentDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      title: 'Pomyślnie przekazano wycieczki do weryfikacji',
      desc: 'Wycieczki powinny zostać zweryfikowane przez odpowiedniego przodownika w ciągu kilku dni.',
      tableTitle: 'Lista przekazanych wycieczek:',
      dataSource: SENT_DATA,
    };

    dialogConfig.height = '550px';

    dialogConfig.panelClass = 'custom-dialog-background';
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected == numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  checkboxLabel(row?: PeriodicElement): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    if(ELEMENT_DATA.length == 0) this.openErrorDialog();
  }

}
