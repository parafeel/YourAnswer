package ssm.util;

/**
 * Created by wh-pc on 2017/7/31.
 */
public class StatusCode {
	//用户操作
	public final static byte TYPE_QUESTION = 100;	//问题
	public final static byte TYPE_ANSWER = 101;		//答案
	public final static byte TYPE_ESSAY = 102;		//随笔
	public final static byte TYPE_SHARE = 103;		//分享

	public final static String  FOCUS_QUESTION = "关注了问题";		//下级动作
	public final static String  ANSWER_QUESTION = "回答了问题";
	public final static String  AGREE_ANSWER = "赞同了回答";
	public final static String  PUBLISH_ESSAY = "发表了随笔";
	public final static String  AGREE_ESSAY = "赞了随笔";
	public final static String  PUBLISH_SHARE = "分享了";
	public final static String  AGREE_SHARE = "赞了分享";


	//Json状态码
	public final static int CODE_SUCCESS=200;	//成功
	public final static int CODE_FAILURE=400;	//失败
	public final static int CODE_DUPLICATE=222;	//重复

	public final static String REASON_SUCCESS="成功";
	public final static String REASON_FAILURE="失败";


}
