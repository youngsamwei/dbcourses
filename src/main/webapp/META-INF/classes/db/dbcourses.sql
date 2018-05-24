
DROP TABLE IF EXISTS `knowledgepoint`;

CREATE TABLE `knowledgepoint` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `knowledgepointName` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '知识点名称',
  `knowledgepointCreateDate` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建时间',
  `addName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `paragraph`;

CREATE TABLE `paragraph` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `knowledgepointId` int(4) NOT NULL COMMENT '所属的知识点的编号',
  `paragraphOrder` int(4) COLLATE utf8_bin NOT NULL DEFAULT 0 COMMENT '段落顺序编号',
  `paragraphTitle` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '段落标题',
  `paragraphContent` text COLLATE utf8_bin NOT NULL COMMENT '段落内容',
  `paragraphCreateDate` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建时间',
  `addName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '添加人',
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`knowledgepointId`) REFERENCES `knowledgepoint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
