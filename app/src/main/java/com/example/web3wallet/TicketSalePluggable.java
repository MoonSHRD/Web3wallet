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
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class TicketSalePluggable extends Contract {
    public static final String BINARY = "0x6080604052600060055534801561001557600080fd5b50604051610b90380380610b908339818101604052606081101561003857600080fd5b50805160208201516040909201516000805460ff19166001179055909190828282826100ab576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b038216610106576040805162461bcd60e51b815260206004820152601d60248201527f6f726967696e2063616e6e6f74206265207a65726f2061646472657373000000604482015290519081900360640190fd5b600180546001600160a01b0319166001600160a01b0384811691909117918290556040805163521eb27360e01b81529051929091169163521eb27391600480820192602092909190829003018186803b15801561016257600080fd5b505afa158015610176573d6000803e3d6000fd5b505050506040513d602081101561018c57600080fd5b5051600680546001600160a01b0319166001600160a01b039283161790556007849055600482815560015460408051637e062a3560e11b81529051919093169263fc0c546a9281810192602092909190829003018186803b1580156101f057600080fd5b505afa158015610204573d6000803e3d6000fd5b505050506040513d602081101561021a57600080fd5b505160008054610100600160a81b0319166101006001600160a01b039384160217905560015460408051634881cef760e11b8152905191909216916391039dee916004808301926020929190829003018186803b15801561027a57600080fd5b505afa15801561028e573d6000803e3d6000fd5b505050506040513d60208110156102a457600080fd5b505160028190556000805460065460408051630645c0fb60e41b815260048101959095526001600160a01b03918216602486015251610100909204169263645c0fb09260448083019360209390929083900390910190829087803b15801561030b57600080fd5b505af115801561031f573d6000803e3d6000fd5b505050506040513d602081101561033557600080fd5b50516003555050505050506108418061034f6000396000f3fe6080604052600436106100e85760003560e01c806391039dee1161008a578063f00a93d311610059578063f00a93d314610255578063f9cfe26b1461026a578063fbbdc4651461027f578063fc0c546a14610294576100e8565b806391039dee146101df578063a5f8cdbb146101f4578063ec8ac4d81461021a578063ecd0c0c314610240576100e8565b8063521eb273116100c6578063521eb27314610167578063610757e41461017c5780636c1e81e3146101915780636ed08ea0146101ca576100e8565b80632c4e722e146100fa5780634042b66f1461012157806341035a4114610136575b6100f86100f36102a9565b6102ad565b005b34801561010657600080fd5b5061010f6102b9565b60408051918252519081900360200190f35b34801561012d57600080fd5b5061010f6102bf565b34801561014257600080fd5b5061014b6102c5565b604080516001600160a01b039092168252519081900360200190f35b34801561017357600080fd5b5061014b6102d4565b34801561018857600080fd5b5061014b6102e3565b34801561019d57600080fd5b506100f8600480360360408110156101b457600080fd5b506001600160a01b0381351690602001356102f2565b3480156101d657600080fd5b5061010f610390565b3480156101eb57600080fd5b5061010f610396565b6100f86004803603602081101561020a57600080fd5b50356001600160a01b03166102ad565b6100f86004803603602081101561023057600080fd5b50356001600160a01b031661039c565b34801561024c57600080fd5b5061014b6104d3565b34801561026157600080fd5b5061010f6104e7565b34801561027657600080fd5b5061010f6104ed565b34801561028b57600080fd5b5061010f6104f3565b3480156102a057600080fd5b5061014b6104f9565b3390565b6102b68161039c565b50565b60075490565b60085490565b6001546001600160a01b031681565b6006546001600160a01b031690565b6006546001600160a01b031681565b60006102fc6104f9565b90506000610308610396565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b15801561037257600080fd5b505af1158015610386573d6000803e3d6000fd5b5050505050505050565b60025481565b60025490565b60005460ff166103f3576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff1916815534906104088261050d565b905061041583838361052a565b600854610428908363ffffffff61060f16565b60085560055461043e908263ffffffff61060f16565b60055561044b8382610670565b826001600160a01b031661045d6102a9565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36104af838361067a565b6104b761067e565b6104c1838361067a565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60045490565b60055481565b60035490565b60005461010090046001600160a01b031690565b6000610524600754836106b790919063ffffffff16565b92915050565b6001600160a01b03831661056f5760405162461bcd60e51b815260040180806020018281038252602a8152602001806107e3602a913960400191505060405180910390fd5b816105c1576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600554600454908201908111156106095760405162461bcd60e51b815260040180806020018281038252602a815260200180610798602a913960400191505060405180910390fd5b50505050565b600082820183811015610669576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b61067a8282610710565b5050565b6006546040516001600160a01b03909116903480156108fc02916000818181858888f193505050501580156102b6573d6000803e3d6000fd5b6000826106c657506000610524565b828202828482816106d357fe5b04146106695760405162461bcd60e51b81526004018080602001828103825260218152602001806107c26021913960400191505060405180910390fd5b60008054600254600354604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b15801561077b57600080fd5b505af115801561078f573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d6974536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f2061646472657373a265627a7a7231582018fb18e6b3ec07f4e29a03fdf6032b19bdfc5c359ab72b2b708aeb139bb3f9fb64736f6c63430005110032";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__SOLD_COUNT = "_sold_count";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final String FUNC_EVENT_ID = "event_id";

    public static final String FUNC_ORIGIN_SALE = "origin_sale";

    public static final String FUNC_RATE = "rate";

    public static final String FUNC_SALE_LIMIT = "sale_limit";

    public static final String FUNC_TICKET_TYPE = "ticket_type";

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

    public RemoteFunctionCall<BigInteger> _sold_count() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__SOLD_COUNT, 
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

    public RemoteFunctionCall<BigInteger> sale_limit() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SALE_LIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> ticket_type() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKET_TYPE, 
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

    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String _origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String _origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String _origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(TicketSalePluggable.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketSalePluggable> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String _origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(_origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
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
