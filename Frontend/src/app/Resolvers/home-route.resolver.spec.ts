import { TestBed } from '@angular/core/testing';

import { HomeRouteResolver } from './home-route.resolver';

describe('HomeRouteResolver', () => {
  let resolver: HomeRouteResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(HomeRouteResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
