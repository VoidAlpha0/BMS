import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AddBusDetailsComponent } from "./add-bus-details/add-bus-details.component";
import { AddPassengersComponent } from "./add-passengers/add-passengers.component";
import { AdminHomeComponent } from "./admin-home/admin-home.component";
import { AdminLoginComponent } from "./admin-login/admin-login.component";
import { ErrorComponent } from "./error/error.component";
import { HomeComponent } from "./home/home.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { ShowUserBookingsComponent } from "./show-user-bookings/show-user-bookings.component";
import { UpdateBusComponent } from "./update-bus/update-bus.component";
import { UpdatePassengerComponent } from "./update-passenger/update-passenger.component";
import { UpdateUserDetailsComponent } from "./update-user-details/update-user-details.component";
import { UserHomeComponent } from "./user-home/user-home.component";
import { UserLoginComponent } from "./user-login/user-login.component";
import { UserRegisterComponent } from "./user-register/user-register.component";
import { ViewAllBusDetailsComponent } from "./view-all-bus-details/view-all-bus-details.component";
import { ViewUserDetailsComponent } from "./view-user-details/view-user-details.component";
import { LocationComponent } from "./location/location.component";
import { UpdateLocationComponent } from "./update-location/update-location.component";
import { AddLocationComponent } from "./add-location/add-location.component";
import { EditUserDetailsComponent } from "./edit-user-details/edit-user-details.component";
import { UserModificationComponent } from "./user-modification/user-modification.component";


const routes: Routes = [
  { path: "", redirectTo: "/home", pathMatch: "full" },
  { path: "home", component: HomeComponent},
  { path: "userLogin", component: UserLoginComponent },
  { path: "addUser", component: UserRegisterComponent },
  { path: "error/:message", component: ErrorComponent },
  { path: "addBusDetails", component: AddBusDetailsComponent },
  { path: "allBussDetails", component: ViewAllBusDetailsComponent },
  { path: "adminHome", component: AdminHomeComponent },
  { path: "adminLogin", component: AdminLoginComponent },
  { path: "userHome", component: UserHomeComponent },
  { path: "getBookingByUser/:userId", component: ShowUserBookingsComponent },
  { path: "viewUser", component: ViewUserDetailsComponent },
  { path: "updateUser", component: UpdateUserDetailsComponent },
  { path: "updateBus/:busNumber", component: UpdateBusComponent },
  { path: "addPassengers/:busNumber", component: AddPassengersComponent },
  { path: "updatePassenger/:passengerId", component: UpdatePassengerComponent },
  { path: "locationList", component: LocationComponent },
  {path: "updateLocation/:locId", component: UpdateLocationComponent},
  {path: "addLocation",component: AddLocationComponent},
  { path: "edituser", component: EditUserDetailsComponent},
  { path: "user-modification", component: UserModificationComponent},
  { path: "**", component: PageNotFoundComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
