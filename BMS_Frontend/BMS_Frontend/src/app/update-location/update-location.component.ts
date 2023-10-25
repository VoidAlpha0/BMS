import { Component, OnInit } from '@angular/core';
import { LocationserviceService } from '../services/locationservice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { location } from '../model/location';
import { FormBuilder, Validators } from "@angular/forms";

@Component({
  selector: 'app-update-location',
  templateUrl: './update-location.component.html',
  styleUrls: ['./update-location.component.css']
})
export class UpdateLocationComponent implements OnInit {
  loc:any;
  constructor(
    private formBuilder: FormBuilder,
    private service:LocationserviceService,
    private router:Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params =>{
      var locId=params.get('locId');
      console.log(locId)
      console.log("Ng noInit called")

      let response=this.service.getLocationById(locId).subscribe((data:any)=>this.loc=data)
      console.log(location)
  })}

  locationForm = this.formBuilder.group({
    terminal: [null, Validators.required],
    city: [null, Validators.required],
    state: [null, Validators.required]
  })


  onSubmit(id:number) {
    this.service.updateLocation(id,this.loc).subscribe(()=>
      this.router.navigate(['/locationList']));
}

}