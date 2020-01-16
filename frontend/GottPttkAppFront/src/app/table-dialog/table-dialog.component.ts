import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PeriodicElement} from "../trip-table/trip-table.component";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-table-dialog',
  templateUrl: './table-dialog.component.html',
  styleUrls: ['./table-dialog.component.css']
})
export class TableDialogComponent implements OnInit {

  title: string;
  description: string;
  tableTitle: string;
  dataSource: MatTableDataSource<PeriodicElement>;

  displayedColumns: string[] = ['begin_date', 'end_date', 'mnt_group', 'status', 'sugg_score'];

  constructor(
    private dialogRef: MatDialogRef<TableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
    this.description = data.desc;
    this.title = data.title;
    this.tableTitle = data.tableTitle;
    this.dataSource = new MatTableDataSource<PeriodicElement>(data.dataSource);
  }

  ngOnInit() {
  }

}
