import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyTripDetailsComponent } from './verify-trip-details.component';

describe('VerifyTripDetailsComponent', () => {
  let component: VerifyTripDetailsComponent;
  let fixture: ComponentFixture<VerifyTripDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifyTripDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyTripDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
