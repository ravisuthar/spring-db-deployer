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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.ashy.deployer.processor.enums.FileMeta;
import app.ashy.deployer.processor.property.DeployerPropertyHolder;

/**
 * @author Ashish
 *
 */
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
