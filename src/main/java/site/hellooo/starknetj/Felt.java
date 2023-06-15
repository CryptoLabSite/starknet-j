package site.hellooo.starknetj;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Felt implements Comparable<Felt> {

//    decimal: 3618502788666131213697322783095070105623107215331596699973092056135872020481
    private static final BigInteger PRIME = new BigInteger("800000000000011000000000000000000000000000000000000000000000001", 16);

    private BigInteger value;

    public Felt(BigInteger value) {
        this.value = value;

        if (value.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Default Felt constructor does not accept negative numbers, [" + value + "] given.");
        }
        if (value.compareTo(PRIME) >= 0) {
            throw new IllegalArgumentException("Default Felt constructor accepts values smaller than Felt.PRIME, [" + value + "] given.");
        }
    }

    public Felt(long value) {
        this(BigInteger.valueOf(value));
    }

    public Felt(int value) {
        this(BigInteger.valueOf(value));
    }

    public static Felt fromHexString(String value) {
        if (!value.startsWith("0x")) {
            throw new IllegalArgumentException("Value should start with 0x");
        }

        String hexValue = value.substring(2);
        return new Felt(new BigInteger(hexValue, 16));
    }

    public String hexString() {
        return "0x" + value.toString(16);
    }

    public String decimalString() {
        return value.toString();
    }

    public BigInteger getValue() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Felt o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return "Felt(" + hexString() + ")";
    }
}
