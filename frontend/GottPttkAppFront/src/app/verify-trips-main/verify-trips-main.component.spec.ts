import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyTripsMainComponent } from './verify-trips-main.component';

describe('VerifyTripsMainComponent', () => {
  let component: VerifyTripsMainComponent;
  let fixture: ComponentFixture<VerifyTripsMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifyTripsMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyTripsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
