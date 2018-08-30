package com.ks.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author zwp
 * @date 创建时间：2017年3月24日 下午5:12:12
 * @version 2.0
 * @parameter
 * @since
 * @return
 */
public class TesseractOCRUtil1 {

	private final String LANG_OPTION = "-l";
	private final String EOL = System.getProperty("line.separator");
	
	/**
	 * @param imageFile
	 *            传入的图像文件
	 * @param imageFormat
	 *            传入的图像格式
	 * @return 识别后的字符串
	 */
	public String recognizeText(File imageFile) throws Exception {
		/**
		 * 设置输出文件的保存的文件目录
		 */
		File outputFile = new File(imageFile.getParentFile(), "output");

		StringBuffer strB = new StringBuffer();
		List<String> cmd = new ArrayList<String>();

		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			cmd.add("tesseract");
		} else {
			cmd.add("tesseract");
		}
		cmd.add(imageFile.getName());
		cmd.add(outputFile.getName());
		cmd.add("digits");
		ProcessBuilder pb = new ProcessBuilder();

		pb.directory(imageFile.getParentFile());
		pb.command(cmd);
		pb.redirectErrorStream(true);
		Process process = pb.command("C:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe",imageFile.getName(),outputFile.getName(),LANG_OPTION,"chi_sim").start();
		int w = process.waitFor();
		if (w == 0)// 0代表正常退出
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile.getAbsolutePath() + ".txt"), "UTF-8"));
			String str;

			while ((str = in.readLine()) != null) {
				strB.append(str).append(EOL);
			}
			in.close();
		} else {
			String msg;
			switch (w) {
			case 1:
				msg = "Errors accessing files. There may be spaces in your image's filename.";
				break;
			case 29:
				msg = "Cannot recognize the image or its selected region.";
				break;
			case 31:
				msg = "Unsupported image format.";
				break;
			default:
				msg = "Errors occurred.";
			}
			throw new RuntimeException(msg);
		}
		return strB.toString();
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * Tesseract("d:\\2.png"); Tesseract("/2.png"); Tesseract("/3.png");
	 * Tesseract("/4.png"); Tesseract("/5.png"); Tesseract("/6.png"); }
	 */
	public static void Tesseract(String fileString) {
		try {
			// String filePath = Test.class.getResource(fileString).getFile().toString();
			// processImg(filePath);
			File file = new File(fileString);
			String recognizeText = new TesseractOCRUtil1().recognizeText(file);
			System.out.println(recognizeText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args)  {
		Tesseract("d:\\4444.jpg");
//		test("d:\\\\3333.jpg");
	}
}