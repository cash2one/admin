package com.jumper.angel.sociality.util;

import com.jumper.angel.utils.ConfigProUtils;


/**
 * 系统常量
* @ClassName: Constans 
* @Description: 
* @author caishiming
* @date 2016年10月17日 下午1:45:44
 */
public class Constans {
	
	 // 通道标识
    public static final String APP_CHANNEL = "talker";
    // 上线消息标识
    public static final String UP = "up";
    // 下线消息标识
    public static final String DOWN = "down";
    // 改名消息标识
    public static final String RENAME = "rename";
    
    public static final String VIDEO = "video";
    // 话语消息标识
    public static final String TALK = "talk";
    // 系统健康信息
    public static final String HEALTH = "health";
    //心跳标示
    public static final String ALIVE = "Alive";

	/** 成功 */
	public final static int SUCCESS = 0;
	
	/** 失败 */
	public final static int FAIL = 1;
	
	public final static String SUCCESS_MSG = "请求成功";
	
	public final static String FAIL_MSG = "请求失败";
	
	public final static String IM_SERVER_URL ="https://console.tim.qq.com/v4/openim/";
	
	/**
	 * UTF-8编码
	 */
	public final static String UTF8_CODE = "UTF-8";
	/**
	 * 单条消息推送 
	 **/
	public final static String SEND_MSG_URL = "https://console.tim.qq.com/v4/openim/sendmsg?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/**
	 * 导入账户
	 */
	public final static String ACCOUNT_IMPORT = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/** 修改群成员资料 */
	public final static String MODIFY_GROUP_MEMBER_INFO = "https://console.tim.qq.com/v4/group_open_http_svc/modify_group_member_info?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/** 创建群组 */
	public final static String CREATE_GROUP ="https://console.tim.qq.com/v4/group_open_http_svc/create_group?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/** 修改群组基本信息 */
	public final static String MODIFY_GROUP_BASE_INFO ="https://console.tim.qq.com/v4/group_open_http_svc/modify_group_base_info?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/** 获取群所有成员 */
	public final static String GET_GROUP_MEMBER_INFO ="https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/** 批量禁言和取消禁言 */
	public final static String FORBID_SEND_MSG ="https://console.tim.qq.com/v4/group_open_http_svc/forbid_send_msg?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/** 加入交流圈 */
	public final static String ADD_GROUP ="https://console.tim.qq.com/v4/group_open_http_svc/add_group_member?usersig="+ConfigProUtils.get("SIG")+"&identifier=admin&sdkappid="+ConfigProUtils.get("SDK_APPID")+"&random=0123456789&contenttype=json";
	
	/**
	 * 医生
	 */
	public final static String YS = "ys_";
	public final static String IS_DEAL_YS = "1";
	
	/**
	 * 用户
	 */
	public final static String YF = "yf_";
	public final static String IS_DEAL_YF = "0";
	/**
	 *  用户在线状态  1  在线   0 离线
	 */
	public final static String LOGIN_STATUS_ON_LINE = "1";
	public final static String LOGIN_STATUS_OFF_LINE = "0";

	/**
	 * 发送用户 标示   0未发   1 已发
	 */
	public final static String WHETHER_TO_SEND_N = "0";
	public final static String WHETHER_TO_SEND_Y = "1";

	/**
	 * 返回状态
	 */
	public final static int RT_MSG_SUCCESS = 1; //成功
	public final static int RT_MSG_FAIL= 0;//失败

	/**
	 * 是否已读
	 */
	public final static int READ_MARK_N =0; //未读
	public final static int READ_MARK_Y =1; //已读

	/*huangzg add*/
	public final static String SENDER = "sender";
	public final static String RECEIVER = "receiver";
	public final static String MSGTYPE = "msgType";
	public final static String TEXT_MSG_TYPE = "1";
	public final static String IMG_MSG_TYPE = "2";
	
	/**
	 * 数据初始值 0
	 */
	public final static int  INITIALIZATION_VALUE = 0;
	
	
	/**
	 * 腾讯云返回  目标账号不存在
	 */
	public final static String ACCOUNT_DOES_NOT_EXIST = "20003";
	
	/**
	 * 腾讯云返回  错误信息
	 */
	public final static String IM_ERROR_CODE = "ErrorCode";
	
	/**
	 * 腾讯云返回  错误码
	 */
	public final static String IM_ERROR_INFO = "ErrorInfo";
	
	/**
	 * 腾讯云请求处理的结果，OK表示处理成功，FAIL表示失败。
	 */
	public final static String IM_ACTION_STATUS = "ActionStatus";
	
	/**
	 * 语音消息。
	 */
	public final static String MSG_TYPE_SOUND = "TIMSoundElem";
	public final static String MSG_TYPE_SOUND_HINT = "系统提示：您的好友跟您发送了一条语音消息，请用手机查看";
	
	/**
	 * 消息类型 ：表情消息
	 */
	public final static String MSG_TYPE_FACE  = "TIMFaceElem";
	/**
	 * 消息类型 ：位置消息
	 */
	public final static String MSG_TYPE_LOCATION  = "TIMLocationElem";
	/**
	 * 消息类型 ：自定义消息
	 */
	public final static String MSG_TYPE_CUSTOM  = "TIMCustomElem";
	/**
	 * 消息类型 ：图片消息
	 */
	public final static String MSG_TYPE_IMG = "TIMImageElem";
	
	/**
	 * 消息类型 ：文字消息
	 */
	public final static String MSG_TYPE_TEXT = "TIMTextElem";
	
	/**
	 * 是否刪除 0 正常
	 */
	public final static int IS_DELETE_0 = 0;
	/**
	 * 是否刪除 1 禁用
	 */
	public final static int IS_DELETE_1= 1;//未刪除
	
	/**
	 * 是否刪除 2 删除
	 */
	public final static int IS_DELETE_2= 2;
	
	/**
	 * 是否刪除 0 正常
	 */
	public final static int QUANTITY_DEFAULT = 0;
	
	/**
	 * 发帖状态  0 正常
	 */
	public final static int POST_STATE_0 = 0;
	/**
	 * 发帖状态   1 禁止发帖
	 */
	public final static int POST_STATE_1 = 1;
	
	/**
	 * 帖子状态   0 正常
	 */
	public final static int TOPIC_STATE_0 = 0;
	/**
	 * 帖子状态  1 热门
	 */
	public final static int TOPIC_STATE_1 = 1;
	/**
	 * 话题操作状态 0 禁用
	 */
	public final static int TOPIC_OPERATION_TYPE_0 = 0;
	/**
	 * 话题操作状态 1 启用
	 */
	public final static int TOPIC_OPERATION_TYPE_1 = 1;
	/**
	 * 话题操作状态 2 设置热门
	 */
	public final static int TOPIC_OPERATION_TYPE_2 = 2;
	
	/**
	 * 话题操作状态 3 取消热门
	 */
	public final static int TOPIC_OPERATION_TYPE_3 = 3;
	
	/**
	 * 帖子操作类型
	 * 0 设置热门    1 取消热门 2 隐藏帖子   3 恢复帖子  4删除帖子
	 */
	public final static int POST_OPERATION_TYPE_0 = 0;  
	public final static int POST_OPERATION_TYPE_1 = 1;
	public final static int POST_OPERATION_TYPE_2 = 2;
	public final static int POST_OPERATION_TYPE_3 = 3;
	public final static int POST_OPERATION_TYPE_4 = 4;
	
	/**
	 * 点赞状态 
	 * 0 点赞   1 取消赞
	 */
	public final static int POINT_PRAISE_TYPE_0 = 0; 
	public final static int POINT_PRAISE_TYPE_1 = 1; 
	
	/**
	 * 数量 计算
	 * 1  加一    -1  减一
	 */
	public final static int NUMBER_PLUS_ONE=1;
	public final static int QUANTITY_MINUS_ONE=-1;
	
}
