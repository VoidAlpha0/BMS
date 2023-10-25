import { Component, OnInit } from '@angular/core';
import { AdminService } from "../services/admin.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user-details',
  templateUrl: './edit-user-details.component.html',
  styleUrls: ['./edit-user-details.component.css']
})
export class EditUserDetailsComponent implements OnInit {

  adminId = null;
  bookings=null;
  constructor(private adminService: AdminService, private router: Router) {}


  ngOnInit(): void {
    this.adminId = localStorage.getItem("adminId");
    if (this.adminId == null) {
      this.router.navigate(["/error", "login to continue"]);
    } else {
      this.adminService.getAllBookings().subscribe(
        (data) => {
          this.bookings=data;
        },
        (error) => {
          this.router.navigate(["/error", "some error occured"]);
        }
      );
    }

  }


  updateStatus(bookingId){
    console.log(bookingId);
    this.adminService.updateBookingStatus(bookingId).subscribe(
      (data) => {
        this.adminService.getAllBookings().subscribe(
          (data) => {
            this.bookings=data;
          }
        )
      },
      (error) => {
        this.router.navigate(["/error", "some error occured"]);
      }
    );
  }

}
