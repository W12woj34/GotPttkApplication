import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

export interface PeriodicElement {
  position: number;
  date: string;
  category: string;
  start_point: string;
  end_point: string;
  is_back: string;
  is_repeated: string;
  points: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, date: '02-11-2019', category: 'Zdef.', start_point: 'Przedbórze', end_point: 'Fajna Ryba', is_back: 'Nie', is_repeated: 'Nie', points: 10},
  {position: 2, date: '02-11-2019', category: 'Zdef.', start_point: 'Fajna Ryba', end_point: 'Kozłowa', is_back: 'Nie', is_repeated: 'Tak', points: 0},
  {position: 3, date: '02-11-2019', category: 'Zdef.', start_point: 'Kozłowa', end_point: 'Buczyna', is_back: 'Nie', is_repeated: 'Nie', points: 6},
  {position: 4, date: '02-11-2019', category: 'Niezdef.', start_point: 'Buczyna', end_point: 'Rączki', is_back: 'Tak', is_repeated: 'Nie', points: 3},
];

@Component({
  selector: 'app-verify-trip-details',
  templateUrl: './verify-trip-details.component.html',
  styleUrls: ['./verify-trip-details.component.css']
})
export class VerifyTripDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private location: Location) { }

  displayedColumns: string[] = ['date', 'category', 'start_point', 'end_point', 'is_back', 'is_repeated', 'points'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  goBack(): void {
    this.location.back();
  }

}
