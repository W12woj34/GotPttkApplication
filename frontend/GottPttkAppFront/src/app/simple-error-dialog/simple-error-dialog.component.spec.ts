import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleErrorDialogComponent } from './simple-error-dialog.component';

describe('SimpleErrorDialogComponent', () => {
  let component: SimpleErrorDialogComponent;
  let fixture: ComponentFixture<SimpleErrorDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimpleErrorDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleErrorDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
