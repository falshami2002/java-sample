package core;

public class TestResult {
    private final TestStatus status;
    private final String message;
    private final long durationMillis;

    public TestResult(TestStatus status, String message, long durationMillis) {
        this.status = status;
        this.message = message;
        this.durationMillis = durationMillis;
    }

    public TestStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getDurationMillis() {
        return durationMillis;
    }
}

