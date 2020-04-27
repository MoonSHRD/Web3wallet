package com.example.web3wallet;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.15.
 */
@SuppressWarnings("rawtypes")
public class TicketSale721 extends Contract {
    public static final String BINARY = "0x60806040526001600255600060045534801561001a57600080fd5b5060405161116c38038061116c833981810160405260a081101561003d57600080fd5b81019080805190602001909291908051906020019092919080519060200190929190805190602001909291908051604051939291908464010000000082111561008557600080fd5b8382019150602082018581111561009b57600080fd5b82518660018202830111640100000000821117156100b857600080fd5b8083526020830192505050908051906020019080838360005b838110156100ec5780820151818401526020810190506100d1565b50505050905090810190601f1680156101195780820380516001836020036101000a031916815260200191505b50604052505050848484848460016000806101000a81548160ff021916908315150217905550600085116101b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f43726f776473616c653a2072617465206973203000000000000000000000000081525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff16141561023b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001806111476025913960400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156102c1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260248152602001806111236024913960400191505060405180910390fd5b8460068190555083600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600381905550600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a201b238600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16836040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610434578082015181840152602081019050610419565b50505050905090810190601f1680156104615780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561048157600080fd5b505af1158015610495573d6000803e3d6000fd5b505050506040513d60208110156104ab57600080fd5b810190808051906020019092919050505060018190555050505050505050505050610c48806104db6000396000f3fe6080604052600436106100dd5760003560e01c806391039dee1161007f578063ecd0c0c311610059578063ecd0c0c314610357578063f00a93d3146103ae578063f9cfe26b146103d9578063fc0c546a14610404576100dd565b806391039dee146102a4578063a5f8cdbb146102cf578063ec8ac4d814610313576100dd565b8063521eb273116100bb578063521eb27314610170578063610757e4146101c75780636c1e81e31461021e5780636ed08ea014610279576100dd565b806323e6f340146100ef5780632c4e722e1461011a5780634042b66f14610145575b6100ed6100e861045b565b610463565b005b3480156100fb57600080fd5b5061010461046f565b6040518082815260200191505060405180910390f35b34801561012657600080fd5b5061012f610475565b6040518082815260200191505060405180910390f35b34801561015157600080fd5b5061015a61047f565b6040518082815260200191505060405180910390f35b34801561017c57600080fd5b50610185610489565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101d357600080fd5b506101dc6104b3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561022a57600080fd5b506102776004803603604081101561024157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506104d9565b005b34801561028557600080fd5b5061028e61059e565b6040518082815260200191505060405180910390f35b3480156102b057600080fd5b506102b96105a4565b6040518082815260200191505060405180910390f35b610311600480360360208110156102e557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610463565b005b6103556004803603602081101561032957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506105ae565b005b34801561036357600080fd5b5061036c610755565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103ba57600080fd5b506103c361077b565b6040518082815260200191505060405180910390f35b3480156103e557600080fd5b506103ee61078a565b6040518082815260200191505060405180910390f35b34801561041057600080fd5b50610419610790565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600033905090565b61046c816105ae565b50565b60025481565b6000600654905090565b6000600754905090565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006104e3610790565b905060006104ef6105a4565b90508173ffffffffffffffffffffffffffffffffffffffff1663a27c282e8585846040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281526020019350505050600060405180830381600087803b15801561058057600080fd5b505af1158015610594573d6000803e3d6000fd5b5050505050505050565b60015481565b6000600154905090565b6000809054906101000a900460ff1661062f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f5265656e7472616e637947756172643a207265656e7472616e742063616c6c0081525060200191505060405180910390fd5b60008060006101000a81548160ff02191690831515021790555060003490506000610659826107b9565b905061066683838361084a565b61067b826007546109b190919063ffffffff16565b600781905550610696816004546109b190919063ffffffff16565b6004819055506106a68382610a39565b8273ffffffffffffffffffffffffffffffffffffffff166106c561045b565b73ffffffffffffffffffffffffffffffffffffffff167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36107248383610a47565b61072c610a4b565b6107368383610ab6565b505060016000806101000a81548160ff02191690831515021790555050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600061078561077b565b905090565b60045481565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600654821015610816576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602c815260200180610be8602c913960400191505060405180910390fd5b6000670de0b6b3a76400006006548161082b57fe5b04670de0b6b3a7640000848161083d57fe5b0402905080915050919050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156108d0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610bbe602a913960400191505060405180910390fd5b6000821415610947576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f43726f776473616c653a20776569416d6f756e7420697320300000000000000081525060200191505060405180910390fd5b6000816004540190506003548111156109ab576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610b94602a913960400191505060405180910390fd5b50505050565b600080828401905083811015610a2f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b610a438282610aba565b5050565b5050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610ab3573d6000803e3d6000fd5b50565b5050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663cd3a15f883836001546002546040518563ffffffff1660e01b8152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828152602001945050505050600060405180830381600087803b158015610b7757600080fd5b505af1158015610b8b573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d697443726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a7231582060a1bfca8fd9ab3b0f3dc538b415b9ea228747ada0455829f7cbc94464ef34ec64736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f2061646472657373";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__SOLD_COUNT = "_sold_count";

    public static final String FUNC__TICKET_TYPE = "_ticket_type";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final String FUNC_EVENT_ID = "event_id";

    public static final String FUNC_RATE = "rate";

    public static final String FUNC_SALE_LIMIT = "sale_limit";

    public static final String FUNC_TOKEN = "token";

    public static final String FUNC_WALLET = "wallet";

    public static final String FUNC_WEIRAISED = "weiRaised";

    public static final String FUNC_BUYTICKET = "buyTicket";

    public static final String FUNC_REDEEMTICKET = "redeemTicket";

    public static final Event TOKENSPURCHASED_EVENT = new Event("TokensPurchased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected TicketSale721(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TicketSale721(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TicketSale721(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TicketSale721(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<TokensPurchasedEventResponse> getTokensPurchasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENSPURCHASED_EVENT, transactionReceipt);
        ArrayList<TokensPurchasedEventResponse> responses = new ArrayList<TokensPurchasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokensPurchasedEventResponse typedResponse = new TokensPurchasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.purchaser = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.beneficiary = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokensPurchasedEventResponse> tokensPurchasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokensPurchasedEventResponse>() {
            @Override
            public TokensPurchasedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENSPURCHASED_EVENT, log);
                TokensPurchasedEventResponse typedResponse = new TokensPurchasedEventResponse();
                typedResponse.log = log;
                typedResponse.purchaser = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.beneficiary = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokensPurchasedEventResponse> tokensPurchasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENSPURCHASED_EVENT));
        return tokensPurchasedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> _event_id() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__EVENT_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> _sold_count() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__SOLD_COUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> _ticket_type() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__TICKET_TYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> _token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> _wallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__WALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyTokens(String beneficiary, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> event_id() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EVENT_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> rate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> sale_limit() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SALE_LIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> wallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> weiRaised() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WEIRAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyTicket(String buyer, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYTICKET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(buyer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> redeemTicket(String visitor, BigInteger token_id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDEEMTICKET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(visitor), 
                new org.web3j.abi.datatypes.generated.Uint256(token_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TicketSale721 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketSale721(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TicketSale721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketSale721(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TicketSale721 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TicketSale721(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TicketSale721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TicketSale721(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TicketSale721> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid)));
        return deployRemoteCall(TicketSale721.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketSale721> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid)));
        return deployRemoteCall(TicketSale721.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSale721> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid)));
        return deployRemoteCall(TicketSale721.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSale721> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String wallet, String token, BigInteger sale_limit, String jid) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit), 
                new org.web3j.abi.datatypes.Utf8String(jid)));
        return deployRemoteCall(TicketSale721.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class TokensPurchasedEventResponse extends BaseEventResponse {
        public String purchaser;

        public String beneficiary;

        public BigInteger value;

        public BigInteger amount;
    }
}
