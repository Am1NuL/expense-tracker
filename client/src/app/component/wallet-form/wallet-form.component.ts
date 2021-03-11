import { Component, OnInit } from '@angular/core';
import { WalletService } from '../../service/wallet.service';
import { Wallet } from '../../model/wallet';

@Component({
  selector: 'app-wallet-form',
  templateUrl: './wallet-form.component.html',
  styleUrls: ['./wallet-form.component.css']
})
export class WalletFormComponent implements OnInit {

  wallet: Wallet;

  constructor(private walletService: WalletService) {
    this.wallet = new Wallet();
   }

  ngOnInit() {
  }

  onSubmit() {
    this.walletService.save(this.wallet).subscribe(data => {
      alert("Wallet created!")
    });
  }
}
