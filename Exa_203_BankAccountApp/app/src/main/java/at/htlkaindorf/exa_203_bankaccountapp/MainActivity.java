package at.htlkaindorf.exa_203_bankaccountapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
}
