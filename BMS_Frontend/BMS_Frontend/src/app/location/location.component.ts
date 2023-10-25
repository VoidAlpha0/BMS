import { Component, OnInit } from '@angular/core';
import { location } from "../model/location";
import { LocationserviceService } from '../services/locationservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})

export class LocationComponent implements OnInit {
  location=null;
  adminId=null;
  constructor(
    private service: LocationserviceService,
    private router : Router
  ) {}
  // locations: location[] =[]
  ngOnInit(): void {
    // let response = this.service.getLocations();
    // response.subscribe((data:any)=>this.locations=data);

    this.adminId = localStorage.getItem("adminId");
    if (this.adminId == null) {
      this.router.navigate(["/error", "login to continue"]);
    } else {
      this.service.getLocations().subscribe(
        (data) => {
          this.location = data;
        },
        (error) => {
          this.router.navigate(["/error", "some error occured"]);
        }
      );
    }
    
  }

  updateLocation(locid) {
    this.router.navigate(["/updateLocation", locid]);
  }
  removeLocation(locid){
    if (confirm("are you sure you want to delete?")) {
      this.service.removeLocation(locid).subscribe(
        (data) => {
          this.service.getLocations().subscribe(
            (data) => {
              this.location = data;
            },
            (error) => {
              this.router.navigate(["/error", "some error occured"]);
            }
          );
        },
        (error) => {
          this.router.navigate(["/error", "unable to delete"]);
        }
      );
    }
  }

}
