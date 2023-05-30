-- create database
CREATE
database glass_bottle CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `mbti_metadata`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'mbti',
    `description` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Mbti 설명',
    `created_at`  datetime                                DEFAULT NULL COMMENT '생성일',
    `modified_at` datetime                                DEFAULT NULL COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
