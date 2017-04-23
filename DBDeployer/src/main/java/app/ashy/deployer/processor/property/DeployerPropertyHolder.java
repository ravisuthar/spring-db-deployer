/*
 * Copyright 2017 Ashish Gundewad

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package app.ashy.deployer.processor.property;

/**
 * @author Ashish
 *
 */
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
