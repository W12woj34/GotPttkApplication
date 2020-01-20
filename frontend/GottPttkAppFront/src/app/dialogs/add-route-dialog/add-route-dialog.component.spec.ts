import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRouteDialogComponent } from './add-route-dialog.component';

describe('AddRouteDialogComponent', () => {
  let component: AddRouteDialogComponent;
  let fixture: ComponentFixture<AddRouteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddRouteDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRouteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
