const unit_prices = {
    "A": 50,
    "B": 30,
    "C": 20,
    "D": 15
}

export class checkout {
    constructor() {
        this.total = 0
    }

    scan(str) {
        this.total = this.total + price(str);
    }
}

export function price(str) {
    let total = 0;
    for (const item of str.split(""))
        if (item in unit_prices)
            total += unit_prices[item]
    return total
}