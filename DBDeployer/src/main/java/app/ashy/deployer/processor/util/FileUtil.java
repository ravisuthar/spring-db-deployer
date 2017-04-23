package app.ashy.deployer.processor.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.ashy.deployer.processor.enums.FileMeta;
import app.ashy.deployer.processor.property.DeployerPropertyHolder;

public class FileUtil {

	public static String getScriptFolderPath() {
		return DeployerPropertyHolder.getScriptPath();
	}

	public static Map<FileMeta, String> getFileMetadata(String fileName) {
		String[] fileMeta = fileName.split("_");
		Map<FileMeta, String> fileMetaMap = new HashMap<FileMeta, String>();
		fileMetaMap.put(FileMeta.FILE_NUM, fileMeta[0]);
		fileMetaMap.put(FileMeta.FILE_TYPE, fileMeta[1]);
		fileMetaMap.put(FileMeta.FILE_OPER, fileMeta[2]);
		fileMetaMap.put(FileMeta.FILE_NAME, fileMeta[3]);
		return fileMetaMap;
	}
	
	public static String getFileNameWithoutExtensiton(File file) {
		String fileName = file.getName();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0) {
		    fileName = fileName.substring(0, pos);
		}
		return fileName;
	}
}
