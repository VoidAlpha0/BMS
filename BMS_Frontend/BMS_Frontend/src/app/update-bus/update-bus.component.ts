import { Component, OnInit } from "@angular/core";
import { AbstractControl, FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { AdminService } from "../services/admin.service";
import { LocationserviceService } from "../services/locationservice.service";
import { BusDetails } from "../model/bus.component";

@Component({
  selector: "app-update-bus",
  templateUrl: "./update-bus.component.html",
  styleUrls: ["./update-bus.component.css"],
})
export class UpdateBusComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private adminService: AdminService,
    private service: LocationserviceService
  ) {}

  adminUName = null;
  adminDetails = null;
  busNumber = null;
  bus: BusDetails = null;
  location: any = null;

  formatLocation(location: any): string {
    return `${location.terminal} ${location.city} ${location.state}`;
  }

  busForm = this.formBuilder.group({
    departureBusstop: [null, Validators.required],
    arrivalBusstop: [null, Validators.required],
    departureDate: [null, [Validators.required, this.departureDateValidator]],
    arrivalDate: [null, [Validators.required]],
    availableSeats: [
      null,
      [Validators.required, Validators.max(100), Validators.min(0)],
    ],
    arrivalTime: [null, Validators.required],
    departureTime: [null, Validators.required],
    busVendor: [null, Validators.required],
    cost: [null, [Validators.required, Validators.min(1), Validators.max(10000)],
    ],
  }, { validators: this.arrivalDateValidator });

  ngOnInit(): void {
    this.adminUName = localStorage.getItem("adminUName");
    if (this.adminUName == null) {
      this.router.navigate(["/error", "admin not logged in login to continue"]);
    } else {
      this.adminUName = this.adminUName;
      this.adminService.getAdminDetails(this.adminUName).subscribe(
        (data) => {
          this.adminDetails = data;
          console.log(this.adminDetails);
        }
      );
      this.route.paramMap.subscribe((param: ParamMap) => {
        this.busNumber = parseInt(param.get("busNumber"));
      });
    }
    let response = this.service.getLocations();
    response.subscribe((data: any) => (this.location = data));
    console.log(this.location);

    let busResponse = this.adminService.getBusById(this.busNumber);
    busResponse.subscribe((data: any) => {
      this.bus = data;
      console.log(data);
      console.log(this.bus.departureBusstop);
      this.busForm.patchValue({
        departureBusstop: this.bus.departureBusstop,
        arrivalBusstop: this.bus.arrivalBusstop,
        departureDate: this.bus.departureDate,
        arrivalDate: this.bus.arrivalDate,
        availableSeats: this.bus.availableSeats,
        arrivalTime: this.bus.arrivalTime,
        departureTime: this.bus.departureTime,
        busVendor: this.bus.busVendor,
        cost: this.bus.cost,
      });
    });
  }

  logout() {
    localStorage.removeItem("adminUName");
    this.router.navigate(["/adminLogin"]);
  }

  departureDateValidator(control: AbstractControl) {
    const inputDate = new Date(control.value);
    const currentDate = new Date();
    if (inputDate < currentDate) {
      return { dateError: true };
    }
    return null;
  }

  arrivalDateValidator(control: AbstractControl) {
    const depDate = control.get("departureDate");
    const arrDate = control.get("arrivalDate");
    if (depDate && arrDate && new Date(depDate.value) > new Date(arrDate.value)) {
      return { arrivalDateError: true };
    } else {
      return null;
    }
  }

  onSubmit() {
    let data = this.busForm.value;
    if (this.busNumber != null) {
      data.busNumber = this.busNumber;
    }
    this.adminService.modifyBus(data).subscribe(
      (data) => {
        this.router.navigate(["/allBussDetails"]);
      },
      (error) => {
        this.router.navigate(["/error", "unable to update"]);
      }
    );
  }
}
