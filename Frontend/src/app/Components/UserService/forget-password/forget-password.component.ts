import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserserviceService} from '../../../Service/userservice.service';
import {Router} from '@angular/router';
import {PasswordServiceService} from '../../../Service/password-service.service';
import {validate} from 'codelyzer/walkerFactory/walkerFn';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  public changepasswordForm: FormGroup;
  error = false;
  message = String;
  success = true;

  constructor(private userservice: UserserviceService, private passwordservice: PasswordServiceService, private route: Router, private formBuilder: FormBuilder) {
  }


  ngOnInit(): void {
    this.changepasswordForm = this.formBuilder.group(
    {
      username: ['', Validators.required]
    });
  }

  onchangepasswordSumbit(): void{
    this.passwordservice.forgetPassword(this.changepasswordForm.value).subscribe(
      data => {
        this.success = false;
      },
      error1 => {
        this.error = true;
        this.message = error1.error.message;
      }
    );
  }

}
