import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TweetsService} from '../../../Service/tweets.service';
import {
  faComment as faComment,
  faHeart as faHeart,
  faRetweet as faRetweet,
  faShare as faShare,
  faTimes as faTimes,
  faSearch as faSearch,
} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {CommentsService} from '../../../Service/comments.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public tweetform: FormGroup;
  public commentform: FormGroup;
  public posts: any [];
  ffaTimes = faTimes;
  faComment = faComment;
  faShare = faShare;
  faHeart = faHeart;
  faRetweet = faRetweet;
  faSearch = faSearch;
  public commentpopup = false;
  public popupdata: any;

  constructor(private formbuilder: FormBuilder, private tweetPostService: TweetsService,
              private commentService: CommentsService,
              private route: Router) {
  }

  ngOnInit(): void {

    this.tweetform = this.formbuilder.group({
      postDescription: ['', Validators.required, Validators.maxLength(150)],
      tags: ['', Validators.required],
      url: ['', Validators.required],
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

    this.Alltweets();
  }

  posttweet(): void {
    console.log(this.tweetform.value.postDescription);
    this.tweetPostService.postTweet(this.tweetform.value).subscribe(
      data => {
        this.Alltweets();
      },
      error => {
        console.log(error);
      }
    );
  }

  Alltweets(): void {
    this.tweetPostService.getAllTweets().subscribe(
      data => {
        this.posts = data.lst_posts;
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
    console.log(this.popupdata);
    console.log(this.commentpopup);
  }

  postdetails(postid): void {
    const url = 'i/' + postid;
    console.log(url);
    this.route.navigate([url]);
  }


  postcomment(postdata): void {
    this.commentform.controls.posts.setValue(postdata);
    this.commentService.postcomment(this.commentform.value).subscribe(
      data => {
        console.log(data);
        this.popup(null);
        this.commentpopup = false;
        },
      error => {console.log(error);
      }
    );
  }
}
