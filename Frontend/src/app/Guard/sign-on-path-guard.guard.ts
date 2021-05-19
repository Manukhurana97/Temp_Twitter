import {Injectable} from '@angular/core';
import {CanActivate, Router, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserserviceService} from '../Service/userservice.service';

@Injectable({
  providedIn: 'root'
})
export class SignOnPathGuardGuard implements CanActivate {

  public check: boolean;

  constructor(private userservice: UserserviceService, private route: Router) {
  }

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return new Observable<boolean>(obs => {
      this.userservice.CheckToken().subscribe(
        data => {
          if (data.status === 200 && data.username !== '') {
            obs.next(true);
            console.log(obs);
          }
          else {
            this.route.navigate(['/Login']);
            obs.next(false);
          }
        },
        error => {
          this.route.navigate(['/Login']);
          obs.next(false);
        }
      );
    });
  }
}
