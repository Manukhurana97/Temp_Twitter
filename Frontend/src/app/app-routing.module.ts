import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PathGaurdGuard} from './Guard/path-gaurd.guard';



import { LoginComponent } from './Components/UserService/login/login.component';
import { RegisterComponent } from './Components/UserService/register/register.component';
import { ChangePasswordComponent } from './Components/UserService/change-password/change-password.component';
import { ForgetPasswordComponent } from './Components/UserService/forget-password/forget-password.component';
import { ForgetPasswordConfirmComponent } from './Components/UserService/forget-password-confirm/forget-password-confirm.component';
import { UserDetailsComponent } from './Components/UserService/user-details/user-details.component';

import { HomeComponent } from './Components/Tweetsandcomments/home/home.component';
import {RouterModule, Routes} from '@angular/router';
import {SignOnPathGuardGuard} from './Guard/sign-on-path-guard.guard';
import {ForgotPasswordTokenGuard} from './Guard/forgot-password-token.guard';
import { UsertweetsComponent } from './Components/Tweetsandcomments/usertweets/usertweets.component';
import { TweetinfoComponent } from './Components/Tweetsandcomments/tweetinfo/tweetinfo.component';


const route: Routes = [
  {path: '', component: HomeComponent},
  {path: 'Login', component: LoginComponent, },
  {path: 'Register', component: RegisterComponent, },
  {path: 'ChangePassword', component: ChangePasswordComponent, },
  {path: 'ForgotPassword', component: ForgetPasswordComponent},
  {path: 'ForgotPasswordConform/:token', component: ForgetPasswordConfirmComponent},
  {path: 'a', component: UserDetailsComponent, },
  {path: 'u', component: UsertweetsComponent, },
  {path: 'i/:postid', component: TweetinfoComponent },

];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(route)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
