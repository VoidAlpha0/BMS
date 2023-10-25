import { Passenger } from "./passenger";

export class booking{
    bookingDate : string;

    bookingId: number;
    bookingTime: string;
    busNumber: number;

    ownerId: number;

    passengerCount: number;

   passenger:Passenger;
    
    status: boolean;
    totalCost: number;
}

