package app.ashy.deployer.processor.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

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
