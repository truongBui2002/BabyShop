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
  `full_name` varchar(255), 
  `image_id` int,
  `dob` date,
  `status` varchar(255),
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
  `full_name` varchar(255),
  `phone_number` varchar(255),
  `email` varchar(255),
  `gender` boolean,
  `address` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(customer_id)
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

CREATE TABLE `category` (
  `category_id` int AUTO_INCREMENT,
  `name` varchar(255),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(category_id)
);

CREATE TABLE `subcategory`(
  `subcategory_id` int AUTO_INCREMENT,
  `name` varchar(255),
  `category_id` int,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(subcategory_id)
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

ALTER TABLE `user` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `user_role` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

ALTER TABLE `user_role` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `order` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `brand` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);


ALTER TABLE `product` ADD FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`);

ALTER TABLE `product` ADD FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`subcategory_id`);

ALTER TABLE `subcategory` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);


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


-- INSERT USER
-- $2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW : 123
INSERT role(name) VALUES("ROLE_CUSTOMER");
INSERT user(email, password, phone_number) VALUES("buivantruong16082002@gmail.com", "$2a$10$9B0uI.dhioLrXEPg11M9/e.YTrLnUVgP.TORXBhF510yZKEgUKLcW", "0384761608");
INSERT user_role(user_id, role_id) VALUES(1, 1);

-- INSERT category
INSERT category(name) VALUES ("Clothing");
INSERT category(name) VALUES ("Footwear");
INSERT category(name) VALUES ("Accessories");
INSERT category(name) VALUES ("Strollers");
INSERT category(name) VALUES ("Car Seats");
INSERT category(name) VALUES ("Baby Gear");
INSERT category(name) VALUES ("Home");
INSERT category(name) VALUES ("Toys");
INSERT category(name) VALUES ("Outlet");
INSERT category(name) VALUES ("Green Page");
INSERT category(name) VALUES ("Magazine");

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
INSERT subcategory(name, category_id) VALUES ("Recycled Material", 10);
INSERT subcategory(name, category_id) VALUES ("Responsible Wood", 10);
INSERT subcategory(name, category_id) VALUES ("Organic Material", 10);
INSERT subcategory(name, category_id) VALUES ("Responsible Animal Welfare", 10);

-- Magazine
-- INSERT subcategory(name, category_id) VALUES ("Magazine", 11);



-- INSERT 6 PRODUCT
INSERT image(name) VALUES ("Absorba.jpg");

INSERT brand(name, image_id, description) VALUES ("Absorba", 1, "");

-- , subcategory_id
INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Long Sleeved T-Shirt Cream", 
299,
"Your little one can play all day in comfort. Cream T-Shirt by Absorba. The T-Shirt has a regular fit.
– Snap buttons at the shoulder.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 1, 0.3);

INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream.jpg");
INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream1.jpg");
INSERT image(name) VALUES ("PrintedLongSleevedT-ShirtCream2.jpg");
INSERT image(name) VALUES ("PrintedLongSleevedTShirtCream3.jpg");

INSERT image_product(product_id, image_id) VALUES(1, 2);
INSERT image_product(product_id, image_id) VALUES(1, 3);
INSERT image_product(product_id, image_id) VALUES(1, 4);
INSERT image_product(product_id, image_id) VALUES(1, 5);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"2-Pack Footed Baby Body Blue", 
409,
"Let your baby explore and play in comfort. Blue footed baby body by Absorba. Footed baby body has a soft velour material.
– Snap buttons at the back.
– This product is crafted with sustainable organic cotton.", 
"– 75% Organic Cotton, 25% Polyester.
– Machine washable 30 degrees.", 1, 8, 0.4);

INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue.jpg");
INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue1.jpg");
INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue2.jpg");
INSERT image(name) VALUES ("2-PackFootedBabyBodyBlue3.jpg");

INSERT image_product(product_id, image_id) VALUES(2, 6);
INSERT image_product(product_id, image_id) VALUES(2, 7);
INSERT image_product(product_id, image_id) VALUES(2, 8);
INSERT image_product(product_id, image_id) VALUES(2, 9);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Striped Shorts Indigo", 
229,
"The perfect shorts for a sunny day with friends. Blue shorts by Absorba. The shorts have an elasticated waist.
– Two front pockets.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 6, 0.3);

INSERT image(name) VALUES ("StripedShortsIndigo.jpg");
INSERT image(name) VALUES ("StripedShortsIndigo1.jpg");

INSERT image_product(product_id, image_id) VALUES(3, 10);
INSERT image_product(product_id, image_id) VALUES(3, 11);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Leggings Cream", 
229,
"Keep little legs comfy. Cream leggings by Absorba. The leggings have a ribbed, folded waist.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 2, 0.2);

INSERT image(name) VALUES ("PrintedLeggingsCream.jpg");
INSERT image(name) VALUES ("PrintedLeggingsCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(4, 12);
INSERT image_product(product_id, image_id) VALUES(4, 13);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed One-piece Cream", 
299,
"Perfect for a good night's sleep. Cream one-piece by Absorba. The one-piece has a ribbed design.
– Snap buttons at the front and between the legs.
– This product is crafted with sustainable organic cotton.", 
"– 100% Organic Cotton.
– Machine washable 30 degrees.", 1, 2, 0.3);

INSERT image(name) VALUES ("PrintedOne-pieceCream.jpg");
INSERT image(name) VALUES ("PrintedOne-pieceCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(5, 14);
INSERT image_product(product_id, image_id) VALUES(5, 15);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Footed Baby Body Cream", 
299,
"Let your baby explore and play in comfort. Cream footed baby body by Absorba. The footed baby body has a soft velour material.
– Snap buttons at the back.
– This product is crafted with sustainable organic cotton.", 
"– 75% Organic Cotton, 25% Polyester.
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

INSERT brand(name, image_id, description) VALUES ("Kuling", 18, "Designed for active and adventurous kids, Swedish brand Kuling designs long-lasting apparel with signature Scandinavian style. Discover their colorful collection of kids’ and babies’ fashion such as UV clothing, ski jackets, footwear and rain gear. Check out the lates items from Kuling here!");
INSERT brand(name, image_id, description) VALUES ("MiniRodini", 19, "Established in Sweden in 2006, Mini Rodini is an eco-conscious brand devoted to the playful personalities of children. Mini Rodini encourages kids to stand out from the crowd with fun prints, bright colors and bold characters.");
INSERT brand(name, image_id, description) VALUES ("Wheat", 20, "Danish brand Wheat started in 2002 and has as its focus to reinterpret classic kids and baby clothes to put its own spin on the items. The brand does everything from coats and accessories to tops and dresses. Wheat has as part of its core to focus on sustainability and natural fibers in their products.");
INSERT brand(name, image_id, description) VALUES ("Molo", 21, "Kidswear brand Molo started in Copenhagen in 2003 with the aim to bring color and vibrancy to clothes for kids and babies. The clothes are designed for real life and for kids to fully be themselves. Everything from dresses to sweaters feature the brand’s unique prints, vibrant colors, and its nod to Scandinavian design.");
INSERT brand(name, image_id, description) VALUES ("Stokke", 22, "Stokke® is a Norwegian brand recognized worldwide for premium solutions for babies and children. All the products have a common purpose: to encourage child development and to strengthen bonding between parants and their children. Stokke® is focusing on timeless design, durable materials and functionality in the category: high chairs, nursery, strollers, travel and more. Here we grow™");
INSERT brand(name, image_id, description) VALUES ("BeSafe", 23, "");

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Dili Sandals Sand", 
349,
"Perfect for those sunny days. Beige Dili sandals by Kuling. The sandals have a faux leather upper.
– Closed toe.
– Velcro closure for easy on and off.
– Cushioned insole.
– Non-slip sole.", 
"– Upper: Other Materials.
– Lining: Other Materials.
– Sole: Other Materials.
– Please note that when you purchase shoes for your child, you should select a size that is 1.5 cm larger than your child's foot. Wellies and boots should be up to 2 cm larger, for extra socks and insoles.", 2, 14, 0.5);

INSERT image(name) VALUES ("DiliSandalsSand.jpg");
INSERT image(name) VALUES ("DiliSandalsSand1.jpg");
INSERT image(name) VALUES ("DiliSandalsSand2.jpg");
INSERT image(name) VALUES ("DiliSandalsSand3.jpg");

INSERT image_product(product_id, image_id) VALUES(7, 24);
INSERT image_product(product_id, image_id) VALUES(7, 25);
INSERT image_product(product_id, image_id) VALUES(7, 26);
INSERT image_product(product_id, image_id) VALUES(7, 27);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Lofoten Waterproof Sneakers Always Black", 
599,
"Keep little feet comfortable and stylish all day long. Black Lofoten sneakers by Kuling. The sneakers have a mesh upper.
– Padded collar.
– Velcro fastening for easy on and off.
– Cushioning insole.
– Sole with rough tread for a sure step.
– Waterproof.", 
"– Upper: Textiles, Other Materials.
– Lining: Textiles.
– Sole: Other Materials.
– Please note that when you purchase shoes for your child, you should select a size that is 1.5 cm larger than your child's foot. Wellies and boots should be up to 2 cm larger, for extra socks and insoles.", 2, 12, 0.3);

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


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Paros One-piece Rashguard Swimsuit Lilac Daisy", 
349,
"Perfect for some fun on the beach. Purple Paros one-piece rashguard swimsuit by Kuling. The one-piece rashguard swimsuit has a stretchy, quick-drying material.
– Mesh lining.
– Zip closure at the front.
– Provides UPF 50+ sun protection.
– Made with recycled materials.", 
"– 96% Nylon, 4% Elastane.
– Machine washable 30 degrees.", 2, 8, 0.3);

INSERT image(name) VALUES ("ParosOne-pieceRashguardSwimsuitLilacDaisy.jpg");
INSERT image(name) VALUES ("ParosOne-pieceRashguardSwimsuitLilacDaisy1.jpg");
INSERT image(name) VALUES ("ParosOne-pieceRashguardSwimsuitLilacDaisy2.jpg");

INSERT image_product(product_id, image_id) VALUES(9, 34);
INSERT image_product(product_id, image_id) VALUES(9, 35);
INSERT image_product(product_id, image_id) VALUES(9, 36);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"San Marino Dotted Recycled Rain Set Hazelnut", 
599,
"Time to jump through puddles. Brown San Marino rain set by Kuling. The set has welded seams.
– Jacket with a detachable hood, a zip closure at the front and elasticated cuffs.
– Pants with adjustable, elasticated shoulder straps, snap buttons at the sides, a front pocket, elasticated cuffs and adjustable, detachable foot loops.
– Reflective details for visibility.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Made with recycled materials.", 
"– Waterproof: 8000 mm.
– Windproof.
– Shell: 100% Recycled Polyester.
– Coating: 100% Polyurethane.
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

INSERT image(name) VALUES ("MiniRodini.jpg");
INSERT brand(name, image_id, description) VALUES ("Mini Rodini", 45, "Established in Sweden in 2006, Mini Rodini is an eco-conscious brand devoted to the playful personalities of children. Mini Rodini encourages kids to stand out from the crowd with fun prints, bright colors and bold characters.");
INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Floral Dress Cream", 
599,
"Eco-friendly fashion at its finest!. Cream dress by Mini Rodini. The dress has a floral all-over print.
– Flared skirt with ruffles.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 95% Organic Cotton, 5% Elastane.
– Machine washable 40 degrees.", 8, 3, 1);

INSERT image(name) VALUES ("FloralDressCream.jpg");
INSERT image(name) VALUES ("FloralDressCream1.jpg");

INSERT image_product(product_id, image_id) VALUES(11, 46);
INSERT image_product(product_id, image_id) VALUES(11, 47);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"GOTS Pelican Printed Dress Yellow", 
479,
"A dress that is chic and comfortable. A must have!. Yellow dress by Mini Rodini. The dress has a playful all-over print with pelicans.
– Gathered waist.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 95% Organic Cotton, 5% Elastane.
– Machine washable 40 degrees.", 8, 3, 1);

INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow.jpg");
INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow1.jpg");
INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow2.jpg");
INSERT image(name) VALUES ("GOTSPelicanPrintedDressYellow3.jpg");

INSERT image_product(product_id, image_id) VALUES(12, 48);
INSERT image_product(product_id, image_id) VALUES(12, 49);
INSERT image_product(product_id, image_id) VALUES(12, 50);
INSERT image_product(product_id, image_id) VALUES(12, 51);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"GOTS Plaid Dress Red", 
489,
"Eco-friendly fashion at its finest!. Red dress by Mini Rodini. The dress has a sailor collar.
– Snap buttons at the front.
– Puff sleeves.
– Gathered waist.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.
– Made in Portugal.", 
"– 100% Organic Cotton.
– Machine washable 40 degrees.", 8, 3, 1);

INSERT image(name) VALUES ("GOTSPlaidDressRed.jpg");

INSERT image_product(product_id, image_id) VALUES(13, 52);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Floral Dress Orange", 
599,
"Eco-friendly fashion at its finest!. Orange dress by Mini Rodini. The dress has a floral all-over print.
– Peter Pan collar.
– Puff sleeves.
– Snap buttons at the back.
– Gathered waist.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.
– Machine washable 40 degrees.", 8, 3, 0.3);

INSERT image(name) VALUES ("FloralDressOrange.jpg");
INSERT image(name) VALUES ("FloralDressOrange1.jpg");

INSERT image_product(product_id, image_id) VALUES(14, 53);
INSERT image_product(product_id, image_id) VALUES(14, 54);

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Printed Dress Navy", 
899,
"A special day deserves a special dress. Navy dress by Mini Rodini. The dress has an all-over print with strawberries.
– Smocked bodice.
– Puff sleeves.
– Sustainably grown lyocell.", 
"– 100% Lyocell.
– Machine washable 40 degrees.", 8, 3, 0.3);

INSERT image(name) VALUES ("PrintedDressNavy.jpg");
INSERT image(name) VALUES ("PrintedDressNavy1.jpg");
INSERT image(name) VALUES ("PrintedDressNavy2.jpg");
INSERT image(name) VALUES ("PrintedDressNavy3.jpg");

INSERT image_product(product_id, image_id) VALUES(15, 55);
INSERT image_product(product_id, image_id) VALUES(15, 56);
INSERT image_product(product_id, image_id) VALUES(15, 57);
INSERT image_product(product_id, image_id) VALUES(15, 58);

INSERT image(name) VALUES ("BUDDYHOPE.jpg");
INSERT brand(name, image_id, description) VALUES ("BUDDY & HOPE", 59, "Buddy & Hope is a baby brand with Swedish heritage founded in 2019 offering home and baby products in a universal fit. Working after the words confident, subtle and universal the Buddy & Hope products adds a silver lining to the everyday life. The range of carefully designed products caters to the conscious parents aware of great materials and design. In the assortment you can find products that will make life a bit extra cozy for your child, both in the stroller, in the cot or in the kids room. Believing in the saying “Everything can always be better” we can trust Buddy & Hope to deliver universal products to fit most strollers and rooms out there, season after season.");

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Vivianne GOTS Dotted Dress Pink Mauve", 
199,
"Dress your little baby extra nicely. Cream Vivianne dress by Buddy & Hope. The dress has ruffle sleeves.
– Button closure at the back.
– Gathered waist.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.
– Machine washable 40 degrees.", 9, 3, 0.3);

INSERT image(name) VALUES ("VivianneGOTSDottedDressPinkMauve.jpg");
INSERT image(name) VALUES ("VivianneGOTSDottedDressPinkMauve1.jpg");
INSERT image(name) VALUES ("VivianneGOTSDottedDressPinkMauve2.jpg");

INSERT image_product(product_id, image_id) VALUES(16, 60);
INSERT image_product(product_id, image_id) VALUES(16, 61);
INSERT image_product(product_id, image_id) VALUES(16, 62);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Vivianne GOTS Floral Dress Wildflowers", 
199,
"Dress your little baby extra nicely. Cream Vivianne dress by Buddy & Hope. The dress has ruffle sleeves.
– Button closure at the back.
– Gathered waist.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.
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

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Siv GOTS Shirt And Shorts Set With Lemon Sand", 
399,
"Sunny days will be even more fun. Cream Siv shirt and shorts set by Buddy & Hope. The shirt and shorts set has a soft terry material.
– Short-sleeved shirt with a button closure at the front and an embroidered lemon on the chest.
– Shorts with an elasticated drawstring waist, two front pockets and a back pocket with an embroidered lemon.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– 100% Organic Cotton.
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

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Melvin GOTS 2 Baby Bodies With Lemon Print Cream", 
399,
"Treat your baby with a new favorite body. Cream Melvin GOTS baby bodies by Buddy & Hope. The baby bodies have snap buttons at the shoulder and between the legs.
– This product is GOTS certified, which means that it is made with certified organic materials and produced under the strictest social and environmental standards throughout the entire manufacturing process.", 
"– Baby Body 1: 100% Organic Cotton.
– Baby Body 2: 95% Organic Cotton, 5% Elastane.
– Machine washable 40 degrees.", 9, 1, 0.3);

INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream.jpg");
INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream1.jpg");
INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream2.jpg");
INSERT image(name) VALUES ("MelvinGOTS2BabyBodiesWithLemonPrintCream3.jpg");

INSERT image_product(product_id, image_id) VALUES(19, 73);
INSERT image_product(product_id, image_id) VALUES(19, 74);
INSERT image_product(product_id, image_id) VALUES(19, 75);
INSERT image_product(product_id, image_id) VALUES(19, 76);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Stockholm Heart Printed Shell Jacket Woody Rose", 
699,
"Get ready for outdoor adventures. Pink Stockholm jacket by Kuling. The jacket has taped seams.
– YKK® zipper at the front.
– Detachable hood.
– Adjustable cuffs with a velcro closure.
– Two front pockets.
– Reflective details for visibility.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Made with recycled materials.", 
"– Waterproof: 15 000 mm.
– Breathable: 8000 g/m2/24 h.
– Windproof.
– Shell: 100% Polyester.
– Lining: 100% Recycled Polyester.
– Coating: 100% Polyurethane.
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


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Stockholm Shell Jacket Hazelnut Leo", 
699,
"Get ready for outdoor adventures. Beige Stockholm shell jacket by Kuling. The jacket has taped seams.
– YKK® zipper at the front.
– Detachable hood.
– Adjustable cuffs with a velcro closure.
– Two front pockets.
– Reflective details for visibility.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Made with recycled materials.", 
"– Waterproof: 15 000 mm.
– Breathable: 8000 g/m2/24 h.
– Windproof.
– Shell: 100% Polyester.
– Lining: 100% Recycled Polyester.
– Coating: 100% Polyurethane.
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


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Lillehammer Color-blocked Shell Jacket Honey Orange/Mist Blue", 
599,
"Orange Lillehammer jacket by Kuling. The jacket has taped seams.
– Detachable hood.
– Zip closure at the front.
– Two front pockets.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Made with recycled materials.", 
"– Waterproof: 15 000 mm.
– Breathable: 8000 g/m2/24 h.
– Windproof.
– Shell: 100% Polyamide.
– Lining: 100% Recycled Polyester.
– Coating: 100% Polyurethane.
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

INSERT image(name) VALUES ("reima.jpg");
INSERT brand(name, image_id, description) VALUES ("Reima", 98, "Founded in 1944 in Finland, Reima’s mission is to encourage children to discover the joy of movement by providing them with functional and durable clothing. Reima’s boots and sneakers, coveralls and jackets, and swimwear and UV-clothing are some of the ways Reima has your child covered.");

INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Veli Winter Jacket Thyme Green", 
1199,
"Be warm and comfy on winter adventures. Green Veli winter jacket by Reima. The jacket has sealed main seams for waterproofing.
– Durable material.
– Detachable hood.
– Zip closure at the front.
– Two front pockets.
– Adjustable cuffs.
– Adjustable waist.
– Reflective details for visibility.
– Smooth lining.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Martindale-tested as abrasion-resistant up to 40,000 cycles.
– Made with recycled materials.", 
"– Waterproof: 10 000 mm.
– Breathable: 7000 g/m2/24 h.
– Windproof.
– Temperature: 0 to -20°C.
– 100% Polyester.
– Coating: 100% Polyurethane.
– Lining: 100% Polyester.
– Machine washable 40 degrees.", 10, 7, 1);

INSERT image(name) VALUES ("VeliWinterJacketThymeGreen.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen1.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen2.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen3.jpg");
INSERT image(name) VALUES ("VeliWinterJacketThymeGreen4.jpg");

INSERT image_product(product_id, image_id) VALUES(23, 99);
INSERT image_product(product_id, image_id) VALUES(23, 100);
INSERT image_product(product_id, image_id) VALUES(23, 101);
INSERT image_product(product_id, image_id) VALUES(23, 102);
INSERT image_product(product_id, image_id) VALUES(23, 103);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Reimatec Mutka Winter Jacket Navy", 
1199,
"Navy Reimatec Mutka jacket by Reima. The jacket has sealed seams.
– Detachable hood with faux fur trim.
– Zip closure at the front.
– Two front pockets.
– Adjustable cuffs.
– Reflective detail for visibility.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Martindale-tested as abrasion-resistant up to 20,000 cycles.", 
"– Waterproof: 10 000 mm.
– Breathable: 12 000 g/m²/24h.
– Temperature: -10 to -30°C.
– Shell, Lining And Padding: 100% Polyester.
– Coating: 100% Polyurethane.
– Machine washable 40 degrees.", 10, 7, 1);

INSERT image(name) VALUES ("ReimatecMutkaWinterJacketNavy.jpg");
INSERT image(name) VALUES ("ReimatecMutkaWinterJacketNavy1.jpg");
INSERT image(name) VALUES ("ReimatecMutkaWinterJacketNavy2.jpg");
INSERT image(name) VALUES ("ReimatecMutkaWinterJacketNavy3.jpg");
INSERT image(name) VALUES ("ReimatecMutkaWinterJacketNavy4.jpg");
INSERT image(name) VALUES ("ReimatecMutkaWinterJacketNavy5.jpg");

INSERT image_product(product_id, image_id) VALUES(24, 104);
INSERT image_product(product_id, image_id) VALUES(24, 105);
INSERT image_product(product_id, image_id) VALUES(24, 106);
INSERT image_product(product_id, image_id) VALUES(24, 107);
INSERT image_product(product_id, image_id) VALUES(24, 108);
INSERT image_product(product_id, image_id) VALUES(24, 109);


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Kumlinge Reimatec Shell Jacket Black", 
899,
"Get ready for outdoor adventures. Black Kumlinge Reimatec shell jacket by Reima. The jacket has sealed seams.
– Detachable hood.
– Zip closure at the front.
– Two front pockets.
– Elasticated cuffs.
– Reflective details for visibility.
– Fluorocarbon-free water- and dirt-repellent BIONIC-FINISH®ECO.
– Martindale-tested as abrasion-resistant up to 5,000 cycles.
– Made with recycled materials.", 
"– Waterproof: 10 000 mm.
– Breathable: 5000 g/m²/24h.
– Shell: 100% Recycled Polyester.
– Coating: 100% Polyurethane.
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


INSERT product(name, price, description, specification, brand_id, subcategory_id, discount) VALUES (
"Sipoo Softshell Jacket Grayish green", 
799,
"Get ready for outdoor adventures. Grayish green Sipoo softshell jacket by Reima. The softshell jacket has a technical material with three layers in fleece.
– Detachable hood.
– Zip closure at the front.
– Two front pockets with a zip closure.
– Reflective details for better visibility.
– Fleece lining.
– Teflon EcoElite® fluorocarbon-free water- and dirt-repellent finish.
– Made with recycled materials.", 
"– Water-resistant.
– Breathable.
– Main: 92% Polyester, 8% Elastane.
– Membrane: 100% Polyurethane.
– Machine washable 40 degrees.", 10, 7, 1);

INSERT image(name) VALUES ("SipooSoftshellJacketGrayishgreen.jpg");
INSERT image(name) VALUES ("SipooSoftshellJacketGrayishgreen1.jpg");
INSERT image(name) VALUES ("SipooSoftshellJacketGrayishgreen2.jpg");

INSERT image_product(product_id, image_id) VALUES(26, 118);
INSERT image_product(product_id, image_id) VALUES(26, 119);
INSERT image_product(product_id, image_id) VALUES(26, 120);




