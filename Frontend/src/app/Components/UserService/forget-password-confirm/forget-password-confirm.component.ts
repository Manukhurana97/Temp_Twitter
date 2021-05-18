import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserserviceService} from '../../../Service/userservice.service';
import {PasswordServiceService} from '../../../Service/password-service.service';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';

@Component({
  selector: 'app-forget-password-confirm',
  templateUrl: './forget-password-confirm.component.html',
  styleUrls: ['./forget-password-confirm.component.css']
})
export class ForgetPasswordConfirmComponent implements OnInit {

  public changepasswordForm: FormGroup;
  error = false;
  message = String;
  success = true;


  constructor(private userservice: UserserviceService, private passwordservice: PasswordServiceService,
              private route: Router, private formBuilder: FormBuilder, private urlroute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.changepasswordForm = this.formBuilder.group({
      password : ['Manu@123', Validators.required]
    });
  }

  onchecktoken(route: ActivatedRouteSnapshot): Promise<any> {
    // tslint:disable-next-line:variable-name
    return this.passwordservice.checkchangepasswordToken(this.urlroute.snapshot.params.token).toPromise();
  }


  onchangepasswordSumbit(): void {
    this.passwordservice.forgotpasswordconfirm(this.urlroute.snapshot.params.token, this.changepasswordForm.value).subscribe(
      data => {
        this.success = false;
        this.message = 'password changes successfully';
      },
      error1 => {
        this.error = true;
        this.message = error1.error.message;
      }
    );
  }


}
