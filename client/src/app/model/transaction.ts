import {Category} from './category'
import {Wallet} from './wallet'

export class Transaction {
    id: bigint;
    amount: number;
    date: Date;
    comment: string;
    category: Category;
    wallet: Wallet;
}
