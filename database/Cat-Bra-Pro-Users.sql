-- create database SWP

CREATE TABLE Category (
    cat_id INT IDENTITY(1,1) PRIMARY KEY,
    cat_name VARCHAR(30) NOT NULL,
    description TEXT
);
INSERT INTO Category (cat_name, description)
VALUES 
('Smartphone', 'A portable device that combines mobile telephone and computing functions'),
('Earphone', 'A small headphone worn inside the ear'),
('Watch', 'A portable timepiece intended to be carried or worn by a person'),
('Laptop', 'A small, portable personal computer with a screen and alphanumeric keyboard'),
('Console', 'A gaming device that outputs a video signal to display a video game'),
('VR', 'Virtual Reality headset for immersive gaming and experiences'),
('Speaker', 'An output device that produces sound');

--  1 - Smartphone
--  2 - Earphone
--  3 - Watch
--  4 - Laptop
--  5 - Console
--  6 - VR
--  7 - Speaker



CREATE TABLE Brand (
    brand_id INT IDENTITY(1,1) PRIMARY KEY,
    brand_name VARCHAR(30) NOT NULL,
    description TEXT
);
INSERT INTO Brand (brand_name, description)
VALUES
('Apple', 'A technology company known for iPhones, MacBooks, and other devices'),
('Samsung', 'A multinational conglomerate producing smartphones, TVs, and other electronics'),
('Sony', 'A Japanese company famous for PlayStation, cameras, and audio equipment'),
('Dell', 'A global leader in computers and related products'),
('HP', 'A multinational information technology company known for laptops and printers'),
('Xiaomi', 'A Chinese electronics company producing smartphones, smartwatches, and more'),
('Oculus', 'A brand specializing in virtual reality products, owned by Meta (Facebook)');
('Asus', ' A technology company known for laptops, gaming hardware, and innovative computing solutions.')
('Nintendo', 'A video game company known for its iconic consoles, innovative gameplay experiences, and beloved franchises like Mario, Zelda, and Pokémon.')

--  1 - Apple
--  2 - Samsung
--  3 - Sony
--  4 - Dell
--  5 - HP
--  6 - Xiaomi
--  7 - Oculus
--  8 - Asus
--  9 - Nintendo



CREATE TABLE [Product] (
    pro_id INT IDENTITY(1,1) PRIMARY KEY,
    pro_name VARCHAR(255) NOT NULL,
    description TEXT,
    pro_price DECIMAL(10, 2) NOT NULL,
    pro_sale DECIMAL(10, 2),
    pro_quantity INT NOT NULL,
    madein VARCHAR(100) Not null,
    created_at DATE DEFAULT GETDATE(),
    updated_at DATE,
    pro_image VARCHAR(555) not null,
    cat_id INT,
    brand_id INT,
    FOREIGN KEY (cat_id) REFERENCES Category(cat_id),
    FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);

SELECT * FROM Product WHERE cat_id = '1'

INSERT INTO Product (pro_name, description, pro_price, pro_quantity, madein, updated_at, pro_image, cat_id, brand_id)
VALUES
('JBL Flip 6', 'The JBL Flip 6 is a portable Bluetooth speaker offering powerful sound, deep bass, and up to 12 hours of playtime. It''s waterproof, dustproof, and built for durability, making it ideal for outdoor use.', 2200000, 100, 'America', '2024-10-1', './asset/img/img_all/img_product/img_speaker/jblflip6.jpg', 7, 3),
('PlayStation 5', 'The PlayStation 5 is Sony''s latest console, delivering fast performance with a custom SSD, 4K graphics, and ray tracing. Its DualSense controller adds haptic feedback and adaptive triggers for immersive gameplay. The PS5 supports most PS4 games and comes in Standard and Digital editions.', 11000000, 100, 'Japan', '2024-10-1', './asset/img/img_all/img_product/img_console/playstation5.jpg', 5, 3),
('Apple Watch Series 6', 'The Apple Watch Series 6 offers advanced health tracking with a blood oxygen sensor, ECG app, and heart rate monitor. It features an always-on display, built-in GPS, and sleep tracking, all in a stylish design.', 10270000, 100, 'America', '2024-10-1', './asset/img/img_all/img_product/img_watch/applewatchseries6_carbon.jpg', 3, 1),
('IPhone 14 Pro', 'The iPhone 14 Pro features a 6.1-inch Super Retina XDR display, A16 Bionic chip, Dynamic Island for interactive notifications, and a 48MP camera. It offers 5G, enhanced low-light performance, and longer battery life.', 17590000, 100, 'America', '2024-10-1', './asset/img/img_all/img_product/img_phone/iphone14_purple.jpg', 1, 1),
('Manami Earphone', 'The Manami Earphone offers clear sound with deep bass, noise isolation, and a comfortable fit. It includes a built-in mic and controls, perfect for music lovers wanting performance and style.',100000, 100,'Japan', '2024-09-30', './asset/img/img_all/img_product/img_earphone/nanami_Headset.png', 2, 2),
('MacBook Air 2022', 'The MacBook Air 2022 is an ultra-portable laptop with the M2 chip, a 13.6-inch Liquid Retina display, and up to 18 hours of battery life. It features a fanless, thin design, improved 1080p camera, and MagSafe charging, ideal for everyday and creative tasks.',999999, 100, 'America', '2024-09-30', './asset/img/img_all/img_product/img_laptop/laptop.png', 4, 1),
('Oculus Quest 2', 'The Oculus Quest 2 is a wireless VR headset by Meta with a high-resolution display, Snapdragon XR2 processor, and intuitive Touch controllers. It offers a standalone VR experience with a vast game library, lightweight design, and 3D audio for immersive gaming and fitness.',5999999, 100,'America', '2024-09-30', './asset/img/img_all/img_product/img_vr/vr.png', 6, 7),
('Bose SoundLink Revolve', 'The Bose SoundLink Revolve is a Bluetooth speaker with 360-degree sound for consistent, uniform coverage. It is water-resistant and offers up to 12 hours of battery life for portable use.', 5000000, 80, 'America', '2024-10-5', './asset/img/img_all/img_product/img_speaker/BoseSoundLinkRevolve_white.jpg', 7, 3),

INSERT INTO Product (pro_name, description, pro_price, pro_sale, pro_quantity, madein, updated_at, pro_image, cat_id, brand_id)
VALUES
('Asus Zenbook Ux305', 'The Asus ZenBook UX305 is an ultra-slim, lightweight laptop with a 13.3-inch Full HD display, Intel Core M processor, and fanless design for quiet operation. It offers fast SSD storage, up to 8GB RAM, and long battery life, perfect for portable productivity.', 16500000, 14100000, 100, 'Taiwan', '2024-10-3', './asset/img/img_all/img_product/img_laptop/AsusZenbookUx305_Sale.png', 4, 8),
('Nintendo Switch', 'The Nintendo Switch is a versatile gaming console that switches between handheld and TV modes. With a wide library of games and innovative Joy-Con controllers, it offers immersive multiplayer experiences and portability, making it ideal for gamers of all ages.', 8500000, 7500000, 100, 'Japan', '2024-10-3', './asset/img/img_all/img_product/img_console/NintendoSwitch_sale.jpg', 5, 9),
('Samsung Galaxy S24 Ultra', 'The Samsung Galaxy S24 Ultra is a premium smartphone with a Dynamic AMOLED 2X display, powerful performance, and an advanced camera system. It offers long battery life and S Pen support, making it perfect for users seeking a high-performance device.', 30000000, 27000000, 100, 'South Korea', '2024-10-3', './asset/img/img_all/img_product/img_phone/SamsungGalaxyS24Ultra_sale.jpg', 1, 2),
('Dell XPS 13', 'The Dell XPS 13 is a premium laptop featuring a 13.4-inch InfinityEdge display, 11th Gen Intel Core processors, up to 16GB of RAM, and a sleek design with advanced thermal cooling.', 22000000, 20000000, 50, 'China', '2024-10-6', './asset/img/img_all/img_product/img_laptop/DellXPS13_sale.png', 4, 4),
('HP Spectre x360', 'The HP Spectre x360 is a convertible laptop with a 13.3-inch 4K display, Intel Core i7 processor, 16GB RAM, and 512GB SSD. It has a sleek, lightweight design with long battery life and versatile tablet mode.', 24000000, 22000000, 70, 'USA', '2024-10-7', './asset/img/img_all/img_product/img_laptop/HPSpectrex360_sale.png', 4, 5),
('Apple Watch Series 8', 'The Apple Watch Series 8 features an always-on Retina display, advanced health tracking, ECG, and blood oxygen monitoring, plus enhanced workout features.', 12000000, 10500000, 150, 'China', '2024-10-8', './asset/img/img_all/img_product/img_watch/AppleWatchSeries8_black_sale.jpg', 3, 1),
('Sony WF-1000XM4', 'The Sony WF-1000XM4 wireless earphones feature industry-leading noise cancellation, superior sound quality, and up to 24 hours of battery life with the charging case.', 6000000, 5200000, 180, 'Japan', '2024-10-11', './asset/img/img_all/img_product/img_earphone/SonyWF1000XM4_sale.png', 2, 3),
('HTC Vive Pro 2', 'The HTC Vive Pro 2 is a high-end VR headset offering 5K resolution, a wide 120-degree field of view, and precise tracking for a premium virtual reality experience.', 25000000, 22000000, 40, 'Taiwan', '2024-10-12', './asset/img/img_all/img_product/img_vr/HTCVivePro2_sale.png', 6, 3),


-- Tạo bảng Users
CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(150) NOT NULL,
    password VARCHAR(70) NOT NULL,
    email VARCHAR(40) NOT NULL,
    phone VARCHAR(10),
    address VARCHAR(255),
    role INT,
    create_at DATE DEFAULT GETDATE(),
    avatar VARCHAR(555),
    status_user BIT
);

-- Thêm user với role = 1
--INSERT INTO Users (username, password, email, phone, address, role, avatar, status_user)
--VALUES ('user1', HASHBYTES('MD5', '123'), 'khoalhce181099@fpt.edu.vn', '1234567890', 'Địa chỉ 1', 1, 'avatar1.jpg', 1);


select * from Users Where user_id = 4;



-- Thêm user với role = 2
INSERT INTO Users (username, password, email, phone, address, role, avatar, status_user)
VALUES ('le huu khoa', '6cb75f652a9b52798eb6cf2201057c73', 'khoa@gmail.com', '0911396989', 'Địa chỉ 2', 2, './asset/img/img_all/img_user/chihuahua.jpg', 1);
--password2

select * from Users