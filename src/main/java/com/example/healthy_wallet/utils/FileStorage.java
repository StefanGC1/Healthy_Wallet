package com.example.healthy_wallet.utils;

import com.example.healthy_wallet.AbstractTransaction;
import com.example.healthy_wallet.AbstractTransactionAdapter;
import com.example.healthy_wallet.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static volatile FileStorage instance = null;

    private static final String FILE_PATH = "transactions.json"; // temporary hard-coded file path
    private final Gson gson;

    public FileStorage() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(AbstractTransaction.class, new AbstractTransactionAdapter())
                .setPrettyPrinting()
                .create();
    }

    public static FileStorage getInstance() {
        if (instance == null) {
            instance = new FileStorage();
        }
        return instance;
    }

    public void saveTransactions(List<AbstractTransaction> transactions) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(transactions, writer);
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file to write to was not found / could not be opened.");
        } catch (IOException e) {
            System.err.println("Error: Could not write to file.");
        }
    }

    public List<AbstractTransaction> loadTransactions() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<ArrayList<AbstractTransaction>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file to read from was not found / could not be opened.");
            return null;
        } catch (IOException e) {
            System.err.println("Error: Could not read from file.");
            System.exit(1);
        }
        return null;
    }
}
