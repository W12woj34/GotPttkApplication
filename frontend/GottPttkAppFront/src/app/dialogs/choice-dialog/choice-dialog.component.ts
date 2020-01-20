import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {BadgeService} from "../../_services/Badge/badge.service";

@Component({
  selector: 'app-choice-dialog',
  templateUrl: './choice-dialog.component.html',
  styleUrls: ['./choice-dialog.component.css']
})
export class ChoiceDialogComponent implements OnInit {

  title: string;
  description: string;
  selected: string;
  userID: string;

  options : string[] = [];
  showSpinner = false;

  constructor(
    private dialogRef: MatDialogRef<ChoiceDialogComponent>,
    private badgeService: BadgeService,
    @Inject(MAT_DIALOG_DATA) data) {
    this.description = data.desc;
    this.title = data.title;
    this.userID = data.userID;
  }

  ngOnInit() {
    this.getPossibleBadges()
  }

  getPossibleBadges(){
    this.badgeService.getPossibleBadges(this.userID).subscribe(badges => {
      badges.forEach(badge => {
        this.options.push(badge.nazwa);
      });
      this.showSpinner = false;
    })
  }
}
