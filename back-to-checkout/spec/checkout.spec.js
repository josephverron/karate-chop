import {price} from "../lib/checkout.js";

describe('Checkout Pricing', function() {
  it('Empty basket cost nothing', function() {
    expect(price("")).toEqual(0);
  });
});
