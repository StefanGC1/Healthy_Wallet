package com.example.healthy_wallet;

import java.time.LocalDate;

public interface ITransaction {
    String getType();
    double getAmount();
    LocalDate getDate();
    String getDescription();
    String getCategory();
}
