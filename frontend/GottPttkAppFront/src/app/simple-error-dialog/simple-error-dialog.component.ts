import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-simple-error-dialog',
  templateUrl: './simple-error-dialog.component.html',
  styleUrls: ['./simple-error-dialog.component.css']
})
export class SimpleErrorDialogComponent implements OnInit {

  title: string;
  description: string;

  constructor(
    private dialogRef: MatDialogRef<SimpleErrorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
    this.description = data.desc;
    this.title = data.title;
  }

  ngOnInit() {
  }

}
