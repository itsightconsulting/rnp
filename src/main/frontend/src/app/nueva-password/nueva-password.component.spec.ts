import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevaPasswordComponent } from './nueva-password.component';

describe('NuevaPasswordComponent', () => {
  let component: NuevaPasswordComponent;
  let fixture: ComponentFixture<NuevaPasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NuevaPasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevaPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
