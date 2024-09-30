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



CREATE TABLE [Product] (
    pro_id INT IDENTITY(1,1) PRIMARY KEY,
    pro_name VARCHAR(255) NOT NULL,
    description TEXT,
    pro_price DECIMAL(10, 2) NOT NULL,
    pro_discount DECIMAL(5, 2),
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


INSERT INTO Product (pro_name, description, pro_price, pro_discount, pro_quantity, madein, updated_at, pro_image, cat_id, brand_id)
VALUES 
('Manami Earphone', 'The Manami Earphone is a high-quality in-ear audio accessory designed for clear sound and comfort. Featuring noise isolation, deep bass, and crisp treble, it provides an immersive listening experience. The ergonomic design ensures a secure fit for extended use, while the built-in microphone and inline controls make it easy to take calls and control your music. Ideal for music lovers seeking reliable performance and stylish design.',100000, 0, 100,'Japan', '2024-09-30', './asset/img/img_all/img_product/img_earphone/nanami_Headset.png', 2, 2),
('MacBook Air 2022', 'The MacBook Air 2022 is a sleek, ultra-portable laptop featuring Apple''s M2 chip, offering impressive performance with energy efficiency. It has a 13.6-inch Liquid Retina display, up to 18 hours of battery life, a fanless design for silent operation, and a redesigned look that''s thinner and lighter. The device also includes an improved 1080p FaceTime HD camera, a MagSafe charging port, and support for up to 24GB of unified memory, making it ideal for everyday tasks and creative workflows.',999999, 0, 100, 'America', '2024-09-30', './asset/img/img_all/img_product/img_laptop/laptop.png', 4, 1),
('Oculus Quest 2', 'The Oculus Quest 2 is an advanced all-in-one virtual reality headset by Meta, featuring a high-resolution display, powerful Qualcomm Snapdragon XR2 processor, and intuitive Touch controllers. It offers a wireless, standalone VR experience with access to a vast library of games and apps. The lightweight design, adjustable straps, and 3D positional audio provide comfort and immersion, making it ideal for gaming, fitness, and social experiences without the need for a PC or external sensors.',5999999, 0, 100,'America', '2024-09-30', './asset/img/img_all/img_product/img_vr/vr.png', 6, 7),



-- Tạo bảng Users
CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(150) NOT NULL,
    password VARCHAR(70) NOT NULL,
    email VARCHAR(40) NOT NULL,
    phone INT,
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
VALUES ('le huu khoa', '6cb75f652a9b52798eb6cf2201057c73', 'khoa@gmail.com', '0123', 'Địa chỉ 2', 2, './asset/img/img_all/img_user/chihuahua.jpg', 1);
--password2