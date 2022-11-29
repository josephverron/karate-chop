export class checkout {
    #prices = {}
    items = []

    constructor(prices) {
        this.#prices = prices
    }

    scan(item) {
        this.items.push(item);
    }

    total() {
        let total = 0;
        for (const item of this.items) {
            if (item in this.#prices)
                total += this.#prices[item][1];
            else
                throw new Error(`Unknown price for item ${item} in ${JSON.stringify(this.#prices)}`)
        }
        return total
    }
}
