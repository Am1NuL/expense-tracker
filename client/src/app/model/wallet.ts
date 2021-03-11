import { Account } from './account'

export class Wallet {
    id: bigint;
    totalWealth: number;
    account: Account;
    name: string;
}
