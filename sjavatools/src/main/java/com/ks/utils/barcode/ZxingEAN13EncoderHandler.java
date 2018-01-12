package com.ks.utils.barcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成条码图片
 * @author pks
 * @date 2018年1月12日
 */
public class ZxingEAN13EncoderHandler {
	
	public static void encode(String contents,int width,int height,OutputStream stream){
		int codeWidth = 3 + // start guard
				      (7 * 6) + // left bars
				      5 + // middle guard
				      (7 * 6) + //right bars
				      3; // end guard
		codeWidth = Math.max(codeWidth, width);
		
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			//指定编码格式
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			//MatrixToImageConfig config  = new MatrixToImageConfig();
			//指定纠错级别(L--7%,M--15%,Q--25%,H--30%) 
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			//
			//hints.put(EncodeHintType.AZTEC_LAYERS, 15);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(new String(contents.getBytes("utf-8")), BarcodeFormat.CODE_128, codeWidth, height,hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static void encode(String contents,int width,int height,String path){
		File file = new File(path);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			encode(contents,width,height,out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(null != out){
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	

}
