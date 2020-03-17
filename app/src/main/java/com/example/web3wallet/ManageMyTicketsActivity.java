package com.example.web3wallet;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.web3j.protocol.core.RemoteCall;

import java.util.List;

import static com.example.web3wallet.MainActivity.CUSTOM_GAS_LIMIT;
import static com.example.web3wallet.MainActivity.CUSTOM_GAS_PRICE;
import static com.example.web3wallet.MainActivity.credentials;
import static com.example.web3wallet.MainActivity.ticketfactory;
import static com.example.web3wallet.MainActivity.web3;

public class ManageMyTicketsActivity extends AppCompatActivity {

    Ticket721 ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_tickets);
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


    public void setupTicket() {
        RemoteCall<String> ticket_template_address = ticketfactory.getTicketTemplateAddress();
        String ticket_address = ticket_template_address.toString();
        ticket = Ticket721.load(ticket_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);
    }


    public Integer[] getMyTickets(String owner) {
        try {
            RemoteCall<List> tickets_call = ticket.getTicketByOwner(owner);
            List tickets_list = tickets_call.send();
            Integer[] TicketsArray = new Integer[tickets_list.size()];
            tickets_list.toArray(TicketsArray);
            return TicketsArray;
        } catch (Exception e) {
            return null;
        }

    }


}
