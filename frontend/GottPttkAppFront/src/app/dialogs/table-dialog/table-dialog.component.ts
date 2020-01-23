import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';
import {Trip} from '../../_models/Trip/trip';

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
  dataSource: MatTableDataSource<Trip>;

  displayedColumns: string[] = ['beginDate', 'endDate', 'mntGroups', 'status', 'suggScore'];

  constructor(
    private dialogRef: MatDialogRef<TableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
    this.dialogType = data.dialogType;
    this.title = data.title;
    this.description = data.description;
    this.tableTitle = data.tableTitle;
    this.dataSource = new MatTableDataSource<Trip>(data.dataSource);
  }

  ngOnInit() {
  }

}
