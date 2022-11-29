import {Checkout} from "../lib/checkout.js";

/****************************************
 * | Item | Unit Price | Special Price |
 * | :--: | :--------: | :-----------: |
 * |  A   |  50        |   3 for 130   |
 * |  B   |  30        |   2 for 45    |
 * |  C   |  20        |               |
 * |  D   |  15        |               |
 ***************************************/
const prices = {
    "A": {1: 50, 3: 130},
    "B": {1: 30, 2: 45},
    "C": {1: 20},
    "D": {1: 15}
}

function price(str) {
    const co = new Checkout(prices)
    str.split("").forEach((item) => co.scan(item))
    return co.total()
}

describe('Checkout Pricing', function () {
    it('incremental', function () {
        const co = new Checkout(prices)
        expect(co.total()).toEqual(0)

        co.scan("A");
        expect(co.total()).toEqual(50)

        co.scan("B");
        expect(co.total()).toEqual(80)

        co.scan("A");
        expect(co.total()).toEqual(130)

        co.scan("A");
        expect(co.total()).toEqual(160)

        co.scan("B");
        expect(co.total()).toEqual(175)
    });

    it('total from singles', function () {
        expect(price("")).toEqual(0);
        expect(price("A")).toEqual(50);
        expect(price("AB")).toEqual(80);
        expect(price("CDBA")).toEqual(115);
    });

    it('total from multiples', function () {
        expect(price("AA")).toEqual(100);
        expect(price("AAA")).toEqual(130);
        expect(price("AAAA")).toEqual(180);
        expect(price("AAAAA")).toEqual(230);
        expect(price("AAAAAA")).toEqual(260);
    });

    it('total from composition', function () {
        expect(price("AAAB")).toEqual(160);
        expect(price("AAABB")).toEqual(175);
        expect(price("AAABBD")).toEqual(190);
        expect(price("DABABA")).toEqual(190);
    });
});
