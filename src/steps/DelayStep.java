package steps;
import core.*;

public class DelayStep implements TestStep {

    private final String name;
    private final long millis;

    public DelayStep(String name, long millis) {
        this.name = name;
        this.millis = millis;
    }

    @Override
    public TestResult execute(TestContext context) throws Exception {
        long start = System.currentTimeMillis();

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            long duration = System.currentTimeMillis() - start;
            return new TestResult(
                    TestStatus.ERROR,
                    "Interrupted while waiting",
                    duration
            );
        }

        long duration = System.currentTimeMillis() - start;
        return new TestResult(
                TestStatus.PASS,
                "Waited " + millis + " ms",
                duration
        );
    }

    @Override
    public String getName() {
        return name;
    }
}
