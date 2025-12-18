package core;

public interface TestStep {
	TestResult execute(TestContext context) throws Exception;
    String getName();
}
