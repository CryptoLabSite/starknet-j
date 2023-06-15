package site.hellooo.starknetj;

import site.hellooo.starknetj.utils.SelectorUtils;

import java.math.BigInteger;

public class Utils {
//    invoke_v1_tx_hash=ℎ("invoke",version,sender_address,0,ℎ(calldata),max_fee,chain_id,nonce)
    public static void calculateHash() {

    }

    private static final BigInteger MASK_250 = BigInteger.valueOf(2).pow(250).subtract(BigInteger.ONE);

    public static void main(String[] args) {
//        String name = "mint";
//
////        cal keccak
//        byte[] bytes = name.getBytes(StandardCharsets.US_ASCII);
//
//        MessageDigest digest256 = new Keccak.Digest256();
//        digest256.update(bytes);
//
//        byte[] digest = digest256.digest();
//        BigInteger keccak = new BigInteger(digest);
//
        BigInteger result = SelectorUtils.starknetKeccak("mint");
        System.out.println(result.toString());
        System.out.println(result.toString().equals("1329909728320632088402217562277154056711815095720684343816173432540100887380"));


        BigInteger b = new BigInteger("74be5dce8413d7b837b2536481e9ca5af705f7d811f567366942d764606aaa9", 16);
        System.out.println(b.toString(10));


    }

}
