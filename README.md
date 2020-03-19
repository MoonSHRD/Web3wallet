# Web3wallet
web3j wallet prototype for android

## Main features
TBA

## Description
TBA

## Contracts end-point for moonshard application
TBA, see MS-Factory readme for it.

## Connection to network
For public network you should consider use ```connectToInfura```, replacing your own API key for infura

For private networks and for ganache testing you should consider use ```connectToLocalNetwork```, replacing API end-point by your own. 
If you are testing with **ganache**, then you can obtain address of endpoint directly from ganache GUI

**Note** - you can change api end-point by switching 'server' configuration from ganache GUI

### Custom gas price and gas limiting for private networks
You can consider to use your own private network with ```gasprice = 0```, therefore your user can interact with your private blockchain without neccessarity to obtain your private coin
This feature also can allow you to present your private coin as staible coin, which tethered to your local fiat currency

**Note** - by default **web3j** has gas limit at 6m gas and 20Gwei gas price. If you want to change those values -- you should either hardcode it as CUSTOM_GAS_PRICE(BigInteger) and use it when load contracts (bad practice)
either you should use custom gas provider class, which extend gas price provider and override default values to your own.

**TIP** -- you can check what is gas limit and what is gas price by google it.

## Creation and managment user private keys (wallets)

For any interaction with blockchain user should have private key / wallet

wallet file could be generated as ```WalletUtils.generateLightNewWalletFile(password,walletDir)```

you can load wallet from file  as ```credentials = WalletUtils.loadCredentials(password, walletDir);```
**note** -- credentials could be a global static variable for storing wallet object inside lifecycle of application for fastening interaction.

wallet file could also be generated and restored from ```String``` private key, so you **can store and load user key, when they authorize at your application backend**

**Note** -- if you want the best funds protection - you can use multisigs with 2FA instead of user single private keys. 

## Setup contracts
If you follow standart web3j smart-contracts artifacts generation, then you could simply put your generated artifacts file inside your main application src folder.

To setup contracts for your application with this artifacts you need to do 2 simple step:
1. get contract deployed address via ```contract_address = YourContract.getPreviouslyDeployedAddress(deployed_net_id);``` ,
where deployed_net_id is your network id (5777 for ganache)
2. load smart-contract instance, as ```contract = YourContract.load(contract_address,web3,credentials,CUSTOM_GAS_PRICE,CUSTOM_GAS_LIMIT);```

Now you can interact with smart-contracts in native way.

## Interaction with blockchain
All interaction with blockchain should be invoked as ```CompletableFuture```

### Changing state of blockchain
If your call is **changinging state of blockchain**, i.e. sending money from one account to another / changing state of smart-contract -- you 
should use construction with **tx receipt** and call this functions in **async way**

**Example of Sending money:**
```
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
```
**Example of smart-contract call:**
```
try {
            CompletableFuture<TransactionReceipt> receipt = ticketfactory.createTicketSale(organizer_address,price,event_JID).sendAsync();
            receipt.thenAccept(transactionReceipt -> {
                // get tx receipt only if successful
                String txHash = transactionReceipt.getTransactionHash(); // can play with that
                Log.d("receipt", "receipt"+transactionReceipt);
                Log.d("txhash", "txhash:" +txHash);
            }).exceptionally(transactionReceipt -> {
                return null;
            });
        } catch (Exception e) {
            Log.e("tx exeption", "Sale creation fails:",e);

        }
```

**Note** - as you can see, if you call sc method, which **change state** - you cannot get result of this call directly, you get only **transaction hash**
This is because there will be some time before transaction will include in block and mined to blockchain, and therefore -- you cannot directly get result of invokation of this methods.
In such cases you should only watch for successful tx acceptance (watch for ```revert``` errors if it fails) and also you **can get result of this invokation from events** -- because events are includes in transaction receipt

### Interaction with blockchain without changing state (view functions)
In cases, when you need just to retrive info from blockchain without changing state -- you can use ```CompletableFuture``` **or** ```RemoteCall<>```
In RemoteCall you don't need to wait for mining blocks for just retriving values, so you *could* call it in *sync* mode. 
However, as far as android forbid **networkOnUiThread** -- you should either call it from separete threads, either keep continue use CompleatableFuture and call async methods from *activity* directly.

**Example of blockchain calls without changing state:**
```
public BigDecimal getSalePriceInfo(TicketSale721 sale_instance) {
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
```

**Note** -- money values inside smart-contracts are in wei, consider proper convertation at UI fromWei and toWei.

As you can see, you can get result of a s-c function call as simple as ```.get()```.  
There are no need for interaction with transaction/events if you just want to retrive value.

