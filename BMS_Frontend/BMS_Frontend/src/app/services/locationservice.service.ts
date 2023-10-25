import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class LocationserviceService {

  constructor(private http : HttpClient) { }
    httpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
    });
    
  
   options = { headers: this.httpHeaders };
   public getLocations(){
    let location =this.http.get('http://localhost:8086/location/getLocations')
    console.log(location)
    return this.http.get('http://localhost:8086/location/getLocations')
  }

  removeLocation(locid) : Observable<any> {
    return this.http.delete(`http://localhost:8086/location/delete/${locid}`).pipe
    (catchError(this.errorHandler));
  }
  errorHandler(error) {
    let errorMessage = "";
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

  addLocation(location): Observable<Object> {
    return this.http.post(`http://localhost:8086/location/addlocation`, JSON.stringify(location),this.options).pipe(catchError(this.errorHandler));
  }

  getLocationById(id:any) {
    return this.http
      .get(`http://localhost:8086/location/getLocation/`+id)
      .pipe(catchError(this.errorHandler));
  }

  updateLocation(id:number,location:any):Observable<Object>{
    return this.http.put<any>(`http://localhost:8086/location/updateLocation/${id}`,location);
  }
}