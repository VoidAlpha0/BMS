import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';


@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  constructor(private router:Router,private adminService:AdminService) { }

  adminId = null;
  adminUName=null;
  adminDetails = null;

  ngOnInit(): void {
    this.adminUName = localStorage.getItem("adminUName");
    if (this.adminUName == null){
      this.router.navigate(["/error","admin not logged in login to continue"]);
    }
    this.adminUName = this.adminUName.toString();
    this.adminService.getAdminDetails(this.adminUName).subscribe(
      (data) => {
        this.adminDetails = data;
      },
      (error) => {
        this.router.navigate([
          "/error",
          "admin not logged in login to continue",
        ]);
      }
    );
  }

  logout(){
    localStorage.removeItem("adminUName");
    this.router.navigate(["/adminLogin"]);
  }
}