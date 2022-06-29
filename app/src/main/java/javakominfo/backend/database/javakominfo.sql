-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 05, 2022 at 05:57 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javakominfo`
--

-- --------------------------------------------------------

--
-- Table structure for table `gaji`
--

CREATE TABLE `gaji` (
  `NIP` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `golongan` char(1) NOT NULL,
  `gapok` int(15) NOT NULL,
  `transport` int(15) NOT NULL,
  `pulsa` int(15) NOT NULL,
  `total_gaji` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `NIP` varchar(15) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` char(1) NOT NULL,
  `golongan` char(1) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(75) NOT NULL,
  `password` varchar(75) NOT NULL,
  `email` varchar(75) NOT NULL,
  `role` enum('SUPERADMIN','ADMIN','GUEST') NOT NULL,
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `email`, `role`, `createdAt`) VALUES
('AD', '1234', 'dasd@dd.com', 'ADMIN', '2022-04-04'),
('GS', '1234', 'gst@jj.com', 'GUEST', '2022-04-04'),
('SA', '1234', 'dasdsad@dasdas.com', 'SUPERADMIN', '2022-04-03');

-- --------------------------------------------------------

--
-- Table structure for table `v_a`
--

CREATE TABLE `v_a` (
  `ID` int(5) NOT NULL,
  `tanggal` date NOT NULL,
  `NIP` varchar(15) NOT NULL,
  `nama_va` varchar(80) NOT NULL,
  `domain` varchar(255) NOT NULL,
  `file_dir` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gaji`
--
ALTER TABLE `gaji`
  ADD PRIMARY KEY (`NIP`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`NIP`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `v_a`
--
ALTER TABLE `v_a`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `v_a`
--
ALTER TABLE `v_a`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
