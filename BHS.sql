/*
SQLyog Ultimate
MySQL - 5.7.37 : Database - bhs_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `ad_user` */

CREATE TABLE `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Cot nay la ID',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anonymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anonymous',
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `active_key` text COLLATE utf8mb4_unicode_ci,
  `reset_key` text COLLATE utf8mb4_unicode_ci,
  `is_locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `booking` */

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_profile_id` bigint(20) DEFAULT NULL,
  `hair_stylist_id` bigint(20) DEFAULT NULL,
  `voucher_option_id` bigint(20) DEFAULT NULL,
  `voucher_user_id` bigint(20) DEFAULT NULL,
  `date_time` date DEFAULT NULL,
  `start_time` text COLLATE utf8mb4_vietnamese_ci,
  `end_time` text COLLATE utf8mb4_vietnamese_ci,
  `total_duration` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `phone_number` varchar(13) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `note` text COLLATE utf8mb4_vietnamese_ci,
  `status` text COLLATE utf8mb4_vietnamese_ci,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT 'anonymus',
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT 'anonymus',
  PRIMARY KEY (`id`),
  KEY `fk_user_profile_id` (`user_profile_id`),
  KEY `fk1_voucher_user_id` (`voucher_user_id`),
  KEY `fk1_hair_stylist_id` (`hair_stylist_id`),
  KEY `fk1_voucher_option_id` (`voucher_option_id`),
  CONSTRAINT `fk1_hair_stylist_id` FOREIGN KEY (`hair_stylist_id`) REFERENCES `hair_stylist` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_profile_id` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=381 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

/*Table structure for table `booking_option_mapping` */

CREATE TABLE `booking_option_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_id` bigint(20) DEFAULT NULL,
  `option_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk3_booking_id` (`booking_id`),
  KEY `fk3_option_id` (`option_id`),
  CONSTRAINT `fk3_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk3_option_id` FOREIGN KEY (`option_id`) REFERENCES `options` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=531 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `categories` */

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `hair_stylist` */

CREATE TABLE `hair_stylist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stylist_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anonymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anonymous',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `option_image` */

CREATE TABLE `option_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_src` longblob,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `option_id` bigint(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`),
  KEY `fk_options` (`option_id`),
  CONSTRAINT `fk_options` FOREIGN KEY (`option_id`) REFERENCES `options` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `options` */

CREATE TABLE `options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `option_name` text COLLATE utf8mb4_unicode_ci,
  `service_id` bigint(20) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`),
  KEY `fk_service` (`service_id`),
  CONSTRAINT `fk_service` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `rating` */

CREATE TABLE `rating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_id` bigint(20) DEFAULT NULL,
  `star` bigint(20) DEFAULT NULL,
  `note` text COLLATE utf8mb4_vietnamese_ci,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT 'anonymus',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT 'anonymus',
  PRIMARY KEY (`id`),
  KEY `fk_booking_id` (`booking_id`),
  CONSTRAINT `fk_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

/*Table structure for table `schedule_booking` */

CREATE TABLE `schedule_booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `schedule_id` bigint(20) DEFAULT NULL,
  `booking_note` text COLLATE utf8mb4_unicode_ci,
  `description` text COLLATE utf8mb4_unicode_ci,
  `status` text COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`),
  KEY `fk_stylist_schedule` (`schedule_id`),
  CONSTRAINT `fk_stylist_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `stylist_schedule` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `services` */

CREATE TABLE `services` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `categories_id` bigint(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`),
  KEY `fk_categories` (`categories_id`),
  CONSTRAINT `fk_categories` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `shift` */

CREATE TABLE `shift` (
  `id` bigint(20) NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `duration` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `stylist_schedule` */

CREATE TABLE `stylist_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shift_id` bigint(20) DEFAULT NULL,
  `stylist_id` bigint(20) DEFAULT NULL,
  `date_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shift` (`shift_id`),
  KEY `fk_stylish` (`stylist_id`),
  CONSTRAINT `fk_shift` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_stylish` FOREIGN KEY (`stylist_id`) REFERENCES `hair_stylist` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `user_profile` */

CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'M: Male, F: Female, O: Other',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_of_birth` timestamp NULL DEFAULT NULL,
  `profile_image` longblob,
  `user_id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anonymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anonymous',
  PRIMARY KEY (`id`),
  KEY `fk_ad_user` (`user_id`),
  CONSTRAINT `fk_ad_user` FOREIGN KEY (`user_id`) REFERENCES `ad_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `voucher_option_mapping` */

CREATE TABLE `voucher_option_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `option_id` bigint(20) DEFAULT NULL,
  `voucher_option_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_voucher_option` (`voucher_option_id`),
  KEY `-1` (`id`),
  KEY `fk_options_id` (`option_id`),
  CONSTRAINT `fk_options_id` FOREIGN KEY (`option_id`) REFERENCES `options` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_voucher_option` FOREIGN KEY (`voucher_option_id`) REFERENCES `voucher_options` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `voucher_options` */

CREATE TABLE `voucher_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `day_start` date DEFAULT NULL,
  `day_expire` date DEFAULT NULL,
  `is_actived` tinyint(1) DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `voucher_user` */

CREATE TABLE `voucher_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `day_start` date DEFAULT NULL,
  `day_expire` date DEFAULT NULL,
  `is_actived` tinyint(1) DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'anounymous',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `voucher_user_mapping` */

CREATE TABLE `voucher_user_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `voucher_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_aduser_id` (`user_id`),
  KEY `fk_voucher_user_id` (`voucher_user_id`),
  CONSTRAINT `fk_aduser_id` FOREIGN KEY (`user_id`) REFERENCES `ad_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_voucher_user_id` FOREIGN KEY (`voucher_user_id`) REFERENCES `voucher_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
