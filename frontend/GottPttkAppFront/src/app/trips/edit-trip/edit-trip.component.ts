import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {SimpleErrorDialogComponent} from "../../dialogs/simple-error-dialog/simple-error-dialog.component";
import { Location } from '@angular/common';

@Component({
  selector: 'app-edit-trip',
  templateUrl: './edit-trip.component.html',
  styleUrls: ['./edit-trip.component.css']
})
export class EditTripComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location) {

  }

  showSpinner = false;
  startDate;
  endDate;

  displayedColumns: string[] = ['date', 'category', 'start_point', 'end_point', 'is_back', 'is_repeated', 'points', 'actions'];
  dataSource;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  submitTrip(){
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
