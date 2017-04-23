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

package app.ashy.deployer.processor.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author Ashish
 *
 */
public class AppUtil {

	public static boolean isEmpty(String str) {
		if(str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean toBoolean(String str) {
		if(str.equalsIgnoreCase("true") || 
				str.equalsIgnoreCase("false")) {
			return Boolean.parseBoolean(str);
		} else {
			throw new IllegalArgumentException("Invalid boolean value.");
		}
	}
	
	public static EncodedResource getEncodedResource(String fileContent) {

		if(AppUtil.isEmpty(fileContent)) {
			throw new IllegalArgumentException("Empty content passed while creating resource.");
		}
		Resource resource = new ByteArrayResource(fileContent.getBytes());
		return new EncodedResource(resource, "UTF-8");
	}
}
