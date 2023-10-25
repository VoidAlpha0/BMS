
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class AdminService {
  constructor(private http: HttpClient) {}


  private baseUrl = "http://localhost:8086/admin";

  httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
  });

  options = { headers: this.httpHeaders };

  adminLogin(admin) {
    console.log(JSON.stringify(admin));
    return this.http
      .post(
        `${this.baseUrl}/adminLogin`,
        JSON.stringify(admin),
        this.options
      )
      .pipe(catchError(this.errorHandler));
  }



  getAdminDetails(adminUname: string): Observable<any> {
    // Create an instance of HttpParams and set the adminUname parameter
    const params = new HttpParams().set('adminUname', adminUname);

    return this.http
      .get(`${this.baseUrl}/getAdminByUname`, { params })
      .pipe(catchError(this.errorHandler));
  }

  addBus(bus): Observable<Object> {
    return this.http.post(`${this.baseUrl}/addBusDetails`, JSON.stringify(bus),this.options).pipe(catchError(this.errorHandler));
  }

  modifyBus(bus): Observable<Object> {
    return this.http.post(`${this.baseUrl}/updateBusDetails`, JSON.stringify(bus),this.options).pipe(catchError(this.errorHandler));
  }

  removeBus(busNo): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteBusDetails/${busNo}`).pipe(catchError(this.errorHandler));
  }

  viewAllBus(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllBusDetails`).pipe(catchError(this.errorHandler));
  }

  getAllBookings(): Observable<any>{
    return this.http.get(`${this.baseUrl}/getAllBookings`).pipe(catchError(this.errorHandler));
  }

  getBusById(busNumber): Observable<any>{
    console.log("get bus method called")
  return this.http.get(`${this.baseUrl}/getBusById/${busNumber}`).pipe(catchError(this.errorHandler));
  
  }

  updateBookingStatus(bookingId: number) {
    const url = `${this.baseUrl}/updatebookingStatusById/${bookingId}`;
    return this.http.put(url, null);
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
}
