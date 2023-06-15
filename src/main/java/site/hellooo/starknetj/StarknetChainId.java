package site.hellooo.starknetj;

public enum StarknetChainId {
    MAINNET(Felt.fromHexString("0x534e5f4d41494e")), // encodeShortString('SN_MAIN'),
    TESTNET(Felt.fromHexString("0x534e5f474f45524c49")), // encodeShortString('SN_GOERLI'),
    TESTNET2(Felt.fromHexString("0x534e5f474f45524c4932")); // encodeShortString('SN_GOERLI2'),

    private Felt chainId;

    StarknetChainId(Felt chainId) {
        this.chainId = chainId;
    }

    public Felt getValue() {
        return this.chainId;
    }
}
