package com.ks.barcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * 条码测试
 * @author pks
 * @version 2018年1月10日
 */
public class Test {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024*1024);
		ByteArrayInputStream inputStream;
		ZxingEAN13EncoderHandler.encode("sdfgjsdfgrgegsjfkg", 105, 50, out);
		inputStream = new ByteArrayInputStream(out.toByteArray());
		byte[] buf = new byte[inputStream.available()];
		while ((inputStream.read(buf)) != -1)
		{
			//
		}
		ByteImageUtil.byte2image(buf, "F:/test.png");
		String s = ZxingEAN13DecoderHandler.decode("F:/test.png");
		System.out.println(s);
	}
}

