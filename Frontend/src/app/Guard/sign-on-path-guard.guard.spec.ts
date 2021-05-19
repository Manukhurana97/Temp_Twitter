import { TestBed } from '@angular/core/testing';

import { SignOnPathGuardGuard } from './sign-on-path-guard.guard';

describe('SignOnPathGuardGuard', () => {
  let guard: SignOnPathGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SignOnPathGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
