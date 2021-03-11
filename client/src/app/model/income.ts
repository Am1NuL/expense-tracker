import { Transaction } from "./transaction";

export class Income extends Transaction {
    public getType() {
        return "income";
    }

    public toJSON(): Income {
    return Object.assign({}, this, {
        type: 'com.thesis.expensetracker.model.Income'
    });
    }
}