
import {Component, OnInit} from '@angular/core';
import {catchError, map, of} from "rxjs";
import {WebsocketService} from "../../service/websocket.service";
import {MessagehandlerService} from "../../service/messagehandler.service";
import { faAngleDoubleDown } from "@fortawesome/free-solid-svg-icons";
import { faAngleDoubleUp } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-chatarea',
  templateUrl: './chatarea.component.html',
})

export class ChatareaComponent implements OnInit{

  stringObject: any;
  messageArray = [];
  faAngleDoubleDown = faAngleDoubleDown;
  faAngleDoubleUp = faAngleDoubleUp;

  constructor(private websocketService: WebsocketService, private messagehandlerService: MessagehandlerService)
  {
    this.messageArray = this.messagehandlerService.messageArray;
    this.messageArray.push({name: null, data: null, message: null});
  }

  ngOnInit(): void {
    this.websocketService.createObservableSocket()
      .pipe(
        map(data => {
          this.stringObject = JSON.parse(data);
          this.messagehandlerService.receiveNewMessage('Bot', this.stringObject.text, this.stringObject.data);
          return data;
        }),
        catchError(err => {
          console.log(err);
          return of(null);
        })
      ).subscribe();
  }
}
