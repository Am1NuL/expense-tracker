import { Category } from './category';
import { Wallet } from './wallet';
export class Budget {
    id: bigint;
    name: string;
    amount: number;
    startDate: Date;
    endDate: Date;
    wallet: Wallet;
    category: Category;
}