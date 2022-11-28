export class checkout {
    #total = 0;

    constructor(prices) {
        this.prices = prices
    }

    scan(item) {
        if (item in this.prices)
            this.#total += this.prices[item];
        else
            throw new Error(`Unknown price for item ${item} in ${Object.entries(this.prices).reduce((accumulator, entry) => accumulator + entry[0] + ":" + entry[1] + " ", "")}`)
    }

    total() {
        return this.#total
    }
}
