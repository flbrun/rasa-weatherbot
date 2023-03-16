
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ChatareaComponent } from './chatarea.component';
import {AppComponent} from "../../app.component";
import {ChatComponent} from "../chat.component";
import {InputboxComponent} from "../inputbox/inputbox.component";

describe('ChatareaComponent', () => {
  let component: ChatareaComponent;
  let fixture: ComponentFixture<ChatareaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppComponent,
                      ChatComponent,
                      InputboxComponent,
                      ChatareaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChatareaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
