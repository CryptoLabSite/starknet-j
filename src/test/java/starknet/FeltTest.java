package starknet;

import com.swmansion.starknet.data.types.Felt;
import org.junit.Test;

public class FeltTest {
    @Test
    public void test() {
        Felt felt = Felt.fromShortString("mint");
        System.out.println(felt.hexString());
    }
}
