package com.example.web3wallet.lib;

import org.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class TicketService {

    public Tuple3<BigInteger, BigInteger, String> getTicketInfo() {
        try {
            CompletableFuture<Tuple3<BigInteger, BigInteger, String>> ticketsCall = MainService.getWalletService().getTicket().ticketInfoStorage(BigInteger.valueOf(1)).sendAsync();
            Tuple3<BigInteger, BigInteger, String> ticketInfo = ticketsCall.get();
            BigInteger ticketType = ticketInfo.component2();
            //Log.d("tickets","my tickets value: " + ticketsList.size());
            return ticketInfo;
        } catch (Exception e) {
            return null;
        }
    }

}
