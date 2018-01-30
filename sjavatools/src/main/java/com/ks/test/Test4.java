package com.ks.test;
/**
 * @author pks
 * @version 2018年1月26日
 */
public class Test4 {
	public static void main(String[] args) {
		int index = 1;
		Double s = 0.0;
		int days = 60;
		double lv = 1.1;
		for(int n = 1; n <= 50;n++){
			index = n;
			s = 0.0;
			for (int i = 1; i <= 50; i++) {
				if( index > i ){
					s += -(2000 + 2000/10000.0*lv*days+s/10000.0*lv*days);
				}else if( index == i ){
					if(index==1){
						s += 100000+100000/10000.0*lv*days;
					}else{
						s += (100000+(i-1)*200) + (100000+(i-1)*200)/10000.0*lv*days+s/10000.0*lv*days;
					}
				}else{
					if(index==1){
						s += -(2000+ 2000/10000.0*lv*days)+s/10000.0*lv*days;
					}else{
						s += -(2200+ 2200/10000.0*lv*days)+s/10000.0*lv*days;
					}
				}
//				System.out.println(i+":"+s);
			}
//			System.out.println("-------------------------------------");
			System.out.println(n+":"+s);
		}
	}
}

