import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-yes-no-dialog',
  templateUrl: './yes-no-dialog.component.html',
  styleUrls: ['./yes-no-dialog.component.css']
})
export class YesNoDialogComponent implements OnInit {

  title: string;
  description: string;
  nolink: string;
  yeslink: string;

  constructor(
    private dialogRef: MatDialogRef<YesNoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
    this.description = data.desc;
    this.title = data.title;
    this.nolink = data.nolink;
    this.yeslink = data.yeslink;
  }

  ngOnInit() {
  }

}
