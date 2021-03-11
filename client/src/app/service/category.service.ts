import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../model/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8080/";
   }

   public findAll(): Observable<Category[]> {
     return this.http.get<Category[]>(this.url + 'categories');
   }
}
