import { TestBed } from '@angular/core/testing';

import { ForgotPasswordTokenGuard } from './forgot-password-token.guard';

describe('ForgotPasswordTokenGuard', () => {
  let guard: ForgotPasswordTokenGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ForgotPasswordTokenGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
