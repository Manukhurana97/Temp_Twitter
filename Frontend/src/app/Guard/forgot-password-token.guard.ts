import { Injectable } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {UserserviceService} from '../Service/userservice.service';
import {PasswordServiceService} from '../Service/password-service.service';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordTokenGuard implements CanActivate {

  public check: boolean;
  private response: any;

  constructor(private passwordServiceService: PasswordServiceService, private urlroute: ActivatedRoute) {
  }

  canActivate(route: ActivatedRouteSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    // tslint:disable-next-line:variable-name
    const pass_token = route.params.token;
    // @ts-ignore
    this.response =  this.verifypasswordtoken(pass_token);

    console.log(this.response);

    return this.check;
  }


  verifypasswordtoken(pass_token: any): Promise<any>{
    console.log(pass_token);
    return this.passwordServiceService.checkchangepasswordToken(pass_token).toPromise();
  }

}
