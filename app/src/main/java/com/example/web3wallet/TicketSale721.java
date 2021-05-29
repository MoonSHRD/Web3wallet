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
public class TicketSale721 extends Contract {
    public static final String BINARY = "0x60806040526001600255600060045534801561001a57600080fd5b50604051610bb9380380610bb9833981810160405260a081101561003d57600080fd5b815160208301516040808501516060860151608087018051935195979496929591949193928201928464010000000082111561007857600080fd5b90830190602082018581111561008d57600080fd5b82516401000000008111828201881017156100a757600080fd5b82525081516020918201929091019080838360005b838110156100d45781810151838201526020016100bc565b50505050905090810190601f1680156101015780820380516001836020036101000a031916815260200191505b5060405250506000805460ff191660011790555084848484848461016c576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b0384166101b15760405162461bcd60e51b8152600401808060200182810382526025815260200180610b946025913960400191505060405180910390fd5b6001600160a01b0383166101f65760405162461bcd60e51b8152600401808060200182810382526024815260200180610b706024913960400191505060405180910390fd5b6006859055600580546001600160a01b0319166001600160a01b03868116919091179182905560008054610100600160a81b031916610100878416810291909117808355600387905560408051631440364760e31b81529585166004870181815260248801928352885160448901528851949093049095169563a201b2389594889460649092019160208601918190849084905b838110156102a257818101518382015260200161028a565b50505050905090810190601f1680156102cf5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156102ef57600080fd5b505af1158015610303573d6000803e3d6000fd5b505050506040513d602081101561031957600080fd5b505160015550505050505050505050610839806103376000396000f3fe6080604052600436106100e85760003560e01c806391039dee1161008a578063f00a93d311610059578063f00a93d314610255578063f9cfe26b1461026a578063fbbdc4651461027f578063fc0c546a14610294576100e8565b806391039dee146101df578063a5f8cdbb146101f4578063ec8ac4d81461021a578063ecd0c0c314610240576100e8565b8063521eb273116100c6578063521eb2731461014b578063610757e41461017c5780636c1e81e3146101915780636ed08ea0146101ca576100e8565b806323e6f340146100fa5780632c4e722e146101215780634042b66f14610136575b6100f86100f36102a9565b6102ad565b005b34801561010657600080fd5b5061010f6102b9565b60408051918252519081900360200190f35b34801561012d57600080fd5b5061010f6102bf565b34801561014257600080fd5b5061010f6102c5565b34801561015757600080fd5b506101606102cb565b604080516001600160a01b039092168252519081900360200190f35b34801561018857600080fd5b506101606102da565b34801561019d57600080fd5b506100f8600480360360408110156101b457600080fd5b506001600160a01b0381351690602001356102e9565b3480156101d657600080fd5b5061010f610387565b3480156101eb57600080fd5b5061010f61038d565b6100f86004803603602081101561020a57600080fd5b50356001600160a01b03166102ad565b6100f86004803603602081101561023057600080fd5b50356001600160a01b0316610393565b34801561024c57600080fd5b506101606104ca565b34801561026157600080fd5b5061010f6104de565b34801561027657600080fd5b5061010f6104e4565b34801561028b57600080fd5b5061010f6104ea565b3480156102a057600080fd5b506101606104f0565b3390565b6102b681610393565b50565b60025481565b60065490565b60075490565b6005546001600160a01b031690565b6005546001600160a01b031681565b60006102f36104f0565b905060006102ff61038d565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b15801561036957600080fd5b505af115801561037d573d6000803e3d6000fd5b5050505050505050565b60015481565b60015490565b60005460ff166103ea576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff1916815534906103ff82610504565b905061040c838383610570565b60075461041f908363ffffffff61065516565b600755600454610435908263ffffffff61065516565b60045561044283826106b6565b826001600160a01b03166104546102a9565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36104a683836106c0565b6104ae6106c4565b6104b883836106c0565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60035490565b60045481565b60025490565b60005461010090046001600160a01b031690565b60006006548210156105475760405162461bcd60e51b815260040180806020018281038252602c8152602001806107d9602c913960400191505060405180910390fd5b6000670de0b6b3a76400006006548161055c57fe5b04670de0b6b3a76400008404029392505050565b6001600160a01b0383166105b55760405162461bcd60e51b815260040180806020018281038252602a8152602001806107af602a913960400191505060405180910390fd5b81610607576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b6004546003549082019081111561064f5760405162461bcd60e51b815260040180806020018281038252602a815260200180610785602a913960400191505060405180910390fd5b50505050565b6000828201838110156106af576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b6106c082826106fd565b5050565b6005546040516001600160a01b03909116903480156108fc02916000818181858888f193505050501580156102b6573d6000803e3d6000fd5b60008054600154600254604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b15801561076857600080fd5b505af115801561077c573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d697443726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a72315820b4aee8bdce237fae3b2909943e620f3c6ba56736475724d79c97d604ca70e90764736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f2061646472657373";

    public static final String FUNC__EVENT_ID = "_event_id";

    public static final String FUNC__SOLD_COUNT = "_sold_count";

    public static final String FUNC__TICKET_TYPE = "_ticket_type";

    public static final String FUNC__TOKEN = "_token";

    public static final String FUNC__WALLET = "_wallet";

    public static final String FUNC_BUYTOKENS = "buyTokens";

    public static final String FUNC_EVENT_ID = "event_id";

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
