import {checkout} from "../lib/checkout.js";

const prices = {
    "A": 50,
    "B": 30,
    "C": 20,
    "D": 15
}

function price(str) {
    const co = new checkout(prices)
    str.split("").forEach((item) => co.scan(item))
    return co.total()
}

describe('Checkout Pricing', function () {
    it('incremental', function () {
        const co = new checkout(prices)
        expect(co.total()).toEqual(0)

        co.scan("A");
        expect(co.total()).toEqual(50)

        co.scan("B");
        expect(co.total()).toEqual(80)

        co.scan("A");
        expect(co.total()).toEqual(130)

        //co.scan("A");
        //expect(co.total()).toEqual(160)

        //co.scan("B");
        //expect(co.total()).toEqual(175)
    });

    it('total from singles', function () {
        expect(price("")).toEqual(0);
        expect(price("A")).toEqual(50);
        expect(price("AB")).toEqual(80);
        expect(price("CDBA")).toEqual(115);
    });

    it('total from multiples', function () {
        expect(price("AA")).toEqual(100);
        //expect(price("AAA")).toEqual(130);
        //expect(price("AAAA")).toEqual(180);
        //expect(price("AAAAA")).toEqual(230);
        //expect(price("AAAAAA")).toEqual(260);
    });

    xit('total from composition', function () {
        //expect(price("AAAB")).toEqual(160);
        //expect(price("AAABB")).toEqual(175);
        //expect(price("AAABBD")).toEqual(190);
        //expect(price("DABABA")).toEqual(190);
    });
});
