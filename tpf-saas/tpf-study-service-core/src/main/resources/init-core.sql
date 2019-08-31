create database `tpf-study-core`

DROP TABLE IF EXISTS tpf_saas;
CREATE TABLE tpf_saas(
	id INT NOT NULL AUTO_INCREMENT COMMENT 'saas id',
	saas_name VARCHAR(20) NOT NULL COMMENT 'saas名',
	saas_alias VARCHAR(20) COMMENT 'saas别名',
	saas_dbconfig TEXT NOT NULL COMMENT '数据库配置',
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'saas表';


-- INSERT INTO tpf_saas(saas_name, saas_alias, saas_dbconfig)
-- value('tpf-study-saas1','测试saas1','{"db.driverClassName":"com.mysql.jdbc.Driver","db.username":"root","db.password":"123456","db.url":"jdbc:mysql://192.168.3.178:3306/tpf-study-saas1?useUnicode=true&characterEncoding=utf8"}');
