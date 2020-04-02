package com.example.web3wallet;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import static com.example.web3wallet.MainActivity.credentials;
import static com.example.web3wallet.MainActivity.ticketfactory;

public class ManageTicketSaleActivity extends AppCompatActivity {

 //   public TicketFactory721 ticketfactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ticket_sale);
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

/*
    public void setupContracts() {
        ticketfactory = MainActivity.ticketfactory;
    }
*/

    public void createSaleViewTest(View v) {
        EditText ePrice = (EditText) findViewById(R.id.TicketPrice);
        String sPrice = ePrice.getText().toString();
        Integer iPrice = Integer.parseInt(sPrice);
        BigInteger price = BigInteger.valueOf(iPrice);

        EditText eLimit = (EditText) findViewById(R.id.max_tickets);
        String sLimit = eLimit.getText().toString();
        Integer iLimit = Integer.parseInt(sLimit);
        BigInteger limit = BigInteger.valueOf(iLimit);

        // for test
        String event_JID = "mutabor@conference.moonshard.tech";
        createTicketSale(price,event_JID,limit);
    }



    public void createTicketSale(BigInteger price, String event_JID, BigInteger limit) {


        String organizer_address = credentials.getAddress();



        try {
            CompletableFuture<TransactionReceipt> receipt = ticketfactory.createTicketSale(organizer_address,price,event_JID,limit).sendAsync();
            receipt.thenAccept(transactionReceipt -> {
                // get tx receipt only if successful
                String txHash = transactionReceipt.getTransactionHash(); // can play with that
                // vtxHash = txHash;
                Log.d("receipt", "receipt"+transactionReceipt);
                Log.d("txhash", "txhash:" +txHash);

            }).exceptionally(transactionReceipt -> {

                return null;
            });
        } catch (Exception e) {
            // Log.d("tx hash", "txhash"+txHash);
            Log.e("tx exeption", "Sale creation fails:",e);

        }
    }


}
