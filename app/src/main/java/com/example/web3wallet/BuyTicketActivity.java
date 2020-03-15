package com.example.web3wallet;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.web3j.protocol.core.RemoteCall;

import java.math.BigInteger;

import static com.example.web3wallet.MainActivity.CUSTOM_GAS_LIMIT;
import static com.example.web3wallet.MainActivity.CUSTOM_GAS_PRICE;
import static com.example.web3wallet.MainActivity.credentials;
import static com.example.web3wallet.MainActivity.ticketfactory;
import static com.example.web3wallet.MainActivity.web3;

public class BuyTicketActivity extends AppCompatActivity {


    public Ticket721 ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setupTicketContract() {
        RemoteCall<String> ticket_template_address = ticketfactory.getTicketTemplateAddress();
        String ticket_address = ticket_template_address.toString();
        ticket = Ticket721.load(ticket_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);

    }

    public void getTicketSale(String event_jid){

        RemoteCall<BigInteger> event_id = ticketfactory.getEventIdByJid(event_jid);
        


    }


}
