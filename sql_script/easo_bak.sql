-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.62-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema easygo
--

CREATE DATABASE IF NOT EXISTS easygo;
USE easygo;

--
-- Definition of table `app_goods`
--

DROP TABLE IF EXISTS `app_goods`;
CREATE TABLE `app_goods` (
  `goods_id` varchar(50) NOT NULL COMMENT 'id',
  `goods_name` varchar(45) NOT NULL COMMENT '商品名称',
  `price` double unsigned NOT NULL COMMENT '单价',
  `quantity` int(10) unsigned NOT NULL COMMENT '数量',
  `description` longtext COMMENT '商品介绍（大段html文本）',
  `state` int(10) unsigned NOT NULL COMMENT '有效状态',
  `goods_type_id` int(10) unsigned DEFAULT NULL COMMENT '商品类型id',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_goods`
--

/*!40000 ALTER TABLE `app_goods` DISABLE KEYS */;
INSERT INTO `app_goods` (`goods_id`,`goods_name`,`price`,`quantity`,`description`,`state`,`goods_type_id`) VALUES 
 ('297ef1a44ac6db73014ac6de29b50001','商品1',100,10,'哈哈',1,NULL),
 ('297ef1a44ac6db73014ac6de9f210002','联想k27笔记本电脑',3000,10,'',1,NULL),
 ('297ef1a44ac6db73014ac6df0a6c0003','宾得K30',3000,20,'',1,NULL),
 ('297ef1a44ac6db73014ac6dfa3a00004','iphone 4s',2000,21,'',1,NULL),
 ('297ef1a44ac6db73014ac6dfd4100005','iphone 3gs',1000,2,'',1,NULL),
 ('297ef1a44ac6db73014ac6dffce70006','iphone 5',3200,10,'',1,NULL),
 ('297ef1a44ac6db73014ac6e04fa40007','asus a42笔记本',4000,123,'',1,NULL),
 ('297ef1a44ac6db73014ac6e0facc0008','AKG k450',400,12,'',1,NULL),
 ('297ef1a44ac6db73014ac6e164350009','apple earpod',100,12,'',1,NULL),
 ('297ef1a44ac6db73014ac6e1fe6e000a','win 8正版',800,12,'',1,NULL),
 ('297ef1a44ac6db73014ac6e28fb1000b','ipad 3',1500,123,'',1,NULL),
 ('297ef1a44ac6db73014ac6e2cd50000c','ipad mini2',1800,12,'',1,NULL),
 ('297ef1a44ac6db73014ac6e320a4000d','HuaWei p6',1000,32,'',1,NULL),
 ('297ef1a44ac6db73014ac6e3743e000e','HuaWei mate 7',4000,21,'',1,NULL),
 ('402881e94ab09f53014ab0a08ac60002','iphone4s',1000,10,'4ssssssssssss',0,NULL),
 ('402881e94ab09f53014ab0a344cc0003','iphone5',2500,123,'iphone5',0,NULL),
 ('402881e94ab0b273014ab0b3110f0001','iphone6',5000,12,'6666666666',1,NULL),
 ('402881e94ab0b273014ab0bed8e90002','smartisan',3000,10,'ajdlkfasd',1,NULL);
/*!40000 ALTER TABLE `app_goods` ENABLE KEYS */;


--
-- Definition of table `app_goods_type`
--

DROP TABLE IF EXISTS `app_goods_type`;
CREATE TABLE `app_goods_type` (
  `goods_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_name` varchar(100) NOT NULL COMMENT '类型名称',
  `type_intro` longtext COMMENT '类型描述',
  PRIMARY KEY (`goods_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_goods_type`
--

/*!40000 ALTER TABLE `app_goods_type` DISABLE KEYS */;
INSERT INTO `app_goods_type` (`goods_type_id`,`type_name`,`type_intro`) VALUES 
 (2,'3C数码','数码产品hiahiahia'),
 (3,'食品','吃的'),
 (4,'五金','五金用品'),
 (5,'家用电器','电器');
/*!40000 ALTER TABLE `app_goods_type` ENABLE KEYS */;


--
-- Definition of table `app_order`
--

DROP TABLE IF EXISTS `app_order`;
CREATE TABLE `app_order` (
  `order_id` varchar(50) NOT NULL COMMENT '订单id',
  `goods_id` varchar(50) NOT NULL COMMENT '商品id',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `quantity` int(10) unsigned NOT NULL COMMENT '数量',
  `total_price` double NOT NULL COMMENT '总价',
  `state` int(10) unsigned DEFAULT '1' COMMENT '有效状态',
  `pay_state` int(10) unsigned DEFAULT '0' COMMENT '支付状态',
  `trans_state` int(10) unsigned DEFAULT '0' COMMENT '物流状态',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `trans_time` datetime DEFAULT NULL COMMENT '发货时间',
  `sign_time` datetime DEFAULT NULL COMMENT '签收时间',
  `sign_state` int(10) unsigned DEFAULT '0' COMMENT '签收状态',
  `invoice_num` varchar(50) DEFAULT NULL COMMENT '物流编号',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_order`
--

/*!40000 ALTER TABLE `app_order` DISABLE KEYS */;
INSERT INTO `app_order` (`order_id`,`goods_id`,`user_id`,`quantity`,`total_price`,`state`,`pay_state`,`trans_state`,`create_time`,`pay_time`,`trans_time`,`sign_time`,`sign_state`,`invoice_num`) VALUES 
 ('123','402881e94ab09f53014ab0a08ac60002','5f8399184a94b11d014a94b148800001',1,100,1,0,1,NULL,NULL,'2015-01-07 11:45:15',NULL,0,'1234num');
/*!40000 ALTER TABLE `app_order` ENABLE KEYS */;


--
-- Definition of table `core_mapping`
--

DROP TABLE IF EXISTS `core_mapping`;
CREATE TABLE `core_mapping` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_mapping`
--

/*!40000 ALTER TABLE `core_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_mapping` ENABLE KEYS */;


--
-- Definition of table `core_role`
--

DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role` (
  `role_id` varchar(50) NOT NULL DEFAULT '',
  `role_name` varchar(50) DEFAULT NULL,
  `role_pinyin_name` varchar(100) DEFAULT NULL,
  `role_description` varchar(200) DEFAULT NULL,
  `state` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_role`
--

/*!40000 ALTER TABLE `core_role` DISABLE KEYS */;
INSERT INTO `core_role` (`role_id`,`role_name`,`role_pinyin_name`,`role_description`,`state`) VALUES 
 ('ID000','管理员','guanliyuan','系统管理员',1),
 ('ID001','商品管理员','shangpinguanliyuan','商品管理员',1),
 ('ID002','网购用户','wanggouyonghu','网购用户',1),
 ('ID003','订单管理员','dingdanguanliyuan','订单管理员',1);
/*!40000 ALTER TABLE `core_role` ENABLE KEYS */;


--
-- Definition of table `core_role_man_url`
--

DROP TABLE IF EXISTS `core_role_man_url`;
CREATE TABLE `core_role_man_url` (
  `role_man_url_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` varchar(45) NOT NULL COMMENT 'roleId',
  `url` varchar(255) NOT NULL COMMENT '管理页面url',
  `url_name` varchar(50) NOT NULL COMMENT '管理页面名称',
  PRIMARY KEY (`role_man_url_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色-管理页面 关联表';

--
-- Dumping data for table `core_role_man_url`
--

/*!40000 ALTER TABLE `core_role_man_url` DISABLE KEYS */;
INSERT INTO `core_role_man_url` (`role_man_url_id`,`role_id`,`url`,`url_name`) VALUES 
 (4,'ID001','bsGoodsType/index.do','商品类型管理'),
 (5,'ID003','bsOrder/index.do','订单管理'),
 (8,'ID001','bsGoods/index.do','商品管理'),
 (11,'ID000','bsUser/index.do','用户管理'),
 (12,'ID000','bsGoods/index.do','商品管理'),
 (13,'ID000','bsGoodsType/index.do','商品类型管理'),
 (14,'ID000','bsOrder/index.do','订单管理');
/*!40000 ALTER TABLE `core_role_man_url` ENABLE KEYS */;


--
-- Definition of table `core_user`
--

DROP TABLE IF EXISTS `core_user`;
CREATE TABLE `core_user` (
  `user_id` varchar(50) NOT NULL COMMENT 'id',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `state` int(11) DEFAULT NULL COMMENT '状态：0/无效，1/有效',
  `sex` int(11) DEFAULT NULL COMMENT '性别：0/男，1/女',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色Id',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `address` longtext COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_user`
--

/*!40000 ALTER TABLE `core_user` DISABLE KEYS */;
INSERT INTO `core_user` (`user_id`,`login_name`,`nick_name`,`password`,`state`,`sex`,`role_id`,`email`,`address`) VALUES 
 ('402881e94ab0e4ac014ab0e4c6770001','jall','jallenjia','202cb962ac59075b964b07152d234b70',0,0,'ID000','jallenjia@sina.com','雨花台'),
 ('5f8399184a94b11d014a94b148800001','root','root','202cb962ac59075b964b07152d234b70',1,0,'ID000','root@sina.com','root地址'),
 ('5f83ede24a9a1cfe014a9a1d5bbb0001','test1','test1','202cb962ac59075b964b07152d234b70',1,NULL,'ID001','',''),
 ('5f83ede24a9b1e12014a9b1e71fc0001','test2','test2','202cb962ac59075b964b07152d234b70',1,1,'ID002','',''),
 ('5f83ede24a9b1e12014a9b1ebee10003','test3','test3','202cb962ac59075b964b07152d234b70',0,NULL,'ID003','','');
/*!40000 ALTER TABLE `core_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
