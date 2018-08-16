/**
 * 
 */
package com.ks.ocr;

import java.io.File;

import com.ks.utils.image.ImageUtils;

/**
 * @date 2018年8月9日
 * @author pks
 *
 */
public class Test2 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String dir_path = "d:/data";
		String bak_path = "d:/data_bak/";
		File dir = new File(dir_path);
		String[] paths = dir.list();
		for (String path : paths) {
			String name = path.substring(0, path.lastIndexOf("."));
			System.out.println(name);
			ImageUtils.scale2(dir_path+"/"+path, bak_path+name+".jpg", 300, 500, true);
			ImageUtils.cut(bak_path+name+".jpg", bak_path+name+"_name"+".jpg", 100, 54, 140, 33);
			ImageUtils.cut(bak_path+name+".jpg", bak_path+name+"_card"+".jpg", 155, 240, 250, 36);
			String s = TesseractOCRUtil.recognizeText(new File(bak_path+name+"_name"+".jpg"));
			System.out.println(s);
			s = TesseractOCRUtil.recognizeText(new File(bak_path+name+"_card"+".jpg"));
			System.out.println(s);
		}
//		ImageUtils.cut("d:/abc_scale2.jpg", "d:/abc_cut_name.jpg", 100, 54, 140, 33);
//		ImageUtils.cut("d:/abc_scale2.jpg", "d:/abc_cut_idcard.jpg", 155, 240, 250, 36);
//		String s = TesseractOCRUtil.recognizeText(new File("d:/abc_cut_name.jpg"));
//		System.out.println(s);
//		s = TesseractOCRUtil.recognizeText(new File("d:/abc_cut_idcard.jpg"));
//		System.out.println(s);
	}
}
