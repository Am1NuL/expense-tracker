import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Transaction } from '../../model/transaction';
import { TransactionService } from '../../service/transaction.service';
import { Wallet } from '../../model/wallet';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../model/category';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';


@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css'],
  providers: [TransactionService],
  styles: [`
        .expense {
          border-left: 2px solid red;
          font-family:Tahoma; color:Red;
        }

        .income {
          border-left: 2px solid #00cc44;
          font-family:Tahoma; color:#00cc44;
        }
    `
  ]
})
export class TransactionListComponent implements OnInit {

  transaction: Transaction;
  transactions: Transaction[];
  walletId: number;
  cols: any[];
  months: any[];
  years: any[];
  selectedMonth: any;
  selectedYear: any;
  displayDialog: boolean = false;
  newTransaction: boolean;
  categories: Category[];
  wallets: Wallet[];
  selectedTransaction: Transaction = new Transaction();
  inputDate: Date;
  display: boolean;
  submitted: boolean;
  transactionForm: FormGroup;
  dateFilters: any;
  @ViewChild('dt', { static: false }) _table: Table;


  constructor(
    private transactionService: TransactionService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService,
    private formBuilder: FormBuilder) { }

  // ngAfterViewInit() {
  //   var _self = this;
  //   // this will be called from your templates onSelect event
  //   this._table.filterConstraints['dateRangeFilter'] = (value, filter): boolean => {
  //     // get the from/start value
  //     var s = _self.dateFilters[0].getTime();
  //     var e;
  //     // the to/end value might not be set
  //     // use the from/start date and add 1 day
  //     // or the to/end date and add 1 day
  //     if (_self.dateFilters[1]) {
  //       e = _self.dateFilters[1].getTime() + 86400000;
  //     } else {
  //       e = s + 86400000;
  //     }
  //     // compare it to the actual values
  //     return value.getTime() >= s && value.getTime() <= e;
  //   }
  // }

  ngOnInit() {
    this.transaction = new Transaction();

    this.transactionForm = this.formBuilder.group({
      amount: ['', Validators.required],
      date: ['', Validators.required],
      comment: '',
      category: ['', Validators.required],
      wallet: ['', Validators.required]
    });

    this.walletId = parseInt(this.route.snapshot.paramMap.get("id"));

    if (this.walletId) {
      this.transactionService.findAllByWallet(this.walletId).subscribe(data => {
        this.transactions = data;
      });
    }
    else {
      this.transactionService.findAll().subscribe(data => {
        this.transactions = data;
      });
    }

    this.transactionService.findAllCategories().subscribe(data => {
      this.categories = data;
    })

    this.transactionService.findAllWallets().subscribe(data => {
      this.wallets = data;
    })

    this.cols = [
      { field: 'amount', header: 'Amount' },
      { field: 'date', header: 'Date' },
      { field: 'comment', header: 'Comment' }
    ];

    this.months = [
      { label: 'January', value: '-01' },
      { label: 'December', value: '-12' }
    ];
    this.years = [
      { label: '2019', value: '-2019' },
      { label: '2018', value: '-2018' }
    ];
  }

  showDialogToAdd() {
    this.newTransaction = true;
    this.transaction = new Transaction();
    this.displayDialog = true;
  }

  showDialog() {
    this.newTransaction = true;
    this.display = true;
  }

  save() {
    let transactions = [...this.transactions];
    let transaction = this.transactionService.returnTransaction(this.transaction);
    if (this.newTransaction) {
      transactions.push(transaction);
      this.transactionService.save(transaction).subscribe(data => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Transaction added!' });
      });
    }
    else {
      transactions[this.transactions.indexOf(this.selectedTransaction)] = transaction;
      this.transactionService.update(transaction).subscribe(data => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Transaction updated!' });
      });
    }

    this.transactions = transactions;
    this.transaction = new Transaction();
    this.displayDialog = false;
  }

  onRowSelect(event) {
    this.newTransaction = false;
    this.transaction = this.cloneTransaction(event.data);
    this.displayDialog = true;
  }

  cloneTransaction(t: Transaction): Transaction {
    let transaction = new Transaction();
    for (let prop in t) {
      transaction[prop] = t[prop];
    }
    return transaction;
  }

  onSubmit() {
    this.submitted = true;

    this.transaction.amount = this.transactionForm.controls['amount'].value;
    this.transaction.date = this.transactionForm.controls['date'].value;
    this.transaction.comment = this.transactionForm.controls['comment'].value;
    this.transaction.category = this.transactionForm.controls['category'].value;
    this.transaction.wallet = this.transactionForm.controls['wallet'].value;

    let transactions = [...this.transactions];
    let transaction = this.transactionService.returnTransaction(this.transaction);
    if (this.newTransaction) {
      transactions.push(transaction);
      this.transactionService.save(transaction).subscribe(data => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Transaction added!' });
      });
    }
    else {
      transactions[this.transactions.indexOf(this.selectedTransaction)] = transaction;
      this.transactionService.update(transaction).subscribe(data => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Transaction updated!' });
      });
    }

    this.transactions = transactions;
    this.transaction = new Transaction();
    this.displayDialog = false;
  }

  gotoList() {
    this.router.navigate(['/transactions']);
  }
}
