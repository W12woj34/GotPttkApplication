import { TestBed } from '@angular/core/testing';

import { TripRouteService } from './trip-route.service';

describe('TripRouteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TripRouteService = TestBed.get(TripRouteService);
    expect(service).toBeTruthy();
  });
});
