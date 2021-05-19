import { Component, OnInit } from '@angular/core';
import {UserDetailsService} from '../../../Service/user-details.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  // tslint:disable-next-line:variable-name
  public userdetails_data: any = [];

  constructor(private userdetailsservice: UserDetailsService) { }

  ngOnInit(): void {
    this.getuserdetails();
  }

  getuserdetails(): void{
    this.userdetailsservice.Userdetails().subscribe(
      data => {
        this.userdetails_data = data;
        },
      error => {console.log(error); }
    );
  }

}
