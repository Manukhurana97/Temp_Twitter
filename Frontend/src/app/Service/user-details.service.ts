import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserserviceService} from './userservice.service';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  private token: string;
  private UserservicebaseUrl = 'http://localhost:8762/api/v1.0/UserService/Userdetails/';

  constructor(private userservice: UserserviceService, private http: HttpClient, private route: Router) {
  }

  Userdetails(): Observable<any> {
    this.token = this.userservice.getToken();
    // tslint:disable-next-line:triple-equals
    if (this.token == 'undefined' || this.token == null) {
      this.userservice.removeToken();
      this.route.navigate(['/']);
    }
    // @ts-ignore
    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
    }).set('Authentication', this.token);

    return this.http.post(this.UserservicebaseUrl + '', '', {headers: header});
  }

  getCovidNews(): Observable<any> {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=in&category=Health&apiKey=32693197245c49b8901c21f70b42bb26');
  }
}

