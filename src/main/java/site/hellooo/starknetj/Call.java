package site.hellooo.starknetj;

import java.util.List;

public class Call {
    private Felt contractAddress;
    private Felt entrypoint;
    private List<Felt> calldata;

    public Felt getContractAddress() {
        return contractAddress;
    }

    public Felt getEntrypoint() {
        return entrypoint;
    }

    public List<Felt> getCalldata() {
        return calldata;
    }
}
