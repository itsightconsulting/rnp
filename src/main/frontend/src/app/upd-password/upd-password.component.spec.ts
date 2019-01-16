import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdPasswordComponent } from './upd-password.component';

describe('UpdPasswordComponent', () => {
  let component: UpdPasswordComponent;
  let fixture: ComponentFixture<UpdPasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdPasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
