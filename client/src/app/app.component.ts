import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component } from '@angular/core';
import {Location} from '@angular/common';
import {MenuItem} from 'primeng/api';
import { Wallet } from './model/wallet';
import { TransactionService } from './service/transaction.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: string;

  constructor() {
    this.title = 'Expense Tracker';
  }
    ngOnInit() {
    }
}
