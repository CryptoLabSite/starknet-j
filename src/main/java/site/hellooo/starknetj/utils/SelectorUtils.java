package site.hellooo.starknetj.utils;

import org.bouncycastle.jcajce.provider.digest.Keccak;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SelectorUtils {

    private static final BigInteger MASK_250 = BigInteger.valueOf(2).pow(250).subtract(BigInteger.ONE);

    public static BigInteger starknetKeccak(String input) {
        BigInteger keccak = keccak256(input.getBytes(StandardCharsets.UTF_8));
        return keccak.and(MASK_250);
    }

    private static BigInteger keccak256(byte[] value) {

        MessageDigest keccak256 = new Keccak.Digest256();
        keccak256.update(value);

        return new BigInteger(keccak256.digest());
    }
}
