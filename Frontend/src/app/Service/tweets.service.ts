import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserserviceService} from './userservice.service';
import {Observable} from 'rxjs';
import {Postsform} from '../Model/Posts.model';

@Injectable({
  providedIn: 'root'
})
export class TweetsService {


  private viewusertweetsbaseUrl = 'http://localhost:8762/api/v1.0/ViewTweetsandComments/Posts/';
  private PostTweetsandComments = 'http://localhost:8762/api/v1.0/PostTweetsandComments/Posts/';


  constructor(private http: HttpClient, private userservice: UserserviceService) { }


  postTweet(postform: Postsform): Observable<any> {
    const header = new HttpHeaders().set('Authentication', this.userservice.getToken());
    return this.http.post(this.PostTweetsandComments + 'Tweet/' , postform, {headers: header});
  }

  getAllTweets(): Observable<any>
  {
    const header = new HttpHeaders().set('Authentication', this.userservice.getToken());
    return this.http.post(this.viewusertweetsbaseUrl + '', '', {headers: header});
  }

  getUserTweets(): Observable<any>
  {
    const header = new HttpHeaders().set('Authentication', this.userservice.getToken());
    return this.http.post(this.viewusertweetsbaseUrl + 'UserPosts', '', {headers: header});
  }

  getTweetInfo(postform: Postsform): Observable<any>
  {
    const header = new HttpHeaders().set('Authentication', this.userservice.getToken());
    return this.http.post(this.viewusertweetsbaseUrl + 'Postinfo', postform, {headers: header});
  }


}
