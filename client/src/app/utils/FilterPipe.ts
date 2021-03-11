import { Pipe, PipeTransform } from '@angular/core';
import { Transaction } from '../model/transaction';

@Pipe({
    name: 'myfilter',
    pure: false
})
export class FilterPipe implements PipeTransform {
    transform(items: Transaction[], filter: Date): any {
        if (!items || !filter) {
            return items;
        }
        // filter items array, items which match and return true will be
        // kept, false will be filtered out
        return items.filter(item => item.date == filter);
    }
}