const unit_prices = {
    "A": 50,
    "B": 30
}

export function price(str){
    if(str in unit_prices)
        return unit_prices[str]
    return 0
}