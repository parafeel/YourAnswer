package ssm.util;

/**
 * Created by wh-pc on 2017/8/2.
 */
public class UserRelation {
	public final static byte RELATION_NONE = -10;			//未发生关系
	public final static byte RELATION_FOLLOW = 10;			//主动关注
	public final static byte RELATION_UNFOLLOW = 00;		//取消关注

	public final static byte RELATION_FOLLOWED = 11;		//被动被关注
	public final static byte RELATION_BLACKLIST = 20;		//主动拉黑
	public final static byte RELATION_BLACKLISTED = 21;		//被动被拉黑拉黑

	public final static byte RELATION_ISSELF = -1;		//自己
	public final static byte RELATION_UNLOAD = -5;		//未登录
}
