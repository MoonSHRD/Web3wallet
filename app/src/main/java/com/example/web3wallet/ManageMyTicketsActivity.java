package com.example.web3wallet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.example.web3wallet.MainActivity.CUSTOM_GAS_LIMIT;
import static com.example.web3wallet.MainActivity.CUSTOM_GAS_PRICE;
import static com.example.web3wallet.MainActivity.credentials;
import static com.example.web3wallet.MainActivity.ticketfactory;
import static com.example.web3wallet.MainActivity.web3;

public class ManageMyTicketsActivity extends AppCompatActivity {

    Ticket721 ticket;
    TicketSale721 ticketSale721;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_tickets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupTicketAsContract();
        getMyTicketsByOwner(credentials.getAddress());
        setupTicketSaleContract();

        getAddressByOwner();
        getTicketInfo();

        Button scanQrBtn = findViewById(R.id.scanQr);
        scanQrBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scanQrCode();
                getTicketInfo();
            }
        });

        Button createNewTypeSale = findViewById(R.id.createNewTypeSale);
        createNewTypeSale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createNewType();
                getTicketSale("mutabor@conference.moonshard.tech");
            }
        });
    }

    public void setupTicketAsContract() {
        try {
            //   RemoteCall<String> ticket_template_address = ticketfactory.getTicketTemplateAddress().send();   //FIXME: change to async send.  //TODO: change to async send
            CompletableFuture<String> ticket_template_address = ticketfactory.getTicketTemplateAddress().sendAsync();
            String ticket_address = ticket_template_address.get();
            ticket = Ticket721.load(ticket_address, web3, credentials, CUSTOM_GAS_PRICE, CUSTOM_GAS_LIMIT);
        } catch (Exception e) {
            Log.e("eth_call_fail", "error during ticket contract setup: ", e);
        }
    }

    public void setupTicketSaleContract() {
        String[] saleInstances = getTicketSale("mutabor@conference.moonshard.tech");    // Getting every items on sale
        String itemSaleAddress = saleInstances[0];      // Get address of particular item sale
        ticketSale721 = TicketSale721.load(itemSaleAddress, web3, credentials, CUSTOM_GAS_PRICE, CUSTOM_GAS_LIMIT);
    }


    // возвращает инстансы продаж по каждому типу билетов т.е адресс продажи в массиве
    public String[] getTicketSale(String event_jid) {
        try {
            CompletableFuture<BigInteger> event_id_call = ticketfactory.getEventIdByJid(event_jid).sendAsync();
            BigInteger event_id = event_id_call.get();
            //RemoteCall<String[]> SaleInstances = ticket.eventsales(event_id);  // FIXME: Missing getter for eventsales at Ticket721.sol.
            CompletableFuture<List> SaleInstancesCall = ticket.getTicketSales(event_id).sendAsync(); //вовзращает все продажи всех билетов
            List SaleInstancesList = SaleInstancesCall.get();
            String[] SaleInstances = new String[SaleInstancesList.size()];
            SaleInstancesList.toArray(SaleInstances);
            return SaleInstances;
        } catch (Exception e) {
            Log.e("error", "error in transaction remote call: " + e);
            return null;
        }
    }


    //получаю все купленные билеты - работает отлично
    public List<BigInteger> getMyTicketsByOwner(String owner) {
        try {
            CompletableFuture<List> ticketsCall = ticket.getTicketByOwner(owner).sendAsync();
            List<BigInteger> ticketsList = ticketsCall.get();
            Log.d("tickets", "my tickets value: " + ticketsList.size());
            return ticketsList;
        } catch (Exception e) {
            return null;
        }
    }

    //todo какой метод для получения информации по билету? Мол я передаю айдишник и получаю инфу.
    public Tuple3<BigInteger, BigInteger, String> getTicketInfo() {
        try {
            CompletableFuture<Tuple3<BigInteger, BigInteger, String>> ticketsCall = ticket.ticketInfoStorage(BigInteger.valueOf(1)).sendAsync();
            Tuple3<BigInteger, BigInteger, String> ticketInfo = ticketsCall.get();
            BigInteger ticketType = ticketInfo.component2();
            //Log.d("tickets","my tickets value: " + ticketsList.size());
            return ticketInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAddressByOwner() {
        try {
            /*
            1. Получаю массив продаж по каждому типу getTicketSale
            2.  Создаю инстанс  ticketSale721 по каждому
            3. в каждом инстансе я могу посмотерть адресс кошелька орга
               по идее должен быть один и тот же во всех тикет сейлах
             */

            CompletableFuture<String> ticketsCall = ticketSale721.wallet().sendAsync();
            String addressWalletOwner = ticketsCall.get();
            Log.d("addressWalletOwner", "addressWalletOwner: " + addressWalletOwner);
            return addressWalletOwner;
        } catch (Exception e) {
            return null;
        }
    }

    public void scanQrCode() {
        CompletableFuture<TransactionReceipt> receipt = ticketSale721.redeemTicket(credentials.getAddress(), BigInteger.valueOf(1)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            Log.d("scanQrCode", "transactionReceipt: " + transactionReceipt.getBlockHash());

            List event_tx = ticket.getTicketFulfilledHumanEvents(transactionReceipt);
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
    }

    public void createNewType() {
        CompletableFuture<TransactionReceipt> receipt = ticketfactory.PlugInTicketSale("0xaad88c7292dd3e1a19b1143a718eb18c999e9aec", BigInteger.valueOf(1)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            List event_tx_ticket = ticket.getTicketBoughtHumanEvents(transactionReceipt);

            Log.d("scanQrCode", "transactionReceipt: " + transactionReceipt.getBlockHash());
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
    }

    public List createNewTypeModule() {
        List arrayList = new ArrayList();

        //возращть данные по транзе или ошибку
        CompletableFuture<TransactionReceipt> receipt = ticketfactory.PlugInTicketSale("0xaad88c7292dd3e1a19b1143a718eb18c999e9aec", BigInteger.valueOf(1)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            List event_tx_ticket = ticket.getTicketBoughtHumanEvents(transactionReceipt);
            arrayList.addAll(event_tx_ticket);
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
        return arrayList;
    }

    /*
    public List createNewTypeModule() {
        List arrayList = new ArrayList();

        //возращть данные по транзе или ошибку
        CompletableFuture<TransactionReceipt> receipt = ticketfactory.PlugInTicketSale("0xaad88c7292dd3e1a19b1143a718eb18c999e9aec", BigInteger.valueOf(1)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            List event_tx_ticket = ticket.getTicketBoughtHumanEvents(transactionReceipt);
            arrayList.addAll(event_tx_ticket);
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
        return arrayList;
    }

    public void createNewTypeInMoonshard() {
        createNewTypeModule().thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            Log.d("scanQrCode", "transactionReceipt: " + transactionReceipt.getBlockHash());
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });

    }

     */
}
