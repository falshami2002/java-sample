package steps;
import core.*;
import device.DeviceInterface;

public class MeasurementStep implements TestStep {

    private final String name;
    private final String channel;
    private final double min;
    private final double max;

    public MeasurementStep(String name, String channel, double min, double max) {
        this.name = name;
        this.channel = channel;
        this.min = min;
        this.max = max;
    }

    @Override
    public TestResult execute(TestContext context) throws Exception {
        long start = System.currentTimeMillis();

        DeviceInterface device = context.get("device");
        double value = device.measure(channel);

        long duration = System.currentTimeMillis() - start;

        if (value < min || value > max) {
            return new TestResult(
                    TestStatus.FAIL,
                    "Measurement " + value +
                     " out of range (" + min + " .. " + max + ")",
                    duration
            );
        }

        return new TestResult(
                TestStatus.PASS,
                "Measurement " + value + " within range",
                duration
        );
    }

    @Override
    public String getName() {
        return name;
    }
}
