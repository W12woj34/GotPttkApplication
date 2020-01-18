import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-choice-dialog',
  templateUrl: './choice-dialog.component.html',
  styleUrls: ['./choice-dialog.component.css']
})
export class ChoiceDialogComponent implements OnInit {

  title: string;
  description: string;
  selected: string;
  options: string[];

  constructor(
    private dialogRef: MatDialogRef<ChoiceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
    this.description = data.desc;
    this.title = data.title;
    this.options = data.options;
  }

  ngOnInit() {
  }

}
