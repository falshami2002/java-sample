package device;

import java.util.Random;

public class SimulatedDevice implements DeviceInterface {

    private final Random random = new Random();

    @Override
    public double measure(String channel) {
        // simulate different channels with different “signals”
        switch (channel) {
            case "VDD": return 1.1 + random.nextDouble() * 0.1;
            case "VSS": return random.nextDouble() * 0.1 - 0.05;
            default: return random.nextDouble();
        }
    }
}
