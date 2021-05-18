import {Injectable} from '@angular/core';
import {CanActivate, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserserviceService} from '../Service/userservice.service';

@Injectable({
  providedIn: 'root'
})
export class SignOnPathGuardGuard implements CanActivate {

  public check: boolean;

  constructor(private userservice: UserserviceService) {
  }

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.userservice.CheckToken().subscribe(
      data => {
        this.check = true;
      },
      error1 => {
        this.check = false;
      }
    );
    return this.check;
  }
}
