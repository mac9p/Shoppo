import { TestBed } from '@angular/core/testing';

import { ShoppoFormService } from './shoppo-form.service';

describe('ShoppoFormService', () => {
  let service: ShoppoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShoppoFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
