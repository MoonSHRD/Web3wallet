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
public class TicketSalePluggable extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b506040516111773803806111778339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050818160016000806101000a81548160ff021916908315150217905550600082116100e0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f43726f776473616c653a2072617465206973203000000000000000000000000081525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610183576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601d8152602001807f6f726967696e2063616e6e6f74206265207a65726f206164647265737300000081525060200191505060405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663521eb2736040518163ffffffff1660e01b815260040160206040518083038186803b1580156101eb57600080fd5b505afa1580156101ff573d6000803e3d6000fd5b505050506040513d602081101561021557600080fd5b8101908080519060200190929190505050600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503373ffffffffffffffffffffffffffffffffffffffff16600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461030c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260278152602001806111506027913960400191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600581905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc0c546a6040518163ffffffff1660e01b815260040160206040518083038186803b1580156103bc57600080fd5b505afa1580156103d0573d6000803e3d6000fd5b505050506040513d60208110156103e657600080fd5b8101908080519060200190929190505050600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b15801561049f57600080fd5b505afa1580156104b3573d6000803e3d6000fd5b505050506040513d60208110156104c957600080fd5b8101908080519060200190929190505050600281905550600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663645c0fb0600254600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166040518363ffffffff1660e01b8152600401808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050602060405180830381600087803b1580156105ad57600080fd5b505af11580156105c1573d6000803e3d6000fd5b505050506040513d60208110156105d757600080fd5b810190808051906020019092919050505060038190555050505050610b4f806106016000396000f3fe6080604052600436106100a75760003560e01c80636ed08ea0116100645780636ed08ea01461026f57806391039dee1461029a578063a5f8cdbb146102c5578063ec8ac4d814610309578063ecd0c0c31461034d578063fc0c546a146103a4576100a7565b80632c4e722e146100b95780634042b66f146100e457806341035a411461010f578063521eb27314610166578063610757e4146101bd5780636c1e81e314610214575b6100b76100b26103fb565b610403565b005b3480156100c557600080fd5b506100ce61040f565b6040518082815260200191505060405180910390f35b3480156100f057600080fd5b506100f9610419565b6040518082815260200191505060405180910390f35b34801561011b57600080fd5b50610124610423565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561017257600080fd5b5061017b610449565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101c957600080fd5b506101d2610473565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561022057600080fd5b5061026d6004803603604081101561023757600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610499565b005b34801561027b57600080fd5b5061028461055e565b6040518082815260200191505060405180910390f35b3480156102a657600080fd5b506102af610564565b6040518082815260200191505060405180910390f35b610307600480360360208110156102db57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610403565b005b61034b6004803603602081101561031f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061056e565b005b34801561035957600080fd5b506103626106f9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103b057600080fd5b506103b961071f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600033905090565b61040c8161056e565b50565b6000600554905090565b6000600654905090565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006104a361071f565b905060006104af610564565b90508173ffffffffffffffffffffffffffffffffffffffff1663a27c282e8585846040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281526020019350505050600060405180830381600087803b15801561054057600080fd5b505af1158015610554573d6000803e3d6000fd5b5050505050505050565b60025481565b6000600254905090565b6000809054906101000a900460ff166105ef576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f5265656e7472616e637947756172643a207265656e7472616e742063616c6c0081525060200191505060405180910390fd5b60008060006101000a81548160ff02191690831515021790555060003490506106188282610748565b600061062382610849565b905061063a8260065461086790919063ffffffff16565b60068190555061064a83826108ef565b8273ffffffffffffffffffffffffffffffffffffffff166106696103fb565b73ffffffffffffffffffffffffffffffffffffffff167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36106c883836108fd565b6106d0610901565b6106da838361096c565b505060016000806101000a81548160ff02191690831515021790555050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156107ce576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610af1602a913960400191505060405180910390fd5b6000811415610845576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f43726f776473616c653a20776569416d6f756e7420697320300000000000000081525060200191505060405180910390fd5b5050565b60006108606005548361097090919063ffffffff16565b9050919050565b6000808284019050838110156108e5576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b6108f982826109f6565b5050565b5050565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610969573d6000803e3d6000fd5b50565b5050565b60008083141561098357600090506109f0565b600082840290508284828161099457fe5b04146109eb576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526021815260200180610ad06021913960400191505060405180910390fd5b809150505b92915050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663cd3a15f883836002546003546040518563ffffffff1660e01b8152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828152602001945050505050600060405180830381600087803b158015610ab357600080fd5b505af1158015610ac7573d6000803e3d6000fd5b50505050505056fe536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f2061646472657373a265627a7a7231582081fef05b485bfe0c6fb9306aaf9fa5124f35df7f004b528b68b0a92f289734a364736f6c634300051100326f6e6c79206f726967696e206f7267616e697a65722063616e20706c7567206e65772073616c65";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final String FUNC_EVENT_ID = "event_id";

    public static final String FUNC_ORIGIN_SALE = "origin_sale";

    public static final String FUNC_RATE = "rate";

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
    protected TicketSalePluggable(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TicketSalePluggable(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TicketSalePluggable(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TicketSalePluggable(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<String> origin_sale() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORIGIN_SALE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> rate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RATE, 
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
    public static TicketSalePluggable load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketSalePluggable(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TicketSalePluggable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketSalePluggable(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TicketSalePluggable load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TicketSalePluggable(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TicketSalePluggable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TicketSalePluggable(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String _origin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String _origin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String _origin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String _origin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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
