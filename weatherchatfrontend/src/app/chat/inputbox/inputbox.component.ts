/*Emilia Müller*/
import { Component } from '@angular/core';
import {WebsocketService} from "../../service/websocket.service";
import {MessagehandlerService} from "../../service/messagehandler.service";

@Component({
  selector: 'app-chat-inputbox',
  templateUrl: './inputbox.component.html'
})

export class InputboxComponent {

   messageArray = [];

  constructor(private websocketService: WebsocketService, private messagehandlerService: MessagehandlerService) {
   this.messageArray = messagehandlerService.messageArray;
  }

  status;
  newMessage = '';
  data;

  sendMessageToServer() {
    if (this.newMessage.trim().length != 0){
      this.status = this.websocketService.sendMessage(this.newMessage);
      this.messagehandlerService.sendNewMessage('You', this.newMessage);
      console.log(this.messagehandlerService);
    }
  }

  clear(){
    this.newMessage ='';
  }

}
