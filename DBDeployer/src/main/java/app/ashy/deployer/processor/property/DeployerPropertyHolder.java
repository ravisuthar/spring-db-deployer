package app.ashy.deployer.processor.property;

public final class DeployerPropertyHolder {

	private static String scriptPath;
	private static boolean strictScan;

	private DeployerPropertyHolder() {
		scriptPath = "/";
	}

	public static String getScriptPath() {
		return scriptPath;
	}

	public static void setScriptPath(String scriptPath) {
		DeployerPropertyHolder.scriptPath = scriptPath;
	}

	public static boolean isStrictScan() {
		return strictScan;
	}

	public static void setStrictScan(boolean strictScan) {
		DeployerPropertyHolder.strictScan = strictScan;
	}
}
