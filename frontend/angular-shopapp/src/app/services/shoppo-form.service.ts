import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShoppoFormService {

  constructor() { }

  getCreditCardMonths(): Observable<number[]>{
    let data: number[] = [];
    for (let i = 1; i<=12;i++){
      data.push(i);
    }
    return of(data);
  }

  getCreditCardYears():Observable<number[]>{
    let data: number[] = [];
    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear+10;

    for (let i = startYear; i<=endYear;i++){
      data.push(i);
    }
    return of(data);
  }
}
