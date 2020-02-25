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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
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
public class KNS extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b50611905806100206000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063c0ea7fa81161005b578063c0ea7fa8146103ea578063ddecfe27146104e5578063eab818081461067f578063ee5bd245146108195761007d565b80632e37ccb1146100825780636702b4311461017d578063a94f79661461030f575b600080fd5b61013b6004803603602081101561009857600080fd5b81019080803590602001906401000000008111156100b557600080fd5b8201836020820111156100c757600080fd5b803590602001918460018302840111640100000000831117156100e957600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610914565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61030d6004803603608081101561019357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001906401000000008111156101f057600080fd5b82018360208201111561020257600080fd5b8035906020019184600183028401116401000000008311171561022457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561028757600080fd5b82018360208201111561029957600080fd5b803590602001918460018302840111640100000000831117156102bb57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610af5565b005b6103e86004803603604081101561032557600080fd5b810190808035906020019064010000000081111561034257600080fd5b82018360208201111561035457600080fd5b8035906020019184600183028401116401000000008311171561037657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610f12565b005b6104a36004803603602081101561040057600080fd5b810190808035906020019064010000000081111561041d57600080fd5b82018360208201111561042f57600080fd5b8035906020019184600183028401116401000000008311171561045157600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506111ec565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61059e600480360360208110156104fb57600080fd5b810190808035906020019064010000000081111561051857600080fd5b82018360208201111561052a57600080fd5b8035906020019184600183028401116401000000008311171561054c57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506113cd565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610642578082015181840152602081019050610627565b50505050905090810190601f16801561066f5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6107386004803603602081101561069557600080fd5b81019080803590602001906401000000008111156106b257600080fd5b8201836020820111156106c457600080fd5b803590602001918460018302840111640100000000831117156106e657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506114e5565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b838110156107dc5780820151818401526020810190506107c1565b50505050905090810190601f1680156108095780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6108d26004803603602081101561082f57600080fd5b810190808035906020019064010000000081111561084c57600080fd5b82018360208201111561085e57600080fd5b8035906020019184600183028401116401000000008311171561088057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506115fd565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600061091e6117de565b6000836040518082805190602001908083835b602083106109545780518252602082019150602081019050602083039250610931565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ad75780601f10610aac57610100808354040283529160200191610ad7565b820191906000526020600020905b815481529060010190602001808311610aba57829003601f168201915b50505050508152505090506000816020015190508092505050919050565b610afd6117de565b84816000019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505083816020019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff1681525050818160400181905250806000846040518082805190602001908083835b60208310610bad5780518252602082019150602081019050602083039250610b8a565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610c8a92919061182b565b50905050806001836040518082805190602001908083835b60208310610cc55780518252602082019150602081019050602083039250610ca2565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610da292919061182b565b50905050816040518082805190602001908083835b60208310610dda5780518252602082019150602081019050602083039250610db7565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207f460bd0e304b19a98768709c98d7f5943be773145f506c0f490c2f47f703f4757868686604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610ecf578082015181840152602081019050610eb4565b50505050905090810190601f168015610efc5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a25050505050565b610f1a6117de565b6000836040518082805190602001908083835b60208310610f505780518252602082019150602081019050602083039250610f2d565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110d35780601f106110a8576101008083540402835291602001916110d3565b820191906000526020600020905b8154815290600101906020018083116110b657829003601f168201915b50505050508152505090508173ffffffffffffffffffffffffffffffffffffffff16836040518082805190602001908083835b602083106111295780518252602082019150602081019050602083039250611106565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902082604001516040518082805190602001908083835b6020831061118e578051825260208201915060208101905060208303925061116b565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207f515f8f170e144c8fa03149925175e551812aeabc54b31fe6a45e0577e509d97e60405160405180910390a4505050565b60006111f66117de565b6001836040518082805190602001908083835b6020831061122c5780518252602082019150602081019050602083039250611209565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113af5780601f10611384576101008083540402835291602001916113af565b820191906000526020600020905b81548152906001019060200180831161139257829003601f168201915b50505050508152505090506000816020015190508092505050919050565b6000818051602081018201805184825260208301602085012081835280955050505050506000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114db5780601f106114b0576101008083540402835291602001916114db565b820191906000526020600020905b8154815290600101906020018083116114be57829003601f168201915b5050505050905083565b6001818051602081018201805184825260208301602085012081835280955050505050506000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115f35780601f106115c8576101008083540402835291602001916115f3565b820191906000526020600020905b8154815290600101906020018083116115d657829003601f168201915b5050505050905083565b60006116076117de565b6000836040518082805190602001908083835b6020831061163d578051825260208201915060208101905060208303925061161a565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206040518060600160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117c05780601f10611795576101008083540402835291602001916117c0565b820191906000526020600020905b8154815290600101906020018083116117a357829003601f168201915b50505050508152505090506000816000015190508092505050919050565b6040518060600160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061186c57805160ff191683800117855561189a565b8280016001018555821561189a579182015b8281111561189957825182559160200191906001019061187e565b5b5090506118a791906118ab565b5090565b6118cd91905b808211156118c95760008160009055506001016118b1565b5090565b9056fea265627a7a72315820e64fe3404c80b0ca07699d8d81a62be0324a574bfa7bd42e92ccba8ebcfdcb4164736f6c63430005100032";

    public static final String FUNC_REGISTRYJ = "RegistryJ";

    public static final String FUNC_REGISTRYT = "RegistryT";

    public static final String FUNC_REGISTER = "Register";

    public static final String FUNC_GETOWNERBYJID = "GetOwnerByJid";

    public static final String FUNC_GETWALLETBYJID = "GetWalletByJid";

    public static final String FUNC_GETWALLETBYTEL = "GetWalletByTel";

    public static final String FUNC_LOSTKEY = "LostKey";

    public static final Event LOSTEDKEY_EVENT = new Event("LostedKey", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REGISTRED_EVENT = new Event("Registred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0xAF2F68D8cda07c1Bf80D03aC1655a0802889B6a4");
    }

    @Deprecated
    protected KNS(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected KNS(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected KNS(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected KNS(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<LostedKeyEventResponse> getLostedKeyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOSTEDKEY_EVENT, transactionReceipt);
        ArrayList<LostedKeyEventResponse> responses = new ArrayList<LostedKeyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LostedKeyEventResponse typedResponse = new LostedKeyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tel = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.Jid = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.new_wallet = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LostedKeyEventResponse> lostedKeyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LostedKeyEventResponse>() {
            @Override
            public LostedKeyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOSTEDKEY_EVENT, log);
                LostedKeyEventResponse typedResponse = new LostedKeyEventResponse();
                typedResponse.log = log;
                typedResponse.tel = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.Jid = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.new_wallet = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LostedKeyEventResponse> lostedKeyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOSTEDKEY_EVENT));
        return lostedKeyEventFlowable(filter);
    }

    public List<RegistredEventResponse> getRegistredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTRED_EVENT, transactionReceipt);
        ArrayList<RegistredEventResponse> responses = new ArrayList<RegistredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegistredEventResponse typedResponse = new RegistredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tel = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.prime_owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.wallet = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.Jid = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegistredEventResponse> registredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RegistredEventResponse>() {
            @Override
            public RegistredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTRED_EVENT, log);
                RegistredEventResponse typedResponse = new RegistredEventResponse();
                typedResponse.log = log;
                typedResponse.tel = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.prime_owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.wallet = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.Jid = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegistredEventResponse> registredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTRED_EVENT));
        return registredEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple3<String, String, String>> RegistryJ(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REGISTRYJ, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, String>>(function,
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<String, String, String>> RegistryT(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REGISTRYT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, String>>(function,
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> Register(String prime_owner, String wallet, String Jid, String tel) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(prime_owner), 
                new org.web3j.abi.datatypes.Address(wallet), 
                new org.web3j.abi.datatypes.Utf8String(Jid), 
                new org.web3j.abi.datatypes.Utf8String(tel)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> GetOwnerByJid(String Jid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERBYJID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(Jid)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> GetWalletByJid(String Jid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWALLETBYJID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(Jid)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> GetWalletByTel(String tel) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWALLETBYTEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tel)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> LostKey(String Jid, String new_wallet) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOSTKEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(Jid), 
                new org.web3j.abi.datatypes.Address(new_wallet)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static KNS load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new KNS(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static KNS load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new KNS(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static KNS load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new KNS(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static KNS load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new KNS(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<KNS> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KNS.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<KNS> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KNS.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<KNS> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KNS.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<KNS> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KNS.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LostedKeyEventResponse extends BaseEventResponse {
        public byte[] tel;

        public byte[] Jid;

        public String new_wallet;
    }

    public static class RegistredEventResponse extends BaseEventResponse {
        public byte[] tel;

        public String prime_owner;

        public String wallet;

        public String Jid;
    }
}
