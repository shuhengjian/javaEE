/*==============================================================*/
/* Table: T_SYS_APP (多应用、多子系统定义)                                           */
/*==============================================================*/
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists T_SYS_APP cascade ;
create table T_SYS_APP  (
   APP_ID               VARCHAR(32)                    not null,
   APP_NAME             VARCHAR(160),
   APP_ICON             VARCHAR(20),
   ORDER_NO             INT,
   APP_PATH             VARCHAR(200),
   APP_SHORTNAME        VARCHAR(30),
   constraint PK_T_SYS_APP primary key (APP_ID)
);
/*==============================================================*/
/* Table: T_SYS_MODULE  系统模块(菜单)表                                        */
/*==============================================================*/
drop table if exists T_SYS_MODULE cascade ;
create table T_SYS_MODULE  (
   MODULE_ID            VARCHAR(32)                    not null,
   APP_ID               VARCHAR(32),
   MODULE_NAME          VARCHAR(40),
   LINK_ADDR            VARCHAR(300),
   MODULE_CLASS         INT,
   ICON                 VARCHAR(100),
   MODULE_CODE          VARCHAR(60),
   PARENT_MODULEID      VARCHAR(32),
   SORT_NUM             INT,
   REMARK               VARCHAR(200),
   DISPLAY              VARCHAR(2),
   constraint PK_T_SYS_MODULE primary key (MODULE_ID)
);

alter table T_SYS_MODULE
   add constraint FK_T_SYS_MO_REFERENCE_T_SYS_AP foreign key (APP_ID)
      references T_SYS_APP (APP_ID);
/*==============================================================*/
/* Table: T_SYS_MODULEOPERATE模块下的操作定义                                  */
/*==============================================================*/
drop table if exists T_SYS_MODULEOPERATE cascade ;
create table T_SYS_MODULEOPERATE  (
   OPERATE_ID           VARCHAR(32)                    not null,
   MODULE_ID            VARCHAR(32),
   OPERATE_NAME         VARCHAR(100),
   OPERATE_CODE         VARCHAR(30),
   constraint PK_T_SYS_MODULEOPERATE primary key (OPERATE_ID)
);
alter table T_SYS_MODULEOPERATE
   add constraint FK_T_SYS_MO_REFERENCE_T_SYS_MO foreign key (MODULE_ID)
      references T_SYS_MODULE (MODULE_ID);
 drop table if exists T_SYS_POST cascade ;

/*==============================================================*/
/* Table: T_SYS_POST   岗位表                                         */
/*==============================================================*/
create table T_SYS_POST  (
   POST_ID              VARCHAR(32)                    not null,
   POST_NAME            VARCHAR(100),
   REMARK               VARCHAR(200),
   POST_TYPE            VARCHAR(4),
   PARENT_POSTID        VARCHAR(32),
   constraint PK_T_SYS_POST primary key (POST_ID)
);

drop table if exists T_SYS_ROLE cascade ;

/*==============================================================*/
/* Table: T_SYS_ROLE   角色表                                         */
/*==============================================================*/
create table T_SYS_ROLE  (
   ROLE_ID              VARCHAR(32)                    not null,
   ROLE_NAME            VARCHAR(100)                   not null,
   ROLE_TYPE            VARCHAR(2),
   REMARK               VARCHAR(255),
   ORG_ID               VARCHAR(32),
   CREATOR              VARCHAR(32),
   CREATE_DATE          VARCHAR(20),
   DATA_SCOPE           VARCHAR(2),
   constraint PK_T_SYS_ROLE primary key (ROLE_ID)
);


drop table if exists T_ROLE_POST cascade ;

/*==============================================================*/
/* Table: T_ROLE_POST   岗位角色关联表                                        */
/*==============================================================*/
create table T_ROLE_POST  (
   ROLE_POSTID          VARCHAR(32)                    not null,
   ROLE_ID              VARCHAR(32),
   POST_ID              VARCHAR(32),
   constraint PK_T_ROLE_POST primary key (ROLE_POSTID)
);

alter table T_ROLE_POST
   add constraint FK_T_ROLE_P_REFERENCE_T_SYS_RO foreign key (ROLE_ID)
      references T_SYS_ROLE (ROLE_ID);

alter table T_ROLE_POST
   add constraint FK_T_ROLE_P_REFERENCE_T_SYS_PO foreign key (POST_ID)
      references T_SYS_POST (POST_ID);
drop table if exists T_SYS_USERINFO cascade ;

/*==============================================================*/
/* Table: T_SYS_USERINFO        用户基本信息表                                */
/*==============================================================*/
create table T_SYS_USERINFO  (
   USER_ID              VARCHAR(32)                    not null,
   LOGIN_NAME           VARCHAR(30)                    not null,
   PASSWORD             VARCHAR(40)                    not null,
   USER_REALNAME        VARCHAR(100)                   not null,
   USER_NO              VARCHAR(100),
   ORG_ID               VARCHAR(32),
   DEPT_ID              VARCHAR(32),
   SEX                  VARCHAR(2),
   MOBILETEL            VARCHAR(14),
   FAX                  VARCHAR(20),
   EMAIL                VARCHAR(100),
   ZIP                  VARCHAR(10),
   STATE                VARCHAR(2),
   USER_LOGINCOUNT      INT,
   USER_TYPE            VARCHAR(2),
   DREDGE_TIME          VARCHAR(50),
   SORT_NUM             INT,
   CREATE_MAN           VARCHAR(32),
   LOGIN_IP             VARCHAR(100),
   LOGIN_DATE           VARCHAR(20),
   CREATE_DATE          VARCHAR(20),
   constraint PK_T_SYS_USERINFO primary key (USER_ID)
);


drop table if exists T_user_role cascade ;

/*==============================================================*/
/* Table: T_USER_ROLE   用户角色关联表                                        */
/*==============================================================*/
create table T_user_role (
   USER_ROLEID          VARCHAR(32)                    not null,
   USER_ID              VARCHAR(32),
   ROLE_ID              VARCHAR(32),
   constraint PK_T_USER_ROLE primary key (USER_ROLEID)
);


alter table T_USER_ROLE
   add constraint FK_T_USER_R_REFERENCE_T_SYS_US foreign key (USER_ID)
      references T_SYS_USERINFO (USER_ID);

drop table if exists T_SYS_ROLEPRIVILEGE cascade ;

/*==============================================================*/
/* Table: T_SYS_ROLEPRIVILEGE   角色权限表                                */
/*==============================================================*/
create table T_SYS_ROLEPRIVILEGE  (
   PRIVILEGE_ID         VARCHAR(32)                    not null,
   ROLE_ID              VARCHAR(32),
   OPERATE_ID           VARCHAR(32),
   MODULE_ID            VARCHAR(32),
   constraint PK_T_SYS_ROLEPRIVILEGE primary key (PRIVILEGE_ID)
);



alter table T_SYS_ROLEPRIVILEGE
   add constraint FK_T_SYS_RO_REFERENCE_T_SYS_OP foreign key (OPERATE_ID)
      references T_SYS_MODULEOPERATE (OPERATE_ID);

alter table T_SYS_ROLEPRIVILEGE
   add constraint FK_T_SYS_RO_REFERENCE_T_SYS_MO foreign key (MODULE_ID)
      references T_SYS_MODULE (MODULE_ID);

alter table T_SYS_ROLEPRIVILEGE
   add constraint FK_T_SYS_RO_REFERENCE_T_SYS_RO foreign key (ROLE_ID)
      references T_SYS_ROLE (ROLE_ID);
drop table if exists T_USER_POST cascade ;

/*==============================================================*/
/* Table: T_USER_POST   用户岗位关联表                                        */
/*==============================================================*/
create table T_USER_POST  (
   USER_JOBID           VARCHAR(32)                    not null,
   POST_ID              VARCHAR(32),
   USER_ID              VARCHAR(32),
   constraint PK_T_USER_POST primary key (USER_JOBID)
);


alter table T_USER_POST
   add constraint FK_T_USER_P_REFERENCE_T_SYS_US foreign key (USER_ID)
      references T_SYS_USERINFO (USER_ID);

alter table T_USER_POST
   add constraint FK_T_USER_P_REFERENCE_T_SYS_PO foreign key (POST_ID)
      references T_SYS_POST (POST_ID);
drop table if exists T_SYS_USERPRIVILEGE cascade ;

/*==============================================================*/
/* Table: T_SYS_USERPRIVILEGE  用户特权表                                 */
/*==============================================================*/
create table T_SYS_USERPRIVILEGE  (
   USERPRVIID           VARCHAR(32)                     not null,
   USERID               VARCHAR(32),
   MODULEID             VARCHAR(32),
   OPERATEID            VARCHAR(32),
   constraint PK_T_SYS_USERPRIVILEGE primary key (USERPRVIID)
);


drop table if exists T_SYS_ORG cascade ;

/*==============================================================*/
/* Table: T_SYS_ORG   机构信息表                                          */
/*==============================================================*/
create table T_SYS_ORG  (
   ORG_ID               VARCHAR(32)                    not null,
   ORG_SN               INT                      not null,
   ORG_NAME             VARCHAR(100)                   not null,
   PARENT_ID            VARCHAR(32)                    not null,
   LAYER                INT,
   CREATE_DATE          VARCHAR(20),
   CREATOR              VARCHAR(32)                   default '1',
   REMARK               VARCHAR(300),
   ORG_TYPE             VARCHAR(2),
   ADDR                 VARCHAR(200),
   ZIP                  VARCHAR(10),
   EMAIL                VARCHAR(100),
   LEADER               VARCHAR(32),
   PHONE                VARCHAR(30),
   FAX                  VARCHAR(20),
   STATE                VARCHAR(1),
   MOBILE               VARCHAR(14),
   ORG_CODE             VARCHAR(100),
   ANCESTRY             VARCHAR(500),
   constraint PK_T_SYS_ORG primary key (ORG_ID)
);


drop table if exists T_ORG_USER cascade ;

/*==============================================================*/
/* Table: T_ORG_USER    机构用户关联表                                        */
/*==============================================================*/
create table T_ORG_USER  (
   orguser_id           VARCHAR(32)                      not null,
   ORG_ID               VARCHAR(32),
   USER_ID              VARCHAR(32),
   constraint PK_T_ORG_USER primary key (orguser_id)
);


alter table T_ORG_USER
   add constraint FK_T_ORG_US_REFERENCE_T_SYS_OR foreign key (ORG_ID)
      references T_SYS_ORG (ORG_ID);

alter table T_ORG_USER
   add constraint FK_T_ORG_US_REFERENCE_T_SYS_US foreign key (USER_ID)
      references T_SYS_USERINFO (USER_ID);


drop table if exists T_SYS_DATAPRIVILEGE cascade ;

/*==============================================================*/
/* Table: T_SYS_DATAPRIVILEGE    数据权限表                               */
/*==============================================================*/
create table T_SYS_DATAPRIVILEGE  (
   ROLE_ORGID           VARCHAR(32)                    not null,
   ORG_ID               VARCHAR(32),
   ROLE_ID              VARCHAR(32),
   USER_ID              VARCHAR(32),
   constraint PK_T_SYS_DATAPRIVILEGE primary key (ROLE_ORGID)
);


alter table T_SYS_DATAPRIVILEGE
   add constraint FK_T_SYS_DA_REFERENCE_T_SYS_OR foreign key (ORG_ID)
      references T_SYS_ORG (ORG_ID);

alter table T_SYS_DATAPRIVILEGE
   add constraint FK_T_SYS_DA_REFERENCE_T_SYS_RO foreign key (ROLE_ID)
      references T_SYS_ROLE (ROLE_ID);

alter table T_SYS_DATAPRIVILEGE
   add constraint FK_T_SYS_DA_REFERENCE_T_SYS_US foreign key (USER_ID)
      references T_SYS_USERINFO (USER_ID);
drop table if exists T_SYS_DICT cascade ;

/*==============================================================*/
/* Table: T_SYS_DICT    数据字典表                                        */
/*==============================================================*/
create table T_SYS_DICT  (
   DICT_ID              VARCHAR(32)                    not null,
   DICT_TYPE            VARCHAR(30),
   DICT_NAME            VARCHAR(60),
   DICT_VALUE           VARCHAR(200),
   DICT_CODE            VARCHAR(60),
   REMARK               VARCHAR(100),
   DICT_SORT            VARCHAR(2),
   SORT_NUM             INT,
   PARENT_TYPE          VARCHAR(32),
   DISPLAY_SORT         VARCHAR(10),
   DICT_CLASS           INT,
   MULTI_TYPE           VARCHAR(1),
   ORG_ID               VARCHAR(32),
   ISDEFAULT            VARCHAR(20),
   constraint PK_T_SYS_DICT primary key (DICT_ID)
);


drop table if exists T_SYS_CODEBUILDER cascade ;

/*==============================================================*/
/* Table: T_SYS_CODEBUILDER                                     */
/*==============================================================*/
create table T_SYS_CODEBUILDER  (
   CODEID               VARCHAR(32),
   PRI                  VARCHAR(10),
   TABLENAME            VARCHAR(100),
   FIELDS               VARCHAR(100),
   QUERYCONDITION       VARCHAR(4),
   FILL                 VARCHAR(4),
   DESCRIPT             VARCHAR(100),
   CONTROL              VARCHAR(200),
   constraint PK_T_SYS_CODEBUILDER primary key (CODEID)
);


drop table if exists T_SYS_CUSTOMFIELDS cascade ;

/*==============================================================*/
/* Table: T_SYS_CUSTOMFIELDS 针对系统管理部分的自定义字段表                                  */
/*==============================================================*/
create table T_SYS_CUSTOMFIELDS  (
   CUSTOMID             VARCHAR(32)                    not null,
   POSITION             INT,
   CUTOM_TYPE          VARCHAR(60),
   NAME                 VARCHAR(30),
   FIELD_FORMAT          VARCHAR(10),
   MIN_LENGTH            VARCHAR(10),
   MAX_LENGTH           VARCHAR(10),
   IS_REQUIRED           VARCHAR(2),
   DEFAULT_VALUE         VARCHAR(200),
   CUSTOM_REGEXP               VARCHAR(200),
   constraint PK_T_SYS_CUSTOMFIEL_CU primary key (CUSTOMID)
);


drop table if exists T_SYS_CUSTOMVALUES cascade ;

/*==============================================================*/
/* Table: T_SYS_CUSTOMVALUES  自定义属性值表                                  */
/*==============================================================*/
create table T_SYS_CUSTOMVALUES  (
   valueid              VARCHAR(32),
   customized_id        VARCHAR(32),
   custom_field_id      VARCHAR(32),
   field_value          VARCHAR(100),
   constraint PK_T_SYS_CUSTOMVALUES primary key (valueid)
);


drop table if exists T_SYS_PARAMETER cascade ;

/*==============================================================*/
/* Table: T_SYS_PARAMETER 新增参数定义表                                      */
/*==============================================================*/
create table T_SYS_PARAMETER  (
   PARAID               VARCHAR(32)                     not null,
   ISDEFAULT            VARCHAR(2),
   PARANAME             VARCHAR(20),
   PARANO               VARCHAR(100),
   PARA_CLASS	        VARCHAR(2),
   PARA_KEY             VARCHAR(60),
   PARA_TYPE            VARCHAR(20),
   PARA_ORDER           INT,
   REMARK               VARCHAR(100),
   PARENTPARAID	        VARCHAR(32),
   DISPLAYSORT          VARCHAR(10),
   constraint PK_T_SYS_PARAMETER primary key (PARAID)
);
/*=======================解决系统编码问题=================================*/
drop table if exists T_SYS_SERIALNO cascade;
-- Create table
create table T_SYS_SERIALNO
(
  serialno_id   VARCHAR(32) not null,
  serial_name   VARCHAR(200),
  secound_name  VARCHAR(200),
  formular_regx VARCHAR(300),
  create_type   VARCHAR(10),
  serial_length INT,
  step          INT,
  init_value    VARCHAR(20),
  current_value VARCHAR(20),
  remark        VARCHAR(200),
  tab_name      VARCHAR(200),
  SFBL          VARCHAR(2),
  constraint PK_T_SYS_SERIALNO primary key (serialno_id)
);
/*====================================*/
drop table if exists t_ty_advertising;
create table t_ty_advertising
(
   advertising_id       varchar(32) not null  comment '广告id',
   advertising_begin_time timestamp  comment '广告图片上传时间',
   advertising_end_time timestamp  comment '广告图结束时间',
   primary key (advertising_id)
);
alter table t_ty_advertising comment '广告图';
/*====================================*/
drop table if exists t_ty_brand;
create table t_ty_brand
(
   brand_id             varchar(32) not null  comment '品牌id',
   brand_logo           varchar(40)  comment '品牌logo',
   brand_name           varchar(30)  comment '品牌名称',
   type_id              varchar(32)  comment '类型id',
   primary key (brand_id)
);
alter table t_ty_brand comment '品牌
';
/*====================================*/
drop table if exists t_ty_news;
create table t_ty_news
(
   news_id              varchar(32) not null  comment '新闻id',
   news_type_code       int  comment '新闻类型  1企业动态  2行业动态  0活动公告  3促销活动',
   news_title           varchar(50)  comment '新闻标题、',
   news_content         varchar(500)  comment '新闻内容',
   create_time          varchar(32)  comment '新闻发布时间',
   create_user_id       varchar(32)  comment '新闻发布人',
   update_user_id       varchar(32)  comment '修改人',
   update_time          varchar(32)  comment '修改时间',
   news_sort            int  comment '新闻排序号',
   primary key (news_id)
);

alter table t_ty_news comment '新闻

';
/*====================================*/
drop table if exists t_ty_price;
create table t_ty_price
(
   price_id             varchar(32) not null  comment '价格id',
   price_max            decimal(2)  comment '价格区间',
   price_min           decimal(2)  comment '价格区间最小值',
   primary key (price_id)
);

alter table t_ty_price comment '价格
';
/*====================================*/
drop table if exists t_ty_product;
create table t_ty_product
(
   product_id           varchar(32) not null  comment '产品id',
   brand_id             varchar(32)  comment '品牌id',
   price_id             varchar(32)  comment '价格id',
   product_name         varchar(32)  comment '产品名称',
   product_market_price decimal(2)  comment '市场价格',
   product_flatly_price decimal(2)  comment '一口价',
   product_remark       varchar(200)  comment '产品优势/介绍',
   product_colour       varchar(20)  comment '产品颜色',
   product_cover_picture varchar(30)  comment '产品封面图',
   product_pub_time     timestamp  comment '发布时间',
   create_user_id       varchar(32)  comment '新闻发布人',
   update_user_id       varchar(32)  comment '修改人',
   update_time          varchar(32)  comment '修改时间',
   primary key (product_id)
);

alter table t_ty_product comment '产品管理';
/*====================================*/
drop table if exists t_ty_type;
create table t_ty_type
(
   type_id              varchar(32) not null  comment '类型id',
   type_name            varchar(20)  comment '类型名称',
   type_rank            varchar(32)  comment '类型级别，指向中规车/平行车ID',
   primary key (type_id)
);

alter table t_ty_type comment '类型';

/*====================================*/
CREATE TABLE `t_sys_attach` (
  `ATTACH_ID` varchar(32) NOT NULL,
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `FILE_URL` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `FILE_NAME` varchar(200) DEFAULT NULL COMMENT '文件名称',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '文件备注',
  `SECOND_NAME` varchar(100) DEFAULT NULL COMMENT '文件别名',
  `FILE_TYPE` varchar(2) DEFAULT NULL COMMENT '附件分类',
  `PKID` varchar(32) DEFAULT NULL COMMENT '关联ID',
  `FILE_SIZE` varchar(10) DEFAULT NULL COMMENT '文件大小',
  `FILE_SUFFIX` varchar(20) DEFAULT NULL COMMENT '文件后缀',
  `LJ_URL` varchar(1000) DEFAULT NULL COMMENT '链接地址',
  `FILE_NO` varchar(100) DEFAULT NULL COMMENT '文件编号',
  `FILE_STATE` int(11) DEFAULT NULL COMMENT '文件状态',
  `FILE_ORERNO` int(11) DEFAULT NULL COMMENT '文件排序号',
  PRIMARY KEY (`ATTACH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据附件表';

/*==============================================================*/
/* Table: T_SYS_CODEBUILDER 数据初始化                                    */
/*==============================================================*/
INSERT INTO t_sys_app VALUES ('82c786f2bfbf46398e3b495f6c7014bc', '电商平台', '12', NULL, '555', 'app0');
commit;
INSERT INTO T_SYS_ORG VALUES ('0003', 2, '计软Java1602', '1', NULL, '2015-12-11 16:54:21', NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '430000', '.0003.1.-1.');
INSERT INTO T_SYS_ORG VALUES ('1', 1, '湖南信息职业技术学院', '-1', 1, NULL, '1', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '10000', '.1.-1.');
commit;
insert into T_SYS_SERIALNO (serialno_id, serial_name, secound_name, formular_regx, create_type, serial_length, step, init_value, current_value, remark, tab_name)
values ('2a6e2edaf0dd4179a98fcde46fb4c848', '机构ID规则', 'oraIdRule', '{NO}', '1',0004, 1, '2', '2', null, 'T_SYS_ORG');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c8814bc', '82c786f2bfbf46398e3b495f6c7014bc', '系统管理', null, null, 'icon-folder', null, '0', 10, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b49556c7014bc', null, '菜单管理', 'tsysModuleController.do?method=list', null, 'icon-account', null, '82c786f2bfbf46398e32195f6c7014bc', 10, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c7014cd', null, '多应用管理', 'tsysAppController.do?method=list', null, 'icon-works', null, '82c786f2bfbf46398e32195f6c7014bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f677014bc', null, '机构管理', 'tsysOrgController.do?method=list', null, 'icon-org', 'T_SYS_ORG', '82c786f2bfbf46398e3b495f6c8814bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c701312', null, '角色管理', 'tsysRoleController.do?method=list', null, 'icon-role', 'T_SYS_ROLE', '82c786f2bfbf46398e3b495f6c8814bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c7224bc', null, '用户管理', 'tsysUserinfoController.do?method=list', null, 'icon-boy', 'T_SYS_USER', '82c786f2bfbf46398e3b495f6c8814bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c7314bc', null, '岗位管理', 'tsysPostController.do?method=list', null, 'icon-users', null, '82c786f2bfbf46398e3b495f6c8814bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e32195f6c7014bc', '82c786f2bfbf46398e3b495f6c7014bc', '配置管理', 'tsysParameterController.do?method=list', null, 'icon-set', null, '0', 20, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c7914bc', null, '参数管理', 'tsysParameterController.do?method=list', null, 'icon-email', null, '82c786f2bfbf46398e32195f6c7014bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c7514bc', null, '字典管理', 'tsysDictController.do?method=list', null, 'icon-magic', null, '82c786f2bfbf46398e32195f6c7014bc', 9, null, '0');
insert into T_SYS_MODULE (module_id, app_id, module_name, link_addr, module_class, icon, module_code, parent_moduleid, sort_num, remark, display)
values ('82c786f2bfbf46398e3b495f6c9914bc', null, '流水号管理', 'tsysSerialnoController.do?method=list', null, 'icon-magic', 'T_SYS_SERIALNO', '82c786f2bfbf46398e32195f6c7014bc',11, null, '0');
commit;
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('28c312016bfc499991e01cd5e557e61a', '82c786f2bfbf46398e3b495f677014bc', '删除', 'DELETE');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('b77a7288304b41198329d22331ad45c9', '82c786f2bfbf46398e3b495f677014bc', '排序', 'ORDER');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('43af91e5fda745c18d4c34fda5b86531', '82c786f2bfbf46398e3b495f677014bc', '添加', 'ADD');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('49724f859f1446b4ad2efa00f4b46b38', '82c786f2bfbf46398e3b495f6c7314bc', '删除', 'DELETE');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('4e79ecc2814445f59d72633f470f2af1', '82c786f2bfbf46398e3b495f677014bc', '修改', 'EDIT');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('108c8c644f65470b8d87ff7cc9c9b7e9', '82c786f2bfbf46398e3b495f6c7314bc', '添加', 'ADD');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('9794c516a52d401aa409cad17db4376a', '82c786f2bfbf46398e3b495f6c7314bc', '查询', 'QUERY');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('11a07973658248bc8e676399ea321892', '82c786f2bfbf46398e3b495f6c7314bc', '修改', 'EDIT');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('fc9b6c4c0eb940a5bd92058610e2b4af', '82c786f2bfbf46398e3b495f6c701312', '删除', 'DELETE');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('c319ede989894af099d9d27ca43a0b54', '82c786f2bfbf46398e3b495f6c701312', '添加', 'ADD');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('847b01ec2c73482082da17915fb6f214', '82c786f2bfbf46398e3b495f6c701312', '修改', 'EDIT');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('0bcf1362a9094ee4a93d59fcf6f333a0', '82c786f2bfbf46398e3b495f6c701312', '角色成员', 'ROLE_USER');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('07a7d93289274bb985a08d260ce267fb', '82c786f2bfbf46398e3b495f6c7224bc', '用户启用', 'ENABLE');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('fe6bcdf88b1c422fad5e147830613508', '82c786f2bfbf46398e3b495f6c7224bc', '用户停用', 'STOP');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('5e4593e5fd584408b3e135036da3ed6e', '82c786f2bfbf46398e3b495f6c7224bc', '排序', 'SORT');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('a11fb7121b4f4bb085621d6bea319f1b', '82c786f2bfbf46398e3b495f6c7224bc', '删除', 'DELETE');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('0d6563500cf3409ebad8a9bdee16c3d1', '82c786f2bfbf46398e3b495f6c7224bc', '重置', 'RELOAD');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('6d939ce38b3e4138bea6a461dcd40adb', '82c786f2bfbf46398e3b495f6c7224bc', '分配角色', 'AUTH_ROLE');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('8e57726e92c34e639d4ed3a9a9151f38', '82c786f2bfbf46398e3b495f6c7224bc', '添加', 'ADD');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('35079f097383471ebbc693ec99d45ead', '82c786f2bfbf46398e3b495f6c7224bc', '修改', 'EDIT');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('ed3ff3eac72c457f98baf686e633d6ea', '82c786f2bfbf46398e3b495f6c7224bc', '查询', 'QUERY');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('b6d01d35d9ad4612a93353bb534e2034', '82c786f2bfbf46398e3b495f6c7224bc', '重置密码', 'RESET');
insert into T_SYS_MODULEOPERATE (operate_id, module_id, operate_name, operate_code)
values ('0bcf1362a9094ee4a93d59fcf6f33345', '82c786f2bfbf46398e3b495f6c701312', '查询', 'QUERY');
commit;
INSERT INTO T_SYS_PARAMETER VALUES ('01d6ac3f641b4a60b34450afabef974d', NULL, '数据权限范围', '5', '0', '所在部门数据', 'DATA_SCOPE', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e52670025', '1', '用户在线状态', '1', '0', '在线', 'isonline', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e52ac0028', '1', '用户在线状态', '2', '0', '不在线', 'isonline', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5b14006c', '1', '是否有子结点', '1', '0', '有子结点', 'isLeaf', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5b2a006d', '1', '是否有子结点', '2', '0', '无子结点', 'isLeaf', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5b850071', NULL, '用户状态', '4', '0', '离职', 'userState', 4, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5ba40072', NULL, '用户状态', '3', '0', '调岗', 'userState', 3, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5bbc0073', NULL, '用户状态', '2', '0', '停用', 'userState', 2, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5bd10074', '1', '用户状态', '1', '0', '正常', 'userState', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5e6b008c', '1', '性别', '2', '0', '女', 'sex', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5e82008d', '1', '性别', '1', '0', '男', 'sex', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5f810094', NULL, '角色类型', '2', '0', '私有', 'roleType', 2, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e5fe50097', NULL, '角色类型', '1', '0', '公共', 'roleType', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e62e300b8', '1', '用户类型', '2', NULL, '外部用户', 'userType', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e630400b9', '1', '用户类型', '1', NULL, '内部用户', 'userType', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e634900bc', '1', '岗位类型', '4', '0', '总监', 'postType', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e635c00bd', '1', '岗位类型', '3', '0', '副总经理', 'postType', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e638d00be', '1', '岗位类型', '2', '0', '总经理', 'postType', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e63aa00bf', '1', '岗位类型', '1', '0', '主席', 'postType', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1590942b45cf6e1a0145cf6e63d700c1', NULL, '是否为开发管理员', 'devadmin', '0', 'devadmin', 'SUPER_USER', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('159e66d90e174ef6a364920957355f7f', NULL, '生成方式', '4', NULL, '每年递增', 'create_type', 4, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('160175d545a140f3bc978dcbcb6fcf68', NULL, '数据权限范围', '8', '0', '仅本人数据', 'DATA_SCOPE', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('1d5c1a784b1240d3a367f4bb25c9ebb6', NULL, '单位类型', '6', NULL, '特别行政区', 'orgType', 6, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('20fdf895aed64d2b853c537d3ac5e661', '1', '机构类型', '3', NULL, '区县级', 'orgType', 4, '1', NULL, '1');
INSERT INTO T_SYS_PARAMETER VALUES ('239fbfdd55fd497790b56bd95c7efd4f', NULL, '数据权限范围', '4', '0', '所在部门及以下数据', 'DATA_SCOPE', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('424887819f0043bf8d67bf3b8dbb3428', NULL, '生成方式', '5', NULL, '序列递增', 'create_type', 5, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('4a3a7fc3ec1047bbac88d0f08ee2b991', NULL, '数据权限范围', '1', '0', '所有数据', 'DATA_SCOPE', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('6b8e229dbab041beb53ac9494f224dda', NULL, '单位类型', '4', NULL, '自治区', 'orgType', 2, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('73ce459c16ab45c59f0135e9777b6ac3', '1', '字典展现分类', '1', '0', '列表', 'dictDisplaySort', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('792dcf9990fa41b5a83e1a9511f14266', NULL, '单位类型', '5', NULL, '直辖市', 'orgType', 2, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('898b9a7b3a6e438ca55e8776d2c7ad87', NULL, '数据权限范围', '9', '0', '按明细设置', 'DATA_SCOPE', NULL, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('a97019497f1c455ab69557990759eb2d', NULL, '生成方式', '1', NULL, '递增', 'create_type', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('b9c5ed60083a48a1b4a57881937db733', '1', '单选或多选', '1', '0', '单选(radio)', 'dictMultiType', 1, '主要针对树形结构', NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('c8132ab5b82248ac964b6eb84a3d6be2', '1', '单选或多选', '2', '0', '多选(checkbox)', 'dictMultiType', 2, '主要针对树形结构', NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('cad8df422f134e8184db4a61778cf2e2', '1', '机构状态', '2', '0', '停用', 'state', 2, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('d23cb3c903264e84b297d5ed0b7764eb', '1', '机构类型', '1', NULL, '省级', 'orgType', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('d4c8255c9db94f58b77a0f3a930ff5d5', NULL, '生成方式', '3', NULL, '每月递增', 'create_type', 3, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('dc67484007414db9b7e6ec00aaa9dc79', '1', '字典展现分类', '2', '0', '树形', 'dictDisplaySort', 2, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('e016084ab6b741ec9bc886598bfe5a97', '1', '机构状态', '1', '0', '启用', 'state', 1, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('e6e93818d06e437491090852c8685da7', '1', '机构类型', '2', NULL, '市州级', 'orgType', 3, NULL, NULL, NULL);
INSERT INTO T_SYS_PARAMETER VALUES ('eb4c2e4cb3234d528c3300c8a5b6fa84', NULL, '生成方式', '2', NULL, '每日递增', 'create_type', 2, NULL, NULL, NULL);
commit;
insert into T_SYS_USERINFO (user_id, login_name, password, user_realname, user_no, org_id, dept_id, sex, mobiletel, fax, email, zip, state, user_logincount, user_type, dredge_time, sort_num, create_man, login_ip, login_date, create_date)
values ('a594d023a53d459da6435c8589f027df', 'admin', '7115e2e90a868e4dd32aadc940f90530', '超级管理员', null, '1', null, null, null, null, null, null, '1', null, null, null, null, null, null, null, '2015-12-11 11:19:19');
insert into T_SYS_ROLE (role_id, role_name, role_type, remark, org_id, creator, create_date, data_scope)
values ('944f7d02801445ec9c988b5600d9dab7', '超级管理员', null, '超级管理员', null, null, null, '1');
commit;