-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 27, 2022 at 02:35 PM
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
  `golongan` char(1) DEFAULT NULL,
  `gapok` int(15) DEFAULT NULL,
  `transport` int(15) DEFAULT NULL,
  `pulsa` int(15) DEFAULT NULL,
  `total_gaji` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gaji`
--

INSERT INTO `gaji` (`NIP`, `nama`, `golongan`, `gapok`, `transport`, `pulsa`, `total_gaji`) VALUES
('001', 'John Doe', 'A', 2500000, 400000, 100000, 3000000),
('002', 'Jane', 'B', 0, 0, 0, 0);

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

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`NIP`, `nama`, `tanggal_lahir`, `jenis_kelamin`, `golongan`, `alamat`) VALUES
('001', 'John Doe', '2022-07-05', 'L', 'A', 'Beverly Hills St.'),
('002', 'Jane', '2022-07-13', 'P', 'B', 'Nama Jalan');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(75) NOT NULL,
  `password` varchar(75) DEFAULT NULL,
  `email` varchar(75) DEFAULT NULL,
  `role` enum('ADMIN','KARYAWAN') DEFAULT NULL,
  `createdAt` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `email`, `role`, `createdAt`) VALUES
('001', '1234', 'ad@email.com', 'ADMIN', '2022-07-27'),
('002', '1234', 'abc@yahuu.com', 'KARYAWAN', '2022-07-27');

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
  `file` longblob DEFAULT NULL,
  `file_dir` varchar(64) DEFAULT NULL
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
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
