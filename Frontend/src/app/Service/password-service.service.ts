import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserserviceService} from './userservice.service';
import {Userform} from '../Model/User.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PasswordServiceService {

  private token: any;
  private passwordtoken: string;
  private PasswordbaseUrl = 'http://localhost:8762/api/v1.0/UserService/Password/';


  constructor(private http: HttpClient, private route: Router, private userService: UserserviceService) {
  }

  changePassword(user: Userform): Observable<any> {

    this.token = this.userService.getToken();
    // tslint:disable-next-line:triple-equals
    if (this.token == 'undefined' || this.token == null) {
      this.userService.removeToken();
      this.route.navigate(['/']);
    }
    // @ts-ignore
    const header = new HttpHeaders().set('Authentication', this.token);
    return this.http.post(this.PasswordbaseUrl + 'change-password', user, {headers: header});
  }


  forgetPassword(user: Userform): Observable<any>{
    return this.http.post(this.PasswordbaseUrl + 'forgot-password', user);
  }

  checkchangepasswordToken(passwordtoken: string): Observable<any>{
    console.log(passwordtoken);
    return this.http.post(this.PasswordbaseUrl + `reset-password/` + passwordtoken   , '' );
  }

  forgotpasswordconfirm(passwordtoken: string, user: Userform): Observable<any>{
    return this.http.post(this.PasswordbaseUrl + `reset-password-confirm/` + passwordtoken , user);
  }
}
