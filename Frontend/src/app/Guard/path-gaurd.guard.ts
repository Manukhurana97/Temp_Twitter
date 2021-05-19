import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserserviceService} from '../Service/userservice.service';
import {tap} from 'rxjs/operators';
import {any} from 'codelyzer/util/function';

@Injectable({
  providedIn: 'root'
})
export class PathGaurdGuard implements CanActivate {

  public check: boolean;

  constructor(private userservice: UserserviceService, private route: Router) {
  }

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return new Observable<boolean>(obs => {
      this.userservice.CheckToken().subscribe(
        data => {
          if (data.status === 200 && data.username !== '') {
            this.route.navigate(['/']);
            obs.next(false);
            console.log(obs);
          }
          else {
            obs.next(true);
            console.log(obs);
          }
        },
        error => {
          obs.next(true);
          console.log(obs);
        }
      );
    });
  }



}
