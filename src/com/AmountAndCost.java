package com;

class AmountAndCost {
    private int amount;
    private int cost;

    public AmountAndCost(int amount, int cost) {
        this.amount = amount;
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public int getCost() {
        return cost;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}