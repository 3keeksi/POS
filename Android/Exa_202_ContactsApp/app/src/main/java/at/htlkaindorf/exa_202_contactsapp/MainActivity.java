package at.htlkaindorf.exa_202_contactsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import at.htlkaindorf.exa_202_contactsapp.beans.Contact;
import at.htlkaindorf.exa_202_contactsapp.beans.IO_Handler;
import at.htlkaindorf.exa_202_contactsapp.bl.ContactAdapter;
import at.htlkaindorf.exa_202_contactsapp.bl.Utils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private ContactAdapter adapter;
    private SearchView searchView;

    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.main = this;

        IO_Handler.setContext(getApplicationContext());

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ContactAdapter();
        recView.setAdapter(adapter);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchListener());
    }

    private class SearchListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.filterContacts(newText);
            return true;
        }
    }

    public void onEditContact() {
        adapter.notifyDataSetChanged();
    }

    public void removeContact(Contact contact) {
        adapter.removeContact(contact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.update();
    }
}
