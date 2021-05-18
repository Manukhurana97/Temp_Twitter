import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserserviceService} from '../../../Service/userservice.service';
import {Router} from '@angular/router';
import {PasswordServiceService} from '../../../Service/password-service.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public changepasswordForm: FormGroup;
  error = false;
  message = String;


  constructor(private userservice: UserserviceService, private passwordservice: PasswordServiceService, private route: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.changepasswordForm = this.formBuilder.group({
      password: ['Manu@123', Validators.required],
    });
  }

  changepassword(): void {
    this.passwordservice.changePassword(this.changepasswordForm.value).subscribe(
      data => {
        console.log(data);
      },
      error1 => {
        this.error = true;
        this.message = error1.error.message;
      }
    );
  }

}
