import {price} from "../lib/checkout.js";

describe('Checkout Pricing', function() {
    it('Empty basket cost nothing', function() {
        expect(price("")).toEqual(0);
    });
    it('1 A costs 50', function() {
        expect(price("A")).toEqual(50);
    });
});
