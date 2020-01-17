import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {MatCheckboxModule} from "@angular/material/checkbox";
import { MatPaginatorModule } from '@angular/material';
import {MatButtonModule} from "@angular/material/button";
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {MatMenuModule} from "@angular/material/menu";
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import {MatPaginatorIntl} from "@angular/material/paginator";
import {MatPaginatorIntlPol} from "./mat-paginator-intl-pol";
import {MatDialogModule} from "@angular/material/dialog";
import {SimpleErrorDialogComponent} from "./dialogs/simple-error-dialog/simple-error-dialog.component";
import { TableDialogComponent } from './dialogs/table-dialog/table-dialog.component';
import { SendTripsForVerificationComponent } from './send-trips-for-verification/send-trips-for-verification.component';
import { VerifyTripsMainComponent } from './verify-trips-main/verify-trips-main.component';
import { VerifyTripDetailsComponent } from './verify-trip-details/verify-trip-details.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import { YesNoDialogComponent } from './dialogs/yes-no-dialog/yes-no-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    DashboardComponent,
    WorkInProgressComponent,
    SimpleErrorDialogComponent,
    TableDialogComponent,
    SendTripsForVerificationComponent,
    VerifyTripsMainComponent,
    VerifyTripDetailsComponent,
    YesNoDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatButtonModule,
    MatMenuModule,
    AppRoutingModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
  ],
  providers: [{provide: MatPaginatorIntl, useClass: MatPaginatorIntlPol}],
  bootstrap: [AppComponent],
  entryComponents : [SimpleErrorDialogComponent, TableDialogComponent, YesNoDialogComponent]
})
export class AppModule { }
