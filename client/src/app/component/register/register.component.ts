import { Account } from './../../model/account';
import { AlertService } from './../../service/alert.service';
import { TransactionService } from './../../service/transaction.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  account: Account;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private transactionService: TransactionService,
    private alertService: AlertService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.isUserLoggedIn()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.account = new Account();

    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;

    this.account.firstName = this.registerForm.controls['firstName'].value;
    this.account.lastName = this.registerForm.controls['lastName'].value;
    this.account.email = this.registerForm.controls['email'].value;
    this.account.password = this.registerForm.controls['password'].value;
    
    this.transactionService.register(this.account)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Registration successful', true);
          this.router.navigate(['login']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

}
