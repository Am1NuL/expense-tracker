import { BudgetComponent } from './component/budget/budget.component';
import { BudgetListComponent } from './component/budget-list/budget-list.component';
import { RegisterComponent } from './component/register/register.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TransactionListComponent } from './component/transaction-list/transaction-list.component';
import { TransactionFormComponent } from './component/transaction-form/transaction-form.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { WalletFormComponent } from './component/wallet-form/wallet-form.component';
import { LoginComponent } from './component/login/login.component';
import { LogoutComponent } from './component/logout/logout.component';
import { AuthGuardService } from './service/auth-guard.service';

const routes: Routes = [
  { path: 'wallets/:id/transactions', component: TransactionListComponent, canActivate: [AuthGuardService] },
  { path: 'transactions', component: TransactionListComponent, canActivate: [AuthGuardService] },
  { path: 'transactions', component: TransactionFormComponent, canActivate: [AuthGuardService] },
  { path: 'wallets/:id/budgets', component: BudgetListComponent, canActivate: [AuthGuardService] },
  { path: 'budgets', component: BudgetListComponent, canActivate: [AuthGuardService] },
  { path: 'budgets/:id', component: BudgetComponent, canActivate: [AuthGuardService] },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService] },
  { path: 'wallets', component: WalletFormComponent, canActivate: [AuthGuardService] },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate: [AuthGuardService] },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '**', component: DashboardComponent, canActivate: [AuthGuardService] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
