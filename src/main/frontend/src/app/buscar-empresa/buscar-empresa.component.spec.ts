import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarEmpresaComponent } from './buscar-empresa.component';

describe('BuscarEmpresaComponent', () => {
  let component: BuscarEmpresaComponent;
  let fixture: ComponentFixture<BuscarEmpresaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuscarEmpresaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuscarEmpresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
