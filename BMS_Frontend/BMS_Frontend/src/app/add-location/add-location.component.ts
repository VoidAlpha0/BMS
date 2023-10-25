import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder,FormGroup, Validators } from "@angular/forms";

import { LocationserviceService } from '../services/locationservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent implements OnInit {
  // locationForm: FormGroup;
  constructor( 
    private formBuilder: FormBuilder,
    private service: LocationserviceService,
    private router: Router
    ) 
    {
     }

  ngOnInit(): void {
  }


  locationForm = this.formBuilder.group({
    terminal: [null, Validators.required],
    city: [null, Validators.required],
    state: [null, Validators.required]
  })

  onSubmit() {
    console.log(this.locationForm.value)
    this.service.addLocation(this.locationForm.value).subscribe(
      data => {
        console.log(data);
        this.router.navigate(["/locationList"]);
      },
      error => {
        this.router.navigate(["/error","error occured unable to add"]);
      }
    );
  }
}
