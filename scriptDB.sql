USE [master]
GO
/****** Object:  Database [Assignment3_NguyenPhamThanhLong]    Script Date: 4/11/2021 4:52:34 PM ******/
CREATE DATABASE [Assignment3_NguyenPhamThanhLong]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Assignment3_NguyenPhamThanhLong', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Assignment3_NguyenPhamThanhLong.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Assignment3_NguyenPhamThanhLong_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Assignment3_NguyenPhamThanhLong_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Assignment3_NguyenPhamThanhLong].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET RECOVERY FULL 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Assignment3_NguyenPhamThanhLong', N'ON'
GO
USE [Assignment3_NguyenPhamThanhLong]
GO
/****** Object:  Table [dbo].[tblCars]    Script Date: 4/11/2021 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblCars](
	[carId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NOT NULL,
	[color] [varchar](255) NOT NULL,
	[year] [varchar](255) NOT NULL,
	[category] [varchar](255) NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[status] [nchar](30) NULL,
	[feedback] [varchar](255) NULL,
	[rating] [int] NULL,
	[votes] [int] NULL,
 CONSTRAINT [PK__tblCars__1436F17461639F42] PRIMARY KEY CLUSTERED 
(
	[carId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblDemise]    Script Date: 4/11/2021 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDemise](
	[idDemise] [int] IDENTITY(1,1) NOT NULL,
	[carId] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[totalPrice] [float] NOT NULL,
	[rentId] [int] NOT NULL,
	[rentDate] [date] NULL,
	[returnDate] [date] NULL,
 CONSTRAINT [PK__tblDemis__CBAC074C8D3179E8] PRIMARY KEY CLUSTERED 
(
	[idDemise] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 4/11/2021 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[discountId] [int] IDENTITY(1,1) NOT NULL,
	[discountCode] [char](10) NOT NULL,
	[deadline] [date] NOT NULL,
	[discountPercent] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[discountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRental]    Script Date: 4/11/2021 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRental](
	[rentId] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[total] [float] NOT NULL,
	[rentalDate] [date] NOT NULL,
	[status] [varchar](30) NULL,
	[dateRental] [date] NOT NULL,
	[dateReturn] [date] NOT NULL,
	[discountId] [int] NULL,
 CONSTRAINT [PK__Rental__354E5A8727D421D1] PRIMARY KEY CLUSTERED 
(
	[rentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 4/11/2021 4:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[phone] [varchar](50) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[address] [varchar](50) NOT NULL,
	[createDate] [date] NOT NULL,
	[status] [varchar](50) NOT NULL,
	[code] [int] NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[tblCars] ON 

INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (1, N'hyundai', N'den', N'2000', N'suv', 20000, 7, NULL, N' ', 5, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (2, N'toyota', N'trang', N'2003', N'minivan', 10000, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (3, N'honda', N'xanh', N'2002', N'sedan', 30000, 3, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (4, N'mercedes', N'do', N'2000', N'suv', 40000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (5, N'nissan', N'do', N'2001', N'sedan', 20000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (6, N'BMW', N'xanh', N'2004', N'sedan', 50000, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (7, N'Kia', N'do', N'2002', N'minivan', 25000, 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (8, N'chevrolet', N'trang', N'2001', N'suv', 22000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (9, N'ford', N'xanh', N'2005', N'minivan', 23000, 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (10, N'fiat', N'vang', N'2003', N'sedan', 21000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (11, N'Chrysler', N'vang', N'2001', N'suv', 20000, 3, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (12, N'citroen', N'trang', N'2001', N'suv', 40000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (13, N'peugeot', N'den', N'2000', N'sedan', 13000, 3, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (14, N'Suzuki', N'do', N'2007', N'minivan', 20000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (15, N'Geely', N'xanh', N'2001', N'suv', 21000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (16, N'Mazda', N'do', N'2000', N'minivan', 21000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (17, N'Audi', N'vang', N'2007', N'suv', 60000, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (18, N'Dacia', N'xanh', N'2000', N'minivan', 14000, 5, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (19, N'Daewoo', N'den', N'2007', N'minivan', 20000, 4, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (20, N'Ferrari', N'vang', N'2001', N'suv', 24000, 5, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (21, N'Infiniti', N'trang', N'2009', N'suv', 23000, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (22, N'Lamborghini', N'vang', N'2000', N'suv', 70000, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[tblCars] ([carId], [name], [color], [year], [category], [price], [quantity], [status], [feedback], [rating], [votes]) VALUES (23, N'xe1', N'vang', N'2000', N'suv', 20000, 5, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[tblCars] OFF
SET IDENTITY_INSERT [dbo].[tblDemise] ON 

INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (9, 1, 1, 20000, 2, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (10, 1, 1, 20000, 7, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (11, 1, 2, 40000, 8, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (12, 2, 1, 10000, 8, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (13, 18, 2, 28000, 9, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (14, 19, 1, 20000, 9, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (15, 1, 1, 20000, 10, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (16, 1, 1, 20000, 11, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (17, 1, 2, 40000, 12, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (18, 1, 1, 20000, 13, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (19, 1, 1, 20000, 14, NULL, NULL)
INSERT [dbo].[tblDemise] ([idDemise], [carId], [quantity], [totalPrice], [rentId], [rentDate], [returnDate]) VALUES (20, 2, 1, 10000, 14, NULL, NULL)
SET IDENTITY_INSERT [dbo].[tblDemise] OFF
SET IDENTITY_INSERT [dbo].[tblDiscount] ON 

INSERT [dbo].[tblDiscount] ([discountId], [discountCode], [deadline], [discountPercent]) VALUES (7, N'12c       ', CAST(N'2021-06-07' AS Date), 50)
INSERT [dbo].[tblDiscount] ([discountId], [discountCode], [deadline], [discountPercent]) VALUES (8, N'6h        ', CAST(N'2021-07-08' AS Date), 25)
INSERT [dbo].[tblDiscount] ([discountId], [discountCode], [deadline], [discountPercent]) VALUES (9, N'3cc       ', CAST(N'2021-05-05' AS Date), 50)
INSERT [dbo].[tblDiscount] ([discountId], [discountCode], [deadline], [discountPercent]) VALUES (10, N'null      ', CAST(N'2021-03-05' AS Date), 0)
SET IDENTITY_INSERT [dbo].[tblDiscount] OFF
SET IDENTITY_INSERT [dbo].[tblRental] ON 

INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (1, N'1', 20000, CAST(N'2021-03-04' AS Date), N'false', CAST(N'2021-06-06' AS Date), CAST(N'2021-06-07' AS Date), NULL)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (2, N'1', 90000, CAST(N'2021-03-03' AS Date), N'false', CAST(N'2021-07-07' AS Date), CAST(N'2020-07-14' AS Date), NULL)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (7, N'1', 40000, CAST(N'2021-03-05' AS Date), N'true', CAST(N'2021-03-06' AS Date), CAST(N'2021-03-08' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (8, N'1', 40000, CAST(N'2021-03-06' AS Date), N'true', CAST(N'2021-03-02' AS Date), CAST(N'2021-03-12' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (9, N'1', 336000, CAST(N'2021-03-06' AS Date), N'false', CAST(N'2021-03-10' AS Date), CAST(N'2021-03-17' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (10, N'1', 160000, CAST(N'2021-03-06' AS Date), N'false', CAST(N'2021-03-09' AS Date), CAST(N'2021-03-17' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (11, N'1', 20000, CAST(N'2021-03-06' AS Date), N'true', CAST(N'2021-03-11' AS Date), CAST(N'2021-03-12' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (12, N'1', 200000, CAST(N'2021-03-06' AS Date), N'true', CAST(N'2021-03-12' AS Date), CAST(N'2021-03-17' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (13, N'1', 100000, CAST(N'2021-03-06' AS Date), N'true', CAST(N'2021-03-12' AS Date), CAST(N'2021-03-17' AS Date), 10)
INSERT [dbo].[tblRental] ([rentId], [email], [total], [rentalDate], [status], [dateRental], [dateReturn], [discountId]) VALUES (14, N'vttvan@gmail.com', 50000, CAST(N'2021-03-16' AS Date), N'true', CAST(N'2021-03-18' AS Date), CAST(N'2021-03-23' AS Date), 10)
SET IDENTITY_INSERT [dbo].[tblRental] OFF
INSERT [dbo].[tblUser] ([email], [password], [phone], [name], [address], [createDate], [status], [code]) VALUES (N'1', N'1', N'1', N'long', N'1', CAST(N'2020-02-12' AS Date), N'active', NULL)
INSERT [dbo].[tblUser] ([email], [password], [phone], [name], [address], [createDate], [status], [code]) VALUES (N'2', N'2', N'2', N'son', N'2', CAST(N'2020-02-02' AS Date), N'active', 33)
INSERT [dbo].[tblUser] ([email], [password], [phone], [name], [address], [createDate], [status], [code]) VALUES (N'3', N'3', N'3', N'3', N'3', CAST(N'2021-02-26' AS Date), N'active', 23)
INSERT [dbo].[tblUser] ([email], [password], [phone], [name], [address], [createDate], [status], [code]) VALUES (N'thanhlong.uffm@gmail.com', N'1', N'1', N'1', N'1', CAST(N'2021-03-07' AS Date), N'active', 9806)
INSERT [dbo].[tblUser] ([email], [password], [phone], [name], [address], [createDate], [status], [code]) VALUES (N'vttvan@gmail.com', N'vttvan@gmail.com', N'123', N'vttvan@gmail.com', N'vttvan@gmail.com', CAST(N'2021-03-16' AS Date), N'active', 9310)
ALTER TABLE [dbo].[tblDemise]  WITH CHECK ADD  CONSTRAINT [FK_tblDemise_tblCars] FOREIGN KEY([carId])
REFERENCES [dbo].[tblCars] ([carId])
GO
ALTER TABLE [dbo].[tblDemise] CHECK CONSTRAINT [FK_tblDemise_tblCars]
GO
ALTER TABLE [dbo].[tblDemise]  WITH CHECK ADD  CONSTRAINT [FK_tblDemise_tblRental] FOREIGN KEY([rentId])
REFERENCES [dbo].[tblRental] ([rentId])
GO
ALTER TABLE [dbo].[tblDemise] CHECK CONSTRAINT [FK_tblDemise_tblRental]
GO
ALTER TABLE [dbo].[tblRental]  WITH CHECK ADD  CONSTRAINT [FK_tblRental_tblDiscount] FOREIGN KEY([discountId])
REFERENCES [dbo].[tblDiscount] ([discountId])
GO
ALTER TABLE [dbo].[tblRental] CHECK CONSTRAINT [FK_tblRental_tblDiscount]
GO
ALTER TABLE [dbo].[tblRental]  WITH CHECK ADD  CONSTRAINT [FK_tblRental_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblRental] CHECK CONSTRAINT [FK_tblRental_tblUser]
GO
USE [master]
GO
ALTER DATABASE [Assignment3_NguyenPhamThanhLong] SET  READ_WRITE 
GO
