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
import org.web3j.tuples.generated.Tuple6;
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
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class Deposit extends Contract {
    public static final String BINARY = "0x608060405260006100176001600160e01b0361006616565b600080546001600160a01b0319166001600160a01b0383169081178255604051929350917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a35061006a565b3390565b611e1d806100796000396000f3fe6080604052600436106100a75760003560e01c80638f32d59b116100645780638f32d59b1461051257806390b808971461053b5780639e5e435b14610675578063e2b6bc851461069c578063f00fc1101461075f578063f2fde38b14610817576100a7565b8063021ce0cd146100a957806334ae76f2146100d35780633fb4b5de146101fc578063715018a61461041b5780638a3cac3c146104305780638da5cb5b146104e1575b005b3480156100b557600080fd5b506100a7600480360360208110156100cc57600080fd5b503561084a565b6100a7600480360360408110156100e957600080fd5b810190602081018135600160201b81111561010357600080fd5b82018360208201111561011557600080fd5b803590602001918460018302840111600160201b8311171561013657600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561018857600080fd5b82018360208201111561019a57600080fd5b803590602001918460018302840111600160201b831117156101bb57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610af3945050505050565b34801561020857600080fd5b506102ad6004803603602081101561021f57600080fd5b810190602081018135600160201b81111561023957600080fd5b82018360208201111561024b57600080fd5b803590602001918460018302840111600160201b8311171561026c57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610d52945050505050565b604080519081018690526001600160a01b0380861660608301528416608082015282151560a082015260e08082528851908201528751819060208083019160c0840191610100850191908d019080838360005b83811015610318578181015183820152602001610300565b50505050905090810190601f1680156103455780820380516001836020036101000a031916815260200191505b5084810383528a5181528a516020918201918c019080838360005b83811015610378578181015183820152602001610360565b50505050905090810190601f1680156103a55780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b838110156103d85781810151838201526020016103c0565b50505050905090810190601f1680156104055780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561042757600080fd5b506100a7610f53565b34801561043c57600080fd5b506100a76004803603602081101561045357600080fd5b810190602081018135600160201b81111561046d57600080fd5b82018360208201111561047f57600080fd5b803590602001918460018302840111600160201b831117156104a057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610fe4945050505050565b3480156104ed57600080fd5b506104f6611442565b604080516001600160a01b039092168252519081900360200190f35b34801561051e57600080fd5b50610527611452565b604080519115158252519081900360200190f35b34801561054757600080fd5b506105656004803603602081101561055e57600080fd5b5035611476565b6040518080602001878152602001866001600160a01b03166001600160a01b031681526020018060200185815260200184151515158152602001838103835289818151815260200191508051906020019080838360005b838110156105d45781810151838201526020016105bc565b50505050905090810190601f1680156106015780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b8381101561063457818101518382015260200161061c565b50505050905090810190601f1680156106615780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b34801561068157600080fd5b5061068a6115d3565b60408051918252519081900360200190f35b3480156106a857600080fd5b506100a7600480360360608110156106bf57600080fd5b6001600160a01b038235169190810190604081016020820135600160201b8111156106e957600080fd5b8201836020820111156106fb57600080fd5b803590602001918460018302840111600160201b8311171561071c57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955050913592506115d9915050565b34801561076b57600080fd5b506100a76004803603604081101561078257600080fd5b81359190810190604081016020820135600160201b8111156107a357600080fd5b8201836020820111156107b557600080fd5b803590602001918460018302840111600160201b831117156107d657600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506117f9945050505050565b34801561082357600080fd5b506100a76004803603602081101561083a57600080fd5b50356001600160a01b0316611afb565b610852611452565b610891576040805162461bcd60e51b81526020600482018190526024820152600080516020611dc9833981519152604482015290519081900360640190fd5b610899611c45565b6000828152600360209081526040918290208251815460026001821615610100026000190190911604601f8101849004909302810160e090810190945260c081018381529093919284928491908401828280156109375780601f1061090c57610100808354040283529160200191610937565b820191906000526020600020905b81548152906001019060200180831161091a57829003601f168201915b50505091835250506001828101546020808401919091526002808501546001600160a01b031660408086019190915260038601805482516101009682161596909602600019011692909204601f810184900484028501840190915280845260609094019390918301828280156109ee5780601f106109c3576101008083540402835291602001916109ee565b820191906000526020600020905b8154815290600101906020018083116109d157829003601f168201915b50505091835250506004820154602082015260059091015460ff16151560409091015260a081015190915015610a555760405162461bcd60e51b8152600401808060200182810382526033815260200180611d966033913960400191505060405180910390fd5b600160a08201526000828152600360209081526040909120825180518493610a81928492910190611c86565b50602082810151600183015560408301516002830180546001600160a01b0319166001600160a01b0390921691909117905560608301518051610aca9260038501920190611c86565b506080820151600482015560a0909101516005909101805460ff19169115159190911790555050565b3433610afd611c45565b838152602081018390526001600160a01b038216604082015260608101859052600060a0820152610b2e6001611b4e565b610b386001611b57565b608082018190526000818152600360209081526040909120835180518593610b64928492910190611c86565b50602082810151600183015560408301516002830180546001600160a01b0319166001600160a01b0390921691909117905560608301518051610bad9260038501920190611c86565b506080820151816004015560a08201518160050160006101000a81548160ff02191690831515021790555090505080836001600160a01b03167f732c23a2264411acb0e6eae8204bbf440da85b5de65c26580de518ffce5ef0a086896040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610c4c578181015183820152602001610c34565b50505050905090810190601f168015610c795780820380516001836020036101000a031916815260200191505b50935050505060405180910390a37fc5205b571028f8abdf44c5cdb39d2c1c81593178eb4aedc8490d5c9a93232aff8385888460405180856001600160a01b03166001600160a01b0316815260200184815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b83811015610d0d578181015183820152602001610cf5565b50505050905090810190601f168015610d3a5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1505050505050565b805180820160209081018051600280835293830194830194909420939052825460408051601f600019610100600186161502019093169490940491820183900483028401830190528083528391830182828015610df05780601f10610dc557610100808354040283529160200191610df0565b820191906000526020600020905b815481529060010190602001808311610dd357829003601f168201915b505050505090806001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e8e5780601f10610e6357610100808354040283529160200191610e8e565b820191906000526020600020905b815481529060010190602001808311610e7157829003601f168201915b50505050600283810154600385015460048601546005870180546040805160206101006001851615026000190190931697909704601f8101839004830288018301909152808752979894976001600160a01b03948516975093831695600160a01b90930460ff16949390830182828015610f495780601f10610f1e57610100808354040283529160200191610f49565b820191906000526020600020905b815481529060010190602001808311610f2c57829003601f168201915b5050505050905087565b610f5b611452565b610f9a576040805162461bcd60e51b81526020600482018190526024820152600080516020611dc9833981519152604482015290519081900360640190fd5b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600080546001600160a01b0319169055565b610fec611452565b61102b576040805162461bcd60e51b81526020600482018190526024820152600080516020611dc9833981519152604482015290519081900360640190fd5b611033611d04565b6002826040518082805190602001908083835b602083106110655780518252601f199092019160209182019101611046565b518151602093840361010090810a600019908101801990941693909216929092179092529290940196875260408051978890038201882080546002600182161586029097011695909504601f810183900490920288018301905260e08701818152939550869450859250908401828280156111215780601f106110f657610100808354040283529160200191611121565b820191906000526020600020905b81548152906001019060200180831161110457829003601f168201915b50505050508152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111c35780601f10611198576101008083540402835291602001916111c3565b820191906000526020600020905b8154815290600101906020018083116111a657829003601f168201915b505050918352505060028281015460208084019190915260038401546001600160a01b0390811660408086019190915260048601549182166060860152600160a01b90910460ff161515608085015260058501805482516101006001831615026000190190911694909404601f810184900484028501840190925281845260a090940193918301828280156112995780601f1061126e57610100808354040283529160200191611299565b820191906000526020600020905b81548152906001019060200180831161127c57829003601f168201915b5050509190925250505060a0810151909150156112e75760405162461bcd60e51b8152600401808060200182810382526033815260200180611d966033913960400191505060405180910390fd5b606081015160408083015190516001600160a01b0383169082156108fc029083906000818181858888f19350505050158015611327573d6000803e3d6000fd5b50600160a084015260405184518491600291879190819060208401908083835b602083106113665780518252601f199092019160209182019101611347565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208451805191946113a794508593500190611c86565b5060208281015180516113c09260018501920190611c86565b506040820151600282015560608201516003820180546001600160a01b03199081166001600160a01b0393841617909155608084015160048401805460a08701519316919093161760ff60a01b1916600160a01b9115159190910217905560c08201518051611439916005840191602090910190611c86565b50505050505050565b6000546001600160a01b03165b90565b600080546001600160a01b0316611467611b5b565b6001600160a01b031614905090565b60036020908152600091825260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845290929183919083018282801561150d5780601f106114e25761010080835404028352916020019161150d565b820191906000526020600020905b8154815290600101906020018083116114f057829003601f168201915b505050600180850154600280870154600388018054604080516020601f600019998516156101000299909901909316959095049687018290048202850182019052858452979893976001600160a01b0390921696509294509092908301828280156115b95780601f1061158e576101008083540402835291602001916115b9565b820191906000526020600020905b81548152906001019060200180831161159c57829003601f168201915b50505050600483015460059093015491929160ff16905086565b60015481565b6115e1611452565b611620576040805162461bcd60e51b81526020600482018190526024820152600080516020611dc9833981519152604482015290519081900360640190fd5b611628611d04565b602081018390526001600160a01b03841660608201523360808201526040810182905261165481611b5f565b806002846040518082805190602001908083835b602083106116875780518252601f199092019160209182019101611668565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208451805191946116c894508593500190611c86565b5060208281015180516116e19260018501920190611c86565b506040820151600282015560608201516003820180546001600160a01b03199081166001600160a01b0393841617909155608084015160048401805460a08701519316919093161760ff60a01b1916600160a01b9115159190910217905560c0820151805161175a916005840191602090910190611c86565b50905050826040518082805190602001908083835b6020831061178e5780518252601f19909201916020918201910161176f565b51815160209384036101000a60001901801990921691161790526040805192909401829003822088835293519395506001600160a01b038a1694507fe6d3c69bf41366e6392c93656bdfe09104b47ef9a500b54eefc068a54f0dcf1a9391829003019150a350505050565b611801611452565b611840576040805162461bcd60e51b81526020600482018190526024820152600080516020611dc9833981519152604482015290519081900360640190fd5b611848611c45565b6000838152600360209081526040918290208251815460026001821615610100026000190190911604601f8101849004909302810160e090810190945260c081018381529093919284928491908401828280156118e65780601f106118bb576101008083540402835291602001916118e6565b820191906000526020600020905b8154815290600101906020018083116118c957829003601f168201915b50505091835250506001828101546020808401919091526002808501546001600160a01b031660408086019190915260038601805482516101009682161596909602600019011692909204601f8101849004840285018401909152808452606090940193909183018282801561199d5780601f106119725761010080835404028352916020019161199d565b820191906000526020600020905b81548152906001019060200180831161198057829003601f168201915b50505091835250506004820154602082015260059091015460ff16151560409091015260a081015190915015611a045760405162461bcd60e51b8152600401808060200182810382526033815260200180611d966033913960400191505060405180910390fd5b604080820151602083015191519091906001600160a01b0383169082156108fc029083906000818181858888f19350505050158015611a47573d6000803e3d6000fd5b5084826001600160a01b03167f18299177ec34ec12a04fd18c915c81caa62b319c422628d2cdd1374fa84e174483876040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611ab9578181015183820152602001611aa1565b50505050905090810190601f168015611ae65780820380516001836020036101000a031916815260200191505b50935050505060405180910390a35050505050565b611b03611452565b611b42576040805162461bcd60e51b81526020600482018190526024820152600080516020611dc9833981519152604482015290519081900360640190fd5b611b4b81611ba5565b50565b80546001019055565b5490565b3390565b606081015160408083015190516001600160a01b0383169082156108fc029083906000818181858888f19350505050158015611b9f573d6000803e3d6000fd5b50505050565b6001600160a01b038116611bea5760405162461bcd60e51b8152600401808060200182810382526026815260200180611d706026913960400191505060405180910390fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b6040518060c00160405280606081526020016000815260200160006001600160a01b0316815260200160608152602001600081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611cc757805160ff1916838001178555611cf4565b82800160010185558215611cf4579182015b82811115611cf4578251825591602001919060010190611cd9565b50611d00929150611d55565b5090565b6040518060e0016040528060608152602001606081526020016000815260200160006001600160a01b0316815260200160006001600160a01b03168152602001600015158152602001606081525090565b61144f91905b80821115611d005760008155600101611d5b56fe4f776e61626c653a206e6577206f776e657220697320746865207a65726f20616464726573737472616e73616374696f6e20697320616c72656164792065786563757465642120287265656e7472616e6379206775617264294f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572a265627a7a723158208f160e27e8b9a3061e94038bec30515b9f0bc1b5751600e3572fd29db3dd6b7364736f6c63430005110032";

    public static final String FUNC_INREQUEST = "InRequest";

    public static final String FUNC_OUTREQUEST = "OutRequest";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_TX_ID_OUT = "tx_id_out";

    public static final String FUNC_CASHOUTREQUEST = "cashOutRequest";

    public static final String FUNC_CASHINREQUEST = "cashInRequest";

    public static final String FUNC_CASHINSUBMIT = "cashInSubmit";

    public static final String FUNC_CASHOUTSUBMIT = "cashOutSubmit";

    public static final String FUNC_CASHOUTREVERT = "cashOutRevert";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event CASHINREQUESTEVENT_EVENT = new Event("cashInRequestEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event CASHOUTREQUESTEVENT_EVENT = new Event("cashOutRequestEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event CASHOUTREQUESTEVENTANONYMOUSE_EVENT = new Event("cashOutRequestEventAnonymouse", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHOUTREVERTEVENT_EVENT = new Event("cashOutRevertEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x27DFe24fB73D68C563aecf0e404008a0c6ebE999");
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
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.uuid = (byte[]) eventValues.getIndexedValues().get(1).getValue();
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
                typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.uuid = (byte[]) eventValues.getIndexedValues().get(1).getValue();
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
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.txid = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.purce = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.txid = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.purce = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CashOutRequestEventEventResponse> cashOutRequestEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHOUTREQUESTEVENT_EVENT));
        return cashOutRequestEventEventFlowable(filter);
    }

    public List<CashOutRequestEventAnonymouseEventResponse> getCashOutRequestEventAnonymouseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHOUTREQUESTEVENTANONYMOUSE_EVENT, transactionReceipt);
        ArrayList<CashOutRequestEventAnonymouseEventResponse> responses = new ArrayList<CashOutRequestEventAnonymouseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashOutRequestEventAnonymouseEventResponse typedResponse = new CashOutRequestEventAnonymouseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.purce = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.txid = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CashOutRequestEventAnonymouseEventResponse> cashOutRequestEventAnonymouseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CashOutRequestEventAnonymouseEventResponse>() {
            @Override
            public CashOutRequestEventAnonymouseEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHOUTREQUESTEVENTANONYMOUSE_EVENT, log);
                CashOutRequestEventAnonymouseEventResponse typedResponse = new CashOutRequestEventAnonymouseEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.purce = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.txid = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CashOutRequestEventAnonymouseEventResponse> cashOutRequestEventAnonymouseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHOUTREQUESTEVENTANONYMOUSE_EVENT));
        return cashOutRequestEventAnonymouseEventFlowable(filter);
    }

    public List<CashOutRevertEventEventResponse> getCashOutRevertEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHOUTREVERTEVENT_EVENT, transactionReceipt);
        ArrayList<CashOutRevertEventEventResponse> responses = new ArrayList<CashOutRevertEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashOutRevertEventEventResponse typedResponse = new CashOutRevertEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.txid = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.err_msg = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CashOutRevertEventEventResponse> cashOutRevertEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CashOutRevertEventEventResponse>() {
            @Override
            public CashOutRevertEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHOUTREVERTEVENT_EVENT, log);
                CashOutRevertEventEventResponse typedResponse = new CashOutRevertEventEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.txid = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.err_msg = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CashOutRevertEventEventResponse> cashOutRevertEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHOUTREVERTEVENT_EVENT));
        return cashOutRevertEventEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple7<String, String, BigInteger, String, String, Boolean, String>> InRequest(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple7<String, String, BigInteger, String, String, Boolean, String>>(function,
                new Callable<Tuple7<String, String, BigInteger, String, String, Boolean, String>>() {
                    @Override
                    public Tuple7<String, String, BigInteger, String, String, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, BigInteger, String, String, Boolean, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple6<String, BigInteger, String, String, BigInteger, Boolean>> OutRequest(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OUTREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<String, BigInteger, String, String, BigInteger, Boolean>>(function,
                new Callable<Tuple6<String, BigInteger, String, String, BigInteger, Boolean>>() {
                    @Override
                    public Tuple6<String, BigInteger, String, String, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, BigInteger, String, String, BigInteger, Boolean>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
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

    public RemoteFunctionCall<BigInteger> tx_id_out() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TX_ID_OUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> cashOutRequest(String purce, String paymentType, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHOUTREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(purce), 
                new org.web3j.abi.datatypes.Utf8String(paymentType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cashInRequest(String user, String uuid, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHINREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
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

    public RemoteFunctionCall<TransactionReceipt> cashOutSubmit(BigInteger tx_id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHOUTSUBMIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tx_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cashOutRevert(BigInteger tx_id, String err_msg) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CASHOUTREVERT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tx_id), 
                new org.web3j.abi.datatypes.Utf8String(err_msg)), 
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

    public static RemoteCall<Deposit> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(Deposit.class, web3j, credentials, contractGasProvider, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<Deposit> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(Deposit.class, web3j, transactionManager, contractGasProvider, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Deposit> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Deposit.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Deposit> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Deposit.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
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
        public String user;

        public byte[] uuid;

        public BigInteger amount;
    }

    public static class CashOutRequestEventEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger txid;

        public BigInteger amount;

        public String purce;
    }

    public static class CashOutRequestEventAnonymouseEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger amount;

        public String purce;

        public BigInteger txid;
    }

    public static class CashOutRevertEventEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger txid;

        public BigInteger amount;

        public String err_msg;
    }
}
