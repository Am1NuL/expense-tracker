import { WalletService } from './../../service/wallet.service';
import { Component, OnInit } from '@angular/core';
import { Wallet } from '../../model/wallet';
import { TransactionService } from '../../service/transaction.service';
import { MenuItem, MessageService } from 'primeng/api';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  overallData: any;
  monthlyData: any;
  wallets: Wallet[];
  items: MenuItem[];
  data: any;
  display: boolean = false;
  wallet: Wallet = new Wallet;
  addWalletForm: FormGroup;
  submitted = false;
  totalBalance: number;
  totalExpenses: number;
  totalIncome: number;

  activeItem: MenuItem;
  options = {
    responsive: false,
    maintainAspectRatio: false
  };

  constructor(
    private transactionService: TransactionService,
    private walletService: WalletService,
    private formBuilder: FormBuilder,
    private location: Location,
    private messageService: MessageService) {
    this.data = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label: 'Income',
          backgroundColor: '#23a634',
          borderColor: '#1E88E5',
          data: [65, 59, 80, 81, 56, 55, 40]
        },
        {
          label: 'Expense',
          backgroundColor: '#d11111',
          borderColor: '#7CB342',
          data: [28, 48, 40, 19, 86, 27, 90]
        }
      ]
    }

    this.overallData = {
      labels: ['Travel', 'Groceries', 'Bills', 'Shopping'],
      datasets: [
        {
          data: [120, 300, 268, 87],
          backgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56",
            "#B51FB0"
          ],
          hoverBackgroundColor: [
            "#706969",
            "#706969",
            "#706969",
            "#706969"
          ]
        }]
    };

    this.monthlyData = {
      labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
      datasets: [
        {
          label: 'Money Spent',
          data: [10, 20, 5, 200, 12],
          fill: true,
          borderColor: '#d11111'
        }
      ]
    };
  }

  ngOnInit() {
    this.addWalletForm = this.formBuilder.group({
      name: ['', Validators.required],
      totalWealth: ''
    });

    this.transactionService.findAllWallets().subscribe(data => {
      this.wallets = data;
      this.items = this.wallets
        .map(obj => ({
          label: obj.name,
          icon: 'pi pi-money-bill',
          amount: obj.totalWealth,
          routerLink: ['/wallets/' + obj.id + "/transactions"]
        }))
    },
      error => console.log(error)
    )

    this.transactionService.getTotalExpenses().subscribe(data => {
      this.totalExpenses = data;
    })

    this.transactionService.getTotalIncome().subscribe(data => {
      this.totalIncome = data;
    })

    this.walletService.getTotalBalance().subscribe(data => {
      this.totalBalance = data;
    })
  }

  // convenience getter for easy access to form fields
  get f() { return this.addWalletForm.controls; }

  onSubmit() {
    this.submitted = true;
    console.log("dsadda");

    this.wallet.name = this.addWalletForm.controls['name'].value;
    this.wallet.totalWealth = this.addWalletForm.controls['totalWealth'].value;

    this.walletService.save(this.wallet)
      .pipe(first())
      .subscribe(data => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'New wallet added' });
      });

      this.closeDialog();
      location.reload();
  }

  showDialog() {
    this.display = true;
  }

  closeDialog() {
    this.display = false;
  }
}
