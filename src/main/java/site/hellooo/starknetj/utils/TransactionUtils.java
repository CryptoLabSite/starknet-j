package site.hellooo.starknetj.utils;

import site.hellooo.starknetj.Call;
import site.hellooo.starknetj.Felt;

import java.util.LinkedList;
import java.util.List;

public class TransactionUtils {
    public static List<Felt> callsToExecuteCalldata(List<Call> calls) {
//        todo is method of cairo1 different?

        List<Felt> callArray = new LinkedList<>();
        List<Felt> calldata = new LinkedList<>();

        for (Call call : calls) {
            callArray.add(call.getContractAddress()); // to
            callArray.add(call.getEntrypoint()); // selector
            callArray.add(new Felt(calldata.size())); // offset
            callArray.add(new Felt(call.getCalldata().size())); // len

            calldata.addAll(call.getCalldata());
        }

        List<Felt> result = new LinkedList<>();
        result.add(new Felt(calls.size()));
        result.addAll(callArray);
        result.add(new Felt(calldata.size()));
        result.addAll(calldata);

        return result;
    }
}
