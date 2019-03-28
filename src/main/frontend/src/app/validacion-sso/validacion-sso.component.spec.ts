import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidacionSsoComponent } from './validacion-sso.component';

describe('ValidacionSsoComponent', () => {
  let component: ValidacionSsoComponent;
  let fixture: ComponentFixture<ValidacionSsoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidacionSsoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidacionSsoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
