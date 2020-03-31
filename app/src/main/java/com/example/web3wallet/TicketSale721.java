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
    public static final String BINARY = "0x6080604052600060025534801561001557600080fd5b50604051610fd2380380610fd28339818101604052608081101561003857600080fd5b81019080805190602001909291908051906020019092919080519060200190929190805190602001909291905050508383838360016000806101000a81548160ff021916908315150217905550600084116100fb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f43726f776473616c653a2072617465206973203000000000000000000000000081525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610181576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526025815260200180610fad6025913960400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610207576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526024815260200180610f896024913960400191505060405180910390fd5b8360058190555082600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600381905550600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634c0eb465600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166040518263ffffffff1660e01b8152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561035a57600080fd5b505af115801561036e573d6000803e3d6000fd5b505050506040513d602081101561038457600080fd5b81019080805190602001909291905050506001819055505050505050505050610bd7806103b26000396000f3fe6080604052600436106100c25760003560e01c80636ed08ea01161007f578063ec8ac4d811610059578063ec8ac4d8146102f8578063ecd0c0c31461033c578063f00a93d314610393578063fc0c546a146103be576100c2565b80636ed08ea01461025e57806391039dee14610289578063a5f8cdbb146102b4576100c2565b806323e6f340146100d45780632c4e722e146100ff5780634042b66f1461012a578063521eb27314610155578063610757e4146101ac5780636c1e81e314610203575b6100d26100cd610415565b61041d565b005b3480156100e057600080fd5b506100e9610429565b6040518082815260200191505060405180910390f35b34801561010b57600080fd5b5061011461042f565b6040518082815260200191505060405180910390f35b34801561013657600080fd5b5061013f610439565b6040518082815260200191505060405180910390f35b34801561016157600080fd5b5061016a610443565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101b857600080fd5b506101c161046d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561020f57600080fd5b5061025c6004803603604081101561022657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610493565b005b34801561026a57600080fd5b50610273610558565b6040518082815260200191505060405180910390f35b34801561029557600080fd5b5061029e61055e565b6040518082815260200191505060405180910390f35b6102f6600480360360208110156102ca57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061041d565b005b61033a6004803603602081101561030e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610568565b005b34801561034857600080fd5b506103516106f4565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561039f57600080fd5b506103a861071a565b6040518082815260200191505060405180910390f35b3480156103ca57600080fd5b506103d3610729565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600033905090565b61042681610568565b50565b60025481565b6000600554905090565b6000600654905090565b6000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600061049d610729565b905060006104a961055e565b90508173ffffffffffffffffffffffffffffffffffffffff1663a27c282e8585846040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281526020019350505050600060405180830381600087803b15801561053a57600080fd5b505af115801561054e573d6000803e3d6000fd5b5050505050505050565b60015481565b6000600154905090565b6000809054906101000a900460ff166105e9576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f5265656e7472616e637947756172643a207265656e7472616e742063616c6c0081525060200191505060405180910390fd5b60008060006101000a81548160ff0219169083151502179055506000349050600061061382610752565b90506106208383836107e3565b6106358260065461094090919063ffffffff16565b60068190555061064583826109c8565b8273ffffffffffffffffffffffffffffffffffffffff16610664610415565b73ffffffffffffffffffffffffffffffffffffffff167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36106c383836109d6565b6106cb6109da565b6106d58383610a45565b505060016000806101000a81548160ff02191690831515021790555050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600061072461071a565b905090565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60006005548210156107af576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602c815260200180610b77602c913960400191505060405180910390fd5b6000670de0b6b3a7640000600554816107c457fe5b04670de0b6b3a764000084816107d657fe5b0402905080915050919050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610869576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610b4d602a913960400191505060405180910390fd5b60008214156108e0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f43726f776473616c653a20776569416d6f756e7420697320300000000000000081525060200191505060405180910390fd5b60035481111561093b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610b23602a913960400191505060405180910390fd5b505050565b6000808284019050838110156109be576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b6109d28282610a49565b5050565b5050565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610a42573d6000803e3d6000fd5b50565b5050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663cd3a15f883836001546002546040518563ffffffff1660e01b8152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828152602001945050505050600060405180830381600087803b158015610b0657600080fd5b505af1158015610b1a573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d697443726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a72315820c93101261f17a19c8ba2d08c518dac8c4d20a4e6e943b269d5804a3ebb07595164736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f2061646472657373";

    public static final String FUNC__EVENT_ID = "_event_id";

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

    public static RemoteCall<TicketSale721> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String wallet, String token, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(TicketSale721.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketSale721> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String wallet, String token, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(TicketSale721.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSale721> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String wallet, String token, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(TicketSale721.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSale721> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String wallet, String token, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Address(token), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
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
