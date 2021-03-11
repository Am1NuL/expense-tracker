import { WalletService } from './service/wallet.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TransactionListComponent } from './component/transaction-list/transaction-list.component';
import { TransactionService } from './service/transaction.service';
import { TransactionFormComponent } from './component/transaction-form/transaction-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoryService } from './service/category.service';
import { DropdownModule } from 'primeng/dropdown';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { SlideMenuModule } from 'primeng/slidemenu';
import { MenuModule } from 'primeng/menu';
import { ChartModule } from 'primeng/chart';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { WalletFormComponent } from './component/wallet-form/wallet-form.component';
import { SpinnerModule } from 'primeng/spinner';
import { KeyFilterModule } from 'primeng/keyfilter';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { FilterPipe } from './utils/FilterPipe';
import { HeaderComponent } from './component/header/header.component';
import { LoginComponent } from './component/login/login.component';
import { LogoutComponent } from './component/logout/logout.component';
import { BasicAuthHttpInterceptorService } from './service/basic-auth-http-interceptor.service';
import { TabMenuModule } from 'primeng/tabmenu';
import { RegisterComponent } from './component/register/register.component';
import { AlertComponent } from './component/alert/alert.component';
import { FooterComponent } from './component/footer/footer.component';
import { BudgetListComponent } from './component/budget-list/budget-list.component';
import {ProgressBarModule} from 'primeng/progressbar';
import { BudgetComponent } from './component/budget/budget.component';
import {InputMaskModule} from 'primeng/inputmask';


@NgModule({
  declarations: [
    AppComponent,
    TransactionListComponent,
    TransactionFormComponent,
    DashboardComponent,
    WalletFormComponent,
    FilterPipe,
    HeaderComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    AlertComponent,
    FooterComponent,
    BudgetListComponent,
    BudgetComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    CalendarModule,
    InputTextModule,
    SlideMenuModule,
    MenuModule,
    ChartModule,
    SpinnerModule,
    KeyFilterModule,
    TableModule,
    DialogModule,
    ToastModule,
    CardModule,
    TabMenuModule,
    ReactiveFormsModule,
    ProgressBarModule,
    InputMaskModule
  ],
  exports: [
    DropdownModule
  ],
  providers: [TransactionService, CategoryService, MessageService, WalletService,
    {
      provide: HTTP_INTERCEPTORS, useClass: BasicAuthHttpInterceptorService, multi: true
    }],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
