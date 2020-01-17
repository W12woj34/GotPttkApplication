import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {PeriodicElement} from "../../trips/send-trips-for-verification/send-trips-for-verification.component";

@Component({
  selector: 'app-table-dialog',
  templateUrl: './table-dialog.component.html',
  styleUrls: ['./table-dialog.component.css']
})
export class TableDialogComponent implements OnInit {

  dialogType: string;

  title: string;
  description: string;
  tableTitle: string;
  dataSource: MatTableDataSource<PeriodicElement>;

  displayedColumns: string[] = ['begin_date', 'end_date', 'mnt_group', 'status', 'sugg_score'];

  constructor(
    private dialogRef: MatDialogRef<TableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
    this.dialogType = data.dialogType;
    this.title = data.title;
    this.description = data.description;
    this.tableTitle = data.tableTitle;
    this.dataSource = new MatTableDataSource<PeriodicElement>(data.dataSource);
  }

  ngOnInit() {
  }

}
