import { Component, OnInit } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AdminService } from '../services/admin.service';


@Component({
  selector: "app-admin-login",
  templateUrl: "./admin-login.component.html",
  styleUrls: ["./admin-login.component.css"],
})
export class AdminLoginComponent implements OnInit {
  constructor(
    private router: Router,
    private adminService: AdminService,
    private formBuilder: FormBuilder
  ) {}

  failure = { value: false };
  adminData = null;



    /* ----login form--- */

  loginForm = this.formBuilder.group({

    adminUName : [null,Validators.required],
    password : [null,[Validators.required,Validators.minLength(8)]]

  });

  ngOnInit(): void {}


  /* ----method for admin login------- */

  submit(){
    
    this.adminService.adminLogin(this.loginForm.value).subscribe(
      (data) => {
        this.adminData = data;
        this.failure.value = false;
        localStorage.setItem("adminUName",this.adminData.adminUName);
        this.router.navigate(["/adminHome"]);
      },
      (error) => {
        this.failure.value = true;
        this.loginForm.reset();
      }
    );
  }
}