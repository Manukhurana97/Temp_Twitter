import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserserviceService} from '../../../Service/userservice.service';
import {Router} from '@angular/router';


// @ts-ignore
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  error = false;
  message = String;

  constructor(private userservice: UserserviceService, private route: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {


    this.loginForm = this.formBuilder.group({
      username: ['abc1@gmail.com', Validators.required],
      password: ['Manu@123', Validators.required],
      twoStepVerificationToken: ['', Validators.required],
    });

  }

  onLoginSubmit(): void {
    this.userservice.LoginUser(this.loginForm.value).subscribe(
      data => {
        this.userservice.storeToken(data.token);
        this.route.navigate(['/']);
      },
      error => {
        this.error = true;
        this.message = error.error.message;

      }
    );
  }





}
