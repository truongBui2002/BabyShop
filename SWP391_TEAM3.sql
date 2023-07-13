create database swp391_team3;

use swp391_team3;
ALTER DATABASE swp391_team3 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 
CREATE TABLE `slide` (
  `slide_id` int AUTO_INCREMENT,
  `title` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(slide_id)
);

CREATE TABLE `role` (
  `role_id` int  AUTO_INCREMENT,
  `name` varchar(255),
  `description` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(role_id)
);

CREATE TABLE `user` (
  `user_id` int AUTO_INCREMENT,
  `email` varchar(255),
  `phone_number` varchar(255),
  `password` varchar(255),
  `full_name` varchar(255) CHARSET utf8mb4, 
  `image_id` int,
  `dob` date,
  `status` varchar(255),
  `gender` boolean DEFAULT false,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(user_id)
);

CREATE TABLE `user_role`(
  `user_role_id` int AUTO_INCREMENT,
  `user_id` int,
  `role_id` int,
  PRIMARY KEY(user_role_id)
);

CREATE TABLE `customer` (
  `customer_id` int AUTO_INCREMENT,
  `user_id` int,
  `full_name` varchar(255),
  `status` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(customer_id)
);

CREATE TABLE location (
  `location_id` int AUTO_INCREMENT,
  `customer_id` int,
  `address` varchar(255),
  `phone_number` varchar(255),
  `status` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(location_id)
);

CREATE TABLE `brand` (
  `brand_id` int AUTO_INCREMENT,   
  `name` varchar(255) UNIQUE,
  `image_id` int,
  `description` longtext,
  `status` varchar(255) DEFAULT "UNLOCK",
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(brand_id) 
);

CREATE TABLE `product` (
  `product_id` int AUTO_INCREMENT,
  `name` varchar(255),
  `price` decimal,
  `description` longtext,
  `specification` longtext,
  `brand_id` int,
  `subcategory_id` int,
  `discount` double,
  `status` varchar(255) DEFAULT "UNLOCK",
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(product_id)
);

CREATE TABLE `product_info` (
  product_info_id int AUTO_INCREMENT,
  product_id int,
  age_range varchar(255),
  gender varchar(255),
  color varchar(255),
  style varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(product_info_id)
);


CREATE TABLE `category` (
  `category_id` int AUTO_INCREMENT,
  `name` varchar(255),
  `description` longtext, -- new
  `image_id` int, -- new
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(category_id)
);

CREATE TABLE `subcategory`(
  `subcategory_id` int AUTO_INCREMENT,
  `name` varchar(255),
  `description` longtext, -- new
  `image_id` int, -- new
  `category_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(subcategory_id)
);

CREATE TABLE `variant` (
  `variant_id` int AUTO_INCREMENT,
  `product_id` int,
  `name` varchar(255), -- size
  `quantity` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(variant_id)
);

CREATE TABLE `orders` (
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
  `price` decimal,
  `variant_id` int,
  `quantity` int,
  `profit` decimal,
  `discount` decimal,
  `status` varchar(255),
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


CREATE TABLE `feedback` (
  `feedback_id` int AUTO_INCREMENT,
  `rate_star` int,
  `description` longtext,
  `customer_id` int,
  `product_id` int,
  `order_details_id` int,
  `like` int,
  `status` varchar(255),
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
  `name` varchar(255),
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

CREATE TABLE `image_feedback` (
  `image_feedback_id` int AUTO_INCREMENT,
  `feedback_id` int,
  `image_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(image_feedback_id)
);

ALTER TABLE `user` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `user_role` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

ALTER TABLE `user_role` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `brand` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `product` ADD FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`);

ALTER TABLE `product` ADD FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`subcategory_id`);
--
ALTER TABLE `product_info` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `subcategory` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

ALTER TABLE `variant` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

ALTER TABLE `order_details` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);

ALTER TABLE `session` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`order_details_id`) REFERENCES `order_details` (`order_details_id`);

ALTER TABLE `tip_page` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `image_product` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

 ALTER TABLE `image_product` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `image_slide` ADD FOREIGN KEY (`slide_id`) REFERENCES `slide` (`slide_id`);

ALTER TABLE `image_slide` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `customer` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `location` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `subcategory` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `category` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `image_feedback` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `image_feedback` ADD FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`);

-- SET UTF-8
ALTER TABLE user CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- INSERT USER
-- $2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW : 123
INSERT user(email, password, phone_number) VALUES("buivantruong16082002@gmail.com", "$2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW", "0384761608"); -- ROLE_CUSTOMER
INSERT user(email, password, phone_number) VALUES("buivantruong16082003@gmail.com", "$2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW", "0384761607"); -- ROLE_CUSTOMER
INSERT user(email, password, phone_number) VALUES("buivantruong16081001@gmail.com", "$2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW", "0384761609"); -- ROLE_STAFF
INSERT user(email, password, phone_number) VALUES("buivantruong10012002@gmail.com", "$2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW", "0359654920"); -- ROLE_ADMIN

-- INSERT USER
INSERT role(name) VALUES("ROLE_CUSTOMER");
INSERT role(name) VALUES("ROLE_STAFF");
INSERT role(name) VALUES("ROLE_ADMIN");
 
INSERT user_role(user_id, role_id) VALUES(1, 1);
INSERT user_role(user_id, role_id) VALUES(2, 1);
INSERT user_role(user_id, role_id) VALUES(3, 2); 
INSERT user_role(user_id, role_id) VALUES(3, 3); 

-- INSERT CUSTOMER
INSERT customer(user_id, full_name) VALUES (1, "Customer Truong 1");
INSERT customer(user_id, full_name) VALUES (2, "Customer Truong 2");

SELECT * FROM swp391_team3.customer;
-- INSERT category
INSERT category(name) VALUES ("Clothing");
INSERT category(name) VALUES ("Footwear");
INSERT category(name) VALUES ("Accessories");
INSERT category(name) VALUES ("Strollers");
INSERT category(name) VALUES ("Car Seats");
INSERT category(name) VALUES ("Baby Gear");
INSERT category(name) VALUES ("Home");
INSERT category(name) VALUES ("Toys");

-- INSERT subcategory
-- Clothing
INSERT subcategory(name, category_id) VALUES ("Top", 1);
INSERT subcategory(name, category_id) VALUES ("Bottoms", 1);
INSERT subcategory(name, category_id) VALUES ("Dresses", 1);
INSERT subcategory(name, category_id) VALUES ("Underwear", 1);
INSERT subcategory(name, category_id) VALUES ("Skirts", 1);
INSERT subcategory(name, category_id) VALUES ("Shorts", 1);
INSERT subcategory(name, category_id) VALUES ("Coats & Jackets", 1);
INSERT subcategory(name, category_id) VALUES ("Clothing Sets", 1);
INSERT subcategory(name, category_id) VALUES ("Suits", 1);

-- Footwear
INSERT subcategory(name, category_id) VALUES ("Rain Boots", 2);
INSERT subcategory(name, category_id) VALUES ("Boot $ Winter Shoes", 2);
INSERT subcategory(name, category_id) VALUES ("Sneakers", 2);
INSERT subcategory(name, category_id) VALUES ("First Walkers", 2);
INSERT subcategory(name, category_id) VALUES ("Sandals", 2);
INSERT subcategory(name, category_id) VALUES ("Baby Booties", 2);
INSERT subcategory(name, category_id) VALUES ("Classic Shoes", 2);
INSERT subcategory(name, category_id) VALUES ("Sporst Footwear", 2);

-- Accesspries
INSERT subcategory(name, category_id) VALUES ("Scarves", 3);
INSERT subcategory(name, category_id) VALUES ("Gloves & Mittens", 3);
INSERT subcategory(name, category_id) VALUES ("Headwear", 3);
INSERT subcategory(name, category_id) VALUES ("Belts & Suspenders", 3);
INSERT subcategory(name, category_id) VALUES ("Bags", 3);
INSERT subcategory(name, category_id) VALUES ("Eyewear", 3);
INSERT subcategory(name, category_id) VALUES ("Ties & Bows", 3);

-- Strollers
INSERT subcategory(name, category_id) VALUES ("Double Strollers", 4);
INSERT subcategory(name, category_id) VALUES ("Convertible Strollers", 4);
INSERT subcategory(name, category_id) VALUES ("Stroller Accessories", 4);
INSERT subcategory(name, category_id) VALUES ("Travel Strollers & Umbrella Strollers", 4);
INSERT subcategory(name, category_id) VALUES ("Footmuffs", 4);
INSERT subcategory(name, category_id) VALUES ("Stroller Parts & Customization", 4);

-- Car Seats
INSERT subcategory(name, category_id) VALUES ("Infant Car Seats", 5);
INSERT subcategory(name, category_id) VALUES ("Rear-facing car seats", 5);
INSERT subcategory(name, category_id) VALUES ("Forward facing car seats", 5);
INSERT subcategory(name, category_id) VALUES ("Car seats accessories", 5);
INSERT subcategory(name, category_id) VALUES ("Car seat bases", 5);
INSERT subcategory(name, category_id) VALUES ("Travel with kids", 5);

-- Baby Gear
INSERT subcategory(name, category_id) VALUES ("Baby Feeding", 6);
INSERT subcategory(name, category_id) VALUES ("Maternity", 6);
INSERT subcategory(name, category_id) VALUES ("Baby Carries", 6);
INSERT subcategory(name, category_id) VALUES ("Babysitters", 6);
INSERT subcategory(name, category_id) VALUES ("Baby Safety", 6);
INSERT subcategory(name, category_id) VALUES ("Toiletries", 6);

-- Home
INSERT subcategory(name, category_id) VALUES ("Furniture", 7);
INSERT subcategory(name, category_id) VALUES ("Textiles", 7);
INSERT subcategory(name, category_id) VALUES ("Storage", 7);
INSERT subcategory(name, category_id) VALUES ("Carpets", 7);
INSERT subcategory(name, category_id) VALUES ("Lighting", 7);
INSERT subcategory(name, category_id) VALUES ("Bedding", 7);

-- Toys
INSERT subcategory(name, category_id) VALUES ("Puzzles & Games", 8);
INSERT subcategory(name, category_id) VALUES ("Vehicles", 8);
INSERT subcategory(name, category_id) VALUES ("Stuffed Animals", 8);
INSERT subcategory(name, category_id) VALUES ("Stationery", 8);
INSERT subcategory(name, category_id) VALUES ("First Toys & Baby Toys", 8);
INSERT subcategory(name, category_id) VALUES ("Costumes", 8);

-- Outlet
-- INSERT subcategory(name, category_id) VALUES ("Outlet", 9);

-- Green Page
-- INSERT subcategory(name, category_id) VALUES ("Recycled Material", 10);
-- INSERT subcategory(name, category_id) VALUES ("Responsible Wood", 10);
-- INSERT subcategory(name, category_id) VALUES ("Organic Material", 10);
-- INSERT subcategory(name, category_id) VALUES ("Responsible Animal Welfare", 10);

-- Magazine
-- INSERT subcategory(name, category_id) VALUES ("Magazine", 11);



-- INSERT 6 PRODUCT
INSERT image(name) VALUES ("Absorba.jpg");

INSERT brand(name, image_id, description) VALUES ("Absorba", 1, "Absorba is a French children's clothing brand that was founded in 1949. Starting with producing baby pyjamas, it then expanded into offering a wide range of childrens’ garments from dresses and skirts to tops and jackets. One of the key features of Absorba's clothing is that it is made with soft, breathable materials that are gentle on a child's skin. The brand also places a strong emphasis on safety, with all of its products meeting strict quality and safety standards. Absorba is known for its commitment to sustainability and social responsibility.");

-- , subcategory_id
INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Long Sleeved T-Shirt Cream", 
299,
"Your little one can play all day in comfort. Cream T-Shirt by Absorba. The T-Shirt has a regular fit.</br></br>
– Snap buttons at the shoulder.</br>
– This product is crafted with sustainable organic cotton.</br>", 
"– 100% Organic Cotton.</br>
– Machine washable 30 degrees.", 1, 1, 0.3);

INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream.jpg");
INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream1.jpg");
INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream2.jpg");
INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream3.jpg");

INSERT image_product(product_id, image_id) VALUES(1, 2);
INSERT image_product(product_id, image_id) VALUES(1, 3);
INSERT image_product(product_id, image_id) VALUES(1, 4);
INSERT image_product(product_id, image_id) VALUES(1, 5);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(1, '0-3 months', 'Unisex', 'Cream', '');


-- variant
INSERT variant(product_id, name, quantity) VALUES( 1, '50 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 1, '56 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 1, '62 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 1, '68 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 1, '74 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 1, '80 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 1, '86 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 1, '92 cm', 4);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"2-Pack Footed Baby Body Blue", 
409,
"Let your baby explore and play in comfort. Blue footed baby body by Absorba. Footed baby body has a soft velour material.</br></br>
– Snap buttons at the back.</br>
– This product is crafted with sustainable organic cotton.", 
"– 75% Organic Cotton, 25% Polyester.</br>
– Machine washable 30 degrees.", 1, 8, 0.4);

INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue.jpg");
INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue1.jpg");
INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue2.jpg");
INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue3.jpg");

INSERT image_product(product_id, image_id) VALUES(2, 6);
INSERT image_product(product_id, image_id) VALUES(2, 7);
INSERT image_product(product_id, image_id) VALUES(2, 8);
INSERT image_product(product_id, image_id) VALUES(2, 9);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(2, '3-6 months', 'Boys', 'Blue', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 2, '50 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 2, '56 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 2, '62 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 2, '68 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 2, '74 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 2, '80 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 2, '86 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 2, '92 cm', 2);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Striped Shorts Indigo", 
229,
"The perfect shorts for a sunny day with friends. Blue shorts by Absorba. The shorts have an elasticated waist.</br></br>
– Two front pockets.</br>
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.</br>
– Machine washable 30 degrees.", 1, 6, 0.3);

INSERT image(name) VALUES ("StripedShortsIndigo.jpg");
INSERT image(name) VALUES ("StripedShortsIndigo1.jpg");

INSERT image_product(product_id, image_id) VALUES(3, 10);
INSERT image_product(product_id, image_id) VALUES(3, 11);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(3, '6-9 months', 'Boys', 'Blue', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 3, '50 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 3, '56 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 3, '62 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 3, '68 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 3, '74 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 3, '80 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 3, '86 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 3, '92 cm', 0);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Leggings Cream", 
229,
"Keep little legs comfy. Cream leggings by Absorba. The leggings have a ribbed, folded waist.</br></br>
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.</br>
– Machine washable 30 degrees.", 1, 2, 0.2);

INSERT image(name) VALUES ("PrintedLeggingsCream.jpg");
INSERT image(name) VALUES ("PrintedLeggingsCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(4, 12);
INSERT image_product(product_id, image_id) VALUES(4, 13);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(4, '9-12 months', 'Unisex', 'Cream', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 4, '50 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 4, '56 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 4, '62 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 4, '68 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 4, '74 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 4, '80 cm', 10);
INSERT variant(product_id, name, quantity) VALUES( 4, '86 cm', 11);
INSERT variant(product_id, name, quantity) VALUES( 4, '92 cm', 2);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed One-piece Cream", 
299,
"Perfect for a good night's sleep. Cream one-piece by Absorba. The one-piece has a ribbed design.</br></br>
– Snap buttons at the front and between the legs.</br>
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.</br>
– Machine washable 30 degrees.", 1, 2, 0.3);

INSERT image(name) VALUES ("PrintedOne-pieceCream.jpg");
INSERT image(name) VALUES ("PrintedOne-pieceCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(5, 14);
INSERT image_product(product_id, image_id) VALUES(5, 15);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(5, '1 year', 'Girls', 'Cream', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 5, '62 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 5, '68 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 5, '74 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 5, '80 cm', 8);
INSERT variant(product_id, name, quantity) VALUES( 5, '86 cm', 10);
INSERT variant(product_id, name, quantity) VALUES( 5, '92 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 5, '98 cm', 5);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Footed Baby Body Cream", 
299,
"Let your baby explore and play in comfort. Cream footed baby body by Absorba. The footed baby body has a soft velour material.</br></br>
– Snap buttons at the back.</br>
– This product is crafted with sustainable organic cotton.", 
"– 75% Organic Cotton, 25% Polyester.</br>
– Machine washable 30 degrees.", 1, 8, 0.4);

INSERT image(name) VALUES ("PrintedFootedBabyBodyCream.jpg");
INSERT image(name) VALUES ("PrintedFootedBabyBodyCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(6, 16);
INSERT image_product(product_id, image_id) VALUES(6, 17);

INSERT image(name) VALUES ("Kuling.jpg");
INSERT image(name) VALUES ("MiniRodini.jpg");
INSERT image(name) VALUES ("Wheat.jpg");
INSERT image(name) VALUES ("Molo.jpg");
INSERT image(name) VALUES ("Stokke.jpg");
INSERT image(name) VALUES ("BeSafe.jpg");

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(6, '2 year', 'Girls', 'Cream', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 6, '62 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 6, '68 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 6, '74 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 6, '80 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 6, '86 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 6, '92 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 6, '98 cm', 2);

INSERT brand(name, image_id, description) VALUES ("Kuling", 18, "Designed for active and adventurous kids, Swedish brand Kuling designs long-lasting apparel with signature Scandinavian style. Discover their colorful collection of kids’ and babies’ fashion such as UV clothing, ski jackets, footwear and rain gear. Check out the lates items from Kuling here!");
INSERT brand(name, image_id, description) VALUES ("MiniRodini", 19, "Established in Sweden in 2006, Mini Rodini is an eco-conscious brand devoted to the playful personalities of children. Mini Rodini encourages kids to stand out from the crowd with fun prints, bright colors and bold characters.");
INSERT brand(name, image_id, description) VALUES ("Wheat", 20, "Danish brand Wheat started in 2002 and has as its focus to reinterpret classic kids and baby clothes to put its own spin on the items. The brand does everything from coats and accessories to tops and dresses. Wheat has as part of its core to focus on sustainability and natural fibers in their products.");
INSERT brand(name, image_id, description) VALUES ("Molo", 21, "Kidswear brand Molo started in Copenhagen in 2003 with the aim to bring color and vibrancy to clothes for kids and babies. The clothes are designed for real life and for kids to fully be themselves. Everything from dresses to sweaters feature the brand’s unique prints, vibrant colors, and its nod to Scandinavian design.");
INSERT brand(name, image_id, description) VALUES ("Stokke", 22, "Stokke® is a Norwegian brand recognized worldwide for premium solutions for babies and children. All the products have a common purpose: to encourage child development and to strengthen bonding between parants and their children. Stokke® is focusing on timeless design, durable materials and functionality in the category: high chairs, nursery, strollers, travel and more. Here we grow™");
INSERT brand(name, image_id, description) VALUES ("BeSafe", 23, "");

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Dili Sandals Sand", 
349,
"Perfect for those sunny days. Beige Dili sandals by Kuling. The sandals have a faux leather upper.</br></br>
– Closed toe.</br>
– Velcro closure for easy on and off.</br>
– Cushioned insole.</br>
– Non-slip sole.", 
"– Upper: Other Materials.</br>
– Lining: Other Materials.</br>
– Sole: Other Materials.</br>
– Please note that when you purchase shoes for your child, you should select a size that is 1.5  cm larger than your child's foot. Wellies and boots should be up to 2  cm larger, for extra socks and insoles.", 2, 14, 0.5);

INSERT image(name) VALUES ("DiliSandalsSand.jpg");
INSERT image(name) VALUES ("DiliSandalsSand1.jpg");
INSERT image(name) VALUES ("DiliSandalsSand2.jpg");
INSERT image(name) VALUES ("DiliSandalsSand3.jpg");

INSERT image_product(product_id, image_id) VALUES(7, 24);
INSERT image_product(product_id, image_id) VALUES(7, 25);
INSERT image_product(product_id, image_id) VALUES(7, 26);
INSERT image_product(product_id, image_id) VALUES(7, 27);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(7, '3 year', 'Girls', 'Cream', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 7, '20 EU', 1);
INSERT variant(product_id, name, quantity) VALUES( 7, '21 EU', 2);
INSERT variant(product_id, name, quantity) VALUES( 7, '22 EU', 0);
INSERT variant(product_id, name, quantity) VALUES( 7, '23 EU',  8);
INSERT variant(product_id, name, quantity) VALUES( 7, '24 EU', 6);
INSERT variant(product_id, name, quantity) VALUES( 7, '25 EU', 0);
INSERT variant(product_id, name, quantity) VALUES( 7, '26 EU', 2);
INSERT variant(product_id, name, quantity) VALUES( 7, '27 EU', 2);
INSERT variant(product_id, name, quantity) VALUES( 7, '28 EU', 2);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Lofoten Waterproof Sneakers Always Black", 
599,
"Keep little feet comfortable and stylish all day long. Black Lofoten sneakers by Kuling. The sneakers have a mesh upper.</br></br>
– Padded collar.</br>
– Velcro fastening for easy on and off.</br>
– Cushioning insole.</br>
– Sole with rough tread for a sure step.</br>
– Waterproof.", 
"– Upper: Textiles, Other Materials.</br>
– Lining: Textiles.</br>
– Sole: Other Materials.</br>
– Please note that when you purchase shoes for your child, you should select a size that is 1.5  cm larger than your child's foot. Wellies and boots should be up to 2  cm larger, for extra socks and insoles.", 2, 12, 0.3);

INSERT image(name) VALUES ("LofotenWaterproofSneakersAlwaysBlack.jpg");
INSERT image(name) VALUES ("LofotenWaterproofSneakersAlwaysBlack1.jpg");
INSERT image(name) VALUES ("LofotenWaterproofSneakersAlwaysBlack2.jpg");
INSERT image(name) VALUES ("LofotenWaterproofSneakersAlwaysBlack3.jpg");
INSERT image(name) VALUES ("LofotenWaterproofSneakersAlwaysBlack4.jpg");
INSERT image(name) VALUES ("LofotenWaterproofSneakersAlwaysBlack5.jpg");

INSERT image_product(product_id, image_id) VALUES(8, 28);
INSERT image_product(product_id, image_id) VALUES(8, 29);
INSERT image_product(product_id, image_id) VALUES(8, 30);
INSERT image_product(product_id, image_id) VALUES(8, 31);
INSERT image_product(product_id, image_id) VALUES(8, 32);
INSERT image_product(product_id, image_id) VALUES(8, 33);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(8, '4 year', 'Boys', 'Black', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 8, '20 EU', 4);
INSERT variant(product_id, name, quantity) VALUES( 8, '21 EU', 2);
INSERT variant(product_id, name, quantity) VALUES( 8, '22 EU', 4);
INSERT variant(product_id, name, quantity) VALUES( 8, '23 EU', 0);
INSERT variant(product_id, name, quantity) VALUES( 8, '24 EU', 0);
INSERT variant(product_id, name, quantity) VALUES( 8, '25 EU', 3);
INSERT variant(product_id, name, quantity) VALUES( 8, '26 EU', 6);
INSERT variant(product_id, name, quantity) VALUES( 8, '27 EU', 7);
INSERT variant(product_id, name, quantity) VALUES( 8, '28 EU', 2);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Paros One-piece Rashguard Swimsuit Lilac Daisy", 
349,
"Perfect for some fun on the beach. Purple Paros one-piece rashguard swimsuit by Kuling. The one-piece rashguard swimsuit has a stretchy, quick-drying material.</br></br>
– Mesh lining.</br>
– Zip closure at the front.</br>
– Provides UPF 50+ sun protection.</br>
– Made with recycled materials.", 
"– 96% Nylon, 4% Elastane.</br>
– Machine washable 30 degrees.", 2, 8, 0.3);

INSERT image(name) VALUES ("ParosOne-pieceRashguardSwimsuitLilacDaisy.jpg");
INSERT image(name) VALUES ("ParosOne-pieceRashguardSwimsuitLilacDaisy1.jpg");
INSERT image(name) VALUES ("ParosOne-pieceRashguardSwimsuitLilacDaisy2.jpg");

INSERT image_product(product_id, image_id) VALUES(9, 34);
INSERT image_product(product_id, image_id) VALUES(9, 35);
INSERT image_product(product_id, image_id) VALUES(9, 36);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(9, '5 year', 'Girls', 'Pink', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 9, '80 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 9, '86 cm', 9);
INSERT variant(product_id, name, quantity) VALUES( 9, '92 cm', 10);
INSERT variant(product_id, name, quantity) VALUES( 9, '98 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 9, '104 cm', 8);
INSERT variant(product_id, name, quantity) VALUES( 9, '110 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 9, '116 cm', 4);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"San Marino Dotted Recycled Rain Set Hazelnut", 
599,
"Time to jump through puddles. Brown San Marino rain set by Kuling. The set has welded seams.</br></br>
– Jacket with a detachable hood, a zip closure at the front and elasticated cuffs.</br>
– Pants with adjustable, elasticated shoulder straps, snap buttons at the sides, a front pocket, elasticated cuffs and adjustable, detachable foot loops.</br>
– Reflective details for visibility.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Made with recycled materials.", 
"– Waterproof: 8000 mm.</br>
– Windproof.</br>
– Shell: 100% Recycled Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 2, 7, 0.4);

INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut1.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut2.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut3.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut4.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut5.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut6.jpg");
INSERT image(name) VALUES ("SanMarinoDottedRecycledRainSetHazelnut7.jpg");

INSERT image_product(product_id, image_id) VALUES(10, 37);
INSERT image_product(product_id, image_id) VALUES(10, 38);
INSERT image_product(product_id, image_id) VALUES(10, 39);
INSERT image_product(product_id, image_id) VALUES(10, 40);
INSERT image_product(product_id, image_id) VALUES(10, 41);
INSERT image_product(product_id, image_id) VALUES(10, 42);
INSERT image_product(product_id, image_id) VALUES(10, 43);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(10, '6 year', 'Girls', 'Brown', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 10, '80 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 10, '86 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 10, '92 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 10, '98 cm', 6);
INSERT variant(product_id, name, quantity) VALUES( 10, '104 cm', 12);
INSERT variant(product_id, name, quantity) VALUES( 10, '110 cm', 9);
INSERT variant(product_id, name, quantity) VALUES( 10, '116 cm', 0);

INSERT image(name) VALUES ("MiniRodini.jpg");
INSERT brand(name, image_id, description) VALUES ("Mini Rodini", 45, "Established in Sweden in 2006, Mini Rodini is an eco-conscious brand devoted to the playful personalities of children. Mini Rodini encourages kids to stand out from the crowd with fun prints, bright colors and bold characters.");
INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Floral Dress Cream", 
599,
"Eco-friendly fashion at its finest!. Cream dress by Mini Rodini. The dress has a floral all-over print.</br></br>
– Flared skirt with ruffles.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 95% Organic Cotton, 5% Elastane.</br>
– Machine washable 40 degrees.", 8, 3, 1);

INSERT image(name) VALUES ("FloralDressCream.jpg");
INSERT image(name) VALUES ("FloralDressCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(11, 46);
INSERT image_product(product_id, image_id) VALUES(11, 47);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(11, '7 year', 'Girls', 'Red', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 11, '80 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 11, '86 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 11, '92 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 11, '98 cm', 6);
INSERT variant(product_id, name, quantity) VALUES( 11, '104 cm', 8);
INSERT variant(product_id, name, quantity) VALUES( 11, '110 cm', 11);
INSERT variant(product_id, name, quantity) VALUES( 11, '116 cm', 5);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"GOTS Pelican Printed Dress Yellow", 
479,
"A dress that is chic and comfortable. A must have!. Yellow dress by Mini Rodini. The dress has a playful all-over print with pelicans.</br></br>
– Gathered waist.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 95% Organic Cotton, 5% Elastane.</br>
– Machine washable 40 degrees.", 8, 3, 1);

INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow.jpg");
INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow1.jpg");
INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow2.jpg");
INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow3.jpg");

INSERT image_product(product_id, image_id) VALUES(12, 48);
INSERT image_product(product_id, image_id) VALUES(12, 49);
INSERT image_product(product_id, image_id) VALUES(12, 50);
INSERT image_product(product_id, image_id) VALUES(12, 51);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(12, '8 year', 'Girls', 'Yellow', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 12, '86 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 12, '92 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 12, '98 cm', 12);
INSERT variant(product_id, name, quantity) VALUES( 12, '104 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 12, '110 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 12, '116 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 12, '122 cm', 9);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"GOTS Plaid Dress Red", 
489,
"Eco-friendly fashion at its finest!. Red dress by Mini Rodini. The dress has a sailor collar.</br></br>
– Snap buttons at the front.</br>
– Puff sleeves.</br>
– Gathered waist.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.</br>
– Made in Portugal.", 
"– 100% Organic Cotton.</br>
– Machine washable 40 degrees.", 8, 3, 1);

INSERT image(name) VALUES ("GOTSPlaidDressRed.jpg");

INSERT image_product(product_id, image_id) VALUES(13, 52);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(13, '9 year', 'Girls', 'Red', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 13, '86 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 13, '92 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 13, '98 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 13, '104 cm', 6);
INSERT variant(product_id, name, quantity) VALUES( 13, '110 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 13, '116 cm', 10);
INSERT variant(product_id, name, quantity) VALUES( 13, '122 cm', 2);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Floral Dress Orange", 
599,
"Eco-friendly fashion at its finest!. Orange dress by Mini Rodini. The dress has a floral all-over print.</br></br>
– Peter Pan collar.</br>
– Puff sleeves.</br>
– Snap buttons at the back.</br>
– Gathered waist.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.</br>
– Machine washable 40 degrees.", 8, 3, 0.3);

INSERT image(name) VALUES ("FloralDressOrange.jpg");
INSERT image(name) VALUES ("FloralDressOrange1.jpg");

INSERT image_product(product_id, image_id) VALUES(14, 53);
INSERT image_product(product_id, image_id) VALUES(14, 54);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(14, '10 year', 'Girls', 'Red', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 14, '92 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 14, '98 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 14, '104 cm', 8);
INSERT variant(product_id, name, quantity) VALUES( 14, '110 cm', 9);
INSERT variant(product_id, name, quantity) VALUES( 14, '116 cm', 12);
INSERT variant(product_id, name, quantity) VALUES( 14, '122 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 14, '128 cm', 6);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Dress Navy", 
899,
"A special day deserves a special dress. Navy dress by Mini Rodini. The dress has an all-over print with strawberries.</br>
– Smocked bodice.</br>
– Puff sleeves.</br>
– Sustainably grown lyocell.", 
"– 100% Lyocell.</br>
– Machine washable 40 degrees.", 8, 3, 0.3);

INSERT image(name) VALUES ("PrintedDressNavy.jpg");
INSERT image(name) VALUES ("PrintedDressNavy1.jpg");
INSERT image(name) VALUES ("PrintedDressNavy2.jpg");
INSERT image(name) VALUES ("PrintedDressNavy3.jpg");

INSERT image_product(product_id, image_id) VALUES(15, 55);
INSERT image_product(product_id, image_id) VALUES(15, 56);
INSERT image_product(product_id, image_id) VALUES(15, 57);
INSERT image_product(product_id, image_id) VALUES(15, 58);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(15, '10 year', 'Girls', 'Black', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 15, '92 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 15, '98 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 15, '104 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 15, '110 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 15, '116 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 15, '122 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 15, '128 cm', 2);

INSERT image(name) VALUES ("BUDDYHOPE.jpg");
INSERT brand(name, image_id, description) VALUES ("BUDDY & HOPE", 59, "Buddy & Hope is a baby brand with Swedish heritage founded in 2019 offering home and baby products in a universal fit. Working after the words confident, subtle and universal the Buddy & Hope products adds a silver lining to the everyday life. The range of carefully designed products caters to the conscious parents aware of great materials and design. In the assortment you can find products that will make life a bit extra cozy for your child, both in the stroller, in the cot or in the kids room. Believing in the saying “Everything can always be better” we can trust Buddy & Hope to deliver universal products to fit most strollers and rooms out there, season after season.");

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Vivianne GOTS Dotted Dress Pink Mauve", 
199,
"Dress your little baby extra nicely. Cream Vivianne dress by Buddy & Hope. The dress has ruffle sleeves.</br></br>
– Button closure at the back.</br>
– Gathered waist.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.</br>
– Machine washable 40 degrees.", 9, 3, 0.3);

INSERT image(name) VALUES ("VivianneGOTSDottedDressPinkMauve.jpg");
INSERT image(name) VALUES ("VivianneGOTSDottedDressPinkMauve1.jpg");
INSERT image(name) VALUES ("VivianneGOTSDottedDressPinkMauve2.jpg");

INSERT image_product(product_id, image_id) VALUES(16, 60);
INSERT image_product(product_id, image_id) VALUES(16, 61);
INSERT image_product(product_id, image_id) VALUES(16, 62);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(16, '11 year', 'Girls', 'Beige', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 16, '98 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 16, '104 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 16, '110 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 16, '116 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 16, '122 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 16, '128 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 16, '134 cm', 2);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Vivianne GOTS Floral Dress Wildflowers", 
199,
"Dress your little baby extra nicely. Cream Vivianne dress by Buddy & Hope. The dress has ruffle sleeves.</br></br>
– Button closure at the back.</br>
– Gathered waist.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.</br>
– Machine washable 40 degrees.", 9, 3, 1);

INSERT image(name) VALUES ("VivianneGOTSFloralDressWildflowers.jpg");
INSERT image(name) VALUES ("VivianneGOTSFloralDressWildflowers1.jpg");
INSERT image(name) VALUES ("VivianneGOTSFloralDressWildflowers2.jpg");
INSERT image(name) VALUES ("VivianneGOTSFloralDressWildflowers3.jpg");
INSERT image(name) VALUES ("VivianneGOTSFloralDressWildflowers4.jpg");


INSERT image_product(product_id, image_id) VALUES(17, 63);
INSERT image_product(product_id, image_id) VALUES(17, 64);
INSERT image_product(product_id, image_id) VALUES(17, 65);
INSERT image_product(product_id, image_id) VALUES(17, 66);
INSERT image_product(product_id, image_id) VALUES(17, 67);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(17, '12 year', 'Girls', 'Beige', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 17, '104 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 17, '110 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 17, '116 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 17, '122 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 17, '128 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 17, '134 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 17, '140 cm', 8);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Siv GOTS Shirt And Shorts Set With Lemon Sand", 
399,
"Sunny days will be even more fun. Cream Siv shirt and shorts set by Buddy & Hope. The shirt and shorts set has a soft terry material.</br></br>
– Short-sleeved shirt with a button closure at the front and an embroidered lemon on the chest.</br>
– Shorts with an elasticated drawstring waist, two front pockets and a back pocket with an embroidered lemon.</br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.</br>
– Machine washable 40 degrees.", 9, 8, 1);

INSERT image(name) VALUES ("SivGOTSShirtAndShortsSetWithLemonSand.jpg");
INSERT image(name) VALUES ("SivGOTSShirtAndShortsSetWithLemonSand1.jpg");
INSERT image(name) VALUES ("SivGOTSShirtAndShortsSetWithLemonSand2.jpg");
INSERT image(name) VALUES ("SivGOTSShirtAndShortsSetWithLemonSand3.jpg");
INSERT image(name) VALUES ("SivGOTSShirtAndShortsSetWithLemonSand4.jpg");

INSERT image_product(product_id, image_id) VALUES(18, 68);
INSERT image_product(product_id, image_id) VALUES(18, 69);
INSERT image_product(product_id, image_id) VALUES(18, 70);
INSERT image_product(product_id, image_id) VALUES(18, 71);
INSERT image_product(product_id, image_id) VALUES(18, 72);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(18, '7 year', 'Girls', 'Beige', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 18, '80 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 18, '86 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 18, '92 cm', 8);
INSERT variant(product_id, name, quantity) VALUES( 18, '98 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 18, '104 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 18, '110 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 18, '116 cm', 3);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Melvin GOTS 2 Baby Bodies With Lemon Print Cream", 
399,
"Treat your baby with a new favorite body. Cream Melvin GOTS baby bodies by Buddy & Hope. The baby bodies have snap buttons at the shoulder and between the legs.</br></br>
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– Baby Body 1: 100% Organic Cotton.</br>
– Baby Body 2: 95% Organic Cotton, 5% Elastane.</br>
– Machine washable 40 degrees.", 9, 1, 0.3);

INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream.jpg");
INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream1.jpg");
INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream2.jpg");
INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream3.jpg");

INSERT image_product(product_id, image_id) VALUES(19, 73);
INSERT image_product(product_id, image_id) VALUES(19, 74);
INSERT image_product(product_id, image_id) VALUES(19, 75);
INSERT image_product(product_id, image_id) VALUES(19, 76);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(19, '6 year', 'Girls', 'Beige', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 19, '80 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 19, '86 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 19, '92 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 19, '98 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 19, '104 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 19, '110 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 19, '116 cm', 7);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Stockholm Heart Printed Shell Jacket Woody Rose", 
699,
"Get ready for outdoor adventures. Pink Stockholm jacket by Kuling. The jacket has taped seams.</br></br>
– YKK® zipper at the front.</br>
– Detachable hood.</br>
– Adjustable cuffs with a velcro closure.</br>
– Two front pockets.</br>
– Reflective details for visibility.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Made with recycled materials.", 
"– Waterproof: 15 000 mm.</br>
– Breathable: 8000 g/m2/24 h.</br>
– Windproof.</br>
– Shell: 100% Polyester.</br>
– Lining: 100% Recycled Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 2, 7, 0.4);

INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose.jpg");
INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose1.jpg");
INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose2.jpg");
INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose3.jpg");
INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose4.jpg");
INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose5.jpg");
INSERT image(name) VALUES ("StockholmHeartPrintedShellJacketWoodyRose6.jpg");


INSERT image_product(product_id, image_id) VALUES(20, 77);
INSERT image_product(product_id, image_id) VALUES(20, 78);
INSERT image_product(product_id, image_id) VALUES(20, 79);
INSERT image_product(product_id, image_id) VALUES(20, 80);
INSERT image_product(product_id, image_id) VALUES(20, 81);
INSERT image_product(product_id, image_id) VALUES(20, 82);
INSERT image_product(product_id, image_id) VALUES(20, 83);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(20, '13 year', 'Girls', 'Pink', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 20, '110 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 20, '116 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 20, '122 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 20, '128 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 20, '134 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 20, '140 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 20, '146 cm', 8);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Stockholm Shell Jacket Hazelnut Leo", 
699,
"Get ready for outdoor adventures. Beige Stockholm shell jacket by Kuling. The jacket has taped seams.</br></br>
– YKK® zipper at the front.</br>
– Detachable hood.</br>
– Adjustable cuffs with a velcro closure.</br>
– Two front pockets.</br>
– Reflective details for visibility.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Made with recycled materials.", 
"– Waterproof: 15 000 mm.</br>
– Breathable: 8000 g/m2/24 h.</br>
– Windproof.</br>
– Shell: 100% Polyester.</br>
– Lining: 100% Recycled Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 2, 7, 0.4);

INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo.jpg");
INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo1.jpg");
INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo2.jpg");
INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo3.jpg");
INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo4.jpg");
INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo5.jpg");
INSERT image(name) VALUES ("StockholmShellJacketHazelnutLeo6.jpg");

INSERT image_product(product_id, image_id) VALUES(21, 84);
INSERT image_product(product_id, image_id) VALUES(21, 85);
INSERT image_product(product_id, image_id) VALUES(21, 86);
INSERT image_product(product_id, image_id) VALUES(21, 87);
INSERT image_product(product_id, image_id) VALUES(21, 88);
INSERT image_product(product_id, image_id) VALUES(21, 89);
INSERT image_product(product_id, image_id) VALUES(21, 90);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(21, '14 year', 'Girls', 'Beige', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 21, '110 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 21, '116 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 21, '122 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 21, '128 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 21, '134 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 21, '140 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 21, '146 cm', 0);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Lillehammer Color-blocked Shell Jacket Honey Orange/Mist Blue", 
599,
"Orange Lillehammer jacket by Kuling. The jacket has taped seams.</br></br>
– Detachable hood.</br>
– Zip closure at the front.</br>
– Two front pockets.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Made with recycled materials.", 
"– Waterproof: 15 000 mm.</br>
– Breathable: 8000 g/m2/24 h.</br>
– Windproof.</br>
– Shell: 100% Polyamide.</br>
– Lining: 100% Recycled Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 2, 7, 1);

INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange.jpg");
INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange1.jpg");
INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange2.jpg");
INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange3.jpg");
INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange4.jpg");
INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange5.jpg");
INSERT image(name) VALUES ("LillehammerColor-blockedShellJacketHoneyOrange6.jpg");

INSERT image_product(product_id, image_id) VALUES(22, 91);
INSERT image_product(product_id, image_id) VALUES(22, 92);
INSERT image_product(product_id, image_id) VALUES(22, 93);
INSERT image_product(product_id, image_id) VALUES(22, 94);
INSERT image_product(product_id, image_id) VALUES(22, 95);
INSERT image_product(product_id, image_id) VALUES(22, 96);
INSERT image_product(product_id, image_id) VALUES(22, 97);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(22, '15 year', 'Boys', 'Orange', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 22, '116 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 22, '122 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 22, '128 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 22, '134 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 22, '140 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 22, '146 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 22, '152 cm', 0);

INSERT image(name) VALUES ("reima.jpg");
INSERT brand(name, image_id, description) VALUES ("Reima", 98, "Founded in 1944 in Finland, Reima’s mission is to encourage children to discover the joy of movement by providing them with functional and durable clothing. Reima’s boots and sneakers, coveralls and jackets, and swimwear and UV-clothing are some of the ways Reima has your child covered.");

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Veli Winter Jacket Thyme Green", 
1199,
"Be warm and comfy on winter adventures. Green Veli winter jacket by Reima. The jacket has sealed main seams for waterproofing.</br></br>
– Durable material.</br>
– Detachable hood.</br>
– Zip closure at the front.</br>
– Two front pockets.</br>
– Adjustable cuffs.</br>
– Adjustable waist.</br>
– Reflective details for visibility.</br>
– Smooth lining.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Martindale-tested as abrasion-resistant up to 40,000 cycles.</br>
– Made with recycled materials.", 
"– Waterproof: 10 000 mm.</br>
– Breathable: 7000 g/m2/24 h.</br>
– Windproof.</br>
– Temperature: 0 to -20°C.</br>
– 100% Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Lining: 100% Polyester.</br>
– Machine washable 40 degrees.", 10, 7, 1);

INSERT image(name) VALUES ("VeliWinterJacketThymeGreen.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen1.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen2.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen3.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen4.jpg");

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(23, '16 year', 'Boys', 'Green', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 23, '116 cm', 1);
INSERT variant(product_id, name, quantity) VALUES( 23, '122 cm', 6);
INSERT variant(product_id, name, quantity) VALUES( 23, '128 cm', 8);
INSERT variant(product_id, name, quantity) VALUES( 23, '134 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 23, '140 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 23, '146 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 23, '152 cm', 1);

INSERT image_product(product_id, image_id) VALUES(23, 99);
INSERT image_product(product_id, image_id) VALUES(23, 100);
INSERT image_product(product_id, image_id) VALUES(23, 101);
INSERT image_product(product_id, image_id) VALUES(23, 102);
INSERT image_product(product_id, image_id) VALUES(23, 103);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Reimatec Mutka Winter Jacket Navy", 
1199,
"Navy Reimatec Mutka jacket by Reima. The jacket has sealed seams.</br></br>
– Detachable hood with faux fur trim.</br>
– Zip closure at the front.</br>
– Two front pockets.</br>
– Adjustable cuffs.</br>
– Reflective detail for visibility.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Martindale-tested as abrasion-resistant up to 20,000 cycles.", 
"– Waterproof: 10 000 mm.</br>
– Breathable: 12 000 g/m²/24h.</br>
– Temperature: -10 to -30°C.</br>
– Shell, Lining And Padding: 100% Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 10, 7, 1);

INSERT image(name) VALUES ("ReimatecmutkaWinterJacketNavy.jpg");
INSERT image(name) VALUES ("ReimatecmutkaWinterJacketNavy1.jpg");
INSERT image(name) VALUES ("ReimatecmutkaWinterJacketNavy2.jpg");
INSERT image(name) VALUES ("ReimatecmutkaWinterJacketNavy3.jpg");
INSERT image(name) VALUES ("ReimatecmutkaWinterJacketNavy4.jpg");
INSERT image(name) VALUES ("ReimatecmutkaWinterJacketNavy5.jpg");

INSERT image_product(product_id, image_id) VALUES(24, 104);
INSERT image_product(product_id, image_id) VALUES(24, 105);
INSERT image_product(product_id, image_id) VALUES(24, 106);
INSERT image_product(product_id, image_id) VALUES(24, 107);
INSERT image_product(product_id, image_id) VALUES(24, 108);
INSERT image_product(product_id, image_id) VALUES(24, 109);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(24, '17 year', 'Boys', 'Navy', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 24, '116 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 24, '122 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 24, '128 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 24, '134 cm', 2);
INSERT variant(product_id, name, quantity) VALUES( 24, '140 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 24, '146 cm', 7);
INSERT variant(product_id, name, quantity) VALUES( 24, '152 cm', 1);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Kumlinge Reimatec Shell Jacket Black", 
899,
"Get ready for outdoor adventures. Black Kumlinge Reimatec shell jacket by Reima. The jacket has sealed seams.</br></br>
– Detachable hood.</br>
– Zip closure at the front.</br>
– Two front pockets.</br>
– Elasticated cuffs.</br>
– Reflective details for visibility.</br>
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.</br>
– Martindale-tested as abrasion-resistant up to 5,000 cycles.</br>
– Made with recycled materials.", 
"– Waterproof: 10 000 mm.</br>
– Breathable: 5000 g/m²/24h.</br>
– Shell: 100% Recycled Polyester.</br>
– Coating: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 10, 7, 0.3);

INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack1.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack2.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack3.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack4.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack5.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack6.jpg");
INSERT image(name) VALUES ("KumlingeReimatecShellJacketBlack7.jpg");


INSERT image_product(product_id, image_id) VALUES(25, 110);
INSERT image_product(product_id, image_id) VALUES(25, 111);
INSERT image_product(product_id, image_id) VALUES(25, 112);
INSERT image_product(product_id, image_id) VALUES(25, 113);
INSERT image_product(product_id, image_id) VALUES(25, 114);
INSERT image_product(product_id, image_id) VALUES(25, 115);
INSERT image_product(product_id, image_id) VALUES(25, 116);
INSERT image_product(product_id, image_id) VALUES(25, 117);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(25, '18 year', 'Boys', 'Black', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 25, '122 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 25, '128 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 25, '134 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 25, '140 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 25, '146 cm', 6);
INSERT variant(product_id, name, quantity) VALUES( 25, '152 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 25, '158 cm', 4);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Sipoo Softshell Jacket Grayish Green", 
799,
"Get ready for outdoor adventures. Grayish green Sipoo softshell jacket by Reima. The softshell jacket has a technical material with three layers in fleece.</br></br>
– Detachable hood.</br>
– Zip closure at the front.</br>
– Two front pockets with a zip closure.</br>
– Reflective details for better visibility.</br>
– Fleece lining.</br>
– Teflon EcoElite® fluorocarbon-free water- and dirt-repellent finish.</br>
– Made with recycled materials.", 
"– Water-resistant.</br>
– Breathable.</br>
– Main: 92% Polyester, 8% Elastane.</br>
– Membrane: 100% Polyurethane.</br>
– Machine washable 40 degrees.", 10, 7, 1);

INSERT image(name) VALUES ("SipooSoftshellJacketGrayishgreen.jpg");
INSERT image(name) VALUES ("SipooSoftshellJacketGrayishgreen1.jpg");
INSERT image(name) VALUES ("SipooSoftshellJacketGrayishgreen2.jpg");

INSERT image_product(product_id, image_id) VALUES(26, 118);
INSERT image_product(product_id, image_id) VALUES(26, 119);
INSERT image_product(product_id, image_id) VALUES(26, 120);

-- product_info
INSERT product_info(product_id, age_range, gender, color, style) VALUES(26, '18 year', 'Boys', 'Green', '');

-- variant
INSERT variant(product_id, name, quantity) VALUES( 26, '122 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 26, '128 cm', 3);
INSERT variant(product_id, name, quantity) VALUES( 26, '134 cm', 5);
INSERT variant(product_id, name, quantity) VALUES( 26, '140 cm', 0);
INSERT variant(product_id, name, quantity) VALUES( 26, '146 cm', 6);
INSERT variant(product_id, name, quantity) VALUES( 26, '152 cm', 4);
INSERT variant(product_id, name, quantity) VALUES( 26, '158 cm', 4);

-- INSERT Image category 
INSERT image(name) VALUES ("Clothing.jpg"); --
INSERT image(name) VALUES ("Footwear.jpg"); --
INSERT image(name) VALUES ("Accessories.jpg"); --
INSERT image(name) VALUES ("Strollers.jpg"); --
INSERT image(name) VALUES ("CarSeats.jpg"); --
INSERT image(name) VALUES ("BabyGear.jpg"); --
INSERT image(name) VALUES ("Home.jpg"); --
INSERT image(name) VALUES ("Toys.jpg"); --

-- Update Category
UPDATE category set description = "At Babyshop you will find lots of lovely children's clothes and baby clothes for girls and boys of all ages. In our fine assortment there is everything from the first wardrobe for the newborn baby to hard-wearing shells, rain and winter clothes, but also of course trousers, sweaters, tops, dresses and cardigans. In our assortment you will find well-known premium brands such as; Reima, Mini Rodini, Kuling, Buddy & Hope, Ralph Lauren, Liewood, Konges Slöjd, Petit Bateau, Moncler and many more. Buying children's and baby clothes online is easier and more flexible as you can calmly choose what you want for each season. To help you choose the right one, we have created simple size guides and we also have a lovely shop in shop for soft basic garments that are perfect for preschool or school. Babyshop offers the best and most curated range of children's and baby clothes on the market and we are constantly updated with the latest trends and the most interesting brands", image_id = 121  where category_id = 1;
UPDATE category set description = "At Babyshop you will find a wide range of quality shoes for children and babies. Little children's feet need to take many steps per day and grow quickly, so foot-friendly shoes are the best thing you can invest in. With us you will find a wide range of stylish and practical children's shoes that suit both girls and boys of all ages. Our assortment includes everything from rubber boots and baby shoes to winter shoes, sneakers and sandals from well-known premium brands such as; Kavat, Veja, Viking, Kuling, Reima, Uggs, Moon Boots and Adidas.", image_id = 122  where category_id = 2;
UPDATE category set description = "At Babyshop you will find a wide range of lovely accessories from well-known premium brands for children and babies. Regardless of whether you are looking for warm winter mittens, winter hats, wool gloves or sunglasses, caps or sun hats for a warmer season, we have it in our range. Our assortment includes quality accessories from well-known premium brands such as; Elodie, Liewood, Izipizi, Kuling, Petit Bateau, Joha and Reima.", image_id = 123  where category_id = 3;
UPDATE category set description = "Here at Babyshop we offer strollers and car seats from well-known high-quality brands. When you have found the right stroller and car seat you can easily bring your little treasure with you in the car or for a walk. We have a large assortment of car seats, strollers, and accessories so you can get just the right combination for you. If you wish to attach your infant carrier on the stroller chassis we have adapters that are compatible with most strollers and well-known models of infant carriers. We have complete strollers if you’d like a convenient option, and stroller builders if you want to choose between different chassis, seat units, canopies etc., and build the stroller as you want it. Regardless of what stroller and car seat you choose, your baby is sure to travel in a safe and comfortable way when you are on the go. In our carefully sleceted range you will find premium brands such as Bugaboo, Britax Römer, BeSafe, Carena, Axkid, Thule, and UPPAbaby.", image_id = 124  where category_id = 4;
UPDATE category set description = "Here at Babyshop we offer strollers and car seats from well-known high-quality brands. When you have found the right stroller and car seat you can easily bring your little treasure with you in the car or for a walk. We have a large assortment of car seats, strollers, and accessories so you can get just the right combination for you. If you wish to attach your infant carrier on the stroller chassis we have adapters that are compatible with most strollers and well-known models of infant carriers. We have complete strollers if you’d like a convenient option, and stroller builders if you want to choose between different chassis, seat units, canopies etc., and build the stroller as you want it. Regardless of what stroller and car seat you choose, your baby is sure to travel in a safe and comfortable way when you are on the go. In our carefully sleceted range you will find premium brands such as Bugaboo, Britax Römer, BeSafe, Carena, Axkid, Thule, and UPPAbaby.", image_id = 125  where category_id = 5;
UPDATE category set description = "Here at Babyshop you will find a well-curated range of popular products for your baby. In our assortment there are lots of lovely products with everything from baby clothes, pacifiers, feeding bottles, babysitters to baby nests, changing beds, changing bags, and baby carriers; simply everything you need for your newborn treasure. In our fine assortment you will find well-known premium brands such as BabyBjörn, Ergobaby, BIBS, Buddy & Hope, Konges Sløjd, Bonpoint, bbhugme, and Liewood. When you're expecting a baby, there's a lot you need to buy, and if you're a first-time parent, we'll be more than happy to help you find what's right for you, and make sure you pack the hospital bag correctly.", image_id = 126  where category_id = 6;
UPDATE category set description = "Here at Babyshop you will find a wonderful assortment with everything you could possibly need for your nursery, baby room, or kid's room. We love interior design and in our assortment you will find children's furniture, children's beds, dining chairs, interior details, and textiles from well-known premium brands such as Liewood, ferm LIVING, Sebra, Cam Cam Copenhagen, Done by Deer, OYOY, Garbo&Friends, and JOX.", image_id = 127  where category_id = 7;
UPDATE category set description = "At Babyshop you can find a wide range of toys for kids of all ages from market leading brands. We offer everything from soft toys and building blocks to play tents and rattles. We guarantee you’ll find toys that your child will love both for play time and for inspiration and development. We have baby toys that promotes motor skills and stimulate your baby’s senses, fun toys for outdoor play that encourages movement, plush toys that can be a special friend to hug both during fun times and when your little one needs some extra support. Take a look at our bikes from Stoy, building sets from LEGO, train sets from BRIO and lots more!", image_id = 128  where category_id = 8;

-- INSERT Image subcategory
-- 1 Clothing
INSERT image(name) VALUES ("Top.jpg"); 
INSERT image(name) VALUES ("Dresses.jpg"); 
INSERT image(name) VALUES ("Bottoms.jpg"); 
INSERT image(name) VALUES ("ClothingSets.jpg"); 

UPDATE subcategory set description = "Here we’ve collected everything from sweatshirts, hoodies and knitted cardigans to t-shirts, polo shirts, and tank tops. Whether you child needs a cozy knitted sweater to keep warm during cold days or a stylish shirt or blouse for a smart look for a party you can be sure to find something that suits your taste. We have lots of different models and styles from well-known brands like Kuling, Buddy & Hope, Bobo Choses, Ralph Lauren, Louise Misha, Stella McCartney Kids, Molo, Flöss, FUB, and Mini Rodini.", image_id = 129  where subcategory_id = 1;
UPDATE subcategory set description = "We’re proud to offer a wide range of dresses and skirts so you can find something that suits all types of occasions. Whether it’s time to dress up a little extra for special occasions like Christmas and parties, or a more casual occasion, where a summer dress or a denim skirt works you can find it here. We have brands such as Stella McCartney Kids, Bonpoint, Mini Rodini, Ralph Lauren, Louise Misha, Dolly by Le Petite Tom, and Molo.", image_id = 130 where subcategory_id = 3;
UPDATE subcategory set description = "In our selection of pants and shorts we’ve collected all types of styles and models. How about a pair of comfy leggings or sweatpants for lazy days at home? Or a pair of stylish jeans or chinos with a versatile design that suits the casual look as well as special occasions. We have everyday shorts and more formal shorts so you can be sure to find something that makes those warm summer days even better. Ta a look to find something that fits your needs from brands like Bobo Choses, Molo, Mini Rodini, Kuling, Buddy & Hope, Mar Mar Copenhagen, Stella McCartney Kids, and Bonpoint.", image_id = 131  where subcategory_id = 2;
UPDATE subcategory set description = "Here you find our large assortment of comfy baby bodies, footed baby bodies, rompers, and onesies in nice colors and lovely patterns. Versatile items in different materials such as cotton, wool, and bamboo that are gentle against your baby’s sensitive skin. Perfect for both playtime and sleepy time. We have brands like Petit Bateau, Kuling, Buddy & Hope, Bonpoint, Tartine et Chocolat, Ralph Lauren, Mar Mar Copenhagen, Småfolk, and more.", image_id = 132  where subcategory_id = 8;
-- 2 Footwear
INSERT image(name) VALUES ("BabyBooties.jpg"); 
INSERT image(name) VALUES ("ClassicShoes.jpg"); 
INSERT image(name) VALUES ("Sandals.jpg"); 
INSERT image(name) VALUES ("SporstFootwear.jpg"); 

UPDATE subcategory set description = "Winter shoes for kids are a must-have for the colder seasons. The good news is that we have gathered warm and lined shoes and boots for your child right here. Remember that it's good to buy shoes that are about 1.5-2 cm (about 0.6-0.7 inches) bigger than your child's foot. This way you can also put on thicker socks when it is cold outside so you can keep your little one cozy and warm while they explore the outdoors. Shop children’s boots and snowboots from Kuling, Kavat, Reima, Wheat, Bisgaard, UGG, Viking, and more.", image_id = 133  where subcategory_id = 15;
UPDATE subcategory set description = "When your child is learning to walk, you may want to purchase a pair of shoes that are specially designed for your baby's first year when the first tentative steps slowly but steadily become a wandering pace. Walking shoes for babies, or learn-to-walk shoes, often have a high shaft and laces to be extra stable. When the first steps are yet to be done, you do not need much more than a couple of cozy booties to protect your child’s feet from the cold. We have a nice selection from brands like Kuling, Kavat, Little Jalo, Ralph Lauren, Tartine et Chocolat, Donsje Amsterdam, and many more.", image_id = 134  where subcategory_id = 16;
UPDATE subcategory set description = "Here we have our wide range of kids’ sandals for boys and girls. The assortment includes everything from strappy sandals to flip flops which makes the transition from the city to the beach a breeze. The sandals come from brands like Kuling, Kavat, Reima, Pom d'Api, Donsje Amsterdam,and Bisgaard to name a few.", image_id = 135  where subcategory_id = 14;
UPDATE subcategory set description = "In this category we have brought together trendy and classic shoe models so you can find the right pair of shoes to reflect your personality. Regardless if your shoe of choice is a glittery ballerina, a pair of black Mary Janes or a pair of sleek loafers, we’ve gathered together a large assortment for you to choose from.", image_id = 136  where subcategory_id = 17;

-- 3 Accessories
INSERT image(name) VALUES ("GlovesMittens.jpg"); 
INSERT image(name) VALUES ("Eyewear.jpg"); 
INSERT image(name) VALUES ("Bags.jpg"); 
INSERT image(name) VALUES ("Headwear.jpg"); 

UPDATE subcategory set description = "Whether you are looking for ski gloves, regular gloves, or mittens; we have something for every child! Here we have fleece-lined gloves to keep small hands warm on really cold days and unlined versions for the milder days. We have mittens and gloves from famous brands such as Kuling, Reima, Didriksons, Molo, Isbjörn of Sweden, Tretorn and Joha.", image_id = 137  where subcategory_id = 19;
UPDATE subcategory set description = "Here we have gathered all our children’s eyewear, ranging from sunglasses to ski goggles. Whether you are on your way to the beach and your child needs sunglasses to protect their eyes or about to go skiing and need to find your child some ski goggles to protect their eyes from the elements we have them here.", image_id = 138  where subcategory_id = 23;
UPDATE subcategory set description = "In our large range of bags you’ll find backpacks, fanny packs, tote bags and all types of children’s bags for every occasion. We have practical backpacks, stylish handbags and convenient suitcases. No matter if it’s the first school day, outdoor adventures, a shopping day, or travel time you’ll be sure to find the perfect bag here. Take a look at our assortment to find a new favorite.", image_id = 139  where subcategory_id = 22;
UPDATE subcategory set description = "Here you can find our range of hats and beanies for your child. Whether you are looking for something warm and cozy for colder months, a lightweight hat for other times of the year or sun hats and caps for sunny summer days, we have several models to choose from. Our assortment offers well-known brands such as Reima, Molo, Kuling, and Mini Rodini.", image_id = 140  where subcategory_id = 20;

-- 4 Strollers
INSERT image(name) VALUES ("StrollerAccessories.jpg"); 
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh

UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 141  where subcategory_id = 27;
UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 142  where subcategory_id = 28; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 143  where subcategory_id = 29; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 144  where subcategory_id = 30; -- cần sửa subcategory_id và description theo ảnh img đã chèn

-- 5 Car Seats
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("StrollerAccessories.jpg"); -- cần thêm ảnh

UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 145  where subcategory_id = 31; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 146  where subcategory_id = 32; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 147  where subcategory_id = 33; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Here we have the accessories that will complete your stroller, both the necessary ones like adapters but also the fun and practical ones like handwarmers, seat liners, stroller mobiles, and phone holders. You can also check out our selection of footmuffs. Have a look below and see if you can find a new favorite accessory!", image_id = 148  where subcategory_id = 34; -- cần sửa subcategory_id và description theo ảnh img đã chèn

-- 6 Baby Gear
INSERT image(name) VALUES ("BabyFeeding.jpg"); 
INSERT image(name) VALUES ("BabyCarries.jpg"); 
INSERT image(name) VALUES ("BabyCarries.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("BabyCarries.jpg"); -- cần thêm ảnh

UPDATE subcategory set description = "", image_id = 149  where subcategory_id = 8;
UPDATE subcategory set description = "When you want to carry your child with you a baby carrier or baby wrap is a good choice. A baby carrier is easy to put on and gives support to both you and your baby. If you want something smaller and easy to carry with you, a baby wrap is the option for you. Find the best solution for you and look forward to carrying your baby close to you while freeing up your hands to do something else.", image_id = 150  where subcategory_id = 39; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "When you want to carry your child with you a baby carrier or baby wrap is a good choice. A baby carrier is easy to put on and gives support to both you and your baby. If you want something smaller and easy to carry with you, a baby wrap is the option for you. Find the best solution for you and look forward to carrying your baby close to you while freeing up your hands to do something else.", image_id = 151  where subcategory_id = 40; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "When you want to carry your child with you a baby carrier or baby wrap is a good choice. A baby carrier is easy to put on and gives support to both you and your baby. If you want something smaller and easy to carry with you, a baby wrap is the option for you. Find the best solution for you and look forward to carrying your baby close to you while freeing up your hands to do something else.", image_id = 152  where subcategory_id = 41; -- cần sửa subcategory_id và description theo ảnh img đã chèn

-- 7 Home
INSERT image(name) VALUES ("Storage.jpg"); 
INSERT image(name) VALUES ("Bedding.jpg"); 
INSERT image(name) VALUES ("Furniture.jpg"); 
INSERT image(name) VALUES ("Furniture.jpg"); -- cần thêm ảnh

UPDATE subcategory set description = "All those toys your kid has, they need a fun and smart solution to keep the children's room clean and organized. Get a few of those fruit-shaped storage baskets from Bloomingville or Jox and some of those storage boxes from Liewood. The textile storage baskets and pockets from Buddy & Hope, Garbo & Friends and Cam Cam Copenhagen will look very stylish and keep it all in one place.", image_id = 153  where subcategory_id = 45;
UPDATE subcategory set description = "When it’s time for sleep your little one can sleep comfortably with our bedding. We have bed sets, duvets, pillows and fitted sheets in different sizes that are sure to turn the bed into a soft and cozy place. If you want a safe sleeping environment for your baby we have bed bumpers and sleeping bags for a good night’s sleep. We also have canopies that adds something extra to the bedroom. Browse through our selection to make sleepy time better, you will find brands like Garbo & Friends, Liewood, Cam Cam Copenhagen, Konges Sløjd, JOX, Buddy & Hope, Done by Deer, and Filibabba in our nice assortment.", image_id = 154  where subcategory_id = 48;
UPDATE subcategory set description = "On a hunt for some furniture pieces for the nursery or your kid’s room? Pair an elegant crib with some Scandinavian style shelves and storage units for that flawless clean nursery look. To give a more grown-up kid’s room some personality have a look at a variety of armchairs from Jox in all different colors and shapes, add a headboard to give a bed some character and finish with a nice wooden set of chairs and a table.", image_id = 155  where subcategory_id = 43;
UPDATE subcategory set description = "On a hunt for some furniture pieces for the nursery or your kid’s room? Pair an elegant crib with some Scandinavian style shelves and storage units for that flawless clean nursery look. To give a more grown-up kid’s room some personality have a look at a variety of armchairs from Jox in all different colors and shapes, add a headboard to give a bed some character and finish with a nice wooden set of chairs and a table.", image_id = 156  where subcategory_id = 44; -- cần sửa subcategory_id và description theo ảnh img đã chèn

-- 8 Toys
INSERT image(name) VALUES ("FirstToysBabyToys.jpg"); 
INSERT image(name) VALUES ("FirstToysBabyToys.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("FirstToysBabyToys.jpg"); -- cần thêm ảnh
INSERT image(name) VALUES ("FirstToysBabyToys.jpg"); -- cần thêm ảnh

UPDATE subcategory set description = "Newborn toys and baby toys need to be chosen carefully and be safe for your child. We have a wide selection for any age to make sure your little ones have the perfect toys to encourage their development. Whether you are looking for a playmat, a cuddly blanket, a music toy or a classic wooden toy for you or for a gift we got you covered!", image_id = 157  where subcategory_id = 53;
UPDATE subcategory set description = "Newborn toys and baby toys need to be chosen carefully and be safe for your child. We have a wide selection for any age to make sure your little ones have the perfect toys to encourage their development. Whether you are looking for a playmat, a cuddly blanket, a music toy or a classic wooden toy for you or for a gift we got you covered!", image_id = 158  where subcategory_id = 50; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Newborn toys and baby toys need to be chosen carefully and be safe for your child. We have a wide selection for any age to make sure your little ones have the perfect toys to encourage their development. Whether you are looking for a playmat, a cuddly blanket, a music toy or a classic wooden toy for you or for a gift we got you covered!", image_id = 159  where subcategory_id = 51; -- cần sửa subcategory_id và description theo ảnh img đã chèn
UPDATE subcategory set description = "Newborn toys and baby toys need to be chosen carefully and be safe for your child. We have a wide selection for any age to make sure your little ones have the perfect toys to encourage their development. Whether you are looking for a playmat, a cuddly blanket, a music toy or a classic wooden toy for you or for a gift we got you covered!", image_id = 160  where subcategory_id = 52; -- cần sửa subcategory_id và description theo ảnh img đã chèn

-- INSERT ORDER
-- COMPLETED : Hoàn thành
-- SHIP : Đang giao
-- WAIT : Chờ xác nhận
INSERT `orders` (code, customer_id, status) VALUES ("CODE_ABC", 1, "");
INSERT `orders` (code, customer_id, status) VALUES ("CODE_ABC", 1, "");
INSERT `orders` (code, customer_id, status) VALUES ("CODE_ABC", 1, "");

INSERT `orders` (code, customer_id, status) VALUES ("CODE_ABC", 2, "");
INSERT `orders` (code, customer_id, status) VALUES ("CODE_ABC", 2, "");
INSERT `orders` (code, customer_id, status) VALUES ("CODE_ABC", 2, "");


-- ORDER-ID: 1
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (1, 2, 11, 2, 490, 0, 0, "COMPLETED");
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (1, 3, 18, 1, 160, 0, 0, "COMPLETED");
-- ORDER-ID: 2
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (2, 2, 13, 1, 245, 0, 0, "SHIP");
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (2, 1, 3, 2, 418, 0, 0, "SHIP");
-- ORDER-ID: 3
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (3, 4, 30, 1, 160, 0, 0, "WAIT");
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (3, 5, 37, 1, 183, 0, 0, "WAIT");
-- ORDER-ID: 4
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (4, 2, 11, 2, 490, 0, 0, "COMPLETED");
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (4, 4, 30, 1, 160, 0, 0, "COMPLETED");
-- ORDER-ID: 5
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (5, 4, 30, 1, 160, 0, 0, "SHIP");
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (5, 2, 13, 1, 245, 0, 0, "SHIP");
-- ORDER-ID: 6
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (6, 4, 30, 1, 160, 0, 0, "WAIT");
INSERT order_details(order_id, product_id, variant_id, quantity, price, profit, discount, status) VALUES (6, 2, 11, 2, 490, 0, 0, "WAIT");


-- INSERT FEEDBACK
-- ACTIVE : hoạt động
-- INACTIVE : Không hoạt động
INSERT feedback(product_id, customer_id, description, rate_star, order_details_id, `like`, status) VALUES (2, 1, "Very good, 9.5 point", 5, 1, 0, "ACTIVE");
INSERT feedback(product_id, customer_id, description, rate_star, order_details_id, `like`, status) VALUES (3, 1, "Temporarily, in general, give 8 points :))", 4, 2, 0, "ACTIVE");

INSERT feedback(product_id, customer_id, description, rate_star, order_details_id, `like`, status) VALUES (2, 2, "It seems to suit me quite well !!!", 4, 7, 0, "ACTIVE");
INSERT feedback(product_id, customer_id, description, rate_star, order_details_id, `like`, status) VALUES (4, 2, "The product is pretty bad.", 3, 8, 0, "ACTIVE");

-- INSERT IMAGE FEEDBACK : start image-id: 161
INSERT image(name) VALUES ("feedback12312412gdf.webp"); 
INSERT image(name) VALUES ("feedback1241231ew.webp"); 
INSERT image(name) VALUES ("feedback234892hg.webp"); 
INSERT image(name) VALUES ("feedback235243qw.webp"); 
INSERT image(name) VALUES ("feedback23894asd.webp"); 
INSERT image_feedback(feedback_id, image_id) VALUES(1, 161);
INSERT image_feedback(feedback_id, image_id) VALUES(1, 162);
INSERT image_feedback(feedback_id, image_id) VALUES(1, 163);
INSERT image_feedback(feedback_id, image_id) VALUES(1, 164);
INSERT image_feedback(feedback_id, image_id) VALUES(1, 165);

INSERT image(name) VALUES ("feedback123124asdjk.webp"); 
INSERT image(name) VALUES ("feedback12312sdj123.webp"); 
INSERT image_feedback(feedback_id, image_id) VALUES(2, 166);
INSERT image_feedback(feedback_id, image_id) VALUES(2, 167);

INSERT image(name) VALUES ("feedback12234asd12.webp"); 
INSERT image(name) VALUES ("feedback12430sd32a.webp"); 
INSERT image_feedback(feedback_id, image_id) VALUES(4, 168);
INSERT image_feedback(feedback_id, image_id) VALUES(4, 169);

--  UPDATE avater user
INSERT image(name) VALUES ("avatarwibu1.webp"); 
INSERT image(name) VALUES ("avatarwibu2.jpg"); 
UPDATE user SET image_id = 170 where user_id = 1;
UPDATE user SET image_id = 171 where user_id = 1;

SELECT * FROM swp391_team3.order_details;
SELECT * FROM swp391_team3.variant;
SELECT * FROM swp391_team3.product;
SELECT * FROM swp391_team3.orders;
SELECT * FROM swp391_team3.feedback;
SELECT * FROM swp391_team3.image;
SELECT * FROM swp391_team3.image_feedback;