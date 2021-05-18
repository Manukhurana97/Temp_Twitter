import { TestBed } from '@angular/core/testing';

import { PathGaurdGuard } from './path-gaurd.guard';

describe('PathGaurdGuard', () => {
  let guard: PathGaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(PathGaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
