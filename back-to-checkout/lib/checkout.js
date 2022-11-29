function listPackSizes(pricing) {
    return Object.keys(pricing).map(key => Number(key));
}

function biggestPackSizes(size, packs) {
    if (size <= 0) return []
    const maxFit = packs
        .filter((pack) => pack <= size)
        .reduce((currentMax, current) => Math.max(currentMax, current));
    return [maxFit, ...biggestPackSizes(size - maxFit, packs)]
}

export class Checkout {
    #pricings = {}
    #items = {}

    constructor(pricings) {
        this.#pricings = pricings
    }

    scan(item) {
        if (item in this.#items)
            this.#items[item] += 1;
        else
            this.#items[item] = 1;
    }

    total() {
        return Object.entries(this.#items)
            .filter(([item, _]) => item in this.#pricings)
            .map(([item, qty]) => [this.#pricings[item], qty])
            .map(([pricing, qty]) => [pricing, biggestPackSizes(qty, listPackSizes(pricing))])
            .flatMap(([pricing, packs]) => packs.map(pack => pricing[pack]))
            .reduce((currentTotal, subTotal) => currentTotal + subTotal, 0);
    }
}
