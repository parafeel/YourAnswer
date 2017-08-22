package ssm.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by wh-pc on 2017/8/21.
 */
public class OperateImage {

	public OperateImage() {
		super();
	}

	/**
	 * 对图片裁剪，并把裁剪新图片保存
	 *
	 * @param srcPath          读取源图片路径
	 * @param toPath           写入图片路径
	 * @param x                剪切起始点x坐标
	 * @param y                剪切起始点y坐标
	 * @param width            剪切宽度
	 * @param height           剪切高度
	 * @param readImageFormat  读取图片格式
	 * @param writeImageFormat 写入图片格式
	 * @throws IOException
	 */
	public static void cropImage(String srcPath, String toPath, double x, double y, double width, double height, String
			readImageFormat, String writeImageFormat) throws IOException {
		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			//读取图片文件
			fis = new FileInputStream(srcPath);
			Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat);
			ImageReader reader = (ImageReader) it.next();
			//获取图片流
			iis = ImageIO.createImageInputStream(fis);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			//定义一个矩形
			Rectangle rect = new Rectangle((int)x, (int)y, (int)width, (int)height);
			//提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			//保存新图片
			ImageIO.write(bi, writeImageFormat, new File(toPath));
		} finally {
			if (fis != null) fis.close();
			if (iis != null) iis.close();
		}
	}

	/**
	 * 按倍率缩小图片
	 *
	 * @param srcImagePath 读取图片路径
	 * @param toImagePath  写入图片路径
	 * @param widthRatio   宽度缩小比例
	 * @param heightRatio  高度缩小比例
	 * @throws IOException
	 */
	public static void reduceImageByRatio(String srcImagePath, String toImagePath, int widthRatio, int heightRatio) throws
			IOException {
		FileOutputStream out = null;
		try {
			//读入文件
			File file = new File(srcImagePath);
			// 构造Image对象
			BufferedImage src = javax.imageio.ImageIO.read(file);
			int width = src.getWidth();
			int height = src.getHeight();
			// 缩小边长
			BufferedImage tag = new BufferedImage(width / widthRatio, height / heightRatio, BufferedImage.TYPE_INT_RGB);
			// 绘制 缩小  后的图片
			tag.getGraphics().drawImage(src, 0, 0, width / widthRatio, height / heightRatio, null);
			out = new FileOutputStream(toImagePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}