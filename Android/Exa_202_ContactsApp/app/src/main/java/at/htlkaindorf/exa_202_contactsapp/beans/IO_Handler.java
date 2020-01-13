package at.htlkaindorf.exa_202_contactsapp.beans;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IO_Handler {
    private static Context c;

    public static List<Contact> loadContacts() {
        AssetManager am = c.getAssets();
        List<Contact> contacts;

        try {
            InputStream is = am.open("contact_data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            contacts = br.lines().skip(1).map(Contact::new).collect(Collectors.toList());
            return contacts;
        } catch(IOException e) {
            return new ArrayList<>();
        }
    }

    public static void setContext(Context context) {
        c = context;
    }
}
