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

export interface PeriodicElement {
  begin_date: string;
  end_date: string;
  mnt_group: string;
  status: string;
  sugg_score: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
  {begin_date: '02-11-2019', end_date: '02-11-2019', mnt_group: 'Góry Świętokrzyskie', status: 'Niezweryfikowana', sugg_score: 14},
];

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
              private location: Location) {
  }

  displayedColumns: string[] = ['select', 'begin_date', 'end_date', 'mnt_group', 'status', 'sugg_score'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  selection = new SelectionModel<PeriodicElement>(true, []);

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
    this.dataSource.paginator = this.paginator;
    if (ELEMENT_DATA.length == 0) this.openErrorDialog();
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
