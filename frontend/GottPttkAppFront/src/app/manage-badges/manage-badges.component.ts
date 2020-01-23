import {Component, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {MatTableDataSource} from '@angular/material/table';
import {ChoiceDialogComponent} from '../dialogs/choice-dialog/choice-dialog.component';
import {BadgeService} from '../_services/Badge/badge.service';
import {Badge} from '../_models/Badge/badge';
import {BadgeInfoService} from '../_services/BadgeInfo/badge-info.service';

@Component({
  selector: 'app-manage-badges',
  templateUrl: './manage-badges.component.html',
  styleUrls: ['./manage-badges.component.css']
})
export class ManageBadgesComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute,
              private location: Location,
              private badgeService: BadgeService,
              private badgeInfoService: BadgeInfoService) {
  }

  displayedColumns: string[] = ['badgeName', 'earnDate'];
  dataSource;
  currentBadge;
  maxPoints;
  earnedPoints;
  showSpinner = false;

  ngOnInit() {
    this.showSpinner = true;
    this.checkIfNextBadgeNeeded();
  }

  checkIfNextBadgeNeeded() {
    this.badgeService.getBadgeForUserOfStatus(JSON.parse(localStorage.getItem('currentUser')).id, 0).subscribe(badge => {
      if (badge.length === 0) {
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;

        dialogConfig.data = {
          title: 'Wybierz następną zdobywaną odznakę',
          desc: 'Możesz wybrać, na którą odznakę będziesz teraz zbierać punkty w swojej książeczce.',
          userID: JSON.parse(localStorage.getItem('currentUser')).id,
        };

        dialogConfig.panelClass = 'custom-dialog-background';

        const dialogRef = this.dialog.open(ChoiceDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(result => {
          if (result !== 'canceled') {
            this.setSelectedBadge(result);
          } else {
            this.router.navigate(['/dashboard']);
          }
        });
      } else {
        this.currentBadge = badge[0].badgeName;
        this.earnedPoints = badge[0].points;
        this.getEarnedBadges();
        this.getMaxPoints();
      }
    });
  }

  getEarnedBadges() {
    this.badgeService.getBadgeForUserOfStatus(JSON.parse(localStorage.getItem('currentUser')).id, 1).subscribe(badges => {
      this.dataSource = new MatTableDataSource<Badge>(badges);
    });
  }

  getMaxPoints() {
    this.badgeInfoService.getBadgeInfo(this.currentBadge).subscribe(info => {
      this.maxPoints = info.wymaganePunkty;
      this.showSpinner = false;
    });
  }

  setSelectedBadge(selectedBadge: string) {
    this.badgeService.setSelectedBadge(selectedBadge, JSON.parse(localStorage.getItem('currentUser')).id).subscribe(() => {
      window.location.reload();
    });
  }

  goBack(): void {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

}
