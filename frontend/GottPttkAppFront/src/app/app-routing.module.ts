import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {SendTripsForVerificationComponent} from "./send-trips-for-verification/send-trips-for-verification.component";


const routes : Routes = [
  {path : 'dashboard', component: DashboardComponent},
  {path : '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path : 'sendTripsForVerification', component: SendTripsForVerificationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
