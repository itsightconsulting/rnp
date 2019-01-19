import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDatosIdenComponent } from './form-datos-iden.component';

describe('FormDatosIdenComponent', () => {
  let component: FormDatosIdenComponent;
  let fixture: ComponentFixture<FormDatosIdenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormDatosIdenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormDatosIdenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
