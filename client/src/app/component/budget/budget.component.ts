import { MessageService } from 'primeng/api';
import { Wallet } from './../../model/wallet';
import { Category } from './../../model/category';
import { ActivatedRoute } from '@angular/router';
import { TransactionService } from './../../service/transaction.service';
import { Component, OnInit } from '@angular/core';
import { Budget } from 'src/app/model/budget';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Location} from '@angular/common';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  budgetId: number;
  budget: Budget;
  budgetSpent = new Map();
  diff: number;
  editBudgetForm: FormGroup;
  submitted: boolean;
  display: boolean = false;
  categories: Category[];
  wallets: Wallet[];
  moneyLeft: number;

  constructor(
    private transactionService: TransactionService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private location: Location
  ) { }

  ngOnInit() {
    this.transactionService.findAllCategories().subscribe(data => {
      this.categories = data;
    })

    this.transactionService.findAllWallets().subscribe(data => {
      this.wallets = data;
    })

    this.budgetId = parseInt(this.route.snapshot.paramMap.get("id"));

    if (this.budgetId) {
      this.transactionService.findBudgetById(this.budgetId).subscribe(data => {
        this.budget = data;
        this.transactionService.findBudgetExpenseSum(this.budget.id).subscribe(data => this.budgetSpent.set(this.budget.id, data));
        let date1 = new Date(this.budget.startDate);
        let date2 = new Date(this.budget.endDate);

        var time = date2.getTime() - date1.getTime();
        this.diff = time / (1000 * 3600 * 24);

        this.editBudgetForm = this.formBuilder.group({
          name: [this.budget.name, Validators.required],
          amount: [this.budget.amount, Validators.required],
          startDate: [new Date(this.budget.startDate), Validators.required],
          endDate: [new Date(this.budget.endDate), Validators.required],
          wallet: [this.budget.wallet.name, Validators.required],
          category: [this.budget.category.name, Validators.required],
        });
      });

      this.moneyLeft = this.budget.amount - this.budgetSpent.get(this.budget.id);
    }
  }

  // convenience getter for easy access to form fields
  get f() { return this.editBudgetForm.controls; }

  onSubmit() {
    this.submitted = true;

    this.budget.name = this.editBudgetForm.controls['name'].value;
    this.budget.amount = this.editBudgetForm.controls['amount'].value;
    this.budget.startDate = this.editBudgetForm.controls['startDate'].value;
    this.budget.endDate = this.editBudgetForm.controls['endDate'].value;
    this.budget.wallet = this.editBudgetForm.controls['wallet'].value;
    this.budget.category = this.editBudgetForm.controls['category'].value;

    this.transactionService.saveBudget(this.budget).subscribe(data => {
      this.messageService.add({severity:'success', summary:'Success', detail:'Budget updated!'});
    })

    this.closeDialog();
  }

  closeDialog() {
    this.display = false;
  }

  showDialog() {
    this.display = true;
  }
}
