package at.htlkaindorf.exa_202_contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import at.htlkaindorf.exa_202_contactsapp.R;
import at.htlkaindorf.exa_202_contactsapp.beans.Contact;
import at.htlkaindorf.exa_202_contactsapp.bl.Utils;

public class ShowContact extends AppCompatActivity {
    private Contact contact;
    private TextView tvName;
    private ImageButton btEdit;
    private ImageView img;
    private TextView tvPhone;
    private TextView tvLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        contact = Utils.currentContact;

        tvName = findViewById(R.id.tvName);
        btEdit = findViewById(R.id.btEdit);
        img = findViewById(R.id.img);
        tvPhone = findViewById(R.id.tvPhone);
        tvLang = findViewById(R.id.tvLang);

        Picasso.get().load(contact.getPicture()).into(img);
        tvName.setText(contact.getFirstname() + " " + contact.getLastname());
        tvPhone.setText(contact.getPhoneNumber());
        tvLang.setText(contact.getLanguage());
        btEdit.setOnClickListener(v -> {
            Intent intent = new Intent(Utils.main, EditContact.class);
            Utils.main.startActivity(intent);
        });
    }
}
