import {Component, OnInit} from '@angular/core';
import {UserserviceService} from '../../../Service/userservice.service';
import {UserDetailsService} from '../../../Service/user-details.service';
import {TweetsService} from '../../../Service/tweets.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {
  faComment as faComment,
  faHeart as faHeart,
  faRetweet as faRetweet,
  faSearch as faSearch,
  faShare as faShare
} from '@fortawesome/free-solid-svg-icons';
import {CommentsService} from '../../../Service/comments.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-tweetinfo',
  templateUrl: './tweetinfo.component.html',
  styleUrls: ['./tweetinfo.component.css']
})
export class TweetinfoComponent implements OnInit {

  public tweetform: FormGroup;
  public commentform: FormGroup;
  faComment = faComment;
  faShare = faShare;
  faHeart = faHeart;
  faRetweet = faRetweet;
  faSearch = faSearch;

  public postdata: any;
  public comments: any [];
  public news: any [];
  public commentpopup = false;
  public popupdata: any;
  public user: string;
  public date: string;
  public tags: string;
  public url: string;
  public postDescription: string;
  public logoutbutton = false;


  constructor(private userservice: UserserviceService, private userdetails: UserDetailsService,
              private tweetservice: TweetsService, private commentService: CommentsService,
              private formbuilder: FormBuilder, private userService: UserserviceService,
              private userDetails: UserDetailsService,
              private datePipe: DatePipe, private urlroute: ActivatedRoute,
              private route: Router) {
  }

  ngOnInit(): void {
    this.tweetform = this.formbuilder.group({
      postid: ['', Validators.required],
    });

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
    this.getpostinfo();
    this.getpostcomments();
    this.topHeadlines();
  }


  checklogin(): void {
    console.log(this.userService.getToken() !== undefined);
    if (this.userService.getToken()) {
      this.logoutbutton = true;
    } else {
      this.logoutbutton = false;
    }
  }

  logout(): void {
    this.userService.removeToken();
    this.route.navigate(['/Login']);
  }

  getpostinfo(): void {
    this.tweetform.value.postid = this.urlroute.snapshot.params.postid;
    this.tweetservice.getTweetInfo(this.tweetform.value).subscribe(
      data => {
        this.postdata = data.posts;
        this.user = data.posts.user;
        this.date = data.posts.date;
        this.tags = data.posts.tags;
        this.url = data.posts.url;
        this.postDescription = data.posts.postDescription;
        console.log(data.posts);
      },
      error => {
        console.log(error);
      }
    );
  }

  getpostcomments(): void {
    this.tweetform.value.postid = this.urlroute.snapshot.params.postid;
    this.commentService.gettweetcomments(this.tweetform.value).subscribe(
      data => {
        this.comments = data.comments;
      },
      error => {
        console.log(error);
      }
    );
  }

  popup(post): void {
    if (this.commentpopup) {
      this.commentpopup = false;
    } else {
      this.popupdata = post;
      this.commentpopup = true;
    }
  }


  postcomment(postdata): void {
    this.commentform.controls.posts.setValue(postdata);
    this.commentService.postcomment(this.commentform.value).subscribe(
      data => {
        this.popup(null);
        this.getpostcomments();
      },
      error => {
        console.log(error);
      }
    );
  }


  //  news
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
