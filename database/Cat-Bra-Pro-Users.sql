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
('Oculus', 'A brand specializing in virtual reality products, owned by Meta (Facebook)'),
('Asus', ' A technology company known for laptops, gaming hardware, and innovative computing solutions.'),
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
    madein VARCHAR(100) Not null,
    created_at DATE DEFAULT GETDATE(),
    updated_at DATE,
    cat_id INT,
    brand_id INT,
    FOREIGN KEY (cat_id) REFERENCES Category(cat_id),
    FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);





-- Not sale
INSERT INTO Product (pro_name, description, pro_price, madein, updated_at, cat_id, brand_id)
VALUES
('JBL Flip 6', 'The JBL Flip 6 is a portable Bluetooth speaker offering powerful sound, deep bass, and up to 12 hours of playtime. It''s waterproof, dustproof, and built for durability, making it ideal for outdoor use.', 2200000, 'America', '2024-10-1', 7, 3),
('PlayStation 5', 'The PlayStation 5 is Sony''s latest console, delivering fast performance with a custom SSD, 4K graphics, and ray tracing.', 11000000, 'Japan', '2024-10-1', 5, 3),
('Apple Watch Series 6', 'The Apple Watch Series 6 offers advanced health tracking with a blood oxygen sensor, ECG app, and heart rate monitor.', 10270000, 'America', '2024-10-1', 3, 1),
('IPhone 14 Pro', 'The iPhone 14 Pro features a 6.1-inch Super Retina XDR display, A16 Bionic chip, Dynamic Island for interactive notifications.', 17590000, 'America', '2024-10-1', 1, 1),
('Manami Earphone', 'The Manami Earphone offers clear sound with deep bass, noise isolation, and a comfortable fit.', 100000, 'Japan', '2024-09-30', 2, 2),
('MacBook Air 2022', 'The MacBook Air 2022 is an ultra-portable laptop with the M2 chip, a 13.6-inch Liquid Retina display.', 999999, 'America', '2024-09-30', 4, 1),
('Oculus Quest 2', 'The Oculus Quest 2 is a wireless VR headset by Meta with a high-resolution display, Snapdragon XR2 processor.', 5999999, 'America', '2024-09-30', 6, 7),
('Bose SoundLink Revolve', 'The Bose SoundLink Revolve is a Bluetooth speaker with 360-degree sound for consistent, uniform coverage.', 5000000, 'America', '2024-10-5', 7, 3);

-- Sale
INSERT INTO Product (pro_name, description, pro_price, pro_sale, madein, updated_at, cat_id, brand_id)
VALUES
('Asus Zenbook Ux305', 'The Asus ZenBook UX305 is an ultra-slim, lightweight laptop with a 13.3-inch Full HD display, Intel Core M processor, and fanless design for quiet operation. It offers fast SSD storage, up to 8GB RAM, and long battery life, perfect for portable productivity.', 16500000, 14100000, 'Taiwan', '2024-10-3', 4, 8),
('Nintendo Switch', 'The Nintendo Switch is a versatile gaming console that switches between handheld and TV modes. With a wide library of games and innovative Joy-Con controllers, it offers immersive multiplayer experiences and portability, making it ideal for gamers of all ages.', 8500000, 7500000, 'Japan', '2024-10-3', 5, 9),
('Samsung Galaxy S24 Ultra', 'The Samsung Galaxy S24 Ultra is a premium smartphone with a Dynamic AMOLED 2X display, powerful performance, and an advanced camera system. It offers long battery life and S Pen support, making it perfect for users seeking a high-performance device.', 30000000, 27000000, 'South Korea', '2024-10-3', 1, 2),
('Dell XPS 13', 'The Dell XPS 13 is a premium laptop featuring a 13.4-inch InfinityEdge display, 11th Gen Intel Core processors, up to 16GB of RAM, and a sleek design with advanced thermal cooling.', 22000000, 20000000, 'China', '2024-10-6', 4, 4),
('HP Spectre x360', 'The HP Spectre x360 is a convertible laptop with a 13.3-inch 4K display, Intel Core i7 processor, 16GB RAM, and 512GB SSD. It has a sleek, lightweight design with long battery life and versatile tablet mode.', 24000000, 22000000, 'USA', '2024-10-7', 4, 5),
('Apple Watch Series 8', 'The Apple Watch Series 8 features an always-on Retina display, advanced health tracking, ECG, and blood oxygen monitoring, plus enhanced workout features.', 12000000, 10500000, 'China', '2024-10-8', 3, 1),
('Sony WF-1000XM4', 'The Sony WF-1000XM4 wireless earphones feature industry-leading noise cancellation, superior sound quality, and up to 24 hours of battery life with the charging case.', 6000000, 5200000, 'Japan', '2024-10-11', 2, 3),
('HTC Vive Pro 2', 'The HTC Vive Pro 2 is a high-end VR headset offering 5K resolution, a wide 120-degree field of view, and precise tracking for a premium virtual reality experience.', 25000000, 22000000, 'Taiwan', '2024-10-12', 6, 3);



CREATE TABLE Product_Details (
    proDetail_id INT PRIMARY KEY IDENTITY(1,1),
    color_name VARCHAR(55),
    quantity INT,
	image text, 
    pro_id INT,
    FOREIGN KEY (pro_id) REFERENCES [Product](pro_id)
);


-- Not sale
INSERT INTO Product_Details (color_name, quantity, image, pro_id)
VALUES
('default', 100, './asset/img/img_all/img_product/img_speaker/jblflip6.jpg', 1),
('default', 100, './asset/img/img_all/img_product/img_console/playstation5.jpg', 2),
('default', 100, './asset/img/img_all/img_product/img_watch/applewatchseries6_carbon.jpg', 3),
('red', 100, './asset/img/img_all/img_product/img_watch/applewatchseries6_red.jpg', 3),
('default', 100, './asset/img/img_all/img_product/img_phone/iphone14_purple.jpg', 4),
('yellow', 50, './asset/img/img_all/img_product/img_phone/iphone14_yellow.jpg', 4),
('white', 50, './asset/img/img_all/img_product/img_phone/iphone14_white.jpg', 4),
('default', 100, './asset/img/img_all/img_product/img_earphone/nanami_Headset.png', 5),
('default', 100, './asset/img/img_all/img_product/img_laptop/laptop.png', 6),
('green', 100, './asset/img/img_all/img_product/img_laptop/laptop_green.png', 6),
('yellow', 100, './asset/img/img_all/img_product/img_laptop/laptop_yellow.png', 6),
('purple', 100, './asset/img/img_all/img_product/img_laptop/laptop_purple.png', 6),
('default', 100, './asset/img/img_all/img_product/img_vr/vr.png', 7),
('default', 80, './asset/img/img_all/img_product/img_speaker/BoseSoundLinkRevolve_white.jpg', 8),
('black', 80, './asset/img/img_all/img_product/img_speaker/BoseSoundLinkRevolve_blackjpg.jpg', 8);


-- Sale
INSERT INTO Product_Details (color_name, quantity, image, pro_id)
VALUES
('default', 100, './asset/img/img_all/img_product/img_laptop/AsusZenbookUx305_Sale.png', 9),
('default', 100, './asset/img/img_all/img_product/img_console/NintendoSwitch_sale.jpg', 10),
('default', 100, './asset/img/img_all/img_product/img_phone/SamsungGalaxyS24Ultra_sale.jpg', 11),
('default', 50, './asset/img/img_all/img_product/img_laptop/DellXPS13_sale.png', 12),
('default', 70, './asset/img/img_all/img_product/img_laptop/HPSpectrex360_sale.png', 13),
('default', 150, './asset/img/img_all/img_product/img_watch/AppleWatchSeries8_black_sale.jpg', 14),
('white', 150, './asset/img/img_all/img_product/img_watch/AppleWatchSeries8_white_sale.jpg', 14),
('default', 180, './asset/img/img_all/img_product/img_earphone/SonyWF1000XM4_sale.png', 15),
('white', 180, './asset/img/img_all/img_product/img_earphone/SonyWF1000XM4_white_sale.png', 15),
('default', 40, './asset/img/img_all/img_product/img_vr/HTCVivePro2_sale.png', 16);

SELECT * FROM Product_Details Where pro_id = 14 AND color_name != 'default'


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
INSERT INTO Users (username, password, email, phone, address, role, avatar, status_user)
VALUES ('chan', 'E3F75D09A9F7BD7F7666297263A973AB', 'nguyengiachan.gr2020@gmail.com', '1234567890', '', 1, '/asset/img/img_all/img_user/chihuahua.jpg', 1);


select * from Users Where user_id = 4;



-- Thêm user với role = 2
INSERT INTO Users (username, password, email, phone, address, role, avatar, status_user)
VALUES ('le huu khoa', 'E10ADC3949BA59ABBE56E057F20F883E', 'khoa@gmail.com', '0911396989', 'Địa chỉ 2', 2, '/asset/img/img_all/img_user/chihuahua.jpg', 1);
--123456

CREATE TABLE Specification (
    spec_id INT PRIMARY KEY IDENTITY(1,1),
    spec_name VARCHAR(255),
    spec_value VARCHAR(255),
    unit VARCHAR(50),
    weight DECIMAL(5, 2),
    dimensions VARCHAR(255),
    pro_id INT,
    FOREIGN KEY (pro_id) REFERENCES Product(pro_id)
);

INSERT INTO Specification (spec_name, spec_value, unit, weight, dimensions, pro_id) VALUES
('Battery', '5000mAh', 'mAh', 0.2, '15x7x1 cm', 1),
('Display', '6.5 inch', 'inch', 0.15, '16x8x0.9 cm', 1),
('Processor', 'Snapdragon 888', '', 0.1, 'N/A', 2);

--Voucher
CREATE TABLE Voucher (
    voucher_id INT PRIMARY KEY IDENTITY(1,1),
    voucher_type VARCHAR(55), 
    voucher_img TEXT,
	voucher_description VARCHAR(55)
);


INSERT INTO Voucher (voucher_type, voucher_img, voucher_description)
VALUES 
('normal', './asset/img/img_all/img_cart/voucher_normal.png', 'discount 5% - 10%'),
('medium', './asset/img/img_all/img_cart/voucher_medium.png', 'discount 15% - 25%'),
('rare', './asset/img/img_all/img_cart/voucher_rare.png', 'discount 30% - 50%');

--Voucher Detail
CREATE TABLE VoucherDetail (
    voucherDetail_id INT PRIMARY KEY IDENTITY(1,1),
	voucher_name VARCHAR(50),
	voucher_quantity INT,
    voucher_discount INT, 
    voucher_date DATE DEFAULT GETDATE(),
    voucher_expire_date DATE,
    voucher_id INT, 
    user_id INT, 
    FOREIGN KEY (voucher_id) REFERENCES Voucher(voucher_id), 
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Hàm nếu có trùng tên voucher thì tăng quanity lên 1
CREATE PROCEDURE AddOrUpdateVoucher
    @voucher_name VARCHAR(50),
    @voucher_quantity INT,
    @voucher_discount INT,
    @voucher_expire_date DATE,
    @voucher_id INT,
    @user_id INT
AS
BEGIN
    -- Kiểm tra xem voucher với tên và user_id đã tồn tại hay chưa
    IF EXISTS (SELECT 1 FROM VoucherDetail 
               WHERE voucher_name = @voucher_name 
               AND user_id = @user_id)
    BEGIN
        -- Nếu tồn tại, cập nhật số lượng
        UPDATE VoucherDetail
        SET voucher_quantity = voucher_quantity + @voucher_quantity,
            voucher_discount = @voucher_discount, -- Nếu muốn cập nhật discount
            voucher_expire_date = @voucher_expire_date -- Nếu muốn cập nhật expire date
        WHERE voucher_name = @voucher_name 
        AND user_id = @user_id;
    END
    ELSE
    BEGIN
        -- Nếu chưa tồn tại, thêm một bản ghi mới
        INSERT INTO VoucherDetail (
            voucher_name, 
            voucher_quantity, 
            voucher_discount, 
            voucher_expire_date,
            voucher_id, 
            user_id
        )
        VALUES (
            @voucher_name, 
            @voucher_quantity, 
            @voucher_discount, 
            @voucher_expire_date,
            @voucher_id, 
            @user_id
        );
    END
END;


--cách add
EXEC AddOrUpdateVoucher 
    @voucher_name = 'Discount 17%', 
    @voucher_quantity = 1, 
    @voucher_discount = 17, 
    @voucher_expire_date = '2024-12-31', 
    @voucher_id = 2, 
    @user_id = 3;



select * from VoucherDetail
drop table Order_Details
drop table [Order]
drop table Payment

select * from [Order]
select * from Order_Details
select * from Payment


CREATE TABLE Payment (
    payment_id INT PRIMARY KEY IDENTITY(1,1),
    payment_method VARCHAR(50),  -- Payment methods like Credit, PayPal, etc.
);
CREATE TABLE [Order] (
    order_id INT PRIMARY KEY IDENTITY(1,1),
    order_date DATE DEFAULT GETDATE(),
	user_id INTEGER,
    payment_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (payment_id) REFERENCES Payment(payment_id)
);


CREATE TABLE Order_Details (
    order_detail_id INT PRIMARY KEY IDENTITY(1,1),
    quantity INTEGER,
    price DECIMAL(10, 2),
    order_id INTEGER,
    proDetail_id INTEGER,
	voucherDetail_id int,
    status VARCHAR(50), -- tình trạng đơn hàng
	check VARCHAR(50), -- check người dùng có coi thông báo chưa
	FOREIGN KEY (voucherDetail_id) REFERENCES VoucherDetail(voucherDetail_id),
    FOREIGN KEY (order_id) REFERENCES [Order](order_id)
);








