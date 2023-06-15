package site.hellooo.starknetj.utils;

import org.bouncycastle.math.ec.ECCurve;
import site.hellooo.starknetj.Felt;

import java.math.BigInteger;

// references:
// https://docs.starknet.io/documentation/architecture_and_concepts/Hashing/hash-functions
// https://github.com/starkware-libs/cairo-lang/blob/master/src/starkware/crypto/signature/fast_pedersen_hash.py
// https://github.com/starkware-libs/cairo-lang/blob/master/src/starkware/crypto/signature/signature.py
// https://github.com/starkware-libs/cairo-lang/blob/master/src/starkware/crypto/signature/pedersen_params.json
public class ECUtils {
//    curve
//    https://docs.starkware.co/starkex/crypto/stark-curve.html
    private static final BigInteger ALPHA = new BigInteger("1");
    private static final BigInteger BETA = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592307816406665");
    private static final BigInteger PRIME = new BigInteger("3618502788666131213697322783095070105623107215331596699973092056135872020481");
    private static final BigInteger EC_ORDER = new BigInteger("3618502788666131213697322783095070105526743751716087489154079457884512865583");
    private static final BigInteger[] EC_GENERATOR_POINT = {new BigInteger("874739451078007766457464989774322083649278607533249481151382481072868806602"), new BigInteger("152666792071518830868575557812948353041420400780739481342941381225525861407")};
    private static final int N_ELEMENT_BITS_HASH = PRIME.bitLength();

    private static final ECCurve starknetCurve;

    static {
        assert N_ELEMENT_BITS_HASH == 252;

        starknetCurve = new ECCurve.Fp(PRIME, ALPHA, BETA, EC_ORDER, null);
    }

//    pedersen
//    https://docs.starkware.co/starkex/crypto/pedersen-hash-function.html
//    𝑃0=(2089986280348253421170679821480865132823066470938446095505822317253594081284,1713931329540660377023406109199410414810705867260802078187082345529207694986)
//    𝑃1=(996781205833008774514500082376783249102396023663454813447423147977397232763, 1668503676786377725805489344771023921079126552019160156920634619255970485781)
//    𝑃2=(2251563274489750535117886426533222435294046428347329203627021249169616184184,1798716007562728905295480679789526322175868328062420237419143593021674992973)
//    𝑃3=(2138414695194151160943305727036575959195309218611738193261179310511854807447,113410276730064486255102093846540133784865286929052426931474106396135072156)
//    𝑃4=(2379962749567351885752724891227938183011949129833673362440656643086021394946,776496453633298175483985398648758586525933812536653089401905292063708816422)
    private static final BigInteger[][] PEDERSEN_POINTS = {
//            SHIFT_POINT
            {new BigInteger("2089986280348253421170679821480865132823066470938446095505822317253594081284"), new BigInteger("1713931329540660377023406109199410414810705867260802078187082345529207694986")},
//            P1 - P4
            {new BigInteger("996781205833008774514500082376783249102396023663454813447423147977397232763"), new BigInteger("1668503676786377725805489344771023921079126552019160156920634619255970485781")},
            {new BigInteger("2251563274489750535117886426533222435294046428347329203627021249169616184184"), new BigInteger("1798716007562728905295480679789526322175868328062420237419143593021674992973")},
            {new BigInteger("2138414695194151160943305727036575959195309218611738193261179310511854807447"), new BigInteger("113410276730064486255102093846540133784865286929052426931474106396135072156")},
            {new BigInteger("2379962749567351885752724891227938183011949129833673362440656643086021394946"), new BigInteger("776496453633298175483985398648758586525933812536653089401905292063708816422")},
    };


//
// shift_point + x_low * P_0 + x_high * P1 + y_low * P2  + y_high * P3
//export function pedersen(x: PedersenArg, y: PedersenArg): string {
//        let point: ProjectivePoint = PEDERSEN_POINTS[0];
//        point = pedersenSingle(point, x, PEDERSEN_POINTS1);
//        point = pedersenSingle(point, y, PEDERSEN_POINTS2);
//        return extractX(point.toRawBytes(true));
//    }

//    https://docs.starknet.io/documentation/architecture_and_concepts/Hashing/hash-functions/#pedersen_hash
//    𝐻(𝑎,𝑏)=[𝑃0+𝑎low⋅𝑃1+𝑎high⋅𝑃2+𝑏low⋅𝑃3+𝑏high⋅𝑃4]𝑥
    public static Felt pedersen(Felt a, Felt b) {

    }


    public static Felt pedersen() {
        String a = "1";
        String b = "3141592653589793238462643383279502884197169399375105820974944592307816406665";
        String p = "3618502788666131213697322783095070105623107215331596699973092056135872020481";

//        BigInteger aI = new BigInteger(a);
//        BigInteger bI = new BigInteger(b);
//        BigInteger pI = new BigInteger(p);
//
//        BigInteger xI = new BigInteger("874739451078007766457464989774322083649278607533249481151382481072868806602");
//        BigInteger yI = new BigInteger("152666792071518830868575557812948353041420400780739481342941381225525861407");
//
//        ECCurve ecCurve = new ECCurve.Fp(pI, aI, bI, null, null);
//        ECPoint point = ecCurve.createPoint(xI, yI);
//        System.out.println(point.isValid());


        return null;
    }
}
