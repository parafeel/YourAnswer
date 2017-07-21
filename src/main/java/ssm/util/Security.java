package ssm.util;

import com.sun.tools.javac.Main;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by wh-pc on 2017/7/20.
 * 此工具类为实现对String、
 */
public class Security {
	//下应该为盐值
	private static final String SITE_WIDE_SECRET = "wanghao";
	private static final PasswordEncoder encoder = new StandardPasswordEncoder(SITE_WIDE_SECRET);

	//静态工厂方法
	private Security() {}
	private static final Security security = new Security();
	public static Security getInstance() {
		return security;
	}

	//加密
	public static String encrypt(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	//验证是否匹配
	public static boolean match(String rawPassword, String password) {
		return encoder.matches(rawPassword, password);
	}

	/**看看加密效果
	public static void main(String[] args) {
		String ps = "123456";
		Security security = Security.getInstance();
		for(int i=0; i <20 ; i ++) {
			System.out.println(security.encrypt(ps));
		}
	}**/


}
