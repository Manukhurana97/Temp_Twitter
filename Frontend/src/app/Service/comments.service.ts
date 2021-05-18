import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserserviceService} from './userservice.service';
import {Postsform} from '../Model/Posts.model';
import {Observable} from 'rxjs';
import {Commentform} from '../Model/Comment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  private postusertweetsbaseUrl = 'http://localhost:8762/api/v1.0/PostTweetsandComments/Comments/';
  private viewusertweetsbaseUrl = 'http://localhost:8762/api/v1.0/ViewTweetsandComments/Comments/';


  constructor(private http: HttpClient, private userservice: UserserviceService) { }

  postcomment(commentform: Commentform): Observable<any> {
    const header = new HttpHeaders().set('Authentication', this.userservice.getToken());
    return this.http.post(this.postusertweetsbaseUrl + 'Post/' , commentform, {headers: header});
  }


  gettweetcomments(postform: Postsform): Observable<any> {
    const header = new HttpHeaders().set('Authentication', this.userservice.getToken());
    console.log(this.viewusertweetsbaseUrl + 'PostComments/');
    return this.http.post(this.viewusertweetsbaseUrl + 'PostComments/' , postform, {headers: header});
  }

}
