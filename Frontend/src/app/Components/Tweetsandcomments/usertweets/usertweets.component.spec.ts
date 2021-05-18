import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsertweetsComponent } from './usertweets.component';

describe('UsertweetsComponent', () => {
  let component: UsertweetsComponent;
  let fixture: ComponentFixture<UsertweetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsertweetsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsertweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
