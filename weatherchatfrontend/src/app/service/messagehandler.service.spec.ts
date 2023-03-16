
import { TestBed } from '@angular/core/testing';

import { MessagehandlerService } from './messagehandler.service';

describe('MessagehandlerService', () => {
  let service: MessagehandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MessagehandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
