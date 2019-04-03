package com.creatorblue.cbitedu.core.constants;

public class Constant {
	/**
	 * 加密秘钥
	 */
	public static final String PASSWORD = "123478";
	/**
	 * 平台默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 批量新增、修改、删除多少条记录
	 */
	public static final int BATCH_DEAL_NUM = 50;

	/**
	 * 用户在线状态，1为在线，2为不在线
	 */
	public static final String SYS_PARAM_ISONLINE = "isonline";

	/**
	 * 用户状态
	 */
	public static final String SYS_PARAM_USERSTATE = "userState";

	/**
	 * 停用状态
	 */
	public static final String ORG_STATUS_STOPED = "00";

	/**
	 * 正常状态
	 */
	public static final String ORG_STATUS_NORMAL = "01";
	public static String SYS_PAGE_SIZE = "pageSize";
	public static String SYS_DEFAULT_PAGE_SIZE = "15";
	public static String SYS_PAGE_NO = "pageNo";
	public static String SYS_PAGE_ITMECOUNT = "itemCount";
	public static String SYS_WEB_PATH = "";
	public static final String USER_INFO = "userinfo"; // 登录的用户信息
	public static final String ADMIN_INIT_PWD = ""; // 系统初始化时管理员登录的初始密码
	public static final String USER_ID = "userId"; // 登录用户Id
	public static final String USER_NAME = "userName"; // 登录用户姓名
	public static final String USER_DEFAULT_ORG = "defaultOrg"; // 登录用户的默认单位
	public static final String USER_DEFAULT_ROLE = "defaultRole"; // 登录用户的默认角色
	public static final String USER_PRIVILEGES_DATA = "userPrivilegesData"; // 用户的所有权限：角色权限＋用户特权
	public static final String MODULE_NO = "moduleNo"; // 模块定义
	public static final String OPERATE_NO = "operateNo"; // 操作定义
	public static final String PAGE_OPERATE_MAP = "pageOperateMap"; // 用户一个页面中所包含的所有有权限的操作定义
	public static final String SYS_BASEINFO = "sysbaseinfo"; // 基本参数定义
	public static final String PAGE_NO = "pageNo";
	public static final String YES = "Y";
	public static final String NO = "N";

	// 框架初始化信息定义
	// 4.默认管理员信息定义
	public static final String ADMIN_USER_LOGINID = "admin"; //
	public static final String ADMIN_USER_PASSWD = "123478";
	public static final String LOGINACTION = "UserverifyAction";
	public static final String METHODLOGIN = "login";
	public static final String METHODLOGINOFF = "logOff";
	public static final String CUR_ORGID = "curOrgId";
	public static final String ALL_ORGS = "allOrgs";
	/**
	 * 注：此处状态字段可用于多个 不仅仅指文章的删除 其余状态字段常量值 都可引用此处 状态常量 文章状态 状态1待审2已发布 3删除
	 * 
	 */
	public static final String STATUS_FLAG_NORMAL = "0";
	public static final String STATUS_FLAG_WAITAUDIT = "1";
	public static final String STATUS_FLAG_PUBLISH = "2";
	public static final String STATUS_FLAG_DELETE = "3";

	public static final String DELETE_SUSSESS_MSG = "删除成功";
	public static final String DELETE_SUSSESS_FLAG = "true";
	public static final String DELETE_FAILED_MSG = "删除失败";
	public static final String DELETE_FAILED_FLAG = "false";

	public static final String DATA_DELETE_FLAG = "1";// 数据删除标志位 代表数据已经删除的状态位

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	public static enum DATA_SCOPE {
		ALL("所有数据", "1"), OFFICE_AND_CHILD("所在部门及以下数据",
				"4"), OFFICE("所在部门数据", "5"), SELF(
				"仅本人数据", "8"), CUSTOM("按明细设置", "9");
		String name;
		String value;

		private DATA_SCOPE(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		public String toString() {
			return this.name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	};

	// 不可以外部实例化该类
	private Constant() {
	}
}
