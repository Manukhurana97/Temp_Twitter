import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {DatePipe} from '@angular/common';
import { FontawesomeObject } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './Components/UserService/login/login.component';
import { RegisterComponent } from './Components/UserService/register/register.component';
import { ChangePasswordComponent } from './Components/UserService/change-password/change-password.component';
import { ForgetPasswordComponent } from './Components/UserService/forget-password/forget-password.component';
import { ForgetPasswordConfirmComponent } from './Components/UserService/forget-password-confirm/forget-password-confirm.component';
import { UserDetailsComponent } from './Components/UserService/user-details/user-details.component';
import { HomeComponent } from './Components/Tweetsandcomments/home/home.component';
import { UsertweetsComponent } from './Components/Tweetsandcomments/usertweets/usertweets.component';
import { TweetinfoComponent } from './Components/Tweetsandcomments/tweetinfo/tweetinfo.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ChangePasswordComponent,
    ForgetPasswordComponent,
    ForgetPasswordConfirmComponent,
    UserDetailsComponent,
    HomeComponent,
    UsertweetsComponent,
    TweetinfoComponent,



  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    NgbModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule,
    FontAwesomeModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
