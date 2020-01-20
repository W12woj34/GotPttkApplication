import {Component, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MatTableDataSource} from "@angular/material/table";
import {ChoiceDialogComponent} from "../dialogs/choice-dialog/choice-dialog.component";
import {BadgeService} from "../_services/Badge/badge.service";
import {Badge} from "../_models/Badge/badge";

@Component({
  selector: 'app-manage-badges',
  templateUrl: './manage-badges.component.html',
  styleUrls: ['./manage-badges.component.css']
})
export class ManageBadgesComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location,
              private badgeService: BadgeService) {
  }

  displayedColumns: string[] = ['badge_name', 'earnDate'];
  dataSource;
  currentBadge;
  maxPoints;
  earnedPoints;
  showSpinner = false;

  ngOnInit() {
    this.showSpinner = true;
    this.checkIfNextBadgeNeeded()
  }

  checkIfNextBadgeNeeded() {
    this.badgeService.getBadgeForUserOfStatus('16', 0).subscribe(badge => {
      if (badge.length == 0) {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;

        dialogConfig.data = {
          title: 'Wybierz następną zdobywaną odznakę',
          desc: 'Możesz wybrać, na którą odznakę będziesz teraz zbierać punkty w swojej książeczce.',
          userID: '16',
        };

        dialogConfig.panelClass = 'custom-dialog-background';

        const dialogRef = this.dialog.open(ChoiceDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(result => {
          if(result != 'canceled'){
            this.setSelectedBadge(result);
          } else {
            this.goBack();
          }
        })
      } else {
        this.currentBadge = badge[0].badge_name;
        this.earnedPoints = badge[0].points;
        this.getEarnedBadges();
        this.getMaxPoints();
      }
    })
  }

  getEarnedBadges() {
    this.badgeService.getBadgeForUserOfStatus('16', 1).subscribe(badges => {
      this.dataSource = new MatTableDataSource<Badge>(badges);
    })
  }

  getMaxPoints() {
    this.badgeService.getBadgeInfo(this.currentBadge).subscribe(info => {
      this.maxPoints = info.wymaganePunkty;
      this.showSpinner = false;
    })
  }

  setSelectedBadge(selectedBadge: string){
    this.badgeService.setSelectedBadge(selectedBadge,'16').subscribe(() => {
      window.location.reload();
    });
  }

  goBack(): void {
    this.location.back();
  }
}
