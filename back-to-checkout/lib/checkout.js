export class checkout {
    #total = 0;
    #prices = {}

    constructor(prices) {
        this.#prices = prices
    }

    scan(item) {
        if (item in this.#prices)
            this.#total += this.#prices[item][1];
        else
            throw new Error(`Unknown price for item ${item} in ${JSON.stringify(this.#prices)}`)
    }

    total() {
        return this.#total
    }
}
