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
public class PluggableSale extends Contract {
    public static final String BINARY = "0x6080604052600060055534801561001557600080fd5b50604051610a5e380380610a5e8339818101604052606081101561003857600080fd5b50805160208201516040909201516000805460ff19166001179055909190826100a8576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b038216610103576040805162461bcd60e51b815260206004820152601d60248201527f6f726967696e2063616e6e6f74206265207a65726f2061646472657373000000604482015290519081900360640190fd5b600180546001600160a01b0319166001600160a01b0384811691909117918290556040805163521eb27360e01b81529051929091169163521eb27391600480820192602092909190829003018186803b15801561015f57600080fd5b505afa158015610173573d6000803e3d6000fd5b505050506040513d602081101561018957600080fd5b5051600680546001600160a01b0319166001600160a01b039283161790556007849055600482815560015460408051637e062a3560e11b81529051919093169263fc0c546a9281810192602092909190829003018186803b1580156101ed57600080fd5b505afa158015610201573d6000803e3d6000fd5b505050506040513d602081101561021757600080fd5b505160008054610100600160a81b0319166101006001600160a01b039384160217905560015460408051634881cef760e11b8152905191909216916391039dee916004808301926020929190829003018186803b15801561027757600080fd5b505afa15801561028b573d6000803e3d6000fd5b505050506040513d60208110156102a157600080fd5b505160028190556000805460065460408051630645c0fb60e41b815260048101959095526001600160a01b03918216602486015251610100909204169263645c0fb09260448083019360209390929083900390910190829087803b15801561030857600080fd5b505af115801561031c573d6000803e3d6000fd5b505050506040513d602081101561033257600080fd5b5051600355505050610715806103496000396000f3fe6080604052600436106100c25760003560e01c806391039dee1161007f578063f00a93d311610059578063f00a93d3146101d0578063f9cfe26b146101e5578063fbbdc465146101fa578063fc0c546a1461020f576100c2565b806391039dee14610180578063ec8ac4d814610195578063ecd0c0c3146101bb576100c2565b80632c4e722e146100d45780634042b66f146100fb57806341035a4114610110578063521eb27314610141578063610757e4146101565780636ed08ea01461016b575b6100d26100cd610224565b610228565b005b3480156100e057600080fd5b506100e961035f565b60408051918252519081900360200190f35b34801561010757600080fd5b506100e9610365565b34801561011c57600080fd5b5061012561036b565b604080516001600160a01b039092168252519081900360200190f35b34801561014d57600080fd5b5061012561037a565b34801561016257600080fd5b50610125610389565b34801561017757600080fd5b506100e9610398565b34801561018c57600080fd5b506100e961039e565b6100d2600480360360208110156101ab57600080fd5b50356001600160a01b0316610228565b3480156101c757600080fd5b506101256103a4565b3480156101dc57600080fd5b506100e96103b8565b3480156101f157600080fd5b506100e96103be565b34801561020657600080fd5b506100e96103c4565b34801561021b57600080fd5b506101256103ca565b3390565b60005460ff1661027f576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff191681553490610294826103de565b90506102a18383836103fb565b6008546102b4908363ffffffff6104e016565b6008556005546102ca908263ffffffff6104e016565b6005556102d78382610541565b826001600160a01b03166102e9610224565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a361033b838361054b565b61034361054f565b61034d838361054b565b50506000805460ff1916600117905550565b60075490565b60085490565b6001546001600160a01b031681565b6006546001600160a01b031690565b6006546001600160a01b031681565b60025481565b60025490565b60005461010090046001600160a01b031681565b60045490565b60055481565b60035490565b60005461010090046001600160a01b031690565b60006103f56007548361058b90919063ffffffff16565b92915050565b6001600160a01b0383166104405760405162461bcd60e51b815260040180806020018281038252602a8152602001806106b7602a913960400191505060405180910390fd5b81610492576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600554600454908201908111156104da5760405162461bcd60e51b815260040180806020018281038252602a81526020018061066c602a913960400191505060405180910390fd5b50505050565b60008282018381101561053a576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b61054b82826105e4565b5050565b6006546040516001600160a01b03909116903480156108fc02916000818181858888f19350505050158015610588573d6000803e3d6000fd5b50565b60008261059a575060006103f5565b828202828482816105a757fe5b041461053a5760405162461bcd60e51b81526004018080602001828103825260218152602001806106966021913960400191505060405180910390fd5b60008054600254600354604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b15801561064f57600080fd5b505af1158015610663573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d6974536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f2061646472657373a265627a7a723158209f81842488bce85b298d97245cd2ac96fd60acd8fed247ddbc08e9af023fd7d164736f6c63430005110032";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__SOLD_COUNT = "_sold_count";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_ORIGIN_SALE = "origin_sale";

    public static final String FUNC_TOKEN = "token";

    public static final String FUNC_WALLET = "wallet";

    public static final String FUNC_RATE = "rate";

    public static final String FUNC_WEIRAISED = "weiRaised";

    public static final String FUNC_EVENT_ID = "event_id";

    public static final String FUNC_SALE_LIMIT = "sale_limit";

    public static final String FUNC_TICKET_TYPE = "ticket_type";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final Event TOKENSPURCHASED_EVENT = new Event("TokensPurchased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected PluggableSale(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PluggableSale(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PluggableSale(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PluggableSale(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<String> origin_sale() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORIGIN_SALE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<BigInteger> rate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> weiRaised() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WEIRAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> event_id() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EVENT_ID, 
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

    public RemoteFunctionCall<TransactionReceipt> buyTokens(String beneficiary, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static PluggableSale load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PluggableSale(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PluggableSale load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PluggableSale(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PluggableSale load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PluggableSale(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PluggableSale load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PluggableSale(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PluggableSale> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(PluggableSale.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PluggableSale> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(PluggableSale.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PluggableSale> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(PluggableSale.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PluggableSale> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String origin, BigInteger sale_limit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(origin), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)));
        return deployRemoteCall(PluggableSale.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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
