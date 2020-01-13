package at.htlkaindorf.exa_203_bankaccountapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.bl.AccountAdapter;

public class Transaction extends AppCompatActivity {

    private ImageView ivProfile;
    private TextView tvIBAN;
    private TextView tvTitle;
    private TextView tvMoney;
    private TextView tvAvMoney;
    private AutoCompleteTextView edIban;
    private TextView edAmount;
    private Button btTransfer;
    private double currentMinus = 0;
    private double currentMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Intent intent = getIntent();
        ArrayList<String> ibans = intent.getStringArrayListExtra("ibans");
        Account account = intent.getParcelableExtra("account");

        ivProfile = findViewById(R.id.ivProfile);
        tvIBAN = findViewById(R.id.tvIBAN);
        tvTitle = findViewById(R.id.tvTitle);
        tvMoney = findViewById(R.id.tvMoney);
        tvAvMoney = findViewById(R.id.tvAvailableMoney);

        ivProfile.getLayoutParams().width = 0;
        ivProfile.setPadding(0, 0, 0, 0);
        ivProfile.requestLayout();


        edIban = findViewById(R.id.edIban);
        edAmount = findViewById(R.id.edAmount);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, ibans);
        edIban.setAdapter(adapter);

        edAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) return;
                try {
                    currentMinus = Double.parseDouble(s.toString());
                    currentMoney = account.getBalance() - currentMinus;
                    tvMoney.setText(String.format("€ %,10.2f", currentMoney));

                    if(currentMoney < 0) {
                        tvMoney.setTextColor(Color.RED);
                    }

                    if(currentMoney < -1*(account.getAvailable() - account.getBalance())) {
                        tvMoney.setTextColor(Color.RED);
                        edAmount.setTextColor(Color.RED);
                        btTransfer.setEnabled(false);
                    } else {
                        tvMoney.setTextColor(Color.GREEN);
                        edAmount.setTextColor(Color.GREEN);
                        btTransfer.setEnabled(true);
                    }
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btTransfer = findViewById(R.id.btTransfer);
        btTransfer.setOnClickListener(v -> {
            String iban = edIban.getText().toString();
            if(!ibans.contains(iban)) {
                Toast.makeText(this, "Select a IBAN", Toast.LENGTH_LONG).show();
                return;
            }
            intent.putExtra("account", account);
            intent.putExtra("amount",currentMinus);
            intent.putExtra("iban", iban);
            setResult(3, intent);
            finish();
        });

        AccountAdapter.setAccountItem(
                account,
                tvTitle,
                tvAvMoney,
                tvMoney,
                ivProfile,
                tvIBAN);
    }
}
