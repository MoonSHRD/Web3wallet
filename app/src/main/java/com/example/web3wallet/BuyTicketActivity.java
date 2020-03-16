package com.example.web3wallet;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import org.web3j.protocol.core.RemoteCall;

import java.math.BigInteger;
import java.util.List;

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

    public String[] getTicketSale(String event_jid){

      //  String[] result;
        try {
            RemoteCall<BigInteger> event_id_call = ticketfactory.getEventIdByJid(event_jid);

            BigInteger event_id = event_id_call.send();
            //RemoteCall<String[]> SaleInstances = ticket.eventsales(event_id);  // FIXME: Missing getter for eventsales at Ticket721.sol.
            RemoteCall<List> SaleInstancesCall = ticket.getTicketSales(event_id);
            List SaleInstancesList = SaleInstancesCall.send();
            String[] SaleInstances = new String[SaleInstancesList.size()];
            SaleInstancesList.toArray(SaleInstances);
            return SaleInstances;
           // result = SaleInstances;
        } catch (Exception e) {
            Log.e("error","error in transaction remote call: " + e);
            return null;
        }
       // return result;
    }

    public BigInteger getEventIdByJid(String event_jid) {
        try {
            RemoteCall<BigInteger> event_id_call = ticketfactory.getEventIdByJid(event_jid);

            BigInteger event_id = event_id_call.send();
            return event_id;
        } catch (Exception e) {
            Log.e("error","error in transaction remote call: " + e);
            return null;
        }
    }


}
