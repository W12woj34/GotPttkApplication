import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TripsVerifyComponent } from './trips-verify.component';

describe('TripsVerifyComponent', () => {
  let component: TripsVerifyComponent;
  let fixture: ComponentFixture<TripsVerifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TripsVerifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TripsVerifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
