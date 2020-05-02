package com.example.web3wallet.lib;

import android.util.Log;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class TransferService {

    public static void sendMoneyToAddress(String walletAddress, Float amount) {
        try {
            CompletableFuture<TransactionReceipt> receipt = Transfer.sendFunds(MainService.getWalletService().getWeb3(), MainService.getWalletService().getCredentials(), walletAddress, BigDecimal.valueOf(amount), Convert.Unit.ETHER).sendAsync();
            receipt.thenAccept(transactionReceipt -> {
                // get tx receipt only if successful
                String txHash = transactionReceipt.getTransactionHash();
                Log.d("txhash", "txhash for send funds: " + txHash);
            }).exceptionally(transactionReceipt -> {
                return null;
            });
        } catch (Exception e) {
            Log.e("tx exemption", "failed to transfer funds:" + e);
        }
    }

    public static CompletableFuture<TransactionReceipt> sendMoneyToAddressRx(String walletAddress, Float amount) {
        try {
            return Transfer.sendFunds(MainService.getWalletService().getWeb3(), MainService.getWalletService().getCredentials(), walletAddress, BigDecimal.valueOf(amount), Convert.Unit.ETHER).sendAsync();
        } catch (Exception e) {
            Log.e("tx exemption", "failed to transfer funds:" + e);
        }
        return null;
    }

    public  static CompletableFuture<TransactionReceipt>  sendTicketAsPresent(String addressFrom, String addressTo,int ticketId) {
       return MainService.getWalletService().getTicket().transferFrom(addressFrom, addressTo, BigInteger.valueOf(ticketId)).sendAsync();
    }

    public static CompletableFuture<TransactionReceipt> acceptTicketAsPresent(String addressTo,int ticketId) {
        return MainService.getWalletService().getTicket().approve(addressTo, BigInteger.valueOf(ticketId)).sendAsync();
    }
}
