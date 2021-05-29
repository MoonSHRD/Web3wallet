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
public class TicketFactory721 extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b506040516121213803806121218339818101604052602081101561003357600080fd5b5051600080546001600160a01b039092166001600160a01b03199092169190911790556120bc806100656000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80632168d20e1461005157806351c187821461009f578063722b58171461017f578063b992389314610187575b600080fd5b6100836004803603606081101561006757600080fd5b506001600160a01b03813516906020810135906040013561023f565b604080516001600160a01b039092168252519081900360200190f35b61015c600480360360808110156100b557600080fd5b6001600160a01b03823516916020810135918101906060810160408201356401000000008111156100e557600080fd5b8201836020820111156100f757600080fd5b8035906020019184600183028401116401000000008311171561011957600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550509135925061040c915050565b604080516001600160a01b03909316835260208301919091528051918290030190f35b610083610787565b61022d6004803603602081101561019d57600080fd5b8101906020810181356401000000008111156101b857600080fd5b8201836020820111156101ca57600080fd5b803590602001918460018302840111640100000000831117156101ec57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610796945050505050565b60408051918252519081900360200190f35b60008061024b846107fe565b905080858460405161025c906108fe565b9283526001600160a01b0390911660208301526040808301919091525190819003606001906000f080158015610296573d6000803e3d6000fd5b50915060008290506000816001600160a01b03166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b1580156102d957600080fd5b505afa1580156102ed573d6000803e3d6000fd5b505050506040513d602081101561030357600080fd5b50516040805163fbbdc46560e01b815290519192506000916001600160a01b0385169163fbbdc465916004808301926020929190829003018186803b15801561034b57600080fd5b505afa15801561035f573d6000803e3d6000fd5b505050506040513d602081101561037557600080fd5b505160405190915082906001600160a01b038a169033907f80ea680f0864b200472457d5e9db6c7ab4390228ca763e8159260f99ff4c07e490600090a4604080513381526001600160a01b038a1660208201528082018490526060810183905290517f842f53ee085ffb3e94c2cdd4376dd879b170ba013b0f22dcc95e9ba6596fbbce9181900360800190a1505050509392505050565b60008054604051845183926001600160a01b031691600191879190819060208401908083835b602083106104515780518252601f199092019160209182019101610432565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220541591506104bd90505760405162461bcd60e51b81526004018080602001828103825260268152602001806120626026913960400191505060405180910390fd5b806104cb888883888a61080b565b93506000849050806001600160a01b03166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b15801561050b57600080fd5b505afa15801561051f573d6000803e3d6000fd5b505050506040513d602081101561053557600080fd5b50516040805163fbbdc46560e01b815290519195506000916001600160a01b0384169163fbbdc465916004808301926020929190829003018186803b15801561057d57600080fd5b505afa158015610591573d6000803e3d6000fd5b505050506040513d60208110156105a757600080fd5b5051604051895191925086916001918b91819060208401908083835b602083106105e25780518252601f1990920191602091820191016105c3565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842094909455505089518a928291908401908083835b602083106106405780518252601f199092019160209182019101610621565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020858b6001600160a01b03167f87836d9d795936ee8b4b6ffb2c7b7fba245eb618db8294c11e9cf03565afbb408c6040518082815260200191505060405180910390a47f8cf17295cea07172ef627805a194fc701945ac99cb803ae5930490fe2afb45098a8a878b8560405180866001600160a01b03166001600160a01b0316815260200185815260200184815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561073c578181015183820152602001610724565b50505050905090810190601f1680156107695780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a15050505094509492505050565b6000546001600160a01b031690565b60006001826040518082805190602001908083835b602083106107ca5780518252601f1990920191602091820191016107ab565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054949350505050565b670de0b6b3a76400000290565b600080610817866107fe565b9050808786868660405161082a9061090b565b80868152602001856001600160a01b03166001600160a01b03168152602001846001600160a01b03166001600160a01b0316815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156108a0578181015183820152602001610888565b50505050905090810190601f1680156108cd5780820380516001836020036101000a031916815260200191505b509650505050505050604051809103906000f0801580156108f2573d6000803e3d6000fd5b50979650505050505050565b610b908061091983390190565b610bb9806114a98339019056fe6080604052600060055534801561001557600080fd5b50604051610b90380380610b908339818101604052606081101561003857600080fd5b50805160208201516040909201516000805460ff19166001179055909190828282826100ab576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b038216610106576040805162461bcd60e51b815260206004820152601d60248201527f6f726967696e2063616e6e6f74206265207a65726f2061646472657373000000604482015290519081900360640190fd5b600180546001600160a01b0319166001600160a01b0384811691909117918290556040805163521eb27360e01b81529051929091169163521eb27391600480820192602092909190829003018186803b15801561016257600080fd5b505afa158015610176573d6000803e3d6000fd5b505050506040513d602081101561018c57600080fd5b5051600680546001600160a01b0319166001600160a01b039283161790556007849055600482815560015460408051637e062a3560e11b81529051919093169263fc0c546a9281810192602092909190829003018186803b1580156101f057600080fd5b505afa158015610204573d6000803e3d6000fd5b505050506040513d602081101561021a57600080fd5b505160008054610100600160a81b0319166101006001600160a01b039384160217905560015460408051634881cef760e11b8152905191909216916391039dee916004808301926020929190829003018186803b15801561027a57600080fd5b505afa15801561028e573d6000803e3d6000fd5b505050506040513d60208110156102a457600080fd5b505160028190556000805460065460408051630645c0fb60e41b815260048101959095526001600160a01b03918216602486015251610100909204169263645c0fb09260448083019360209390929083900390910190829087803b15801561030b57600080fd5b505af115801561031f573d6000803e3d6000fd5b505050506040513d602081101561033557600080fd5b50516003555050505050506108418061034f6000396000f3fe6080604052600436106100e85760003560e01c806391039dee1161008a578063f00a93d311610059578063f00a93d314610255578063f9cfe26b1461026a578063fbbdc4651461027f578063fc0c546a14610294576100e8565b806391039dee146101df578063a5f8cdbb146101f4578063ec8ac4d81461021a578063ecd0c0c314610240576100e8565b8063521eb273116100c6578063521eb27314610167578063610757e41461017c5780636c1e81e3146101915780636ed08ea0146101ca576100e8565b80632c4e722e146100fa5780634042b66f1461012157806341035a4114610136575b6100f86100f36102a9565b6102ad565b005b34801561010657600080fd5b5061010f6102b9565b60408051918252519081900360200190f35b34801561012d57600080fd5b5061010f6102bf565b34801561014257600080fd5b5061014b6102c5565b604080516001600160a01b039092168252519081900360200190f35b34801561017357600080fd5b5061014b6102d4565b34801561018857600080fd5b5061014b6102e3565b34801561019d57600080fd5b506100f8600480360360408110156101b457600080fd5b506001600160a01b0381351690602001356102f2565b3480156101d657600080fd5b5061010f610390565b3480156101eb57600080fd5b5061010f610396565b6100f86004803603602081101561020a57600080fd5b50356001600160a01b03166102ad565b6100f86004803603602081101561023057600080fd5b50356001600160a01b031661039c565b34801561024c57600080fd5b5061014b6104d3565b34801561026157600080fd5b5061010f6104e7565b34801561027657600080fd5b5061010f6104ed565b34801561028b57600080fd5b5061010f6104f3565b3480156102a057600080fd5b5061014b6104f9565b3390565b6102b68161039c565b50565b60075490565b60085490565b6001546001600160a01b031681565b6006546001600160a01b031690565b6006546001600160a01b031681565b60006102fc6104f9565b90506000610308610396565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b15801561037257600080fd5b505af1158015610386573d6000803e3d6000fd5b5050505050505050565b60025481565b60025490565b60005460ff166103f3576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff1916815534906104088261050d565b905061041583838361052a565b600854610428908363ffffffff61060f16565b60085560055461043e908263ffffffff61060f16565b60055561044b8382610670565b826001600160a01b031661045d6102a9565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36104af838361067a565b6104b761067e565b6104c1838361067a565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60045490565b60055481565b60035490565b60005461010090046001600160a01b031690565b6000610524600754836106b790919063ffffffff16565b92915050565b6001600160a01b03831661056f5760405162461bcd60e51b815260040180806020018281038252602a8152602001806107e3602a913960400191505060405180910390fd5b816105c1576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b600554600454908201908111156106095760405162461bcd60e51b815260040180806020018281038252602a815260200180610798602a913960400191505060405180910390fd5b50505050565b600082820183811015610669576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b61067a8282610710565b5050565b6006546040516001600160a01b03909116903480156108fc02916000818181858888f193505050501580156102b6573d6000803e3d6000fd5b6000826106c657506000610524565b828202828482816106d357fe5b04146106695760405162461bcd60e51b81526004018080602001828103825260218152602001806107c26021913960400191505060405180910390fd5b60008054600254600354604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b15801561077b57600080fd5b505af115801561078f573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d6974536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f2061646472657373a265627a7a7231582018fb18e6b3ec07f4e29a03fdf6032b19bdfc5c359ab72b2b708aeb139bb3f9fb64736f6c6343000511003260806040526001600255600060045534801561001a57600080fd5b50604051610bb9380380610bb9833981810160405260a081101561003d57600080fd5b815160208301516040808501516060860151608087018051935195979496929591949193928201928464010000000082111561007857600080fd5b90830190602082018581111561008d57600080fd5b82516401000000008111828201881017156100a757600080fd5b82525081516020918201929091019080838360005b838110156100d45781810151838201526020016100bc565b50505050905090810190601f1680156101015780820380516001836020036101000a031916815260200191505b5060405250506000805460ff191660011790555084848484848461016c576040805162461bcd60e51b815260206004820152601460248201527f43726f776473616c653a20726174652069732030000000000000000000000000604482015290519081900360640190fd5b6001600160a01b0384166101b15760405162461bcd60e51b8152600401808060200182810382526025815260200180610b946025913960400191505060405180910390fd5b6001600160a01b0383166101f65760405162461bcd60e51b8152600401808060200182810382526024815260200180610b706024913960400191505060405180910390fd5b6006859055600580546001600160a01b0319166001600160a01b03868116919091179182905560008054610100600160a81b031916610100878416810291909117808355600387905560408051631440364760e31b81529585166004870181815260248801928352885160448901528851949093049095169563a201b2389594889460649092019160208601918190849084905b838110156102a257818101518382015260200161028a565b50505050905090810190601f1680156102cf5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156102ef57600080fd5b505af1158015610303573d6000803e3d6000fd5b505050506040513d602081101561031957600080fd5b505160015550505050505050505050610839806103376000396000f3fe6080604052600436106100e85760003560e01c806391039dee1161008a578063f00a93d311610059578063f00a93d314610255578063f9cfe26b1461026a578063fbbdc4651461027f578063fc0c546a14610294576100e8565b806391039dee146101df578063a5f8cdbb146101f4578063ec8ac4d81461021a578063ecd0c0c314610240576100e8565b8063521eb273116100c6578063521eb2731461014b578063610757e41461017c5780636c1e81e3146101915780636ed08ea0146101ca576100e8565b806323e6f340146100fa5780632c4e722e146101215780634042b66f14610136575b6100f86100f36102a9565b6102ad565b005b34801561010657600080fd5b5061010f6102b9565b60408051918252519081900360200190f35b34801561012d57600080fd5b5061010f6102bf565b34801561014257600080fd5b5061010f6102c5565b34801561015757600080fd5b506101606102cb565b604080516001600160a01b039092168252519081900360200190f35b34801561018857600080fd5b506101606102da565b34801561019d57600080fd5b506100f8600480360360408110156101b457600080fd5b506001600160a01b0381351690602001356102e9565b3480156101d657600080fd5b5061010f610387565b3480156101eb57600080fd5b5061010f61038d565b6100f86004803603602081101561020a57600080fd5b50356001600160a01b03166102ad565b6100f86004803603602081101561023057600080fd5b50356001600160a01b0316610393565b34801561024c57600080fd5b506101606104ca565b34801561026157600080fd5b5061010f6104de565b34801561027657600080fd5b5061010f6104e4565b34801561028b57600080fd5b5061010f6104ea565b3480156102a057600080fd5b506101606104f0565b3390565b6102b681610393565b50565b60025481565b60065490565b60075490565b6005546001600160a01b031690565b6005546001600160a01b031681565b60006102f36104f0565b905060006102ff61038d565b9050816001600160a01b031663a27c282e8585846040518463ffffffff1660e01b815260040180846001600160a01b03166001600160a01b031681526020018381526020018281526020019350505050600060405180830381600087803b15801561036957600080fd5b505af115801561037d573d6000803e3d6000fd5b5050505050505050565b60015481565b60015490565b60005460ff166103ea576040805162461bcd60e51b815260206004820152601f60248201527f5265656e7472616e637947756172643a207265656e7472616e742063616c6c00604482015290519081900360640190fd5b6000805460ff1916815534906103ff82610504565b905061040c838383610570565b60075461041f908363ffffffff61065516565b600755600454610435908263ffffffff61065516565b60045561044283826106b6565b826001600160a01b03166104546102a9565b6001600160a01b03167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36104a683836106c0565b6104ae6106c4565b6104b883836106c0565b50506000805460ff1916600117905550565b60005461010090046001600160a01b031681565b60035490565b60045481565b60025490565b60005461010090046001600160a01b031690565b60006006548210156105475760405162461bcd60e51b815260040180806020018281038252602c8152602001806107d9602c913960400191505060405180910390fd5b6000670de0b6b3a76400006006548161055c57fe5b04670de0b6b3a76400008404029392505050565b6001600160a01b0383166105b55760405162461bcd60e51b815260040180806020018281038252602a8152602001806107af602a913960400191505060405180910390fd5b81610607576040805162461bcd60e51b815260206004820152601960248201527f43726f776473616c653a20776569416d6f756e74206973203000000000000000604482015290519081900360640190fd5b6004546003549082019081111561064f5760405162461bcd60e51b815260040180806020018281038252602a815260200180610785602a913960400191505060405180910390fd5b50505050565b6000828201838110156106af576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b6106c082826106fd565b5050565b6005546040516001600160a01b03909116903480156108fc02916000818181858888f193505050501580156102b6573d6000803e3d6000fd5b60008054600154600254604080516319a742bf60e31b81526001600160a01b0388811660048301526024820188905260448201949094526064810192909252516101009093049091169263cd3a15f89260848084019382900301818387803b15801561076857600080fd5b505af115801561077c573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d697443726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a72315820b4aee8bdce237fae3b2909943e620f3c6ba56736475724d79c97d604ca70e90764736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f206164647265737373616c6520776974682074686973204a494420697320616c7265616479206372656174656421a265627a7a72315820249898cbf8670f0c5b6011efd4a7ab260da89d23cc52d5a026bfd3f7f8a24c7964736f6c63430005110032";

    public static final String FUNC_CREATETICKETSALE = "createTicketSale";

    public static final String FUNC_PLUGINTICKETSALE = "PlugInTicketSale";

    public static final String FUNC_GETEVENTIDBYJID = "getEventIdByJid";

    public static final String FUNC_GETTICKETTEMPLATEADDRESS = "getTicketTemplateAddress";

    public static final Event PLUGGEDSALE_EVENT = new Event("PluggedSale", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PLUGGEDSALEHUMAN_EVENT = new Event("PluggedSaleHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SALECREATED_EVENT = new Event("SaleCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event SALECREATEDHUMAN_EVENT = new Event("SaleCreatedHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x94E74D6b44DF3A93f15a4Dff9D893d3cDf73C0c0");
    }

    @Deprecated
    protected TicketFactory721(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TicketFactory721(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TicketFactory721(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TicketFactory721(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<PluggedSaleEventResponse> getPluggedSaleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLUGGEDSALE_EVENT, transactionReceipt);
        ArrayList<PluggedSaleEventResponse> responses = new ArrayList<PluggedSaleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PluggedSaleEventResponse typedResponse = new PluggedSaleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.orginal_sale = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PluggedSaleEventResponse> pluggedSaleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PluggedSaleEventResponse>() {
            @Override
            public PluggedSaleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLUGGEDSALE_EVENT, log);
                PluggedSaleEventResponse typedResponse = new PluggedSaleEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.orginal_sale = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PluggedSaleEventResponse> pluggedSaleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLUGGEDSALE_EVENT));
        return pluggedSaleEventFlowable(filter);
    }

    public List<PluggedSaleHumanEventResponse> getPluggedSaleHumanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLUGGEDSALEHUMAN_EVENT, transactionReceipt);
        ArrayList<PluggedSaleHumanEventResponse> responses = new ArrayList<PluggedSaleHumanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PluggedSaleHumanEventResponse typedResponse = new PluggedSaleHumanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.original_sale = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PluggedSaleHumanEventResponse> pluggedSaleHumanEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PluggedSaleHumanEventResponse>() {
            @Override
            public PluggedSaleHumanEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLUGGEDSALEHUMAN_EVENT, log);
                PluggedSaleHumanEventResponse typedResponse = new PluggedSaleHumanEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.original_sale = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PluggedSaleHumanEventResponse> pluggedSaleHumanEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLUGGEDSALEHUMAN_EVENT));
        return pluggedSaleHumanEventFlowable(filter);
    }

    public List<SaleCreatedEventResponse> getSaleCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SALECREATED_EVENT, transactionReceipt);
        ArrayList<SaleCreatedEventResponse> responses = new ArrayList<SaleCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SaleCreatedEventResponse typedResponse = new SaleCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.event_JID = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SaleCreatedEventResponse> saleCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SaleCreatedEventResponse>() {
            @Override
            public SaleCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SALECREATED_EVENT, log);
                SaleCreatedEventResponse typedResponse = new SaleCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.event_JID = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SaleCreatedEventResponse> saleCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALECREATED_EVENT));
        return saleCreatedEventFlowable(filter);
    }

    public List<SaleCreatedHumanEventResponse> getSaleCreatedHumanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SALECREATEDHUMAN_EVENT, transactionReceipt);
        ArrayList<SaleCreatedHumanEventResponse> responses = new ArrayList<SaleCreatedHumanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SaleCreatedHumanEventResponse typedResponse = new SaleCreatedHumanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.event_JID = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SaleCreatedHumanEventResponse> saleCreatedHumanEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SaleCreatedHumanEventResponse>() {
            @Override
            public SaleCreatedHumanEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SALECREATEDHUMAN_EVENT, log);
                SaleCreatedHumanEventResponse typedResponse = new SaleCreatedHumanEventResponse();
                typedResponse.log = log;
                typedResponse.organizer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.event_id = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.event_JID = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.ticket_type = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SaleCreatedHumanEventResponse> saleCreatedHumanEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALECREATEDHUMAN_EVENT));
        return saleCreatedHumanEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createTicketSale(String organizer, BigInteger price, String event_JID, BigInteger sale_limit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATETICKETSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(organizer), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.Utf8String(event_JID), 
                new org.web3j.abi.datatypes.generated.Uint256(sale_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> PlugInTicketSale(String origin_sale, BigInteger price, BigInteger _sale_limit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PLUGINTICKETSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(origin_sale), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.generated.Uint256(_sale_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getEventIdByJid(String JID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEVENTIDBYJID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(JID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getTicketTemplateAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTICKETTEMPLATEADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static TicketFactory721 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketFactory721(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TicketFactory721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketFactory721(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TicketFactory721 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TicketFactory721(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TicketFactory721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TicketFactory721(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _ticket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket)));
        return deployRemoteCall(TicketFactory721.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _ticket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket)));
        return deployRemoteCall(TicketFactory721.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _ticket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket)));
        return deployRemoteCall(TicketFactory721.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketFactory721> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _ticket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_ticket)));
        return deployRemoteCall(TicketFactory721.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class PluggedSaleEventResponse extends BaseEventResponse {
        public String organizer;

        public String orginal_sale;

        public BigInteger event_id;
    }

    public static class PluggedSaleHumanEventResponse extends BaseEventResponse {
        public String organizer;

        public String original_sale;

        public BigInteger event_id;

        public BigInteger ticket_type;
    }

    public static class SaleCreatedEventResponse extends BaseEventResponse {
        public String organizer;

        public BigInteger event_id;

        public byte[] event_JID;

        public BigInteger price;
    }

    public static class SaleCreatedHumanEventResponse extends BaseEventResponse {
        public String organizer;

        public BigInteger price;

        public BigInteger event_id;

        public String event_JID;

        public BigInteger ticket_type;
    }
}
