package at.htlkaindorf.exa_203_bankaccountapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Contact_IO;
import at.htlkaindorf.exa_203_bankaccountapp.bl.AccountAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvAccounts;
    private AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new AccountAdapter(Contact_IO.loadAccounts(getAssets()), this);

        rvAccounts = findViewById(R.id.rvAccounts);
        rvAccounts.setLayoutManager(new LinearLayoutManager(this));
        rvAccounts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String filter;
        switch(item.getItemId()){
            case R.id.miGiro:
                filter = "giro";
                break;
            case R.id.miStudent:
                filter = "student";
                break;
            default:
                filter = "";
                break;
        }
        adapter.filterAccounts(filter);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == 3) {
            String iban = data.getStringExtra("iban");
            Account acc = data.getParcelableExtra("account");
            double amount = data.getDoubleExtra("amount", 0);
            adapter.transfer(acc, iban, amount);
        }
    }
}
