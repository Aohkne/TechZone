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


select * from Users

-- Thêm user với role = 2
--INSERT INTO Users (username, password, email, phone, address, role, avatar, status_user)
--VALUES ('user2', 'password2', 'user2@example.com', '9876543210', 'Địa chỉ 2', 2, 'avatar2.jpg', 1);