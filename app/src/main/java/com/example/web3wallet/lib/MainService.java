package com.example.web3wallet.lib;

import android.content.Context;

public class MainService {
    private static WalletService instance;


    public static void initService(Context context) {
        instance = new WalletService(context);
    }

    public static WalletService getWalletService() {
        return instance;
    }
}
