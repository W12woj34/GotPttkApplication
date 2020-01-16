import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {TripsVerifyComponent} from "./trips-verify/trips-verify.component";

const routes : Routes = [
  {path : 'dashboard', component: DashboardComponent},
  {path : '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path : 'sendTripsForVerification', component: TripsVerifyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
