 create database swp391_team3;

 use swp391_team3;
CREATE TABLE `slide` (
  `slide_id` int AUTO_INCREMENT,
  `title` varchar(255),
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
  `brands_id` int,
  `discount` double,
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
  `image_name` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(image_id)
);

CREATE TABLE `image_product` (
  `image_product_id` int AUTO_INCREMENT,
  `product_id` int,
  `image_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(image_product_id)
);

CREATE TABLE `image_slide` (
  `image_slide_id` int AUTO_INCREMENT,
  `slide_id` int,
  `image_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(image_slide_id)
);

CREATE TABLE `categories` (
  `category_id` int AUTO_INCREMENT,
  `product_id` int,
  `category_name` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(category_id)
);

ALTER TABLE `user` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

ALTER TABLE `order` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`);

ALTER TABLE `brands` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `product` ADD FOREIGN KEY (`brands_id`) REFERENCES `brands` (`brands_id`);


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

ALTER TABLE `image_product` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `image_product` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `image_slide` ADD FOREIGN KEY (`slide_id`) REFERENCES `slide` (`slide_id`);

ALTER TABLE `image_slide` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `categories` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

-- INSERT 6 PRODUCT
INSERT image(image_name) VALUES ("Absorba");

INSERT brands(name, image_id, description) VALUES ("Absorba", 1, "");
INSERT product(name, price, description, specification, brands_id, discount) VALUES (
"Printed Long Sleeved T-Shirt Cream", 
299,
"Your little one can play all day in comfort. Cream T-Shirt by Absorba. The T-Shirt has a regular fit.
– Snap buttons at the shoulder.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 0.3);

INSERT image(image_name) VALUES ("PrintedLongSleevedT-ShirtCream.jpg");
INSERT image(image_name) VALUES ("PrintedLongSleevedT-ShirtCream1.jpg");
INSERT image(image_name) VALUES ("PrintedLongSleevedT-ShirtCream2..jpg");
INSERT image(image_name) VALUES ("PrintedLongSleevedTShirtCream3.jpg");

INSERT image_product(product_id, image_id) VALUES(1, 2);
INSERT image_product(product_id, image_id) VALUES(1, 3);
INSERT image_product(product_id, image_id) VALUES(1, 4);
INSERT image_product(product_id, image_id) VALUES(1, 5);

INSERT product(name, price, description, specification, brands_id, discount) VALUES (
"2-Pack Footed Baby Body Blue", 
409,
"Let your baby explore and play in comfort. Blue footed baby body by Absorba. Footed baby body has a soft velour material.
– Snap buttons at the back.
– This product is crafted with sustainable organic cotton.", 
"– 75% Organic Cotton, 25% Polyester.
– Machine washable 30 degrees.", 1, 0.4);

INSERT image(image_name) VALUES ("2-PackFootedBabyBodyBlue.jpg");
INSERT image(image_name) VALUES ("2-PackFootedBabyBodyBlue1.jpg");
INSERT image(image_name) VALUES ("2-PackFootedBabyBodyBlue2.jpg");
INSERT image(image_name) VALUES ("2-PackFootedBabyBodyBlue3.jpg");

INSERT image_product(product_id, image_id) VALUES(2, 6);
INSERT image_product(product_id, image_id) VALUES(2, 7);
INSERT image_product(product_id, image_id) VALUES(2, 8);
INSERT image_product(product_id, image_id) VALUES(2, 9);


INSERT product(name, price, description, specification, brands_id, discount) VALUES (
"Striped Shorts Indigo", 
229,
"The perfect shorts for a sunny day with friends. Blue shorts by Absorba. The shorts have an elasticated waist.
– Two front pockets.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 0.3);

INSERT image(image_name) VALUES ("StripedShortsIndigo.jpg");
INSERT image(image_name) VALUES ("StripedShortsIndigo1.jpg");

INSERT image_product(product_id, image_id) VALUES(3, 10);
INSERT image_product(product_id, image_id) VALUES(3, 11);

INSERT product(name, price, description, specification, brands_id, discount) VALUES (
"Printed Leggings Cream", 
229,
"Keep little legs comfy. Cream leggings by Absorba. The leggings have a ribbed, folded waist.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 0.2);

INSERT image(image_name) VALUES ("PrintedLeggingsCream.jpg");
INSERT image(image_name) VALUES ("PrintedLeggingsCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(4, 12);
INSERT image_product(product_id, image_id) VALUES(4, 13);


INSERT product(name, price, description, specification, brands_id, discount) VALUES (
"Printed One-piece Cream", 
299,
"Perfect for a good night's sleep. Cream one-piece by Absorba. The one-piece has a ribbed design.
– Snap buttons at the front and between the legs.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 0.3);

INSERT image(image_name) VALUES ("PrintedOne-pieceCream.jpg");
INSERT image(image_name) VALUES ("PrintedOne-pieceCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(5, 14);
INSERT image_product(product_id, image_id) VALUES(5, 15);

INSERT product(name, price, description, specification, brands_id, discount) VALUES (
"Printed Footed Baby Body Cream", 
299,
"Let your baby explore and play in comfort. Cream footed baby body by Absorba. The footed baby body has a soft velour material.
– Snap buttons at the back.
– This product is crafted with sustainable organic cotton.", 
"– 75% Organic Cotton, 25% Polyester.
– Machine washable 30 degrees.", 1, 0.4);

INSERT image(image_name) VALUES ("PrintedFootedBabyBodyCream.jpg");
INSERT image(image_name) VALUES ("PrintedFooted BabyBodyCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(6, 16);
INSERT image_product(product_id, image_id) VALUES(6, 17);



