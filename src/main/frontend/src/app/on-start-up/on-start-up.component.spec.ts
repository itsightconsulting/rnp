import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OnStartUpComponent } from './on-start-up.component';

describe('OnStartUpComponent', () => {
  let component: OnStartUpComponent;
  let fixture: ComponentFixture<OnStartUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OnStartUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OnStartUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
