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
    public static final String BINARY = "0x608060405234801561001057600080fd5b50604051612ed5380380612ed58339818101604052602081101561003357600080fd5b8101908080519060200190929190505050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050612e41806100946000396000f3fe60806040523480156200001157600080fd5b5060043610620000525760003560e01c806351c187821462000057578063722b58171462000193578063b992389314620001df578063f35bd9ae14620002b4575b600080fd5b6200014a600480360360808110156200006f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190640100000000811115620000b757600080fd5b820183602082011115620000ca57600080fd5b80359060200191846001830284011164010000000083111715620000ed57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019092919050505062000345565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b6200019d620006ec565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6200029e60048036036020811015620001f757600080fd5b81019080803590602001906401000000008111156200021557600080fd5b8201836020820111156200022857600080fd5b803590602001918460018302840111640100000000831117156200024b57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929050505062000715565b6040518082815260200191505060405180910390f35b6200030360048036036040811015620002cc57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506200078a565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b60008060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905060006001866040518082805190602001908083835b60208310620003a8578051825260208201915060208101905060208303925062000383565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020541462000434576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602681526020018062002de76026913960400191505060405180910390fd5b600081905062000448888883888a6200099a565b935060008490508073ffffffffffffffffffffffffffffffffffffffff166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b1580156200049657600080fd5b505afa158015620004ab573d6000803e3d6000fd5b505050506040513d6020811015620004c257600080fd5b81019080805190602001909291905050509350836001886040518082805190602001908083835b602083106200050e5780518252602082019150602081019050602083039250620004e9565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902081905550866040518082805190602001908083835b602083106200057b578051825260208201915060208101905060208303925062000556565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020848a73ffffffffffffffffffffffffffffffffffffffff167f87836d9d795936ee8b4b6ffb2c7b7fba245eb618db8294c11e9cf03565afbb408b6040518082815260200191505060405180910390a47fb702e8e169b710c91381cf2aaaff91ec3426a2858b72c207dd62ad0bf417f9478989868a604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156200069c5780820151818401526020810190506200067f565b50505050905090810190601f168015620006ca5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a184849450945050505094509492505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60006001826040518082805190602001908083835b602083106200074f57805182526020820191506020810190506020830392506200072a565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020549050919050565b600080620007988362000ad0565b90508084604051620007aa9062000ae7565b808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050604051809103906000f08015801562000804573d6000803e3d6000fd5b509150600084905060008173ffffffffffffffffffffffffffffffffffffffff166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b1580156200085557600080fd5b505afa1580156200086a573d6000803e3d6000fd5b505050506040513d60208110156200088157600080fd5b81019080805190602001909291905050509050808673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f80ea680f0864b200472457d5e9db6c7ab4390228ca763e8159260f99ff4c07e460405160405180910390a47fd224f16d25281ddd265d3572339439c7b0c4ed0edf208b6b48ec27f530dc0aca338783604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a183935050505092915050565b600080620009a88662000ad0565b90508087868686604051620009bd9062000af5565b808681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b8381101562000a6c57808201518184015260208101905062000a4f565b50505050905090810190601f16801562000a9a5780820380516001836020036101000a031916815260200191505b509650505050505050604051809103906000f08015801562000ac0573d6000803e3d6000fd5b5091508191505095945050505050565b6000670de0b6b3a764000082029050809050919050565b6111778062000b0483390190565b61116c8062001c7b8339019056fe608060405234801561001057600080fd5b506040516111773803806111778339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050818160016000806101000a81548160ff021916908315150217905550600082116100e0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f43726f776473616c653a2072617465206973203000000000000000000000000081525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610183576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601d8152602001807f6f726967696e2063616e6e6f74206265207a65726f206164647265737300000081525060200191505060405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663521eb2736040518163ffffffff1660e01b815260040160206040518083038186803b1580156101eb57600080fd5b505afa1580156101ff573d6000803e3d6000fd5b505050506040513d602081101561021557600080fd5b8101908080519060200190929190505050600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503373ffffffffffffffffffffffffffffffffffffffff16600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461030c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260278152602001806111506027913960400191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600581905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc0c546a6040518163ffffffff1660e01b815260040160206040518083038186803b1580156103bc57600080fd5b505afa1580156103d0573d6000803e3d6000fd5b505050506040513d60208110156103e657600080fd5b8101908080519060200190929190505050600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166391039dee6040518163ffffffff1660e01b815260040160206040518083038186803b15801561049f57600080fd5b505afa1580156104b3573d6000803e3d6000fd5b505050506040513d60208110156104c957600080fd5b8101908080519060200190929190505050600281905550600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663645c0fb0600254600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166040518363ffffffff1660e01b8152600401808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050602060405180830381600087803b1580156105ad57600080fd5b505af11580156105c1573d6000803e3d6000fd5b505050506040513d60208110156105d757600080fd5b810190808051906020019092919050505060038190555050505050610b4f806106016000396000f3fe6080604052600436106100a75760003560e01c80636ed08ea0116100645780636ed08ea01461026f57806391039dee1461029a578063a5f8cdbb146102c5578063ec8ac4d814610309578063ecd0c0c31461034d578063fc0c546a146103a4576100a7565b80632c4e722e146100b95780634042b66f146100e457806341035a411461010f578063521eb27314610166578063610757e4146101bd5780636c1e81e314610214575b6100b76100b26103fb565b610403565b005b3480156100c557600080fd5b506100ce61040f565b6040518082815260200191505060405180910390f35b3480156100f057600080fd5b506100f9610419565b6040518082815260200191505060405180910390f35b34801561011b57600080fd5b50610124610423565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561017257600080fd5b5061017b610449565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101c957600080fd5b506101d2610473565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561022057600080fd5b5061026d6004803603604081101561023757600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610499565b005b34801561027b57600080fd5b5061028461055e565b6040518082815260200191505060405180910390f35b3480156102a657600080fd5b506102af610564565b6040518082815260200191505060405180910390f35b610307600480360360208110156102db57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610403565b005b61034b6004803603602081101561031f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061056e565b005b34801561035957600080fd5b506103626106f9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103b057600080fd5b506103b961071f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600033905090565b61040c8161056e565b50565b6000600554905090565b6000600654905090565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006104a361071f565b905060006104af610564565b90508173ffffffffffffffffffffffffffffffffffffffff1663a27c282e8585846040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281526020019350505050600060405180830381600087803b15801561054057600080fd5b505af1158015610554573d6000803e3d6000fd5b5050505050505050565b60025481565b6000600254905090565b6000809054906101000a900460ff166105ef576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f5265656e7472616e637947756172643a207265656e7472616e742063616c6c0081525060200191505060405180910390fd5b60008060006101000a81548160ff02191690831515021790555060003490506106188282610748565b600061062382610849565b905061063a8260065461086790919063ffffffff16565b60068190555061064a83826108ef565b8273ffffffffffffffffffffffffffffffffffffffff166106696103fb565b73ffffffffffffffffffffffffffffffffffffffff167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36106c883836108fd565b6106d0610901565b6106da838361096c565b505060016000806101000a81548160ff02191690831515021790555050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156107ce576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610af1602a913960400191505060405180910390fd5b6000811415610845576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f43726f776473616c653a20776569416d6f756e7420697320300000000000000081525060200191505060405180910390fd5b5050565b60006108606005548361097090919063ffffffff16565b9050919050565b6000808284019050838110156108e5576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b6108f982826109f6565b5050565b5050565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610969573d6000803e3d6000fd5b50565b5050565b60008083141561098357600090506109f0565b600082840290508284828161099457fe5b04146109eb576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526021815260200180610ad06021913960400191505060405180910390fd5b809150505b92915050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663cd3a15f883836002546003546040518563ffffffff1660e01b8152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828152602001945050505050600060405180830381600087803b158015610ab357600080fd5b505af1158015610ac7573d6000803e3d6000fd5b50505050505056fe536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7743726f776473616c653a2062656e656669636961727920697320746865207a65726f2061646472657373a265627a7a723158201d70e735f5b22e6b0613582a66b14df60eede9f26273a21a31b9ff85c969822664736f6c634300051100326f6e6c79206f726967696e206f7267616e697a65722063616e20706c7567206e65772073616c6560806040526001600255600060045534801561001a57600080fd5b5060405161116c38038061116c833981810160405260a081101561003d57600080fd5b81019080805190602001909291908051906020019092919080519060200190929190805190602001909291908051604051939291908464010000000082111561008557600080fd5b8382019150602082018581111561009b57600080fd5b82518660018202830111640100000000821117156100b857600080fd5b8083526020830192505050908051906020019080838360005b838110156100ec5780820151818401526020810190506100d1565b50505050905090810190601f1680156101195780820380516001836020036101000a031916815260200191505b50604052505050848484848460016000806101000a81548160ff021916908315150217905550600085116101b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f43726f776473616c653a2072617465206973203000000000000000000000000081525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff16141561023b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001806111476025913960400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156102c1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260248152602001806111236024913960400191505060405180910390fd5b8460068190555083600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600381905550600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a201b238600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16836040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610434578082015181840152602081019050610419565b50505050905090810190601f1680156104615780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561048157600080fd5b505af1158015610495573d6000803e3d6000fd5b505050506040513d60208110156104ab57600080fd5b810190808051906020019092919050505060018190555050505050505050505050610c48806104db6000396000f3fe6080604052600436106100dd5760003560e01c806391039dee1161007f578063ecd0c0c311610059578063ecd0c0c314610357578063f00a93d3146103ae578063f9cfe26b146103d9578063fc0c546a14610404576100dd565b806391039dee146102a4578063a5f8cdbb146102cf578063ec8ac4d814610313576100dd565b8063521eb273116100bb578063521eb27314610170578063610757e4146101c75780636c1e81e31461021e5780636ed08ea014610279576100dd565b806323e6f340146100ef5780632c4e722e1461011a5780634042b66f14610145575b6100ed6100e861045b565b610463565b005b3480156100fb57600080fd5b5061010461046f565b6040518082815260200191505060405180910390f35b34801561012657600080fd5b5061012f610475565b6040518082815260200191505060405180910390f35b34801561015157600080fd5b5061015a61047f565b6040518082815260200191505060405180910390f35b34801561017c57600080fd5b50610185610489565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101d357600080fd5b506101dc6104b3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561022a57600080fd5b506102776004803603604081101561024157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506104d9565b005b34801561028557600080fd5b5061028e61059e565b6040518082815260200191505060405180910390f35b3480156102b057600080fd5b506102b96105a4565b6040518082815260200191505060405180910390f35b610311600480360360208110156102e557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610463565b005b6103556004803603602081101561032957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506105ae565b005b34801561036357600080fd5b5061036c610755565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103ba57600080fd5b506103c361077b565b6040518082815260200191505060405180910390f35b3480156103e557600080fd5b506103ee61078a565b6040518082815260200191505060405180910390f35b34801561041057600080fd5b50610419610790565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600033905090565b61046c816105ae565b50565b60025481565b6000600654905090565b6000600754905090565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006104e3610790565b905060006104ef6105a4565b90508173ffffffffffffffffffffffffffffffffffffffff1663a27c282e8585846040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281526020019350505050600060405180830381600087803b15801561058057600080fd5b505af1158015610594573d6000803e3d6000fd5b5050505050505050565b60015481565b6000600154905090565b6000809054906101000a900460ff1661062f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f5265656e7472616e637947756172643a207265656e7472616e742063616c6c0081525060200191505060405180910390fd5b60008060006101000a81548160ff02191690831515021790555060003490506000610659826107b9565b905061066683838361084a565b61067b826007546109b190919063ffffffff16565b600781905550610696816004546109b190919063ffffffff16565b6004819055506106a68382610a39565b8273ffffffffffffffffffffffffffffffffffffffff166106c561045b565b73ffffffffffffffffffffffffffffffffffffffff167f6faf93231a456e552dbc9961f58d9713ee4f2e69d15f1975b050ef0911053a7b8484604051808381526020018281526020019250505060405180910390a36107248383610a47565b61072c610a4b565b6107368383610ab6565b505060016000806101000a81548160ff02191690831515021790555050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600061078561077b565b905090565b60045481565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600654821015610816576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602c815260200180610be8602c913960400191505060405180910390fd5b6000670de0b6b3a76400006006548161082b57fe5b04670de0b6b3a7640000848161083d57fe5b0402905080915050919050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156108d0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610bbe602a913960400191505060405180910390fd5b6000821415610947576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f43726f776473616c653a20776569416d6f756e7420697320300000000000000081525060200191505060405180910390fd5b6000816004540190506003548111156109ab576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180610b94602a913960400191505060405180910390fd5b50505050565b600080828401905083811015610a2f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b610a438282610aba565b5050565b5050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610ab3573d6000803e3d6000fd5b50565b5050565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663cd3a15f883836001546002546040518563ffffffff1660e01b8152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828152602001945050505050600060405180830381600087803b158015610b7757600080fd5b505af1158015610b8b573d6000803e3d6000fd5b50505050505056fe746f6b656e7320616d6f756e742073686f756c64206e6f74206578636565642073616c655f6c696d697443726f776473616c653a2062656e656669636961727920697320746865207a65726f206164647265737377656920616d6f756e742073686f756c6420626520626967676572206f7220657175616c206f662072617465a265627a7a723158202774aa086c4927a0fcc7dbd36232196e96f316ccc6bdf9710fdf0d29c9b4cdbb64736f6c6343000511003243726f776473616c653a20746f6b656e20697320746865207a65726f206164647265737343726f776473616c653a2077616c6c657420697320746865207a65726f206164647265737373616c6520776974682074686973204a494420697320616c7265616479206372656174656421a265627a7a7231582008377dc732351ab98ece165c058f212c2790a0583fe886490a65e021f377538b64736f6c63430005110032";

    public static final String FUNC_CREATETICKETSALE = "createTicketSale";

    public static final String FUNC_PLUGINTICKETSALE = "PlugInTicketSale";

    public static final String FUNC_GETEVENTIDBYJID = "getEventIdByJid";

    public static final String FUNC_GETTICKETTEMPLATEADDRESS = "getTicketTemplateAddress";

    public static final Event PLUGGEDSALE_EVENT = new Event("PluggedSale", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PLUGGEDSALEHUMAN_EVENT = new Event("PluggedSaleHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SALECREATED_EVENT = new Event("SaleCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event SALECREATEDHUMAN_EVENT = new Event("SaleCreatedHuman", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x43af418E9a67b7C86354fcF3F841Cb40b225d65c");
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

    public RemoteFunctionCall<TransactionReceipt> PlugInTicketSale(String origin_sale, BigInteger price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PLUGINTICKETSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(origin_sale), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
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
    }
}
