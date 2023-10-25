import { Component, OnInit } from "@angular/core";
import { AbstractControl, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UserService } from "../services/user.service";

@Component({
  selector: "app-user-register",
  templateUrl: "./user-register.component.html",
  styleUrls: ["./user-register.component.css"],
})
export class UserRegisterComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) { }

  private userData = null;

  /* -------register form for user------ */
  userRegisterForm = this.formBuilder.group(
    {
      uname: [null, Validators.required],
      userName: [null, Validators.required],
      email: [null, [Validators.required, this.emailValidator]], // integrating emailValidator
      phone: [
        null,
        [Validators.required, Validators.pattern("[6789][0-9]{9}")],
      ],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, [Validators.required]],
    },
    { validators: this.passwordValidator }
  );

  /**
   * ------- function to check password and confirm password field are same---------
   */
  passwordValidator(control: AbstractControl): { [key: string]: any } | null {
    const pass = control.get("password");
    const cnfm = control.get("confirmPassword");
    if (pass && cnfm && pass.value !== cnfm.value) {
      return { mismatch: true };
    } else {
      return null;
    }
  }

  /* ---------function to register-------- */
  submit() {
    this.userRegisterForm.removeControl("confirmPassword");
    this.userService.addUser(this.userRegisterForm.value).subscribe(
      (data) => {
        this.userData = data;
        localStorage.setItem("userId", this.userData.userId);
        this.router.navigate(["/userHome"]);
      },
      (error) => {
        this.router.navigate(["/error", "invalid data provided or unable to connect"]);
      }
    );
  }

  /* ------ email validator function ------- */
  emailValidator(control: AbstractControl): { [key: string]: any } | null {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (control.value && !emailRegex.test(control.value)) {
      return { invalidEmail: true };
    }
    return null;
  }

  ngOnInit(): void {}
}
