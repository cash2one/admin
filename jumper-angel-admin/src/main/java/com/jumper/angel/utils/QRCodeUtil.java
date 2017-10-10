package com.jumper.angel.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCodeUtil {

	/**
	 * @param contents   二维码内容
	 * @param imgPath    二维码存储路径
	 * @param logoPath   logo图片路径
	 * @param qrcodeVersion  // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
	 */
	public static void getQrcodeImgByContents(String contents, String imgPath, String logoPath,int qrcodeVersion) {
		// 图片尺寸     
        int imgSize = 67 + 12 * (qrcodeVersion - 1);  
		try {
			Qrcode qrcode = new Qrcode();
		    // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
			qrcode.setQrcodeErrorCorrect('M');
			qrcode.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcode.setQrcodeVersion(qrcodeVersion);
			byte[] contentBytes = contents.getBytes("UTF-8");

			BufferedImage bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);
			gs.setColor(Color.BLACK);
			//偏移量
			int pixoff = 2;

			if (contentBytes.length != 0 && contentBytes.length < 600) {
				boolean[][] codeOut = qrcode.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.out.println("大小超出限制");
			}
			Image img = ImageIO.read(new File(logoPath));	//实例化一个Image对象。
			//int width = img.getWidth(null);  
	        //int height = img.getHeight(null);  
	       /* boolean needCompress = true;
	        if (needCompress) { // 压缩LOGO  
*/	        /*Image image2 = img.getScaledInstance(width, height,Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image2, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            img = image2;  */
	        //} 
	        
			//int x = (imgSize - img.getWidth(null)) / 2; 
            //int y = (imgSize - img.getHeight(null)) / 2;
            
            gs.drawImage(img, 45, 45, 50,50, null);
			gs.dispose();
			bufImg.flush();
			img.flush();
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String content= "29081933423445";
		Date date = new Date();
		getQrcodeImgByContents(content, "", date.getTime()+".png",20);
	}
}
