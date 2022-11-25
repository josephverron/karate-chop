import {price} from "../lib/checkout.js";

describe('Checkout Pricing', function() {
    it('Empty basket cost nothing', function() {
        expect(price("")).toEqual(0);
    });
    it('1 A costs 50', function() {
        expect(price("A")).toEqual(50);
    });
    it('1 B costs 30', function() {
        expect(price("B")).toEqual(30);
    });
});
