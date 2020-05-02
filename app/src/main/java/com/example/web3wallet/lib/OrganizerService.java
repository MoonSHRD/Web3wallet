package com.example.web3wallet.lib;

import android.util.Log;

import com.example.web3wallet.contracts.TicketSale721;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrganizerService {

    TicketSale721 ticketSale721; //создается для каждой отдельной продажи

    public OrganizerService(String eventJid) {
        setupTicketSaleContract(eventJid);
    }

    public  CompletableFuture<TransactionReceipt> createTicketSale(String sPrice, String event_JID, String sLimit) {
        int iPrice = Integer.parseInt(sPrice);
        BigInteger price = BigInteger.valueOf(iPrice);

        int iLimit = Integer.parseInt(sLimit);
        BigInteger limit = BigInteger.valueOf(iLimit);

        String organizer_address = MainService.getWalletService().getCredentials().getAddress();

        return MainService.getWalletService().getTicketfactory().createTicketSale(organizer_address, price, event_JID, limit).sendAsync();
    }

    /*
    public static void createTicketSale(String sPrice, String event_JID, String sLimit) {

        int iPrice = Integer.parseInt(sPrice);
        BigInteger price = BigInteger.valueOf(iPrice);

        int iLimit = Integer.parseInt(sLimit);
        BigInteger limit = BigInteger.valueOf(iLimit);

        String organizer_address = MainService.getWalletService().getCredentials().getAddress();
        try {
            CompletableFuture<TransactionReceipt> receipt = MainService.getWalletService().getTicketfactory().createTicketSale(organizer_address,price,event_JID,limit).sendAsync();
            receipt.thenAccept(transactionReceipt -> {
                String txHash = transactionReceipt.getTransactionHash(); // can play with that
                Log.d("createTicketSale", "receipt"+transactionReceipt);
                Log.d("createTicketSale", "txhash:" +txHash);

                List<TicketFactory721.SaleCreatedHumanEventResponse> event_tx = MainService.getWalletService().getTicketfactory().getSaleCreatedHumanEvents(transactionReceipt);
            }).exceptionally(transactionReceipt -> {
                return null;
            });
        } catch (Exception e) {
            Log.e("createTicketSale", "Sale creation fails:",e);
        }
    }
     */

    public void setupTicketSaleContract(String eventJid) {
        String[] saleInstances = getTicketSale(eventJid);    // Getting every items on sale
        String itemSaleAddress = saleInstances[0];      // Get address of particular item sale
        ticketSale721 = TicketSale721.load(itemSaleAddress, MainService.getWalletService().getWeb3(), MainService.getWalletService().getCredentials(), MainService.getWalletService().getCustomGasPrice(), MainService.getWalletService().getCustomGasLimit());
    }

    // возвращает инстансы продаж по каждому типу билетов т.е адресс продажи в массиве
    public String[] getTicketSale(String event_jid) {
        try {
            CompletableFuture<BigInteger> event_id_call = MainService.getWalletService().getTicketfactory().getEventIdByJid(event_jid).sendAsync();
            BigInteger event_id = event_id_call.get();
            CompletableFuture<List> SaleInstancesCall = MainService.getWalletService().getTicket().getTicketSales(event_id).sendAsync(); //вовзращает все продажи всех билетов
            List SaleInstancesList = SaleInstancesCall.get();
            String[] SaleInstances = new String[SaleInstancesList.size()];
            SaleInstancesList.toArray(SaleInstances);
            return SaleInstances;
        } catch (Exception e) {
            Log.e("error", "error in transaction remote call: " + e);
            return null;
        }
    }

    public void scanQrCode() {
        CompletableFuture<TransactionReceipt> receipt = ticketSale721.redeemTicket(MainService.getWalletService().getCredentials().getAddress(), BigInteger.valueOf(1)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            Log.d("scanQrCode", "transactionReceipt: " + transactionReceipt.getBlockHash());

            List event_tx = MainService.getWalletService().getTicket().getTicketFulfilledHumanEvents(transactionReceipt);
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
    }

    public void createNewType() {
        CompletableFuture<TransactionReceipt> receipt = MainService.getWalletService().getTicketfactory().PlugInTicketSale("0xaad88c7292dd3e1a19b1143a718eb18c999e9aec", BigInteger.valueOf(1)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            List event_tx_ticket = MainService.getWalletService().getTicket().getTicketBoughtHumanEvents(transactionReceipt);

            Log.d("scanQrCode", "transactionReceipt: " + transactionReceipt.getBlockHash());
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
    }
}
