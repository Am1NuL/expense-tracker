import { Account } from './../model/account';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Transaction } from '../model/transaction';
import { Observable } from 'rxjs';
import { Category } from '../model/category';
import { Wallet } from '../model/wallet';
import { AppSettings } from '../utils/app-settings';
import { Expense } from '../model/expense';
import { Income } from '../model/income';
import { Budget } from '../model/budget';
import { formatDate } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) {
  }

  public returnTransaction(transaction: Transaction) {
    if (transaction.category.type == "EXPENSE") {
      let expense = new Expense();
      if (transaction.id) {
        expense.id = transaction.id;
      }
      expense.amount = transaction.amount;
      expense.category = transaction.category;
      expense.comment = transaction.comment;
      expense.date = transaction.date;
      expense.wallet = transaction.wallet;
      return expense;
    } else if (transaction.category.type == "INCOME") {
      let income = new Income();
      if (transaction.id) {
        income.id = transaction.id;
      }
      income.amount = transaction.amount;
      income.category = transaction.category;
      income.comment = transaction.comment;
      income.date = transaction.date;
      income.wallet = transaction.wallet;
      return income;
    }
  }

  public findBudgetExpenseSum(budgetId: bigint): Observable<number> {
    return this.http.get<number>(AppSettings.API_ENDPOINT + 'budgets/' + budgetId + '/expenses');
  }

  public findAll(): Observable<Transaction[]> {

    return this.http.get<Transaction[]>(AppSettings.API_ENDPOINT + 'transactions');
  }

  public findAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(AppSettings.API_ENDPOINT + 'categories');
  }

public findAllBudgets(): Observable<Budget[]> {
  return this.http.get<Budget[]>(AppSettings.API_ENDPOINT + "budgets");
}

public findBudgetById(id: number) {
  return this.http.get<Budget>(AppSettings.API_ENDPOINT + "budgets/" + id);
}

  public findAllBudgetsByWallet(walletId: number) {
    return this.http.get<Budget[]>(AppSettings.API_ENDPOINT + "wallets/" + walletId + "/budgets");
  }

  public findAllWallets(): Observable<Wallet[]> {
    return this.http.get<Wallet[]>(AppSettings.API_ENDPOINT + 'wallets');
  }

  public findAllByWallet(walletId: number) {
    return this.http.get<Transaction[]>(AppSettings.API_ENDPOINT + "wallets/" + walletId + "/transactions");
  }

  public save(transaction: Transaction) {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=UTF-8');

    return this.http.post<Transaction>(AppSettings.API_ENDPOINT + 'transactions', transaction, { headers: headers });
  }

  public update(transaction: Transaction) {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=UTF-8');

    return this.http.put<Transaction>(AppSettings.API_ENDPOINT + 'transactions', transaction, { headers: headers });
  }

  public register(account: Account) {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=UTF-8');

    return this.http.post<Account>(AppSettings.API_ENDPOINT + 'register', account, { headers: headers });
  }

  public saveBudget(budget: Budget) {
    if(budget.name == "") {
      return;
    }

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=UTF-8');

    return this.http.post<Budget>(AppSettings.API_ENDPOINT + 'budgets', budget, { headers: headers });
  }

  public updateBudget(budget: Budget) {
    if(budget.name == "") {
      return;
    }

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=UTF-8');

    return this.http.put<Budget>(AppSettings.API_ENDPOINT + 'budget', budget, { headers: headers });
  }

  public getTotalExpenses() {
    return this.http.get<number>(AppSettings.API_ENDPOINT + "total-expenses");
  }

  public getTotalIncome() {
    return this.http.get<number>(AppSettings.API_ENDPOINT + "total-income");
  }
}