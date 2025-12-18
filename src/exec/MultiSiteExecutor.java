package exec;

import core.*;
import device.DeviceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiSiteExecutor {

    private final TestStep rootStep;
    private final Callable<DeviceInterface> deviceFactory;

    public MultiSiteExecutor(TestStep rootStep, Callable<DeviceInterface> deviceFactory) {
        this.rootStep = rootStep;
        this.deviceFactory = deviceFactory;
    }

    public List<TestResult> runMultiSite(int numSites) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(numSites);
        List<Future<TestResult>> futures = new ArrayList<>();

        for (int i = 0; i < numSites; i++) {
            final int siteId = i;
            futures.add(executor.submit(() -> {
                DeviceInterface dev = deviceFactory.call();
                TestContext ctx = new TestContext();
                ctx.put("device", dev);
                ctx.put("siteId", siteId);
                return rootStep.execute(ctx);
            }));
        }

        List<TestResult> results = new ArrayList<>();
        for (Future<TestResult> f : futures) {
            results.add(f.get());
        }

        executor.shutdown();
        return results;
    }
}
