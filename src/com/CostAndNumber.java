package com;

class CostAndNumber {
    private int number;
    private int cost;

    CostAndNumber(int number, int cost) {
        this.number = number;
        this.cost = cost;
    }

    public int getNumber() {
        return number;
    }

    public int getCost() {
        return cost;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}