package site.hellooo.starknetj.utils;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;

public class HashUtils {
//    public static Felt calculateTransactionHashCommon(TransactionType txType, Felt version, Felt contractAddress, Felt entryPointSelector, Calldata calldata, Felt maxFee, StarknetChainId starknetChainId, Felt nonce) {
//        StarknetCurve.pedersen()
//    }

//    public static

    public static void main(String[] args) {
        Felt felt = StarknetCurve.pedersen(new Felt(1), new Felt(2));
        System.out.println(felt);

    }
}
