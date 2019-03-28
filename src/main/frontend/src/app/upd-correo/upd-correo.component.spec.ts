import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdCorreoComponent } from './upd-correo.component';

describe('UpdCorreoComponent', () => {
  let component: UpdCorreoComponent;
  let fixture: ComponentFixture<UpdCorreoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdCorreoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdCorreoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
