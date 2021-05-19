import {Component, OnInit} from '@angular/core';
import {UserserviceService} from '../../../Service/userservice.service';
import {TweetsService} from '../../../Service/tweets.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {UserDetailsService} from '../../../Service/user-details.service';
import {
  faComment as faComment,
  faHeart as faHeart,
  faRetweet as faRetweet,
  faSearch as faSearch,
  faShare as faShare,
  faTimes as faTimes
} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {CommentsService} from '../../../Service/comments.service';


@Component({
  selector: 'app-usertweets',
  templateUrl: './usertweets.component.html',
  styleUrls: ['./usertweets.component.css']
})
export class UsertweetsComponent implements OnInit {

  public userdetail: any [];

  public tweets: any [];
  public news: any [];
  public nooftweets: number;
  public now: Date;
  public firstname: string;
  public lastname: string;
  public userid: string;
  public postcount: number;
  public commentform: FormGroup;
  public commentpopup = false;
  public popupdata: any;
  public logoutbutton = false;


  faComment = faComment;
  faShare = faShare;
  faHeart = faHeart;
  faRetweet = faRetweet;
  faTimes = faTimes;
  faSearch = faSearch;


  constructor(private userservice: UserserviceService, private userdetails: UserDetailsService, private tweetservice: TweetsService,
              private formbuilder: FormBuilder, private datePipe: DatePipe, private route: Router,
              private userDetails: UserDetailsService,
              private commentService: CommentsService) {
    this.now = new Date();
  }


  ngOnInit(): void {

    this.commentform = this.formbuilder.group({
      comment_text: ['', Validators.required],
      parent_comment_id: ['0', Validators.required],
      posts: {
        postid: ['', Validators.required],
        postDescription: ['', Validators.required],
        tags: ['', Validators.required],
        url: ['', Validators.required],
      }

    });

    this.checklogin();
    this.getuserdetails();
    this.getUserTweets();
    this.topHeadlines();
  }


  checklogin(): void {
    console.log(this.userservice.getToken() !== undefined);
    if (this.userservice.getToken()) {
      this.logoutbutton = true;
    } else {
      this.logoutbutton = false;
    }
  }

  logout(): void {
    this.userservice.removeToken();
    this.route.navigate(['/Login']);
  }

  getUserTweets(): void {
    this.tweetservice.getUserTweets().subscribe(
      data => {
        this.tweets = data.lst_posts;
        this.postcount = data.lst_posts.length;
      },
      error1 => {
        console.log(error1);
      }
    );
  }

  getuserdetails(): void {
    this.userdetails.Userdetails().subscribe(
      data => {
        this.userdetail = data;
        this.firstname = data.firstname;
        this.lastname = data.lastname;
        this.userid = data.username;
        console.log(this.userdetail);
      },
      error => {
        console.log(error);
      }
    );
  }

  postdetails(postid): void {
    const url = 'i/' + postid;
    console.log(url);
    this.route.navigate([url]);
  }

  popup(post): void {
    if (this.commentpopup) {
      this.commentpopup = false;
    } else {
      this.popupdata = post;
      this.commentpopup = true;
    }
    console.log(this.popupdata);
    console.log(this.commentpopup);
  }

  postcomment(postdata): void {
    this.commentform.controls.posts.setValue(postdata);
    this.commentService.postcomment(this.commentform.value).subscribe(
      data => {
        console.log(data);
        this.popup(null);
        this.getUserTweets();
      },
      error => {
        console.log(error);
      }
    );
  }

  topHeadlines(): void {
    this.userDetails.getCovidNews().subscribe(
      data => {
        this.news = data.articles;
        console.log(this.news);
      },
      error => {
        console.log(error);
      }
    );
  }

}

