USE alt;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS part;
CREATE TABLE part (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  created_by varchar(255) DEFAULT NULL,
  created_date datetime DEFAULT NULL,
  last_modified_by varchar(255) DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  body_part varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  image_url varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  predefined bit(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_ikrq92m53w7ybikb2cmdb74ly (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS action;
CREATE TABLE action (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  created_by varchar(255) DEFAULT NULL,
  created_date datetime DEFAULT NULL,
  last_modified_by varchar(255) DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  equipment varchar(255) DEFAULT NULL,
  image_url varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  predefined bit(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_p6dhhp25fj7w2vok63k0vrsv (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS part_action;
CREATE TABLE part_action (
  part_id bigint(20) NOT NULL,
  action_id bigint(20) NOT NULL,
  PRIMARY KEY (action_id,part_id),
  KEY FK75oarypdelagsonw4rv0g7sp0 (part_id),
  CONSTRAINT FK75oarypdelagsonw4rv0g7sp0 FOREIGN KEY (part_id) REFERENCES part (id),
  CONSTRAINT FKhuo5footpefuolivv3l3ogjwr FOREIGN KEY (action_id) REFERENCES action (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;