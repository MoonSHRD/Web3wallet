package com.example.web3wallet.lib;

import android.util.Log;

import com.example.web3wallet.contracts.TicketSale721;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BuyTicketService{

   // private  static TicketSale721 ticket_sale;


    public static void buyTicketView(String eventJid,int amount) {
        String[] saleInstances = getTicketSale(eventJid);
        String itemSaleAddress = saleInstances[0];
        TicketSale721 itemSaleInstance = getSaleInstance(itemSaleAddress);

        buyTicket(itemSaleInstance,BigDecimal.valueOf(amount));
    }

    public static String[] getTicketSale(String event_jid){
        try {
            CompletableFuture<BigInteger> event_id_call = MainService.getWalletService().getTicketfactory().getEventIdByJid(event_jid).sendAsync();

            BigInteger event_id = event_id_call.get();
            CompletableFuture<List> SaleInstancesCall =  MainService.getWalletService().getTicket().getTicketSales(event_id).sendAsync();
            List SaleInstancesList = SaleInstancesCall.get();
            String[] SaleInstances = new String[SaleInstancesList.size()];
            SaleInstancesList.toArray(SaleInstances);
            return SaleInstances;
        } catch (Exception e) {
            Log.e("error","error in transaction remote call: " + e);
            return null;
        }
    }

    private static TicketSale721 getSaleInstance(String sale_address) {
        return TicketSale721.load(sale_address,MainService.getWalletService().getWeb3(),MainService.getWalletService().getCredentials(),MainService.getWalletService().getCustomGasPrice(),MainService.getWalletService().getCustomGasLimit());
    }

    public static void buyTicket(TicketSale721 sale_instance, BigDecimal amount) {
        BigDecimal price = getSalePriceInfo(sale_instance);
        BigDecimal price_wei = Convert.toWei(price, Convert.Unit.ETHER);
        BigDecimal sum = amount.multiply(price_wei);
        BigInteger sum_int = sum.toBigInteger();

        //вот эти вещи нельзя делать в активности,по хорошему нужно разделять на слои(юзаем архитектуру mvp or mvvm)
        CompletableFuture<TransactionReceipt> receipt = sale_instance.buyTicket(MainService.getWalletService().getCredentials().getAddress(),sum_int).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            String txHash = transactionReceipt.getTransactionHash(); // can play with that
            Log.d("receipt", "receipt"+transactionReceipt);
            Log.d("txhash", "txhash:" +txHash);

            List events_tx = sale_instance.getTokensPurchasedEvents(transactionReceipt);
            // do somthing with event response
            List event_tx_ticket = MainService.getWalletService().getTicket().getTicketBoughtHumanEvents(transactionReceipt);
            // do somthing with event response
        }).exceptionally(transactionReceipt -> {
            Log.e("tx error", "tx error when BUY TICKEt:  " + transactionReceipt);
            return null;
        });
    }

    public static BigDecimal getSalePriceInfo(TicketSale721 sale_instance) {
        try {
            CompletableFuture<BigInteger> price_wei_call = sale_instance.rate().sendAsync();
            BigInteger price_wei_int = price_wei_call.get();
            BigDecimal price_wei = new BigDecimal(price_wei_int);
            BigDecimal price = Convert.fromWei(price_wei, Convert.Unit.ETHER);
            return price;
        } catch (Exception e) {
            Log.e("tx-error","error in tx: " + e);
            return null;
        }
    }
}
