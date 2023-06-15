package site.hellooo.starknetj;

public enum TransactionType {
    DECLARE(Felt.fromHexString("0x6465636c617265")), // encodeShortString('declare'),
    DEPLOY(Felt.fromHexString("0x6465706c6f79")), // encodeShortString('deploy'),
    DEPLOY_ACCOUNT(Felt.fromHexString("0x6465706c6f795f6163636f756e74")), // encodeShortString('deploy_account'),
    INVOKE(Felt.fromHexString("0x696e766f6b65")), // encodeShortString('invoke'),
    L1_HANDLER(Felt.fromHexString("0x6c315f68616e646c6572")); // encodeShortString('l1_handler'),

    private Felt txPrefix;

    TransactionType(Felt txPrefix) {
        this.txPrefix = txPrefix;
    }

    public Felt getTxPrefix() {
        return this.txPrefix;
    }
}
