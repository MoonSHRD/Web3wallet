package com.example.web3wallet.lib;

import android.util.Log;

import com.example.web3wallet.TicketFactory721;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BuyTicketService{

    public void createTicketSale(BigInteger price, String event_JID, BigInteger limit) {
        String organizer_address = MainService.getWalletService().getCredentials().getAddress();
        try {
            CompletableFuture<TransactionReceipt> receipt = MainService.getWalletService().getTicketfactory().createTicketSale(organizer_address,price,event_JID,limit).sendAsync();
            receipt.thenAccept(transactionReceipt -> {
                String txHash = transactionReceipt.getTransactionHash(); // can play with that
                Log.d("receipt", "receipt"+transactionReceipt);
                Log.d("txhash", "txhash:" +txHash);

                List<TicketFactory721.SaleCreatedHumanEventResponse> event_tx = MainService.getWalletService().getTicketfactory().getSaleCreatedHumanEvents(transactionReceipt);
            }).exceptionally(transactionReceipt -> {
                return null;
            });
        } catch (Exception e) {
            Log.e("tx exeption", "Sale creation fails:",e);
        }
    }
}
