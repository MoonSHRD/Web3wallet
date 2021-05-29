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
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class KNS extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5061111b806100206000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063c0ea7fa81161005b578063c0ea7fa814610335578063ddecfe27146103d9578063eab8180814610527578063ee5bd245146105cb5761007d565b80632e37ccb1146100825780636702b43114610142578063a94f796614610286575b600080fd5b6101266004803603602081101561009857600080fd5b810190602081018135600160201b8111156100b257600080fd5b8201836020820111156100c457600080fd5b803590602001918460018302840111600160201b831117156100e557600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061066f945050505050565b604080516001600160a01b039092168252519081900360200190f35b6102846004803603608081101561015857600080fd5b6001600160a01b038235811692602081013590911691810190606081016040820135600160201b81111561018b57600080fd5b82018360208201111561019d57600080fd5b803590602001918460018302840111600160201b831117156101be57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561021057600080fd5b82018360208201111561022257600080fd5b803590602001918460018302840111600160201b8311171561024357600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061079e945050505050565b005b6102846004803603604081101561029c57600080fd5b810190602081018135600160201b8111156102b657600080fd5b8201836020820111156102c857600080fd5b803590602001918460018302840111600160201b831117156102e957600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550505090356001600160a01b03169150610b5c9050565b6101266004803603602081101561034b57600080fd5b810190602081018135600160201b81111561036557600080fd5b82018360208201111561037757600080fd5b803590602001918460018302840111600160201b8311171561039857600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610d6e945050505050565b61047d600480360360208110156103ef57600080fd5b810190602081018135600160201b81111561040957600080fd5b82018360208201111561041b57600080fd5b803590602001918460018302840111600160201b8311171561043c57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610da9945050505050565b60405180846001600160a01b03166001600160a01b03168152602001836001600160a01b03166001600160a01b0316815260200180602001828103825283818151815260200191508051906020019080838360005b838110156104ea5781810151838201526020016104d2565b50505050905090810190601f1680156105175780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b61047d6004803603602081101561053d57600080fd5b810190602081018135600160201b81111561055757600080fd5b82018360208201111561056957600080fd5b803590602001918460018302840111600160201b8311171561058a57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610e6f945050505050565b610126600480360360208110156105e157600080fd5b810190602081018135600160201b8111156105fb57600080fd5b82018360208201111561060d57600080fd5b803590602001918460018302840111600160201b8311171561062e57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610eff945050505050565b600061067961102c565b6000836040518082805190602001908083835b602083106106ab5780518252601f19909201916020918201910161068c565b518151600019602094850361010090810a8201928316921993909316919091179092529490920196875260408051978890038201882060608901825280546001600160a01b039081168a526001828101549091168a850152600280830180548551601f94821615909a0290970190961604908101849004840287018401835280875290975090880195509192508301828280156107895780601f1061075e57610100808354040283529160200191610789565b820191906000526020600020905b81548152906001019060200180831161076c57829003601f168201915b50505091909252505050602001519392505050565b6107a661102c565b6001600160a01b038086168252841660208083019190915260408083018490525184518392600092879290918291908401908083835b602083106107fb5780518252601f1990920191602091820191016107dc565b51815160001960209485036101000a0190811690199190911617905292019485525060408051948590038201909420855181546001600160a01b039182166001600160a01b031991821617835587840151600184018054919093169116179055938501518051610874945060028601935091019061104b565b50905050806001836040518082805190602001908083835b602083106108ab5780518252601f19909201916020918201910161088c565b51815160001960209485036101000a0190811690199190911617905292019485525060408051948590038201909420855181546001600160a01b039182166001600160a01b031991821617835587840151600184018054919093169116179055938501518051610924945060028601935091019061104b565b50905050816040518082805190602001908083835b602083106109585780518252601f199092019160209182019101610939565b51815160209384036101000a6000190180199092169116179052604051919093018190038120885190955088945090928392508401908083835b602083106109b15780518252601f199092019160209182019101610992565b51815160001960209485036101000a01908116901991909116179052604080519490920184900384206001600160a01b03808e1686528c169185019190915281519095507f460bd0e304b19a98768709c98d7f5943be773145f506c0f490c2f47f703f475794509283900301919050a37f25db3868baa42d60a73b6f076fceb10a25e6ad50e7e55ffed52513c1d96fa4828585858560405180856001600160a01b03166001600160a01b03168152602001846001600160a01b03166001600160a01b031681526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015610ab7578181015183820152602001610a9f565b50505050905090810190601f168015610ae45780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015610b17578181015183820152602001610aff565b50505050905090810190601f168015610b445780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a15050505050565b610b6461102c565b6000836040518082805190602001908083835b60208310610b965780518252601f199092019160209182019101610b77565b518151600019602094850361010090810a8201928316921993909316919091179092529490920196875260408051978890038201882060608901825280546001600160a01b039081168a526001828101549091168a850152600280830180548551601f94821615909a029097019096160490810184900484028701840183528087529097509088019550919250830182828015610c745780601f10610c4957610100808354040283529160200191610c74565b820191906000526020600020905b815481529060010190602001808311610c5757829003601f168201915b5050505050815250509050816001600160a01b0316836040518082805190602001908083835b60208310610cb95780518252601f199092019160209182019101610c9a565b51815160001960209485036101000a019081169019919091161790526040805194909201849003842091880151805192965094508392508401908083835b60208310610d165780518252601f199092019160209182019101610cf7565b5181516020939093036101000a60001901801990911692169190911790526040519201829003822093507f515f8f170e144c8fa03149925175e551812aeabc54b31fe6a45e0577e509d97e92506000919050a4505050565b6000610d7861102c565b600183604051808280519060200190808383602083106106ab5780518252601f19909201916020918201910161068c565b80516020818301810180516000825292820193820193909320919092528054600180830154600280850180546040805161010096831615969096026000190190911692909204601f81018890048802850188019092528184526001600160a01b0394851696949092169493830182828015610e655780601f10610e3a57610100808354040283529160200191610e65565b820191906000526020600020905b815481529060010190602001808311610e4857829003601f168201915b5050505050905083565b8051602081830181018051600180835293830194830194909420939052825483830154600280860180546040805161010098831615989098026000190190911692909204601f81018690048602870186019092528186526001600160a01b039384169693909216949293830182828015610e655780601f10610e3a57610100808354040283529160200191610e65565b6000610f0961102c565b6000836040518082805190602001908083835b60208310610f3b5780518252601f199092019160209182019101610f1c565b518151600019602094850361010090810a8201928316921993909316919091179092529490920196875260408051978890038201882060608901825280546001600160a01b039081168a526001828101549091168a850152600280830180548551601f94821615909a0290970190961604908101849004840287018401835280875290975090880195509192508301828280156110195780601f10610fee57610100808354040283529160200191611019565b820191906000526020600020905b815481529060010190602001808311610ffc57829003601f168201915b5050509190925250509051949350505050565b6040805160608082018352600080835260208301529181019190915290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061108c57805160ff19168380011785556110b9565b828001600101855582156110b9579182015b828111156110b957825182559160200191906001019061109e565b506110c59291506110c9565b5090565b6110e391905b808211156110c557600081556001016110cf565b9056fea265627a7a723158200c5188816b5784fe6d272e6ade263407dcad6c003afcafb92bda245240cf782b64736f6c63430005110032";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event REGISTREDHUMAN_EVENT = new Event("RegistredHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x8CC02aEB76940A22069Cad49daD56403369848F7");
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
            typedResponse.Jid = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tel = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.prime_owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.wallet = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.Jid = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tel = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.prime_owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.wallet = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegistredEventResponse> registredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTRED_EVENT));
        return registredEventFlowable(filter);
    }

    public List<RegistredHumanEventResponse> getRegistredHumanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTREDHUMAN_EVENT, transactionReceipt);
        ArrayList<RegistredHumanEventResponse> responses = new ArrayList<RegistredHumanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegistredHumanEventResponse typedResponse = new RegistredHumanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.prime_owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.wallet = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.JID = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.tel = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegistredHumanEventResponse> registredHumanEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RegistredHumanEventResponse>() {
            @Override
            public RegistredHumanEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTREDHUMAN_EVENT, log);
                RegistredHumanEventResponse typedResponse = new RegistredHumanEventResponse();
                typedResponse.log = log;
                typedResponse.prime_owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.wallet = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.JID = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.tel = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegistredHumanEventResponse> registredHumanEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTREDHUMAN_EVENT));
        return registredHumanEventFlowable(filter);
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
        public byte[] Jid;

        public byte[] tel;

        public String prime_owner;

        public String wallet;
    }

    public static class RegistredHumanEventResponse extends BaseEventResponse {
        public String prime_owner;

        public String wallet;

        public String JID;

        public String tel;
    }
}
