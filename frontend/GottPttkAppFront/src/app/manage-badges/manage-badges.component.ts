import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {MatTableDataSource} from "@angular/material/table";
import {ChoiceDialogComponent} from "../dialogs/choice-dialog/choice-dialog.component";

export interface PeriodicElement {
  position: number;
  name: string;
  earn_date: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Mała Brązowa', earn_date: '20-11-2019'},
  {position: 2, name: 'Popularna', earn_date: '20-11-2019'},
];

@Component({
  selector: 'app-manage-badges',
  templateUrl: './manage-badges.component.html',
  styleUrls: ['./manage-badges.component.css']
})
export class ManageBadgesComponent implements OnInit {

  constructor(private dialog: MatDialog,
              private router: Router,
              private location: Location) { }

  displayedColumns: string[] = ['name','earn_date'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  ngOnInit() {
    this.checkIfNextBadgeNeeded()
  }

  checkIfNextBadgeNeeded(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: 'Wybierz następną zdobywaną odznakę',
      desc: 'Możesz wybrać, na którą odznakę będziesz teraz zbierać punkty w swojej książeczce.',
      options: ['Za wytrwałość - mała','Za wytrwałość - inna']
    };

    dialogConfig.panelClass = 'custom-dialog-background';

    const dialogRef = this.dialog.open(ChoiceDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      if(result == 'Za wytrwałość - mała') {
        this.goBack();
      }})
  }

  goBack(): void {
    this.location.back();
  }
}
