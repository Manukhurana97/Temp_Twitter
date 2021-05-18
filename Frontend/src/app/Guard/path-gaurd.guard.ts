import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserserviceService} from '../Service/userservice.service';

@Injectable({
  providedIn: 'root'
})
export class PathGaurdGuard implements CanActivate {

  public check: boolean;

  constructor(private userservice: UserserviceService) {
  }

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.userservice.CheckToken().subscribe(
      data => {
        console.log('false');
        this.check = false;
      },
      error => {
        console.log('true');
        this.check = true;
      }
    );
    return this.check;
  }
}
