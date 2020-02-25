package com.example.web3wallet;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web3wallet.ui.main.MainFragment;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Convert;

import java.io.Console;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Provider;
import java.security.Security;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    private String password;
    private EditText ePassword;
    private String walletPath;
    private File walletDir;
    private String fileName;

    private Web3j web3;
    private Credentials credentials;

    private KNS kns;
    private SuperFactory superfactory;
    private TicketFactory721 ticketfactory;

    public static String kns_address;
    public static String sup_factory_address;
    public static String ticket_factory_address;

    private String deployed_net_id;


    // custom gasprice
    private static final BigInteger CUSTOM_GAS_PRICE = Convert.toWei("8", Convert.Unit.GWEI).toBigInteger();  // FIXME

  //  private static final BigInteger CUSTOM_GAS_LIMIT = Convert.toWei("6721975", Convert.Unit.GWEI).toBigInteger();
  private static final BigInteger CUSTOM_GAS_LIMIT = BigInteger.valueOf(6_000_000);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // workaround for bug with ECDA keys
        setupBouncyCastle();

        walletPath = getFilesDir().getAbsolutePath();
        walletDir = new File(walletPath);


        connectToLocalNetwork();

        createDummyKey();
        loadDummyKey();




        setupContracts();
      //  checkContractAddresses();

       /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        */





    }



    public void createSimpleWallet(View v) {

        ePassword = (EditText) findViewById(R.id.newPass);
        password = ePassword.getText().toString();

        try{
          fileName =  WalletUtils.generateLightNewWalletFile(password,walletDir);
          String filepath = walletPath + "/" + fileName;
            toastAsync("Wallet generated" + filepath);
        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }


    }

    // create dummy key with default password
    public void createDummyKey() {
        password = "1984";

        try{
            fileName =  WalletUtils.generateLightNewWalletFile(password,walletDir);
            String filepath = walletPath + "/" + fileName;
            toastAsync("Wallet generated" + filepath);
        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }
    }

    // load dummy key with default password
    public void loadDummyKey() {
        try {
            String path = walletPath + "/" +fileName;
            walletDir = new File(path);
            credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());
            Log.d("my address", "my address is: " + credentials.getAddress());


           // showAddress(v);
           // showBalance(v);
        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }
    }

    public void loadSimpleWallet(View v) {

        ePassword = (EditText) findViewById(R.id.editPass);
        password = ePassword.getText().toString();


        try {
            String path = walletPath + "/" +fileName;
            walletDir = new File(path);
            credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());
          //  Log.d(TAG, "loadSimpleWallet: ");


            showAddress(v);
            showBalance(v);
        }
        catch (Exception e){
            toastAsync(e.getMessage());
        }
    }


    public String getMyAddress () {

        try {
            credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());

        }
        catch (Exception e){
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
            if(!clientVersion.hasError()){
                toastAsync("Connected!");
            }
            else {
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
        web3 = Web3j.build(new HttpService("HTTP://192.168.1.39:7545")); // defaults to http://localhost:8545/
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                toastAsync("Connected!");
                Log.d("client_web3_version", "client web3 version: "+clientVersion.getWeb3ClientVersion());
            }
            else {
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

    public void showBalance(View v) {
        String balance = getBalance();

        TextView mBalance = (TextView) findViewById(R.id.userBalance);
        mBalance.setText(balance);

    }

    public void showAddress(View v) {

        String address = getMyAddress();
        TextView mAddress = (TextView) findViewById(R.id.walletAdress);
        mAddress.setText(address);
        Log.d("address",address);

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

        // FIXME: can't automatically get net id from artifacts, probably bug
        /*
        try{
            String net_id =  web3.netVersion().send().getNetVersion();
            deployed_net_id = net_id;
        } catch (Exception e) {
            Log.e("exeption", "can't get version of network (network id), check deployment script "+e);
            Log.e("exeption","can't get version of network (network id), check deployment script",e);
        }
        */

        // FIXME: check net id for local and cloud enviroment
        String net_id = "5777";  // 5777 - for ganache (local testing)
        deployed_net_id = net_id;



        kns_address = KNS.getPreviouslyDeployedAddress(deployed_net_id);
        sup_factory_address = SuperFactory.getPreviouslyDeployedAddress(deployed_net_id);
        ticket_factory_address = TicketFactory721.getPreviouslyDeployedAddress(deployed_net_id);

        Log.d("deployed_address", "kns_address: "+kns_address);
        Log.d("deployed_address", "sup_factory_address: "+sup_factory_address);
        Log.d("deployed_address", "ticket_factory_address: "+ticket_factory_address);


        ContractGasProvider gasprovider = new DefaultGasProvider();

        Log.d("gasLimit", "gasLimit: "+gasprovider.getGasLimit());
       // gasprovider.getGasLimit();

        Log.d("gasLimit", "custom gas limit: " + CUSTOM_GAS_LIMIT);




        kns = KNS.load(kns_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);
        String check = kns.getContractAddress();
        Log.d("instance_address", "KNS address: "+check);

      //  superfactory = SuperFactory.load(sup_factory_address,web3,credentials,new DefaultGasProvider()); //FIXME: change default gas provider to custom.  We will need this for invoking functions with gasprice = 0
      //  ticketfactory = TicketFactory721.load(ticket_factory_address,web3,credentials,new DefaultGasProvider()); // FIXME: probably could workaround with custom transaction calls. need to check that.


        superfactory = SuperFactory.load(sup_factory_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);
        ticketfactory = TicketFactory721.load(ticket_factory_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);

        // Check
        Log.d("instance_address", "superfactory address:"+superfactory.getContractAddress());
        Log.d("instance_address ", "ticketfactory address:"+ticketfactory.getContractAddress());

    }


    public Void createSimpleMultisigWalletTest() {
        String _owner = getMyAddress();
        BigInteger _required = new BigInteger("1");
        BigInteger _dailyLimit = new BigInteger("10000");
        String JID = "cheburek@conference.moonhsard.tech";
        String telephone = "+79687003680";

        createSimpleMultisigWallet(_owner,_required,_dailyLimit,JID,telephone);
        return null;
    }

    //FIXME: function createMultisigWallet with multiple owners

    public void createSimpleMultisigWallet(String _owner,BigInteger _required, BigInteger _dailyLimit, String JID, String telephone) {

       // TransactionReceipt recept;

        /*
        TransactionReceiptProcessor receiptProcessor =
                new PollingTransactionReceiptProcessor(web3, TransactionManager.DEFAULT_POLLING_FREQUENCY,
                        TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH);
        */


        try {
            TransactionReceipt receipt = superfactory.createSimpleWallet(_owner, _required, _dailyLimit, JID, telephone).send(); // FIXME: change .send to custom transaction
            Log.d("receipt", "receipt"+receipt);
          //  recept = receipt;
            String txHash = receipt.getTransactionHash();
         //   TransactionReceipt txReceipt = receiptProcessor.waitForTransactionReceipt(txHash);
            Log.d("txHash", "createSimpleMultisigWallet RESULT: "+txHash);
        } catch (Exception e) {
           // Log.d("tx hash", "txhash"+txHash);
            Log.e("tx exeption", "createMultisigWallet Transaction fails:",e);

        }




    }

    public void createSimpleMultisigWalletTestView(View v) {
       // createSimpleMultisigWalletTest();
        new AsyncRequest().execute();
    }


    // For testing purposes
    public void checkContractAddresses() {

      //  kns_address = KNS.;
       // sup_factory_address = SuperFactory.getPreviouslyDeployedAddress(deployed_net_id);
      //  ticket_factory_address = TicketFactory721.getPreviouslyDeployedAddress(deployed_net_id);

      //  Log.d("address", "kns_address: "+kns_address);
      //  Log.d("address", "sup_factory_address: "+sup_factory_address);
      //  Log.d("address", "ticket_factory_address: "+ticket_factory_address);

        try {
            String kns_address_check = KNS.getPreviouslyDeployedAddress("5777");
            Log.d("address check", "KNS address (1 check): "+kns_address_check); // works fine

        } catch (Exception e) {
            Log.e("exeption", "unable to get contract address: ",e);
        }

       // 5777 - is for ganache
       String kns_address_check = KNS.getPreviouslyDeployedAddress("5777");
        Log.d("address check", "KNS address (2 check): "+kns_address_check); // works fine


      //  kns = KNS.load(KNS.getContractAddress(),web3,credentials,new DefaultGasProvider());

        kns = KNS.load(kns_address_check,web3,credentials,new DefaultGasProvider());  // worked only with loaded credentials (!!)
        String check = kns.getContractAddress();
        Log.d("try to retrive address(2)", "KNS address (3 check): "+check);
    }




    class AsyncRequest extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params) {
          //  createSimpleMultisigWalletTest();
            return createSimpleMultisigWalletTest();
        }

        /*
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

         */

    }


}
