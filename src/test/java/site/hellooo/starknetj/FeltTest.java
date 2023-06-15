package site.hellooo.starknetj;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class FeltTest {
    @Test
    public void testFromHexString() {
        String hexValueStr = "0x5f7774546ddcc5cb6b0b00123da2f6789ee28ba03183b3ad0f6b95795a216c6";
        String decimalValueStr = "2698798643431283922683484360915200082820825820136676503669324233245877999302";

        Felt felt = Felt.fromHexString(hexValueStr);
        assertThat(felt.decimalString()).isEqualTo(decimalValueStr);
    }

    @Test
    public void test() {
        //𝐺=(874739451078007766457464989774322083649278607533249481151382481072868806602,
//        152666792071518830868575557812948353041420400780739481342941381225525861407)

        BigInteger gx = new BigInteger("874739451078007766457464989774322083649278607533249481151382481072868806602");
        BigInteger gy = new BigInteger("152666792071518830868575557812948353041420400780739481342941381225525861407");

        BigInteger b = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592307816406665");

        BigInteger p = new BigInteger("1713931329540660377023406109199410414810705867260802078187082345529207694986");

        System.out.println(gx.toString(16));
        System.out.println(gy.toString(16));
        System.out.println(b.toString(16));
        System.out.println(p.toString(16));
//        print(gy**2 % p == (gx**3 + gx +

    }
}
