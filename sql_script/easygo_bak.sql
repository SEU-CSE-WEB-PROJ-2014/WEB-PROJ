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
 ('297ef1a44ac6db73014ac6de29b50001','商品1',100,10,'哈哈',0,NULL),
 ('297ef1a44ac6db73014ac6de9f210002','联想k27笔记本电脑',3000,10,'如果失去联想，世界将会怎样~',1,2),
 ('297ef1a44ac6db73014ac6df0a6c0003','宾得K30',3000,20,'中端单反',1,2),
 ('297ef1a44ac6db73014ac6dfa3a00004','iphone 4s',2000,21,'被上帝咬了一口的苹果~',1,2),
 ('297ef1a44ac6db73014ac6dfd4100005','iphone 3gs',1000,2,'被上帝咬了一口的苹果~',1,2),
 ('297ef1a44ac6db73014ac6dffce70006','iphone 5',3200,10,'被上帝咬了一口的苹果~',1,2),
 ('297ef1a44ac6db73014ac6e04fa40007','asus a42笔记本',4000,123,'华硕',1,2),
 ('297ef1a44ac6db73014ac6e0facc0008','AKG k450',400,12,'爱科技',1,2),
 ('297ef1a44ac6db73014ac6e164350009','apple earpod',100,11,'被上帝咬了一口的苹果~',1,2),
 ('297ef1a44ac6db73014ac6e1fe6e000a','win 8正版',800,12,'支。持。正。版。',1,2),
 ('297ef1a44ac6db73014ac6e28fb1000b','ipad 3',1500,123,'被上帝咬了一口的苹果~',1,2),
 ('297ef1a44ac6db73014ac6e2cd50000c','ipad mini2',1800,11,'被上帝咬了一口的苹果~',1,2),
 ('297ef1a44ac6db73014ac6e320a4000d','HuaWei p6',1000,32,'华为',1,2),
 ('297ef1a44ac6db73014ac6e3743e000e','HuaWei mate 7',4000,21,'华为',1,2),
 ('402881e94ab09f53014ab0a08ac60002','iphone4s',1000,10,'4ssssssssssss',0,NULL),
 ('402881e94ab09f53014ab0a344cc0003','iphone5',2500,123,'iphone5',0,NULL),
 ('402881e94ab0b273014ab0b3110f0001','iphone6',5000,10,'被上帝咬了一口的苹果~',1,2),
 ('402881e94ab0b273014ab0bed8e90002','smartisan',3000,10,'锤子',1,2),
 ('5f8328514aca13a7014aca16defc0001','羽西化妆品 正品 人参塑颜御颜修护日霜50ml',208,87,'对抗皱纹及松弛暗哑，提亮紧致肌肤，天圆地方造型包装',1,2),
 ('5f8328514aca13a7014aca1acce70002','路易威登14新款LV女包帆布手提包经典棋盘格子母包N41362',9085,31,'Neverfull 小号手提包造型挺括，设计经典，是一款气质典雅的都市手袋。',1,13),
 ('5f8328514aca13a7014aca1b4c430003','意大利直邮路易威登14新款LV女包手拿包时尚金色宴会包正品M40918',12028,17,'Limelight中号手包采用覆膜的织布，质感别具一格。 这是一款极富魅力的配饰，内部空间充裕，适合早晚携带。',1,13),
 ('5f8328514aca13a7014aca1c8c880004','瑞士Longines浪琴男表名匠系列手表男机械男表L2.628.4.78.6',12320,3,'浪琴的起源可追溯到1832年，由奥古斯特阿加西在瑞士Saint Limier创办和开始钟表生意。',1,13),
 ('5f8328514aca13a7014aca2152af0005','新款瑞士ck手表专柜正品女表男士男表情侣表K2G2G1C6',1440,102,'男款女款情侣款！人气爆款 限时折扣！ 品牌授权 正品保障！ 全国联保 假一赔三 七天无理由！ ',1,13),
 ('5f8328514aca13a7014aca23481b0006','Tiffany公主方Princess',24990,94,'Cut 47分 I色 VS净度 订婚钻戒',1,13),
 ('5f8328514aca13a7014aca25b97d0007','冬季七匹狼男士加绒加厚衬衫',126,5499,'韩版保暖长袖衬衣男装休闲商务加大码',1,14),
 ('5f8328514aca13a7014aca281ba70008','哥弟女装2015春装休闲百搭小脚裤',480,1957,'弹性铅笔裤修身牛仔裤190007',1,14),
 ('5f8328514aca13a7014aca28f7a30009','汀哥弟斯2015年春季新款毛衣',198,200,'韩版修身衬衫领假两件长袖打底毛衣',1,14),
 ('5f8328514aca13a7014aca2a3e64000a','Marisfrolg玛丝菲尔 简约时尚经典毛呢大衣',2240,2,'面料使用超细澳大利亚羊毛纺制的粗纺纱，使得本该是厚重的大衣面料，却手感丰厚而结实，表面细腻，给人高贵的感觉。',1,14),
 ('5f8328514aca13a7014aca2b634c000b','Marisfrolg玛丝菲尔 羊毛大衣经典西服领',2640,8,'线状穿插花色面料，立体剪裁，高收腰款式，修饰身材的同时更显女士的优雅气质。',1,14),
 ('5f8328514aca13a7014aca2bf651000c','Marisfrolg玛丝菲尔 羊毛小立领气质针织衫',744,155,'面料成分：100%羊毛面料，撞色成分：84.1%羊毛，14.0%锦纶，1.9%氨纶；面料采用超细美利奴防缩羊毛，蓬松柔软，更具保暖性；',1,14),
 ('5f8328514aca13a7014aca2cb4f0000d','Marisfrolg玛丝菲尔复古无袖通勤连衣裙',1240,2,'此面料手感柔软，色彩丰富，怀旧中显露出高贵。无袖洋装，简单的设计手法与完美的工艺使简单的洋装不乏时尚感，加内搭或者搭配外套均可。',1,14),
 ('5f8328514aca13a7014aca308bd1000e','韩都衣舍2015春装新款大码女装印花长袖连衣裙GW3222',188,5306,'【元旦活动】首页领券，229减20，299减30，499减50，599减100，领券立减　',1,14),
 ('5f8328514aca13a7014aca31a697000f','韩版2013冬装新款女装加厚假两件打底长袖T恤',120,162,'【狂欢预告】满229-20/299-30元/469-50/599-100（提前领券）',1,14),
 ('5f8328514aca13a7014aca32d50a0010','Camel 骆驼男鞋真皮商务休闲皮鞋',299,1205,'挺括的楦型设计，完全符合亚洲人的足部版型，设计师精心度量每一分寸，以奢华牛皮进行完美打造，简约富有格调',1,15),
 ('5f8328514aca13a7014aca33ca6d0011','CAMEL骆驼登山鞋 ',378,11257,'男士高帮 头层牛皮防水徒步登山鞋 防滑耐磨的橡胶登山大底，鞋底模块合理分割，耐磨、减震、防滑兼顾，',1,15),
 ('5f8328514aca13a7014aca355d0f0012','骆驼冲锋衣男 户外抓绒保暖三合一两件套',499,5757,'专业科技面料，加入耐洗泼水加工，荷叶式拒水，穿着舒适保暖，兼具透气性',1,15),
 ('5f8328514aca13a7014aca3694560013','探路者冲锋裤',284,166,'采用防泼水防风面料，适合秋冬季节穿着；裤型采用立体分割裁剪，版型能满足运动的需求，具备户外徒步所需的功能，脚口可调节围度。',1,15),
 ('5f8328514aca13a7014aca3738970014','迪卡侬正品 运动水壶水杯',20,58,'大容量 铝合金户外水壶 骑行水壶 BTWIN',1,15),
 ('5f8328514aca13a7014aca3839230015','轩之梦汽车掸子',30,37640,'1.年销售20w把蜡拖，每3把蜡拖就有一把出自轩之梦。 2.今日购买/买一送8套餐，送洗车专用雪尼尔蜡刷 【专业除尘+专业洗车=完美搭配】',1,16),
 ('5f8328514aca13a7014aca38f09a0016','随美汽车头枕记忆棉车用头枕护颈枕',78,88,'1、恒温太空记忆棉零下30°C不变硬。2、3位一体全方位呵护3、脊椎牵引自然伸缩，4超大枕面时刻保护。5极致恒温个性体验。',1,16),
 ('5f8328514aca13a7014aca39f28f0017','沿途长嘴狗竹炭包',10,429,'汽车饰品摆件 汽车装饰 汽车用品超市 车内饰品',1,16),
 ('5f8328514aca13a7014aca3c2f790018','秋冬纯棉保暖婴儿用品',79,148,'新生儿礼盒套装婴儿衣服母婴服装宝宝衣服',1,17),
 ('5f8328514aca13a7014aca3d22a90019','恩诺童新生儿奶瓶',100,769,'宽口防胀气奶瓶塑料PPSU婴儿宝宝防摔母婴用品',1,17),
 ('5f83e5ec4ac95d3f014ac96f05e30001','鲜花饼',38,42377,'云南特产，吃货必尝',1,3),
 ('5f83e5ec4ac95d3f014ac97188c00002','三只松鼠-炭烧腰果',32.92,200706,'吃货经验：凡是炭烧的都很好ci~',1,3),
 ('5f83e5ec4ac95d3f014ac97409850003','丹麦风味蓝铁罐盒装曲奇饼干',54,22000,'曲奇饼干的典范~',1,3),
 ('5f83e5ec4ac974a8014ac9777bec0001','祖名香豆干',19.8,76,'豆干中的豆干~',1,3),
 ('5f83e5ec4ac974a8014ac978625c0002','品客薯片',6.8,1651,'今天你吃薯片了吗？',1,3),
 ('5f83e5ec4ac974a8014ac9799d0e0003','张君雅小妹妹',6.5,814,'日式串烧休闲丸子',1,3),
 ('5f83e5ec4ac974a8014ac97bb7930004','意大利Loacker莱家威化饼干',25,3368,'巧克力、柠檬、杏仁、榛子，多种口味任你选~',1,3),
 ('5f83e5ec4ac974a8014ac97d1bd90005','营养麦片巧克力',10.8,672,'麦片中的巧克力，巧克力中的麦片~',1,3),
 ('5f83e5ec4ac974a8014ac97e917f0006','旺旺浪味仙',28.8,3165,'就是这个经典的味道~',1,3),
 ('5f83e5ec4ac974a8014ac980ed6f0007','德芙摩卡榛仁碗装',29.9,123,'情人节必备~',1,3),
 ('5f83e5ec4ac974a8014ac98246de0008','kisses黑巧克力500克',46.9,1141,'婚庆喜糖',1,3),
 ('5f83e5ec4ac974a8014ac983dfa10009','海天招牌拌饭酱',9.9,4453,'下饭神器~',1,3),
 ('5f83e5ec4ac974a8014ac9855e42000a','母亲香辣牛肉酱',10.99,4049,'正是母亲的味道~',1,3),
 ('5f83e5ec4ac974a8014ac987383d000b','费列罗巧克力礼盒装27粒',88,10537,'爱的礼物送女神~',1,3),
 ('5f83e5ec4ac974a8014ac98ab160000c','宏香记手撕牛肉豆脯200g',13.9,20000,'豆脯里有牛肉，真的假的~',1,3),
 ('5f83e5ec4ac990ab014ac996dce90001','科麦斯五金工具套装',368,25543,'德国家用工具箱电工维修组合套装组套带电钻',1,4),
 ('5f83e5ec4ac990ab014ac99849490002','AK五金工具箱套装',338,331,'多功能家用手动组合工具',1,4),
 ('5f83e5ec4ac990ab014ac9990ebd0003','金德玮',228,965,'木工家用多功能微型电动电钻五金装修工具箱套装组合',1,4),
 ('5f83e5ec4ac9998a014ac99c83f90001','埃维特五金工具套装',368,944,'德国多功能家用手动组合工具箱',1,4),
 ('5f83e5ec4ac9998a014ac99d2b8f0002','汉顿德',226,153,'工具箱组合组套',1,4),
 ('5f83e5ec4ac9998a014ac99da0ac0003','史丹利',399,23,'0件家用工具套装 五金工具箱',1,4),
 ('5f83e5ec4ac9998a014ac99eb5290004','荣力斯不锈钢导轨',6,848,'抽屉轨道三节轨加厚 三节静音导轨 五金配件',1,4),
 ('5f83e5ec4ac9998a014ac9a010290005','卡贝挂衣杆',13.8,107,'加厚衣柜挂衣杆法兰固定衣杆托挂杆衣橱五金配件',1,4),
 ('5f83e5ec4ac9998a014ac9a0a89c0006','弗里特伸缩裤架',69,976,'加厚 推拉西裤架 衣柜五金多功能挂裤架 裤抽架',1,4),
 ('5f83e5ec4ac9998a014ac9a17f310007','佳宝丽欧式门锁',96,42,'室内门锁 卧室房门锁 实木门把手 锁具三件套 装象牙白',1,4),
 ('5f83e5ec4ac9998a014ac9a2f3c40008','德莱',168,63,'空转防盗门超b级锁芯c级双叶片锁芯大门入户门锁心6代通用型',1,4),
 ('5f83e5ec4ac9998a014ac9a40f630009','汇泰龙抽屉轨道三节轨',37.8,972,'三节抽屉道轨导轨滑轨 家具五金轨道 精密滚珠设计 开合顺畅舒适',1,4),
 ('5f83e5ec4ac9998a014ac9a4bceb000a','宝雕伸缩裤架加厚推拉西裤架',68,20100,'推拉裤架衣柜五金 多功能挂裤架阻尼',1,4),
 ('5f83e5ec4ac9998a014ac9a54d57000b','美国玛斯特锁',29.9,573,'千层锁长柄锁 仓库宿舍大门挂锁 防盗防撬防水 正品',1,4),
 ('5f83e5ec4ac9998a014ac9a733aa000c','凯斯曼',118,147,'独家电镀饰面，全网首发；全通体纯铜锁芯 、50万开启寿命、实心把手 、58静音轴承锁体。十年质保！数量有限，抢完即止！享受30降价补差。',1,4),
 ('5f83e5ec4ac9998a014ac9a8e31e000d','牧羊少年奇幻之旅',17,467,'迄今语种超《圣经》的书 ',1,7),
 ('5f83e5ec4ac9998a014ac9aa1ecd000e','小王子(60周年纪念版)精装',18.5,2245,'是一本足以让人永葆童心的不朽经典，被全球亿万读者誉为最值得收藏的书。',1,7),
 ('5f83e5ec4ac9998a014ac9aad748000f','老人与海',19.9,411,'海明威编著的《老人与海》讲述了老渔夫圣地亚哥在海上连续84天没有捕到鱼。起初。有一个叫曼诺林的男孩跟他一道出海。',1,7),
 ('5f83e5ec4ac9998a014ac9ac71240010','父与子全集',13.5,16368,'正版预定包邮 快乐大本营推荐儿童绘本漫画书籍 父与子全集 彩色英汉双语版 与《小王子》媲美的经典畅销寓言童话故事图书籍月底',1,7),
 ('5f83e5ec4ac9998a014ac9ad7e8f0011','我的奋斗',28,156,'《生命不息，折腾不止》是罗永浩2009——2014年的“人生奋斗”全收录。',1,7),
 ('5f83e5ec4ac9998a014ac9ae6eeb0012','正说清朝十二帝',27.88,12,'（增订图文本）9787101044454阎崇年畅销书箱',1,7),
 ('5f83e5ec4ac9998a014ac9afe6910013','那些回不去的年少时光',31.8,246,'新版 史上最受欢迎的青春怀旧小说，桐华经典言情作品，写给年少自己的书。唯美礼盒套装，全文修正，全二册完美收藏，纪念我们共同的青春和成长。',1,7),
 ('5f83e5ec4ac9998a014ac9b0910c0014','步步惊心',20,17,'(新版)(附精美明信片) 比那些回不去的年少时光长相思还',1,7),
 ('5f83e5ec4ac9998a014ac9b12a010015','最美的时光',18,37,' 桐华 继《步步惊心》《大漠谣》《那些回不去的年少时光》长相思后都市爱情小说 青春文学',1,7),
 ('5f83e5ec4ac9998a014ac9b3ac9e0016','云中歌',45.8,2917,'虽然是虚构，却又是有血有肉的人，值得回味~',1,7),
 ('5f83e5ec4ac9998a014ac9b59d8f0017','当时只道是寻常',18.2,394,'安意如以诗来解纳兰心，家家争唱饮水词，纳兰心思几人知~',1,7),
 ('5f83e5ec4ac9998a014ac9b6f4980018','唐诗三百首',21,84,'我国被誉为诗的国度，诗歌创作源远流长，而唐朝是中国诗歌发展的黄金时期。在这个历史时期，诗歌创作结出了丰硕的成果。其中不少名篇佳句脍炙人口，传诵至今，受到一代又一代读者的喜爱。',1,7),
 ('5f83e5ec4ac9998a014ac9b7a9420019','文明之光',123,67,'计算机科学书籍 体会科技与人文之美 畅销书',1,7),
 ('5f83e5ec4ac9998a014ac9b8827e001a','狼图腾',18,1081,'法国著名电影导演让·雅克·阿诺导演，冯绍峰、窦骁主演，电影原著小说，原版小说，一部描绘、研究蒙古草原狼的“旷世奇书”',1,7),
 ('5f83e5ec4ac9998a014ac9b934d4001b','秘密',22,265,'朗达拜恩 励志书籍 畅销书 史上最畅销的心灵励志书，全球第一畅销书籍 全集 秘密(精)\n中国唯一最新正版独家授权！全球销售突破1500万册！第八届作家富豪榜经典上榜作品！',1,7),
 ('5f83e5ec4ac9998a014ac9ba6f58001c','周杰伦:Jay同名专辑',34.5,237,'第1张专辑 上海音像版(CD)',1,8),
 ('5f83e5ec4ac9998a014ac9bb6329001d','孙燕姿 yanzi',18,15,'首张同名专辑 美卡音像发行 正版CD',1,8),
 ('5f83e5ec4ac9998a014ac9bc8da2001e','古巨基 有你这一天CD',22.93,2,'新曲+精选 上海音像',1,8),
 ('5f83e5ec4ac9998a014ac9bd0a71001f','范玮琪：最初的梦想',26,3,'超级梦幻版 新歌+精选 2CD',1,8),
 ('5f83e5ec4ac9998a014ac9bdb7710020','张惠妹:我要飞',39.73,3,'爱永远不会消失(2CD)新歌精选集 上海音像',1,8),
 ('5f83e5ec4ac9998a014ac9beb43e0021','李斯特钢琴作品',112,9,'原装进口CD 环球 预售',1,8),
 ('5f83e5ec4ac9998a014ac9bf4fe00022','理查德·克莱德曼：爱相随',16,8,'敦煌音像正版',1,8),
 ('5f83e5ec4ac9998a014ac9bff3c50023','发烧三声部',48,8,'古典音乐 ANTONNIN DVORAK 安东.德沃夏克 DVD CD音像 GCD-6172',1,8),
 ('5f83e5ec4ac9998a014ac9c132a80024','西城男孩《咫尺天涯》',29.9,3,'白金全胜版 CD 上海音像',1,8),
 ('5f83e5ec4ac9998a014ac9c29d120025','韩红-青藏高原',121,8,'LPCD [进口原装正版] -预售',1,8),
 ('5f83e5ec4ac9998a014ac9c35d430026','儿童脑力革命',30,50,'教材书+DVD 0-3岁 宝宝全脑多元智能启蒙音像光盘\n风靡全球、最科学、最有效的全脑育儿法，决定孩子一生的脑力启蒙训练。',1,8),
 ('5f83e5ec4ac9998a014ac9c3e4910027','正版迪士尼神奇英语12dvd视频光盘儿童教育音像教材英文动画碟片',69,46,'《神奇新概念英语》把英语教学中视、听、说环节紧密融汇在一起，通过卡通形象，营造了足以使孩子们理解、接受所要教授内容的环境。',1,8),
 ('5f83e5ec4ac9998a014ac9c4d4fa0028','1987维也纳新年音乐会',92,10,'卡拉扬 大奖系列 CD -预售',1,8),
 ('5f83e5ec4ac9998a014ac9c635e10029','泰勒斯威夫特专辑 red',69,128,'出道至今的三张录音室专辑都维持全球500万张以上的销售水平，缔造全球2200万张专辑与5000万张数位下载单曲的傲人销售',1,8),
 ('5f83e5ec4ac9998a014ac9c73859002a','泰勒斯威夫特 泰勒史薇芙特 同名专辑(CD)',35,9,'首张同名专辑占据告示牌专辑榜连续4年不间断，打破1997年以来纪录，连续24周称霸乡村榜，赢得全美5白金销量认证。',1,8),
 ('5f83e5ec4ac9998a014ac9c88afa002b','海尔KFR-35GW',2599,61,'大1.5匹宽带无氟变频壁挂式空调，第四代变频技术。',1,9),
 ('5f83e5ec4ac9998a014ac9c9b196002c','美的KFR-35GW',3099,2,'【领先科技】大1.5匹变频冷暖，抗菌银离子+冷触媒双滤网，LED智能显示，干燥智能清洁！',1,9),
 ('5f83e5ec4ac9998a014ac9cadf21002d','美的KFR-75QW',10299,9999,'【金羊贺新春，美的献礼】立减300，可参加首页的抽奖，限1.7-1.13',1,9),
 ('5f83e5ec4ac9998a014ac9cc3aaa002e','西门子KG23D1160W',2799,3,'提前加入购物车，陆续到货一级能效，226L大容量，保鲜室上次两层，拒绝二次污染',1,9),
 ('5f83e5ec4ac9998a014ac9cd2903002f','西门子KG23F1830W',3799,36,'①224L时尚浅金色，实用又耐看②西门子专利技术：零度生物保鲜技术+硅滤保湿，食物不结冰，保湿更保鲜！③独立三循环制冷，精确控温。',1,9),
 ('5f83e5ec4ac9998a014ac9ce13a00030','西门子BCD-610W',7499,44,'【原装进口压缩机/宝马提供商/1分钟享受冰啤】',1,9),
 ('5f83e5ec4ac9998a014ac9d0ded40031','Midea/美的 F60-21WB1(遥控)热水器',1299,480,'电时代任性购<》一级能效 无线红外遥控 可实时预约 E+增容模式 延迟洗浴时间 可分胆加热 半整胆加热',1,9),
 ('5f83e5ec4ac9998a014ac9d787370032','电器城Haier/海尔 ES60H-Z4(ZE)海尔电热水器60升',1399,45,'适合3~4人洗浴；可半胆加热；机控、线控二选一；360度保温，可延时预约、断电记忆；专利金刚三层胆，防电墙',1,9),
 ('5f83e5ec4ac9998a014ac9d8e79f0033','Samsung/三星 UA48HU5900J 48寸UHD',4888,5,'全新UHD技术，超高清、真4K电视、四核智能、WIFI网络、一屏双享、秒速开机！',1,9),
 ('5f83e5ec4ac9998a014ac9d9f1da0034','LG 42LY320C-CA',4888,84,'HDMI 是高清接口使电视画面更加清晰 流畅,RGB 是电脑接口 使用更加方便,快捷,外置扬声器输出,使音响效果更加震撼.',1,9),
 ('5f83e5ec4ac9998a014ac9db18f60035','联想台式机 F5005 台式电脑全套整机',3228,244,'家用四核机型，主流四核，搭配2G独显，高端配置，满足日常和游戏需求。',1,10),
 ('5f83e5ec4ac9998a014ac9dbbfbe0036','联想一体机电脑C455',3328,210,'全新AMD四核APU-A6搭配4G内存，家用办公高性价比机型！',1,10),
 ('5f83e5ec4ac9998a014ac9dcf5070037','现货APPLE/苹果 ME089CH/A 27寸台式一体机新款电脑iMac089',13888,4,'Mac电脑享受1年全球联保，一年内出现任何质量问题均可在全国指定售后维修点免费维修或者更换，售后无忧有保障。',1,10),
 ('5f83e5ec4ac9998a014ac9de47530038','苹果 MF883CH/A 21.5寸台式一体机电脑',7558,7,'Mac电脑享受1年全球联保，一年内出现任何质量问题均可在全国指定售后维修点免费维修或者更换，售后无忧有保障。',1,10),
 ('5f83e5ec4ac9998a014ac9df49730039','清华同方超扬A850台式电脑',4199,4,'现货顺丰包邮，清华同方超扬A850台式电脑整机，四核酷睿i5，2G独显，4G 500G，21.5英寸液晶显示屏',1,10),
 ('5f83e5ec4ac9998a014ac9e068a7003a','正品良品家纺',188,128308,'无印纯棉四件套全棉包邮 床上用品田园婚庆被套床单\n送全国包邮 送金纺洗衣液',1,11),
 ('5f83e5ec4ac9998a014ac9e1bab3003b','齐飞家纺 纯棉四件套',70,5290,'。。上帝啊。。 ┊　┊ 　全棉，此价格 错过真的可惜! ',1,11),
 ('5f83e5ec4ac9998a014ac9e27367003c','月光之恋 全棉家居四件套',169,1213,'2014全新工艺四件套，100%纯棉亲肤舒适，缩水率低，环保健康印染。全棉套件 环保工艺 绿色床品！！！',1,11),
 ('5f83e5ec4ac9998a014ac9ef7ef1003d','智能垃圾桶',129,37,'【健康、环保】24小时防菌防异味，智能开启，省电节能；',1,11),
 ('5f83e5ec4ac9998a014ac9f07cd5003e','木晖',11,838,'壁挂垃圾袋收纳盒厨房用品抽取盒 创意家居环保塑料袋储物架',1,11),
 ('5f83e5ec4ac9998a014ac9f2741a003f','华美仕 黑色皮革纸巾盒',25,73,'销量第一皮革工厂店，自有工厂，专业皮革，更有保障！',1,11),
 ('5f83e5ec4ac9998a014ac9f3e74b0040','振兴家居用品迷你桌面收纳盒',12.8,14,'办公室台面塑料置物盒文具收纳盒子',1,11),
 ('5f83e5ec4ac9998a014ac9f4d1c20041','蓝格子',17.9,159,'韩国创意多格遥控器收纳盒 DIY木质可爱家居用品',1,11),
 ('5f83e5ec4ac9998a014ac9f5db690042','正品希艺欧/CEO',6,91,'家居用品 粗纤维沐浴用品 CEO-691 多彩舒肤浴擦',1,11),
 ('5f83e5ec4ac9998a014ac9f664070043','晶迪水晶洗手液瓶',110,995,'欧式卫浴家居日用品乳液瓶 沐浴露玻璃按压瓶\n立体新设计 高品质显彰品味，可以放乳液、洗手液、洗洁精；',1,11),
 ('5f83e5ec4ac9998a014ac9f758b40044','妮维雅男士洗面奶',39,26951,'全网销量第一男士洁面泥（2013.8-2014.10数据魔方统计结果），月销量50000件！男士必备！男人累了，就对自己好点~',1,12),
 ('5f83e5ec4ac9998a014ac9f80b1c0045','妮维雅 水活畅透精华露50g',79,3272,'水活畅透精华露7g*2支+深层滋润护手霜25ml+精油手工皂1块；瞬间化水，持久滋润不黏腻，快速吸收急速补水持久滋润一整天┊┊跑男第一选择',1,12),
 ('5f83e5ec4ac9998a014ac9f925590046','大宝护肤组合',92,881,'【白】天然海藻精华 全程抵御紫外线 【富】专业补水保湿滋养,清爽控油 【美】隔离彩妆,毛孔隐型　',1,12),
 ('5f83e5ec4ac9998a014ac9fa0c680047','正品大宝SOD蜜',15.9,25277,'保湿乳液 补水面霜 男女士护肤 【护手霜60g+SOD蜜100ml】包邮！秋冬必备两宝！超低价包邮！',1,12),
 ('5f83e5ec4ac9998a014ac9fb9ed90048','温碧泉套装',119,3429,'专柜正品八杯水护肤品套装补水美白保湿化妆品面部护理',1,12),
 ('5f83e5ec4ac9998a014ac9fcbf5c0049','雅诗兰黛小棕瓶眼霜',560,299,'NR眼部精华霜15ml紧致补水保湿去黑眼圈眼袋 抵御多屏光污染，提升眼周肌修护力	',1,12),
 ('5f83eb404acc1f29014accb1a6de0003','google glass',3000,18,'Google Project Glass主要结构包括，\n\n在眼镜前方悬置的一台摄像头和一个位于镜框右侧的宽条状的电脑处理器装置，配备的摄像头像素为 500 万，可拍摄 720p 视频。镜片上配备了一个头戴式微型显示屏，它可以将数据投射到用户右眼上方的小屏幕上。显示效果如同 2.4 米外的 25 英寸高清屏幕。\n还有一条可横置于鼻梁上方的平行鼻托和鼻垫感应器，鼻托可调整，以适应不同脸型。在鼻托里植入了电池，它能够辨识眼镜是否被佩戴的。电池可以支持一天的正常使用，充电可以用 Micro USB 接口或者专门设计的充电器。根据环境声音在屏幕上显示距离和方向，在两块目镜上分别显示地图和导航信息技术的产品。\nGoogle Project Glass的重量只\n\nProject Glass\n有几十克，内存为682MB，使用的操作系统是 Android 4.0.4版本号为Ice Cream Sandwich ，所使用的CPU为德州仪器生产的OMAP 4430处理器。这块晶片2011 年曾被用在摩托罗拉生产的两款手机Droid Bionic 和 Atrix 2上。音响系统采用骨导传感器。网络连接支持蓝牙和 Wifi - 802.11b/g。总存储容量为 16GB，与 Google Cloud 同步。配套的 My Glass 应用需要 Android 4.0.3 或者更高的系统版本；MyGlass 应用需要打开 GPS 和短信发送功能。',1,2),
 ('5f83eb404acc1f29014acd55e2810006','apple watch',5000,9,'苹果watch',1,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_goods_type`
--

/*!40000 ALTER TABLE `app_goods_type` DISABLE KEYS */;
INSERT INTO `app_goods_type` (`goods_type_id`,`type_name`,`type_intro`) VALUES 
 (2,'3C数码','数码产品hiahiahia'),
 (3,'食品','吃的'),
 (4,'五金','五金用品'),
 (7,'书籍','书籍是人类进步的阶梯--高尔基'),
 (8,'音像','mu-mu-music'),
 (9,'家用电器','家用的~插电的~'),
 (10,'电脑','台式机'),
 (11,'家居用品','给你一个舒适的空间'),
 (12,'个护化妆','人活着，一定要学会保养~'),
 (13,'奢侈品','高端大气上档次~'),
 (14,'服装内衣','展现外在美的途径~'),
 (15,'运动户外','热爱运动，热爱森活~'),
 (16,'汽车用品','汽车也需要主人的关爱~'),
 (17,'母婴玩具','给孩子一个温馨快乐的童年~');
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
 ('123','402881e94ab09f53014ab0a08ac60002','5f8399184a94b11d014a94b148800001',1,100,1,1,1,NULL,'2015-01-08 20:53:07','2015-01-07 11:45:15','2015-01-08 20:53:13',1,'1234num'),
 ('5f83e5ec4ac974a8014ac98e4258000e','5f83e5ec4ac95d3f014ac96f05e30001','5f83e5ec4ac974a8014ac98de055000d',1,38,1,1,1,'2015-01-08 20:39:11','2015-01-08 20:42:46','2015-01-09 09:24:19',NULL,0,'LP00003938149313'),
 ('5f83e5ec4acc4207014acc45e9080001','5f83e5ec4ac9998a014ac9a8e31e000d','5f83e5ec4ac974a8014ac98de055000d',1,17,1,0,0,'2015-01-09 09:19:01',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc47461e0002','5f8328514aca13a7014aca3738970014','5f83e5ec4ac974a8014ac98de055000d',1,20,1,0,0,'2015-01-09 09:20:30',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc487bcb0003','297ef1a44ac6db73014ac6e2cd50000c','5f83e5ec4acc2d18014acc3148070003',1,1800,1,1,1,'2015-01-09 09:21:50','2015-01-09 09:22:19','2015-01-09 09:47:14',NULL,0,'123456789'),
 ('5f83e5ec4acc4207014acc48acea0004','402881e94ab0b273014ab0b3110f0001','5f83e5ec4acc2d18014acc3148070003',1,5000,1,0,0,'2015-01-09 09:22:02',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc4c66950005','5f83e5ec4ac9998a014ac9b7a9420019','5f83e5ec4acc2d18014acc36d4910006',1,123,1,0,0,'2015-01-09 09:26:06',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc4ca4cf0006','297ef1a44ac6db73014ac6e164350009','5f83e5ec4acc2d18014acc36d4910006',1,100,1,0,0,'2015-01-09 09:26:22',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc4d115d0007','5f83e5ec4ac9998a014ac9cd2903002f','5f83e5ec4acc2d18014acc2fcb3a0002',1,3799,1,0,0,'2015-01-09 09:26:50',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc4d3d200008','5f83e5ec4ac9998a014ac9d9f1da0034','5f83e5ec4acc2d18014acc2fcb3a0002',1,4888,1,0,0,'2015-01-09 09:27:01',NULL,NULL,NULL,0,NULL),
 ('5f83e5ec4acc4207014acc4e9ab90009','402881e94ab0b273014ab0b3110f0001','5f83e5ec4acc2d18014acc3300ae0005',1,5000,1,1,0,'2015-01-09 09:28:31','2015-01-09 09:28:41',NULL,NULL,0,NULL),
 ('5f83eb404acc1f29014acc5d32c80002','5f8328514aca13a7014aca3c2f790018','5f8399184a94b11d014a94b148800001',1,79,1,0,0,'2015-01-09 09:44:27',NULL,NULL,NULL,0,NULL),
 ('5f83eb404acc1f29014accb2b6a40005','5f83eb404acc1f29014accb1a6de0003','5f83eb404acc1f29014accb2395e0004',2,6000,1,1,1,'2015-01-09 11:17:51','2015-01-09 11:18:05','2015-01-09 11:18:43','2015-01-09 11:19:08',1,'123456789'),
 ('5f83eb404acc1f29014acd581f5c0008','5f83eb404acc1f29014acd55e2810006','5f83eb404acc1f29014acd56ee0b0007',1,5000,1,1,1,'2015-01-09 14:18:32','2015-01-09 14:18:56','2015-01-09 14:19:56','2015-01-09 14:20:22',1,'12345678');
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
 ('5f8399184a94b11d014a94b148800001','admin','admin','202cb962ac59075b964b07152d234b70',1,0,'ID000','root@sina.com','root地址'),
 ('5f83e5ec4ac974a8014ac98de055000d','mz','mz','202cb962ac59075b964b07152d234b70',1,1,'ID002','mzhuo1993@163.com','九龙湖'),
 ('5f83e5ec4acc2d18014acc2f3e650001','root','root','202cb962ac59075b964b07152d234b70',1,0,'ID000','root@163.com','root地址'),
 ('5f83e5ec4acc2d18014acc2fcb3a0002','goodsAdmin','goodsAdmin','202cb962ac59075b964b07152d234b70',1,0,'ID001','jall2014@163.com','jall地址'),
 ('5f83e5ec4acc2d18014acc3148070003','智障儿童欢乐多','智障儿童欢乐多','202cb962ac59075b964b07152d234b70',1,0,'ID002','',''),
 ('5f83e5ec4acc2d18014acc320edf0004','taylor','taylor','202cb962ac59075b964b07152d234b70',1,1,'ID001','taylor@163.com',''),
 ('5f83e5ec4acc2d18014acc3300ae0005','orderAdmin','orderAdmin','202cb962ac59075b964b07152d234b70',1,1,'ID003','bobo@google.com',''),
 ('5f83e5ec4acc2d18014acc36d4910006','神秘叉烧','神秘叉烧','202cb962ac59075b964b07152d234b70',1,0,'ID003','神秘叉烧@google.com','叉烧地址'),
 ('5f83eb404acc1f29014accb2395e0004','test','test','202cb962ac59075b964b07152d234b70',1,NULL,'ID002','test',NULL),
 ('5f83eb404acc1f29014acd56ee0b0007','consumer','consumer','202cb962ac59075b964b07152d234b70',1,NULL,'ID002','jallenjia@qq.com',NULL);
/*!40000 ALTER TABLE `core_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
