import { TestBed } from '@angular/core/testing';

import { MountainGroupService } from './mountain-group.service';

describe('MountainGroupService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MountainGroupService = TestBed.get(MountainGroupService);
    expect(service).toBeTruthy();
  });
});
