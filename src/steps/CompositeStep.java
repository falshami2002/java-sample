package steps;
import core.*;

import java.util.ArrayList;
import java.util.List;

public class CompositeStep implements TestStep {

    private final String name;
    private final List<TestStep> steps = new ArrayList<>();

    public CompositeStep(String name) {
        this.name = name;
    }

    public void addStep(TestStep step) {
        steps.add(step);
    }

    @Override
    public TestResult execute(TestContext context) throws Exception {
        long start = System.currentTimeMillis();

        for (TestStep step : steps) {
            TestResult result;

            try {
                result = step.execute(context);
            } catch (Exception e) {
                long duration = System.currentTimeMillis() - start;
                return new TestResult(
                        TestStatus.ERROR,
                        "Exception in step: " + step.getName() + " -> " + e.getMessage(),
                        duration
                );
            }

            if (result.getStatus() != TestStatus.PASS) {
                long duration = System.currentTimeMillis() - start;
                return new TestResult(
                        result.getStatus(),
                        "Failure in step: " + step.getName() +
                        " (" + result.getMessage() + ")",
                        duration
                );
            }
        }

        long duration = System.currentTimeMillis() - start;
        return new TestResult(TestStatus.PASS,
                "All steps passed in composite",
                duration);
    }

    @Override
    public String getName() {
        return name;
    }
}
