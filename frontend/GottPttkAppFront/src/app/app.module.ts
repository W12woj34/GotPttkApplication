import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatPaginatorModule } from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {MatMenuModule} from '@angular/material/menu';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import {MatPaginatorIntl} from '@angular/material/paginator';
import {MatPaginatorIntlPol} from './mat-paginator-intl-pol';
import {MatDialogModule} from '@angular/material/dialog';
import {SimpleErrorDialogComponent} from './dialogs/simple-error-dialog/simple-error-dialog.component';
import { TableDialogComponent } from './dialogs/table-dialog/table-dialog.component';
import { SendTripsForVerificationComponent } from './trips/send-trips-for-verification/send-trips-for-verification.component';
import { VerifyTripsMainComponent } from './trips/verify-trips-main/verify-trips-main.component';
import { VerifyTripDetailsComponent } from './trips/verify-trip-details/verify-trip-details.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import { YesNoDialogComponent } from './dialogs/yes-no-dialog/yes-no-dialog.component';
import { ManageTripsComponent } from './trips/manage-trips/manage-trips.component';
import { AddTripComponent } from './trips/add-trip/add-trip.component';
import { EditTripComponent } from './trips/edit-trip/edit-trip.component';
import { ManageBadgesComponent } from './manage-badges/manage-badges.component';
import { ChoiceDialogComponent } from './dialogs/choice-dialog/choice-dialog.component';
import {MatSelectModule} from '@angular/material/select';
import { BackBarComponent } from './back-bar/back-bar.component';
import { HttpClientModule } from '@angular/common/http';
import { LoadingSpinnerComponent } from './loading-spinner/loading-spinner.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { AddRouteDialogComponent } from './dialogs/add-route-dialog/add-route-dialog.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';


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
    YesNoDialogComponent,
    ManageTripsComponent,
    AddTripComponent,
    EditTripComponent,
    ManageBadgesComponent,
    ChoiceDialogComponent,
    BackBarComponent,
    LoadingSpinnerComponent,
    AddRouteDialogComponent
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
    FormsModule,
    MatSelectModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [{provide: MAT_DATE_LOCALE, useValue: 'pl-PL'}, {provide: MatPaginatorIntl, useClass: MatPaginatorIntlPol},
    MatDatepickerModule, MatNativeDateModule],
  bootstrap: [AppComponent],
  entryComponents : [SimpleErrorDialogComponent, TableDialogComponent, YesNoDialogComponent, ChoiceDialogComponent,
    AddRouteDialogComponent]
})
export class AppModule { }
