package com.example.web3wallet;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//import android.support.v7.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {


    private String password;
    private EditText ePassword;
    private String walletPath;
    private File walletDir;
    private String fileName;
    public static File walletFile;

    public static Web3j web3;
    public static Credentials credentials;

    public static KNS kns;
    //  public static SuperFactory superfactory;
    public static TicketFactory721 ticketfactory;
    public Ticket721 ticket;
    public TicketSale721 ticketSale;


    public static String kns_address;
    //  public static String sup_factory_address;
    public static String ticket_factory_address;

    private String deployed_net_id;

    private ECKeyPair ecKeyPair;
    private String sPrivateKeyInHex;
    // custom gasprice
    public static final BigInteger CUSTOM_GAS_PRICE = Convert.toWei("8", Convert.Unit.GWEI).toBigInteger();  // FIXME

    //  private static final BigInteger CUSTOM_GAS_LIMIT = Convert.toWei("6721975", Convert.Unit.GWEI).toBigInteger();
    public static final BigInteger CUSTOM_GAS_LIMIT = BigInteger.valueOf(7_000_000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // workaround for bug with ECDA keys
        setupBouncyCastle();

        walletPath = getFilesDir().getAbsolutePath();
        walletDir = new File(walletPath);

        connectToLocalNetwork();

        String filePath = PreferenceManager.getDefaultSharedPreferences(this).getString("filePath", "");
        walletFile = new File(filePath);

        if (walletFile.exists() && !walletFile.isDirectory()) {
            fileName = walletFile.getName();
            loadDummyKey();
        } else {
            generatePrivateKey();
            loadDummyKey();
        }
        setupContracts();

        //todo  List<String> =  ticket.getTicketSales();
        getTicketInfoByJid();
    }

    public void createSimpleWallet(View v) {
        ePassword = findViewById(R.id.newPass);
        password = ePassword.getText().toString();
        generatePrivateKey();

        //todo we can send private key as 'sPrivateKeyInHex' on server

        /*
        try {
            fileName = WalletUtils.generateLightNewWalletFile(password, walletDir);
            String filepath = walletPath + "/" + fileName;
            toastAsync("Wallet generated" + filepath);
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }

         */
    }

    public void generatePrivateKey() {
        try {
            ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
            sPrivateKeyInHex = privateKeyInDec.toString(16);
            createWalletWithKey();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public void createWalletWithKey() {
        try {
            password = "1984";
            fileName = WalletUtils.generateWalletFile(password, ecKeyPair, walletDir, false);
            String filepath = walletPath + "/" + fileName;
            walletFile = new File(filepath);
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("filePath", filepath).apply();
            toastAsync("Wallet generated" + filepath);
        } catch (CipherException | IOException e) {
            e.printStackTrace();
        }
    }

    // create dummy key with default password
    public void createDummyKey() {
        password = "1984";

        try {
            fileName = WalletUtils.generateLightNewWalletFile(password, walletDir);
            String filepath = walletPath + "/" + fileName;
            walletFile = new File(filepath);
            toastAsync("Wallet generated" + filepath);
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }

    // load dummy key with default password
    public void loadDummyKey() {
        password = "1984";
        try {
            String path = walletPath + "/" + fileName;
            walletDir = new File(path);
            credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());
            Log.d("my address", "my address is: " + credentials.getAddress());


            // showAddress(v);
            // showBalance(v);
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }

    public void loadSimpleWallet(View v) {
        ePassword = findViewById(R.id.editPass);
        password = ePassword.getText().toString();

        try {
            String path = walletPath + "/" + fileName;
            walletDir = new File(path);
            credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());
            //  Log.d(TAG, "loadSimpleWallet: ");

            showAddress(v);
            showBalance(v);
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }


    public String getMyAddress() {
        try {
            credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
        return credentials.getAddress();
    }

    // using Infura as a provider for public eth network
    public void connectToInfuraNetwork(View v) {
        toastAsync("Connecting to Ethereum network...");
        // FIXME: Add your own API key here
        web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/YOURKEY"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if (!clientVersion.hasError()) {
                toastAsync("Connected!");
            } else {
                toastAsync(clientVersion.getError().getMessage());
            }
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }

    // using LOCAL client node as web3 provider (geth/ganache)
    public void connectToLocalNetwork() {
        toastAsync("Connecting to LOCAL ETH network...");

        // FIXME: for bug with ganache connection. Should be replaced by address of our node
       //  web3 = Web3j.build(new HttpService("HTTP://192.168.1.39:7545")); // defaults to http://localhost:8545/
       web3 = Web3j.build(new HttpService("HTTP://192.168.1.2:7545")); // defaults to http://localhost:8545/
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if (!clientVersion.hasError()) {
                toastAsync("Connected!");
                Log.d("client_web3_version", "client web3 version: " + clientVersion.getWeb3ClientVersion());
            } else {
                toastAsync(clientVersion.getError().getMessage());
            }
        } catch (Exception e) {
            toastAsync(e.getMessage());
            Log.e("connection", e.getMessage());
        }
    }

    public String getBalance() {
        EthGetBalance ethbalance = null;
        try {
            ethbalance = web3
                    .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        BigInteger wei = ethbalance.getBalance();
        BigDecimal balance = Convert.fromWei(String.valueOf(wei), Convert.Unit.ETHER);
        String strBalance = String.valueOf(balance);

        return strBalance;
    }

    /*  CALLS FROM UI HERE */


    public void StartGeneralTicketActivity(View v) {
        Intent intent = new Intent(this, TicketGeneralActivity.class);
        startActivity(intent);
    }

    public void sendMoneyView(View v) {
        EditText eAddressTo = findViewById(R.id.sendToInput);
        EditText eAmount = findViewById(R.id.amountTo);

        String addressTo = eAddressTo.getText().toString();
        Float amountTo = Float.valueOf(eAmount.getText().toString());

        SendEtherToAddress(addressTo, amountTo);
    }

    public void showBalance(View v) {
        String balance = getBalance();

        TextView mBalance = findViewById(R.id.userBalance);
        mBalance.setText(balance);
    }

    public void showAddress(View v) {
        String address = getMyAddress();
        TextView mAddress = findViewById(R.id.walletAdress);
        mAddress.setText(address);
        Log.d("address", address);
    }

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

    // WARN! workaround for bug with ECDA signatures.
    private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    // setting up instances of smart contracts
    public void setupContracts() {

        // FIXME: check net id for local and cloud enviroment
        String net_id = "5777";  // 5777 - for ganache (local testing)  , 42 - moonshard private network (has not been deployed yet)
        deployed_net_id = net_id;

        kns_address = KNS.getPreviouslyDeployedAddress(deployed_net_id);                    // Registry contract
        //  sup_factory_address = SuperFactory.getPreviouslyDeployedAddress(deployed_net_id);   // Multisigs factory contract, this one is deprecated as we do need to make multisigs now
        ticket_factory_address = TicketFactory721.getPreviouslyDeployedAddress(deployed_net_id); // Ticket Factory contract

        //  Log.d("deployed_address", "kns_address: "+kns_address);
        //  Log.d("deployed_address", "sup_factory_address: "+sup_factory_address);
        Log.d("deployed_address", "ticket_factory_address: " + ticket_factory_address);


        ContractGasProvider gasprovider = new DefaultGasProvider();     // default (network dynamic gasprovider)
        //  Log.d("gasLimit", "gasLimit: "+gasprovider.getGasLimit());    // uncomment this if you want to know network gas info
        // gasprovider.getGasLimit();
        //  Log.d("gasLimit", "custom gas limit: " + CUSTOM_GAS_LIMIT); // check out that our own custom gas limit was properly setted


        //  superfactory = SuperFactory.load(sup_factory_address,web3,credentials,new DefaultGasProvider()); //FIXME: change default gas provider to custom.  We will need this for invoking functions with gasprice = 0
        //  ticketfactory = TicketFactory721.load(ticket_factory_address,web3,credentials,new DefaultGasProvider()); // FIXME: probably could workaround with custom transaction calls. need to check that.


        kns = KNS.load(kns_address, web3, credentials, CUSTOM_GAS_PRICE, CUSTOM_GAS_LIMIT);                 // entangle java contract artifact to actuall contract
        // superfactory = SuperFactory.load(sup_factory_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);         // we are not using superfactory now
        ticketfactory = TicketFactory721.load(ticket_factory_address, web3, credentials, CUSTOM_GAS_PRICE, CUSTOM_GAS_LIMIT);
    }

    //WARN: DEPRECATED
    // Saved this function for EXAMPLE OF USAGE
    /*
    // Main function to create (Register) Simple Multisignature wallet with 2fa and replacer already setted at factory contract
    public String createSimpleMultisigWallet(String _owner,BigInteger _required, BigInteger _dailyLimit, String JID, String telephone) {  //TODO: refactor this as main register wallet function, cleanup others
        String vtxHash = null;
        try {
            CompletableFuture<TransactionReceipt> receipt = superfactory.createSimpleWallet(_owner, _required, _dailyLimit, JID, telephone).sendAsync(); // create simple multisig wallet
            receipt.thenAccept(transactionReceipt -> {
                // get tx receipt only if successful
                String txHash = transactionReceipt.getTransactionHash(); // can play with that

               // EventValues eventValues = kns.getRegistredHumanEvents(transactionReceipt);
                List eventResponseHuman = kns.getRegistredHumanEvents(transactionReceipt);  // get events from tx receipt
                Log.d("event_values_human", "event_response: " + eventResponseHuman);


                List eventValues = kns.getRegistredEvents(transactionReceipt);
                Log.d("event_values", "event_values: " + eventValues);
                Log.d("receipt", "receipt"+transactionReceipt);
                Log.d("txhash", "txhash:" +txHash);
            }).exceptionally(transactionReceipt -> {
                return null;
            });

            String txHash = receipt.get().getTransactionHash();
            vtxHash = txHash;
            List log = receipt.get().getLogs();

            List<KNS.RegistredHumanEventResponse> response = kns.getRegistredHumanEvents(receipt.get()); //alternate call for events

            String JID_responce = response.get(0).JID; // get value of event
            String wallet_responce = response.get(0).wallet;
            String telephone_responce = response.get(0).tel;
            String prime_owner_responce = response.get(0).prime_owner;

            Log.d("event_response", "event_Human_registred_response: " +response);
            Log.d("event_values", "event values: " + JID_responce + " " + telephone_responce + " " + prime_owner_responce + " " + wallet_responce);
            Log.d("resultat", "new wallet address is: " + wallet_responce);
            Log.d("txHash", "createSimpleMultisigWallet RESULT: "+txHash);
            Log.d("events logs", "event_logs:" +log);
        } catch (Exception e) {
            Log.e("tx exeption", "createMultisigWallet Transaction fails:",e);
        }
        return vtxHash;
    }
*/

    // Send Funds to address
    public void SendEtherToAddress(String recepient, Float amount) {
        // credentials = WalletUtils.loadCredentials(password, walletDir);
        try {
            CompletableFuture<TransactionReceipt> receipt = Transfer.sendFunds(web3, credentials, recepient, BigDecimal.valueOf(amount), Convert.Unit.ETHER).sendAsync();
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

    //todo present ticket

    public void sendTicketAsPresent() {
        // принять
        ticket.transferFrom("fromJid", "toJid", BigInteger.valueOf(2131321321));
        //2131321321 - id ticket
    }

    public void renounceMinter() {
        //ticket.
    }

    //я отправляю текет дургому юзеру
    public void approveTicketAsPresent() {
        //todo tokenId - айдишник билета.
        ticket.approve("adres wallet", BigInteger.valueOf(2131321321));
        //2131321321 - id ticket
    }

    //todo get ticketInfo
    public void getTicketInfoByJid() {
        CompletableFuture<BigInteger> receipt = ticketfactory.getEventIdByJid("mutabor@conference.moonshard.tech").sendAsync();
        receipt.thenAccept(eventId -> {
            Log.d("eventId", "eventId: " + eventId);
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
    }

    //todo qrCode
    public void scanQrCode() {
        CompletableFuture<TransactionReceipt> receipt = ticketSale.redeemTicket("address_wallet", BigInteger.valueOf(2131321321)).sendAsync();
        receipt.thenAccept(transactionReceipt -> {
            // full field отсканирован
            // payed - просто куплен но не отсканирован
            Log.d("scanQrCode", "transactionReceipt: " + transactionReceipt.getBlockHash());
        }).exceptionally(throwable -> {
            Log.d("error", "error" + throwable.getMessage());
            return null;
        });
    }
}
