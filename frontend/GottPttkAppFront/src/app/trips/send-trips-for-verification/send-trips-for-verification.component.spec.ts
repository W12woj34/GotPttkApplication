import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendTripsForVerificationComponent } from './send-trips-for-verification.component';

describe('SendTripsForVerificationComponent', () => {
  let component: SendTripsForVerificationComponent;
  let fixture: ComponentFixture<SendTripsForVerificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendTripsForVerificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendTripsForVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
