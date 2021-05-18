import {Inject, Injectable} from '@angular/core';
import {SESSION_STORAGE, StorageService} from 'ngx-webstorage-service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Userform} from '../Model/User.model';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Registerform} from '../Model/Register.model';


@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  private AccountbaseUrl = 'http://localhost:8762/api/v1.0/UserService/Account/';

  constructor(@Inject(SESSION_STORAGE) private storage: StorageService, private http: HttpClient, private route: Router) {

  }

  LoginUser(user: Userform): Observable<any> {
    return this.http.post(this.AccountbaseUrl + `Login`, user);
  }

  RegisterUser(user: Registerform): Observable<any> {
    return this.http.post(this.AccountbaseUrl + `Register`, user);
  }


  CheckToken(): Observable<any>{
    // tslint:disable-next-line:triple-equals
    if (this.getToken() == 'undefined' || this.getToken() == null) {
      this.removeToken();
    }
    const header = new HttpHeaders().set('Authentication', this.getToken());
    console.log(header);
    return this.http.post(this.AccountbaseUrl + `CheckTokenWeb`,  '', {headers: header});
  }

  // tslint:disable-next-line:typedef
  getToken() {
    return this.storage.get('Session_id');
  }

  // tslint:disable-next-line:typedef
  removeToken() {
    this.storage.remove('Session_id');
    return this.storage.clear();
  }

  // tslint:disable-next-line:typedef
  storeToken(token: string) {
    this.storage.set('Session_id', token);
  }

}
