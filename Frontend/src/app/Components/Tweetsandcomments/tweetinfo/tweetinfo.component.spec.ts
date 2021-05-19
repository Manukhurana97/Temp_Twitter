import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TweetinfoComponent } from './tweetinfo.component';

describe('TweetinfoComponent', () => {
  let component: TweetinfoComponent;
  let fixture: ComponentFixture<TweetinfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TweetinfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TweetinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
