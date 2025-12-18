package exec;

import core.*;
import device.*;

public class TestSequenceRunner {

    private final TestStep step;

    public TestSequenceRunner(TestStep step) {
        this.step = step;
    }

    public TestResult runSingleSite(DeviceInterface device) throws Exception {
        TestContext ctx = new TestContext();
        ctx.put("device", device);
        ctx.put("siteId", 0);

        return step.execute(ctx);
    }
}
