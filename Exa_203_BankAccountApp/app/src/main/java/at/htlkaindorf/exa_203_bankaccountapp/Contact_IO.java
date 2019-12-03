package at.htlkaindorf.exa_203_bankaccountapp;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;
import at.htlkaindorf.exa_203_bankaccountapp.beans.StudentAccount;

public class Contact_IO {
    public static List<Account> loadAccounts(AssetManager am) {
        try {
            return new BufferedReader(new InputStreamReader(am.open("account_data.csv")))
                    .lines()
                    .skip(1)
                    .map(l -> {
                        // id,type,iban,amount,overdraft,debitcard,interest
                        String[] split = l.split(",");
                        if(split[1].equalsIgnoreCase("giro")) {
                            return new GiroAccount(
                                    split[2],
                                    Double.parseDouble(split[3]),
                                    Float.parseFloat(split[6]),
                                    Double.parseDouble(split[4])
                            );
                        } else {
                            return new StudentAccount(
                                    split[2],
                                    Double.parseDouble(split[3]),
                                    Float.parseFloat(split[6]),
                                    false
                            );
                        }
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
