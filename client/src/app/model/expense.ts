import { Transaction } from "./transaction";

export class Expense extends Transaction {
    public getType() {
        return "expense";
    }

    public toJSON(): Expense {
      return Object.assign({}, this, {
        type: 'com.thesis.expensetracker.model.Expense'
      });
    }
}