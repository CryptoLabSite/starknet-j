package site.hellooo.starknetj.utils;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
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


//    pedersen
    private static final ECPoint[] PEDERSEN_POINTS;
    private static final BigInteger LOW_PART_BITS = BigInteger.valueOf(248);
    private static final BigInteger LOW_PART_MASK = BigInteger.ONE.shiftLeft(248).subtract(BigInteger.ONE);

    static {
        assert N_ELEMENT_BITS_HASH == 252;

        starknetCurve = new ECCurve.Fp(PRIME, ALPHA, BETA, EC_ORDER, BigInteger.ONE);

//        https://docs.starkware.co/starkex/crypto/pedersen-hash-function.html
//        𝑃0=(2089986280348253421170679821480865132823066470938446095505822317253594081284,1713931329540660377023406109199410414810705867260802078187082345529207694986)
//        𝑃1=(996781205833008774514500082376783249102396023663454813447423147977397232763, 1668503676786377725805489344771023921079126552019160156920634619255970485781)
//        𝑃2=(2251563274489750535117886426533222435294046428347329203627021249169616184184,1798716007562728905295480679789526322175868328062420237419143593021674992973)
//        𝑃3=(2138414695194151160943305727036575959195309218611738193261179310511854807447,113410276730064486255102093846540133784865286929052426931474106396135072156)
//        𝑃4=(2379962749567351885752724891227938183011949129833673362440656643086021394946,776496453633298175483985398648758586525933812536653089401905292063708816422)
//        SHIFT_POINT
        ECPoint P0 = starknetCurve.createPoint(new BigInteger("2089986280348253421170679821480865132823066470938446095505822317253594081284"), new BigInteger("1713931329540660377023406109199410414810705867260802078187082345529207694986"));
        ECPoint P1 = starknetCurve.createPoint(new BigInteger("996781205833008774514500082376783249102396023663454813447423147977397232763"), new BigInteger("1668503676786377725805489344771023921079126552019160156920634619255970485781"));
        ECPoint P2 = starknetCurve.createPoint(new BigInteger("2251563274489750535117886426533222435294046428347329203627021249169616184184"), new BigInteger("1798716007562728905295480679789526322175868328062420237419143593021674992973"));
        ECPoint P3 = starknetCurve.createPoint(new BigInteger("2138414695194151160943305727036575959195309218611738193261179310511854807447"), new BigInteger("113410276730064486255102093846540133784865286929052426931474106396135072156"));
        ECPoint P4 = starknetCurve.createPoint(new BigInteger("2379962749567351885752724891227938183011949129833673362440656643086021394946"), new BigInteger("776496453633298175483985398648758586525933812536653089401905292063708816422"));

        PEDERSEN_POINTS = new ECPoint[]{
                P0, P1, P2, P3, P4
        };
    }

//    https://github.com/starkware-libs/cairo-lang/blob/master/src/starkware/crypto/signature/fast_pedersen_hash.py
//    https://docs.starknet.io/documentation/architecture_and_concepts/Hashing/hash-functions/#pedersen_hash
//    𝐻(𝑎,𝑏)=[𝑃0+𝑎low⋅𝑃1+𝑎high⋅𝑃2+𝑏low⋅𝑃3+𝑏high⋅𝑃4]𝑥
    public static Felt pedersen(Felt a, Felt b) {
        ECPoint shiftPoint = PEDERSEN_POINTS[0];
        ECPoint p1 = PEDERSEN_POINTS[1];
        ECPoint p2 = PEDERSEN_POINTS[2];
        ECPoint p3 = PEDERSEN_POINTS[3];
        ECPoint p4 = PEDERSEN_POINTS[4];

        ECPoint resultPoint = shiftPoint.add(processSingleElement(a.getValue(), p1, p2)).add(processSingleElement(b.getValue(), p3, p4));
        return new Felt(resultPoint.getXCoord().toBigInteger());
    }

    public static ECPoint processSingleElement(BigInteger element, ECPoint p1, ECPoint p2) {
        assert element.compareTo(BigInteger.ZERO) >= 0 && element.compareTo(PRIME) < 0 : "Element integer value is out of range";
        BigInteger highNibble = element.shiftRight(LOW_PART_BITS.intValue());
        BigInteger lowPart = element.and(LOW_PART_MASK);

        ECPoint lp = p1.multiply(lowPart);
        ECPoint hp = p2.multiply(highNibble);
        ECPoint r = lp.add(hp);
        return r;
    }

    public static void main(String[] args) {
        BigInteger ori = BigInteger.valueOf(1112123);
        BigInteger shif = ori.shiftRight(1);
        System.out.println("a: " + ori.toString(10));
        System.out.println("b: " + shif.toString(10));

        ECPoint point = starknetCurve.createPoint(EC_GENERATOR_POINT[0], EC_GENERATOR_POINT[1]);
        System.out.println(point.isValid());

//        h(1, 2) should equals 0x5bb9440e27889a364bcb678b1f679ecd1347acdedcbf36e83494f857cc58026
        System.out.println(pedersen(new Felt(1), new Felt(2)));
//        BigInteger b = new BigInteger("2592987851775965742543459319508348457290966253241455514226127639100457844774");
//        System.out.println(b.toString(16));
    }
}
