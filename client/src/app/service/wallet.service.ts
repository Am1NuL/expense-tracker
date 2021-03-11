import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {AppSettings} from '../utils/app-settings';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Wallet } from '../model/wallet';
import { first } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  constructor(private http : HttpClient){}

  public save(wallet: Wallet): Observable<Wallet> {
    if(wallet.name == "" ){
      return;
    }

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=UTF-8');

    // console.log(wallet.name);
    // console.log('this', this)
    // console.log('this.http.post(', this.http.post<any>('http://localhost:8080/wallets', {}).pipe(first()).subscribe(_=>{}, _ =>{}))
    
    return this.http.post<Wallet>(AppSettings.API_ENDPOINT + 'wallets', wallet, { headers: headers });
  }

  public getTotalBalance() {
    return this.http.get<number>(AppSettings.API_ENDPOINT + "total-balance");
  }
}
