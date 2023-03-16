
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InputboxComponent } from './inputbox.component';
import {AppComponent} from "../../app.component";
import {ChatComponent} from "../chat.component";
import {ChatareaComponent} from "../chatarea/chatarea.component";

describe('InputboxComponent', () => {
  let component: InputboxComponent;
  let fixture: ComponentFixture<InputboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppComponent,
                      ChatComponent,
                      InputboxComponent,
                      ChatareaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
