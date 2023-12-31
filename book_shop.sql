/*
 Navicat Premium Data Transfer

 Source Server         : demon
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : book_shop

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 31/12/2023 22:17:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `book_available` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, 'Naruto', 'Kishimoto', '1999', '499', 'yes');
INSERT INTO `books` VALUES (2, 'Dragon Ball', 'Toriyama', '1984', '499', 'yes');

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `customer_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `customer_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, 'lucifer', 'lucifer', 'lucifer');
INSERT INTO `customers` VALUES (2, 'lucifer', 'lucifer@hell.com', 'Lucifr123');
INSERT INTO `customers` VALUES (3, 'lucifer', 'lucifer@hell.com', 'Lucifr123');
INSERT INTO `customers` VALUES (4, 'lucifer', 'lucifer@hell.com', 'Lucifr123');

-- ----------------------------
-- Table structure for issued_books
-- ----------------------------
DROP TABLE IF EXISTS `issued_books`;
CREATE TABLE `issued_books`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `issued_date` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `due_date` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `returned` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `returned_on` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `book_id`(`book_id` ASC) USING BTREE,
  INDEX `customer_id`(`customer_id` ASC) USING BTREE,
  CONSTRAINT `issued_books_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `issued_books_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of issued_books
-- ----------------------------
INSERT INTO `issued_books` VALUES (1, 1, 1, '30-12-2023', '07-01-2024', 'no', NULL);

-- ----------------------------
-- Table structure for owners
-- ----------------------------
DROP TABLE IF EXISTS `owners`;
CREATE TABLE `owners`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `mail_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pasword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owners
-- ----------------------------
INSERT INTO `owners` VALUES (1, 'lucifer@hll.com', 'lucifer123');

-- ----------------------------
-- Procedure structure for usp_addbook
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_addbook`;
delimiter ;;
CREATE PROCEDURE `usp_addbook`(IN bname VARCHAR(255), IN bauthor VARCHAR(255), IN byear VARCHAR(255), IN bprice VARCHAR(255), IN bavailability VARCHAR(5), OUT latest_id INT)
BEGIN
INSERT INTO books(book_name,book_author,book_year,book_price,book_available) VALUES(bname,bauthor,byear,bprice,bavailability);

SET latest_id = LAST_INSERT_ID();
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_addcustomer
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_addcustomer`;
delimiter ;;
CREATE PROCEDURE `usp_addcustomer`(IN customer_name VARCHAR(255), IN customer_email VARCHAR(255), IN customer_password VARCHAR(255), OUT latest_id INT)
BEGIN
INSERT INTO customers(customer_name,customer_email,customer_password) VALUES(customer_name,customer_email,customer_password);

SET latest_id = LAST_INSERT_ID();
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_allbooks
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_allbooks`;
delimiter ;;
CREATE PROCEDURE `usp_allbooks`()
BEGIN
select id,book_name,book_author,book_year,book_price,book_available from books;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_allcustomers
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_allcustomers`;
delimiter ;;
CREATE PROCEDURE `usp_allcustomers`()
BEGIN
select id,customer_name,customer_email from customers;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_allissuedbooks
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_allissuedbooks`;
delimiter ;;
CREATE PROCEDURE `usp_allissuedbooks`()
BEGIN
	#Routine body goes here...
select book_id,book_name,customer_id,customer_name,issued_date,due_date from issued_books INNER JOIN books on issued_books.book_id=books.id INNER JOIN customers on issued_books.customer_id=customers.id where issued_books.returned='no' OR issued_books.returned='No';

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_allreturnedbooks
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_allreturnedbooks`;
delimiter ;;
CREATE PROCEDURE `usp_allreturnedbooks`()
BEGIN
	#Routine body goes here...
	select book_id,book_name,customer_id,customer_name,issued_date,returned_on from issued_books INNER JOIN books on issued_books.book_id=books.id INNER JOIN customers on issued_books.customer_id=customers.id where issued_books.returned='yes' OR issued_books.returned='Yes';

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_getbookbyname
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_getbookbyname`;
delimiter ;;
CREATE PROCEDURE `usp_getbookbyname`(IN bname VARCHAR(255))
BEGIN
select id,book_name,book_author,book_year,book_price,book_available from books where book_name=bname;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_getcustomerbymail
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_getcustomerbymail`;
delimiter ;;
CREATE PROCEDURE `usp_getcustomerbymail`(IN customer_mail VARCHAR(255))
BEGIN
select id,customer_name,customer_email from customers where customer_email = customer_mail;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_issuebook
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_issuebook`;
delimiter ;;
CREATE PROCEDURE `usp_issuebook`(IN bid INT, IN cid INT, IN issue_date VARCHAR(500), IN due_date VARCHAR(500), IN returned VARCHAR(5))
BEGIN
INSERT INTO issued_books(book_id,customer_id,issued_date,due_date,returned) VALUES(bid,cid,issue_date,due_date,returned);

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_loginowner
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_loginowner`;
delimiter ;;
CREATE PROCEDURE `usp_loginowner`(IN mail varchar(255), in pasw VARCHAR(255))
BEGIN
	#Routine body goes here...
	
	select mail_id, pasword from owners where mail_id=mail AND pasword=pasw;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_registerowner
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_registerowner`;
delimiter ;;
CREATE PROCEDURE `usp_registerowner`(IN mail varchar(255), in pasword VARCHAR(255))
BEGIN
	#Routine body goes here...
	insert into owners(mail_id,pasword) VALUES(mail,pasword);

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_returnbook
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_returnbook`;
delimiter ;;
CREATE PROCEDURE `usp_returnbook`(IN bid INT, IN returned VARCHAR(500))
BEGIN
UPDATE issued_books SET returned = 'yes', returned_on = returned where book_id=bid;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for usp_updatebook
-- ----------------------------
DROP PROCEDURE IF EXISTS `usp_updatebook`;
delimiter ;;
CREATE PROCEDURE `usp_updatebook`(IN bid INT)
BEGIN
UPDATE books SET book_name=bname,book_author=bauthor,book_year=byear,book_price=bprice,book_available=bavailability WHERE id =bid;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
