package com.example.web3wallet;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
import org.web3j.tuples.generated.Tuple7;
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
public class Deposit extends Contract {
    public static final String BINARY = "0x608060405260006100146100b760201b60201c565b9050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3506100bf565b600033905090565b6119a0806100ce6000396000f3fe60806040526004361061007b5760003560e01c80638f32d59b1161004e5780638f32d59b146104935780639b65f4e3146104c2578063d91f86b21461064b578063f2fde38b146107265761007b565b80633fb4b5de146100cc578063715018a61461035d5780638a3cac3c146103745780638da5cb5b1461043c575b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602181526020018061194b6021913960400191505060405180910390fd5b3480156100d857600080fd5b50610192600480360360208110156100ef57600080fd5b810190808035906020019064010000000081111561010c57600080fd5b82018360208201111561011e57600080fd5b8035906020019184600183028401116401000000008311171561014057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610777565b60405180806020018881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001851515151581526020018060200184810384528b818151815260200191508051906020019080838360005b8381101561024e578082015181840152602081019050610233565b50505050905090810190601f16801561027b5780820380516001836020036101000a031916815260200191505b50848103835288818151815260200191508051906020019080838360005b838110156102b4578082015181840152602081019050610299565b50505050905090810190601f1680156102e15780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b8381101561031a5780820151818401526020810190506102ff565b50505050905090810190601f1680156103475780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561036957600080fd5b506103726109e4565b005b34801561038057600080fd5b5061043a6004803603602081101561039757600080fd5b81019080803590602001906401000000008111156103b457600080fd5b8201836020820111156103c657600080fd5b803590602001918460018302840111640100000000831117156103e857600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610b1d565b005b34801561044857600080fd5b5061045161111d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561049f57600080fd5b506104a8611146565b604051808215151515815260200191505060405180910390f35b3480156104ce57600080fd5b50610649600480360360808110156104e557600080fd5b810190808035906020019064010000000081111561050257600080fd5b82018360208201111561051457600080fd5b8035906020019184600183028401116401000000008311171561053657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001906401000000008111156105b957600080fd5b8201836020820111156105cb57600080fd5b803590602001918460018302840111640100000000831117156105ed57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290803590602001909291905050506111a4565b005b6107246004803603604081101561066157600080fd5b810190808035906020019064010000000081111561067e57600080fd5b82018360208201111561069057600080fd5b803590602001918460018302840111640100000000831117156106b257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611556565b005b34801561073257600080fd5b506107756004803603602081101561074957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061160f565b005b600181805160208101820180518482526020830160208501208183528095505050505050600091509050806000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108395780601f1061080e57610100808354040283529160200191610839565b820191906000526020600020905b81548152906001019060200180831161081c57829003601f168201915b5050505050908060010154908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109035780601f106108d857610100808354040283529160200191610903565b820191906000526020600020905b8154815290600101906020018083116108e657829003601f168201915b5050505050908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060040160149054906101000a900460ff1690806005018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109da5780601f106109af576101008083540402835291602001916109da565b820191906000526020600020905b8154815290600101906020018083116109bd57829003601f168201915b5050505050905087565b6109ec611146565b610a5e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a360008060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b610b25611146565b610b97576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b610b9f6117e1565b6001826040518082805190602001908083835b60208310610bd55780518252602082019150602081019050602083039250610bb2565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206040518060e0016040529081600082018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cac5780601f10610c8157610100808354040283529160200191610cac565b820191906000526020600020905b815481529060010190602001808311610c8f57829003601f168201915b50505050508152602001600182015481526020016002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600382018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610dae5780601f10610d8357610100808354040283529160200191610dae565b820191906000526020600020905b815481529060010190602001808311610d9157829003601f168201915b505050505081526020016004820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016004820160149054906101000a900460ff16151515158152602001600582018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ec15780601f10610e9657610100808354040283529160200191610ec1565b820191906000526020600020905b815481529060010190602001808311610ea457829003601f168201915b505050505081525050905060008160a00190151590811515815250610f31576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260338152602001806119186033913960400191505060405180910390fd5b6000816040015190506000826020015190508173ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050158015610f89573d6000803e3d6000fd5b5060018360a0019015159081151581525050826001856040518082805190602001908083835b60208310610fd25780518252602082019150602081019050602083039250610faf565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600082015181600001908051906020019061102192919061184c565b506020820151816001015560408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301908051906020019061108f92919061184c565b5060808201518160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a08201518160040160146101000a81548160ff02191690831515021790555060c082015181600501908051906020019061111392919061184c565b5090505050505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16611188611695565b73ffffffffffffffffffffffffffffffffffffffff1614905090565b6111ac611146565b61121e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b816040518082805190602001908083835b60208310611252578051825260208201915060208101905060208303925061122f565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208373ffffffffffffffffffffffffffffffffffffffff16856040518082805190602001908083835b602083106112ca57805182526020820191506020810190506020830392506112a7565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207fc7257fe765b6dd4946dfa7e3829b142c46f4566767caec90f8396dcaf7e625c1846040518082815260200191505060405180910390a46113366117e1565b82816000018190525083816040019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505084816060018190525033816080019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508181602001818152505060008160a0019015159081151581525050806001846040518082805190602001908083835b6020831061140a57805182526020820191506020810190506020830392506113e7565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600082015181600001908051906020019061145992919061184c565b506020820151816001015560408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160030190805190602001906114c792919061184c565b5060808201518160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a08201518160040160146101000a81548160ff02191690831515021790555060c082015181600501908051906020019061154b92919061184c565b509050505050505050565b60003490508173ffffffffffffffffffffffffffffffffffffffff16836040518082805190602001908083835b602083106115a65780518252602082019150602081019050602083039250611583565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207fe3cb954797a95fbb1caebb51fad66117ac891d940890eb9c2d8c4e3051761288836040518082815260200191505060405180910390a3505050565b611617611146565b611689576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b6116928161169d565b50565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415611723576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001806118f26026913960400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6040518060e001604052806060815260200160008152602001600073ffffffffffffffffffffffffffffffffffffffff16815260200160608152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600015158152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061188d57805160ff19168380011785556118bb565b828001600101855582156118bb579182015b828111156118ba57825182559160200191906001019061189f565b5b5090506118c891906118cc565b5090565b6118ee91905b808211156118ea5760008160009055506001016118d2565b5090565b9056fe4f776e61626c653a206e6577206f776e657220697320746865207a65726f20616464726573737472616e73616374696f6e20697320616c72656164792065786563757465642120287265656e7472616e63792067756172642963616e277420636173684f757420776974686f7574206f75742061646472657373a265627a7a7231582031dd7899469a840c068358c7c030f9affa2feec19c924f7beda3784ffbae575d64736f6c63430005100032";

    public static final String FUNC_INREQUEST = "InRequest";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_CASHOUTREQUEST = "cashOutRequest";

    public static final String FUNC_CASHINREQUEST = "cashInRequest";

    public static final String FUNC_CASHINSUBMIT = "cashInSubmit";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event CASHINREQUESTEVENT_EVENT = new Event("cashInRequestEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event CASHOUTREQUESTEVENT_EVENT = new Event("cashOutRequestEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x521c24eb0517F6457b8CD6a7456eEfa2B642f613");
    }

    @Deprecated
    protected Deposit(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Deposit(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Deposit(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Deposit(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<CashInRequestEventEventResponse> getCashInRequestEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINREQUESTEVENT_EVENT, transactionReceipt);
        ArrayList<CashInRequestEventEventResponse> responses = new ArrayList<CashInRequestEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInRequestEventEventResponse typedResponse = new CashInRequestEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.user = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.uuid = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CashInRequestEventEventResponse> cashInRequestEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CashInRequestEventEventResponse>() {
            @Override
            public CashInRequestEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINREQUESTEVENT_EVENT, log);
                CashInRequestEventEventResponse typedResponse = new CashInRequestEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.user = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.uuid = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CashInRequestEventEventResponse> cashInRequestEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINREQUESTEVENT_EVENT));
        return cashInRequestEventEventFlowable(filter);
    }

    public List<CashOutRequestEventEventResponse> getCashOutRequestEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHOUTREQUESTEVENT_EVENT, transactionReceipt);
        ArrayList<CashOutRequestEventEventResponse> responses = new ArrayList<CashOutRequestEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashOutRequestEventEventResponse typedResponse = new CashOutRequestEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.destination = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.user = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CashOutRequestEventEventResponse> cashOutRequestEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CashOutRequestEventEventResponse>() {
            @Override
            public CashOutRequestEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHOUTREQUESTEVENT_EVENT, log);
                CashOutRequestEventEventResponse typedResponse = new CashOutRequestEventEventResponse();
                typedResponse.log = log;
                typedResponse.destination = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.user = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CashOutRequestEventEventResponse> cashOutRequestEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHOUTREQUESTEVENT_EVENT));
        return cashOutRequestEventEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple7<String, BigInteger, String, String, String, Boolean, String>> InRequest(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple7<String, BigInteger, String, String, String, Boolean, String>>(function,
                new Callable<Tuple7<String, BigInteger, String, String, String, Boolean, String>>() {
                    @Override
                    public Tuple7<String, BigInteger, String, String, String, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, BigInteger, String, String, String, Boolean, String>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cashOutRequest(String destination, String user, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHOUTREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(destination), 
                new org.web3j.abi.datatypes.Address(user)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cashInRequest(String from, String user, String uuid, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHINREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(from), 
                new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.Utf8String(uuid), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cashInSubmit(String uuid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHINSUBMIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uuid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Deposit load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deposit(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Deposit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deposit(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Deposit load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Deposit(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Deposit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Deposit(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Deposit> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Deposit.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Deposit> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Deposit.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Deposit> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Deposit.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Deposit> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Deposit.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class CashInRequestEventEventResponse extends BaseEventResponse {
        public byte[] from;

        public String user;

        public byte[] uuid;

        public BigInteger amount;
    }

    public static class CashOutRequestEventEventResponse extends BaseEventResponse {
        public byte[] destination;

        public String user;

        public BigInteger amount;
    }
}
