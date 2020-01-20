import { TestBed } from '@angular/core/testing';

import { MountainSubgroupService } from './mountain-subgroup.service';

describe('MountainSubgroupService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MountainSubgroupService = TestBed.get(MountainSubgroupService);
    expect(service).toBeTruthy();
  });
});
