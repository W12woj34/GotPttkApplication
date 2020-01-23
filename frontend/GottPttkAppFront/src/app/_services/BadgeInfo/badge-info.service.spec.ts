import { TestBed } from '@angular/core/testing';

import { BadgeInfoService } from './badge-info.service';

describe('BadgeInfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BadgeInfoService = TestBed.get(BadgeInfoService);
    expect(service).toBeTruthy();
  });
});
