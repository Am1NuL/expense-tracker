import { Component, OnInit } from '@angular/core';
import { Transaction } from '../../model/transaction';
import { Router, ActivatedRoute } from '@angular/router';
import { TransactionService } from '../../service/transaction.service';
import { Category } from '../../model/category';
import { Wallet } from '../../model/wallet';
import { Expense } from '../../model/expense';
import { Income } from '../../model/income';
import { DateFormat } from '../../utils/date-format';


@Component({
  selector: 'app-transaction-form',
  templateUrl: './transaction-form.component.html',
  styleUrls: ['./transaction-form.component.css']
})
export class TransactionFormComponent implements OnInit {

  transaction: Transaction;
  categories: Category[];
  wallets: Wallet[];

  constructor(private router: Router, private transactionService: TransactionService) {
    this.transaction = new Transaction();
   }

  ngOnInit() {
    this.transactionService.findAllCategories().subscribe(data => {
      this.categories = data;
    },
      error => console.log(error)
      )

    this.transactionService.findAllWallets().subscribe(data => {
      this.wallets = data;
    },
      error => console.log(error)
      )
  }

  onSubmit() {
    let expense: Expense;
    let income: Income;

    let dateFormat = new DateFormat();

    let transaction = this.transactionService.returnTransaction(this.transaction);

      this.transactionService.save(transaction).subscribe(data => {
        alert("Transaction created!")
      });
    }

  gotoList() {
    this.router.navigate(['/transactions']);
  }
}
