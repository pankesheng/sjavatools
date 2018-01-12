package com.ks.utils.file;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * 文件打包工具类
 * @author Administrator
 *
 */
public class FileToZip {
	private File zipFile;

	/**
	 * 压缩文件构造函数
	 * 
	 * @param pathName
	 *            最终压缩生成的压缩文件：目录+压缩文件名.zip
	 */
	public FileToZip(String finalFile) {
		zipFile = new File(finalFile);
	}

	/**
	 * 执行压缩操作
	 * 
	 * @param srcPathName
	 *            需要被压缩的文件/文件夹
	 */
	public void compressExe(String srcPathName) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists()) {
			srcdir.mkdirs();
			// throw new RuntimeException(srcPathName + "不存在！");
		}

		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		// fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹
		// eg:zip.setIncludes("*.java");
		// fileSet.setExcludes(...); //排除哪些文件或文件夹
		zip.addFileset(fileSet);
		zip.execute();
	}

	public static void removeAll(String filepath) {
		try {
			delAllFile(filepath); // 删除完里面所有内容
			String filePath = filepath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean delAllFile(String filepath) {
		boolean flag = false;
		File file = new File(filepath);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (filepath.endsWith(File.separator)) {
				temp = new File(filepath + tempList[i]);
			} else {
				temp = new File(filepath + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(filepath + "/" + tempList[i]);// 先删除文件夹里面的文件
				removeAll(filepath + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

}
