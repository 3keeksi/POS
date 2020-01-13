package at.htlkaindorf.exa_202_contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import at.htlkaindorf.exa_202_contactsapp.beans.Contact;
import at.htlkaindorf.exa_202_contactsapp.bl.Utils;

public class EditContact extends AppCompatActivity {

    private Contact contact;
    private EditText edFirstname;
    private EditText edLastname;
    private EditText edPhone;
    private EditText edLang;
    private RadioGroup rgGender;
    private ArrayList<RadioButton> rbtns;
    private Button btSave;
    private Button btCancel;
    private ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        contact = Utils.currentContact;


        edFirstname = findViewById(R.id.edFirstname);
        edLastname = findViewById(R.id.edLastname);
        edPhone = findViewById(R.id.edPhone);
        edLang = findViewById(R.id.edLang);
        rgGender = findViewById(R.id.rgGender);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);
        imgProfile = findViewById(R.id.imgProfile);

        Picasso.get().load(contact.getPicture()).into(imgProfile);

        btSave.setOnClickListener(v -> {
            contact.setFirstname(edFirstname.getText().toString());
            contact.setLastname(edLastname.getText().toString());
            contact.setLanguage(edLang.getText().toString());
            contact.setGender(getGender());
            contact.setPhoneNumber(edPhone.getText().toString());
            Utils.main.onEditContact();

            Intent intent = new Intent(Utils.main, ShowContact.class);
            Utils.main.startActivity(intent);
//            finish();
        });
        btCancel.setOnClickListener(v -> finish());

        edFirstname.setText(contact.getFirstname());
        edLastname.setText(contact.getLastname());
        edLang.setText(contact.getLanguage());
        edPhone.setText(contact.getPhoneNumber());

        rbtns = new ArrayList<>(Arrays.asList(findViewById(R.id.rbMale), findViewById(R.id.rbFemale), findViewById(R.id.rbDiverse)));
        rbtns.forEach(rb -> {
            if (rb.getText().toString().charAt(0) == contact.getGender()) {
                rb.setChecked(true);
            }
        });
    }

    private char getGender() {
        RadioButton rb = findViewById(rgGender.getCheckedRadioButtonId());
        return rb.getText().toString().charAt(0);
    }
}
