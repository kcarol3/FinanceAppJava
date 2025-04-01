package com.example.FinanceApp.flyweight;

//Tydzien 3, wzrozec Flywieght 2, tworzenie obiektow bankow
public class Bank implements BankInterface {
    private final String name;
    private final String bicCode;

    public Bank(String name, String bicCode) {
        this.name = name;
        this.bicCode = bicCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBicCode() {
        return bicCode;
    }
}

// koniec, tydzien 3, Wzorzec flyweight 2
