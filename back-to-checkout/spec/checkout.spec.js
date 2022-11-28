import {price} from "../lib/checkout.js";

describe('Checkout Pricing', function() {
    it('Empty basket cost nothing', function () {
        expect(price("")).toEqual(0);
    });
    it('1 A costs 50', function () {
        expect(price("A")).toEqual(50);
    });
    it('1 B costs 30', function () {
        expect(price("B")).toEqual(30);
    });
    it('1 C costs 20', function () {
        expect(price("C")).toEqual(20);
    });
    it('1 D costs 15', function () {
        expect(price("D")).toEqual(15);
    });
    it('Several single items addition', function () {
        expect(price("AB")).toEqual(80);
        expect(price("CDBA")).toEqual(115);
    });
});
