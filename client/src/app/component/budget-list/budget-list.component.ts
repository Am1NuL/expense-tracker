import { MessageService } from 'primeng/api';
import { Category } from './../../model/category';
import { Wallet } from './../../model/wallet';
import { element } from 'protractor';
import { ActivatedRoute, Router } from '@angular/router';
import { TransactionService } from './../../service/transaction.service';
import { Component, OnInit, HostListener } from '@angular/core';
import { Budget } from 'src/app/model/budget';
import { Transaction } from 'src/app/model/transaction';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-budget-list',
  templateUrl: './budget-list.component.html',
  styleUrls: ['./budget-list.component.css']
})
export class BudgetListComponent implements OnInit {

  budgets: Budget[];
  transactions: Transaction[];
  walletId: number;
  display: boolean;
  budgetSpent = new Map();
  addBudgetForm: FormGroup;
  wallets: Wallet[];
  categories: Category[];
  submitted: boolean;
  budget: Budget = new Budget;

  constructor(
    private transactionService: TransactionService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location,
    private messageService: MessageService) { }

  setWalletId(walletId: number) {
    this.walletId = walletId;
  }

  ngOnInit() {
    this.transactionService.findAllCategories().subscribe(data => {
      this.categories = data;
    })

    this.transactionService.findAllWallets().subscribe(data => {
      this.wallets = data;
    })

    this.addBudgetForm = this.formBuilder.group({
      name: ['', Validators.required],
      amount: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      wallet: ['', Validators.required],
      category: ['', Validators.required],
    });

    this.walletId = parseInt(this.route.snapshot.paramMap.get("id"));

    if (this.walletId) {
      this.transactionService.findAllBudgetsByWallet(this.walletId).subscribe(data => {
        this.budgets = data;
      });
    }
    else {
      this.transactionService.findAllBudgets().subscribe(data => {
        this.budgets = data;
        this.budgets.forEach(b => {
          this.transactionService.findBudgetExpenseSum(b.id).subscribe(data => this.budgetSpent.set(b.id, data));
        })
      });
    }
  }

  showDialog() {
    this.display = true;
  }

  closeDialog() {
    this.display = false;
  }

  // convenience getter for easy access to form fields
  get f() { return this.addBudgetForm.controls; }

  onSubmit() {
    this.submitted = true;

    this.budget.name = this.addBudgetForm.controls['name'].value;
    this.budget.amount = this.addBudgetForm.controls['amount'].value;
    this.budget.startDate = this.addBudgetForm.controls['startDate'].value;
    this.budget.endDate = this.addBudgetForm.controls['endDate'].value;
    this.budget.wallet = this.addBudgetForm.controls['wallet'].value;
    this.budget.category = this.addBudgetForm.controls['category'].value;

    this.transactionService.saveBudget(this.budget)
      .pipe(first())
      .subscribe(_ => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'New budget added!' });
      });

    this.closeDialog();
    location.reload();
  }

  viewBudget(id: bigint) {
    this.router.navigate(['budgets/' + id]);
  }
}
