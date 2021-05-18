import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserserviceService} from '../../../Service/userservice.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup;
  error = false;
  message = String;

  constructor(private userservice: UserserviceService, private route: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['abc1@gmail.com', Validators.required],
      password: ['Manu@123', Validators.required],
      firstname: ['Manu@123', Validators.required],
      lastname: ['Manu@123', Validators.required],
      contactno: ['1232467890', Validators.required],
      country: ['']
    });
  }

  checkEmail($event): void{
    console.log(this.registerForm.value);
  }

  Register(): void
  {
    this.userservice.RegisterUser(this.registerForm.value).subscribe(
      data => {
        console.log('Regsitered Successfully');
        this.route.navigate(['/Login']);
      },
      error1 => {
        this.error = true;
        this.message = error1.error.message;
      }
    );
  }


}
