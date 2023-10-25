import { Component, OnInit } from "@angular/core";
import { AbstractControl, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";
import { location } from "../model/location";
import { LocationserviceService } from "../services/locationservice.service";

@Component({
  selector: "app-user-home",
  templateUrl: "./user-home.component.html",
  styleUrls: ["./user-home.component.css"],
})
export class UserHomeComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private service:LocationserviceService
  ) {}
  location:any;
  bus = null;
  notFound = null;
  found = false;
  userId = null;
  user = null;
  formatLocation(location: any): string {
    return `${location.terminal} ${location.city} ${location.state}`;
  }

  /* bus search form */

  searchForm = this.formBuilder.group({
    arrivalBusstop: [null, Validators.required],
    departureBusstop: [null, Validators.required],
    date: [null, [Validators.required, this.dateValidator]],
  });

  ngOnInit(): void {
    this.userId = localStorage.getItem("userId");
    console.log(this.userId);
    if (this.userId == null) {
      this.router.navigate(["/error", "not logged in, login to continue"]);
    } else {
      this.userId = parseInt(this.userId);
      this.userService.getUser(this.userId).subscribe(
        (data) => {
          this.user = data;
        },
        (error) => {
          this.router.navigate(["/error", "not logged in, login to continue"]);
        }
      );
    }
    let response = this.service.getLocations();
    response.subscribe((data:any)=>this.location=data);
  }

  /* ------method to search bus--------- */

  search() {
    console.log(this.searchForm.get("date").value);
    this.userService
      .searchBus(
        this.searchForm.get("departureBusstop").value,
        this.searchForm.get("arrivalBusstop").value,
        this.searchForm.get("date").value
      )
      .subscribe(
        (data) => {
          this.bus = data;
          console.log(this.bus);
          if (this.bus) {
            this.notFound = null;
            this.found = true;
          } else {
            this.notFound = "no bus found";
            this.found = false;
          }
        },
        (error) => {
          this.notFound = "no bus found";
          this.found = false;
        }
      );
  }

  /* method to validate date */

  dateValidator(control: AbstractControl) {
    const inputDate = new Date(control.value);
    const currentDate = new Date();

    if (inputDate < currentDate) {
      return { dateError: true };
    }
    return null;
  }

  logout() {
    localStorage.removeItem("userId");
    this.router.navigate(["/userLogin"]);
  }

  getBookings() {
    this.router.navigate(["/getBookingByUser", this.userId]);
  }

  addPassengers(){
    this.router.navigate(["/addPassengers",this.bus.busNumber]);
  }
}

