import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoOpcionComponent } from './listado-opcion.component';

describe('ListadoOpcionComponent', () => {
  let component: ListadoOpcionComponent;
  let fixture: ComponentFixture<ListadoOpcionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListadoOpcionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoOpcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
