package ssm.pojo.utilPojo;

/**
 * Created by wh-pc on 2017/8/21.
 * 用来保存上传图像的时候的图片截取数据。
 */
public class CropAvatar {
	private double x;
	private double y;
	private double height;
	private double width;
	private int rotate;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}
}
