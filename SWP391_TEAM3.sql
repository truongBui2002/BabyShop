-- create database swp391_team3;
CREATE TABLE `logo_website` (
  `logo_website_id` int AUTO_INCREMENT,
  `logo` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(logo_website_id)
);

CREATE TABLE `slide` (
  `slide_id` int AUTO_INCREMENT,
  `sub_website_id` int,
  `title` varchar(255),
  `upload_link` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(slide_id)
);

CREATE TABLE `role` (
  `role_id` int  AUTO_INCREMENT,
  `code` varchar(255),
  `name` varchar(255),
  `description` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(role_id)
);

CREATE TABLE `user` (
  `user_id` int AUTO_INCREMENT,
  `user_name` varchar(255),
  `pass_word` varchar(255),
  `full_name` varchar(255),
  `phone_number` varchar(255),
  `email` varchar(255),
  `role_id` int,
  `location_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(user_id)
);

CREATE TABLE `customer` (
  `customer_id` int AUTO_INCREMENT,
  `full_name` varchar(255),
  `phone_number` varchar(255),
  `email` varchar(255),
  `gender` boolean,
  `age` int,
  `address` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(customer_id)
);

CREATE TABLE `location` (
  `location_id` int AUTO_INCREMENT,
  `city` varchar(255),
  `stree` varchar(255),
  `building` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(location_id)
);

CREATE TABLE `brands` (
  `brands_id` int AUTO_INCREMENT,
  `code` varchar(255) UNIQUE,
  `name` varchar(255) UNIQUE,
  `image_id` int,
  `description` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(brands_id)
);

CREATE TABLE `product` (
  `product_id` int AUTO_INCREMENT,
  `name` varchar(255),
  `price` decimal,
  `description` longtext,
  `specification` longtext,
  `categories_id` int,
  `image_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(product_id)
);


CREATE TABLE `variant` (
  `variant_id` int AUTO_INCREMENT,
  `name` varchar(255), -- size
  `product_id` int,
  `variant_quantity` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(variant_id)
);

CREATE TABLE `order` (
  `order_id` int AUTO_INCREMENT,
  `code` varchar(255),
  `customer_id` int,
  `status` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(order_id)
);

CREATE TABLE `order_details` (
  `order_details_id` int AUTO_INCREMENT,
  `order_id` int,
  `product_id` int,
  `price` varchar(255),
  `variant_product` varchar(255),
  `profit` decimal,
  `discount` decimal,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(order_details_id)
);

CREATE TABLE `session` (
  `session_id` int AUTO_INCREMENT,
  `user_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(session_id)
);

CREATE TABLE `sub_feedback` (
  `sub_feedback_id` int AUTO_INCREMENT,
  `customer_id` int,
  `session_id` int,
  `feedback_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(sub_feedback_id)
);

CREATE TABLE `feedback` (
  `feedback_id` int AUTO_INCREMENT,
  `image_id` int,
  `rate_star` int,
  `user_id` int,
  `description` longtext,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(feedback_id)
);

CREATE TABLE `tip_page` (
  `tip_page_id` int AUTO_INCREMENT,
  `user_id` int,
  `title` varchar(255),
  `description` longtext,
  `content` longtext,
  `is_hidden` boolean,
  `img_link` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(tip_page_id)
);

CREATE TABLE `image` (
  `image_id` int AUTO_INCREMENT,
  `image_link` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(image_id)
);

ALTER TABLE `slide` ADD FOREIGN KEY (`sub_website_id`) REFERENCES `logo_website` (`logo_website_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

ALTER TABLE `order` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`);

ALTER TABLE `brands` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `product` ADD FOREIGN KEY (`categories_id`) REFERENCES `brands` (`brands_id`);

ALTER TABLE `product` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `variant` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

ALTER TABLE `session` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `sub_feedback` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `sub_feedback` ADD FOREIGN KEY (`session_id`) REFERENCES `session` (`session_id`);

ALTER TABLE `sub_feedback` ADD FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `tip_page` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
