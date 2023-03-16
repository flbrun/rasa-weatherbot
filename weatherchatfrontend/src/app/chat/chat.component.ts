
import {Component, OnInit, ElementRef, ViewChild, AfterViewChecked} from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html'
})
export class ChatComponent implements OnInit, AfterViewChecked {

  @ViewChild('verticalScroll') private myScrollContainer: ElementRef;

  ngOnInit(): void {this.scrollToBottom();}

  ngAfterViewChecked(): void {this.scrollToBottom();}

  scrollToBottom(): void {
    try
    {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    }
    catch(err) { }
  }
}
