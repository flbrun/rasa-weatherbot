
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessagehandlerService {

  private _messageArray = [];
  get messageArray(): any[] {
    return this._messageArray;
  }
  receiveNewMessage(name, message, data){
    this._messageArray.push({name: name, message: message, data: data})
  }

  sendNewMessage(name, message){
    this._messageArray.push({name: name, message: message})
  }
}
