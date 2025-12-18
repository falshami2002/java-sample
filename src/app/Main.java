package app;

import steps.*;
import device.*;
import core.*;
import exec.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        CompositeStep flow = new CompositeStep("Power-On");
        flow.addStep(new DelayStep("Wait", 500));
        flow.addStep(new MeasurementStep("Check VDD", "VDD", 1.1, 1.3));
        flow.addStep(new MeasurementStep("Check VSS", "VSS", -0.05, 0.05));

        TestSequenceRunner runner = new TestSequenceRunner(flow);

        TestResult result = runner.runSingleSite(new SimulatedDevice());

        System.out.println(result.getStatus());
        System.out.println(result.getMessage());
        System.out.println(result.getDurationMillis());
        
        MultiSiteExecutor exec = new MultiSiteExecutor(
        		flow,
        		() -> new SimulatedDevice()
        );

        List<TestResult> results = exec.runMultiSite(4);

        for (int i = 0; i < results.size(); i++) {
        	System.out.println("Site " + i + ": " + results.get(i).getStatus());
        }
    }
}
