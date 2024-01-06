-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2024 at 02:53 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_inventaris_aset`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_barang`
--

CREATE TABLE `tb_barang` (
  `tanggal` varchar(100) NOT NULL,
  `kode_part` varchar(100) NOT NULL,
  `nama_part` varchar(250) NOT NULL,
  `kategori` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_barang`
--

INSERT INTO `tb_barang` (`tanggal`, `kode_part`, `nama_part`, `kategori`, `jumlah`, `keterangan`) VALUES
('06-01-2024', 'BM001', 'Baju Polos', 'Baju', 40, ''),
('06-01-2024', 'BM002', 'Celana jeans', 'Celana', 22, ''),
('06-01-2024', 'BM003', 'Parka', 'Jaket', 22, '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_brg_keluar`
--

CREATE TABLE `tb_brg_keluar` (
  `tanggal` varchar(100) NOT NULL,
  `id_bk` varchar(100) NOT NULL,
  `gudang` varchar(250) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_brg_keluar`
--

INSERT INTO `tb_brg_keluar` (`tanggal`, `id_bk`, `gudang`, `keterangan`) VALUES
('02-01-2024', 'BK-000001', 'Cabang 1', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_brg_masuk`
--

CREATE TABLE `tb_brg_masuk` (
  `tanggal` varchar(100) NOT NULL,
  `id_bm` varchar(100) NOT NULL,
  `supplier` varchar(250) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_brg_masuk`
--

INSERT INTO `tb_brg_masuk` (`tanggal`, `id_bm`, `supplier`, `keterangan`) VALUES
('02-01-2024', 'BM-000002', 'Auto Fashion', ''),
('25-12-2023', 'BM-000003', 'Auto Fashion', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_brg_keluar`
--

CREATE TABLE `tb_detail_brg_keluar` (
  `tanggal` varchar(100) NOT NULL,
  `id_detail_bk` varchar(100) NOT NULL,
  `id_bk` varchar(100) NOT NULL,
  `gudang` varchar(250) NOT NULL,
  `kode_part` varchar(100) NOT NULL,
  `nama_part` varchar(250) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_detail_brg_keluar`
--

INSERT INTO `tb_detail_brg_keluar` (`tanggal`, `id_detail_bk`, `id_bk`, `gudang`, `kode_part`, `nama_part`, `jumlah`, `keterangan`) VALUES
('02-01-2024', 'DTBK-0001', 'BK-000001', 'Cabang 1', 'BM001', 'Baju Polos', 10, ''),
('06-01-2024', 'DTBK-0002', 'BK-000002', 'Cabang 1', 'BM002', 'Celana jeans', 10, '');

--
-- Triggers `tb_detail_brg_keluar`
--
DELIMITER $$
CREATE TRIGGER `batal_keluar` AFTER DELETE ON `tb_detail_brg_keluar` FOR EACH ROW BEGIN
	UPDATE tb_barang SET jumlah = jumlah+OLD.jumlah
    WHERE kode_part = OLD.kode_part;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `keluar` AFTER INSERT ON `tb_detail_brg_keluar` FOR EACH ROW BEGIN
	UPDATE tb_barang SET jumlah = jumlah-NEW.jumlah
    WHERE kode_part = NEW.kode_part;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_brg_masuk`
--

CREATE TABLE `tb_detail_brg_masuk` (
  `tanggal` varchar(100) NOT NULL,
  `id_detail_bm` varchar(100) NOT NULL,
  `id_bm` varchar(100) NOT NULL,
  `supplier` varchar(250) NOT NULL,
  `kode_part` varchar(100) NOT NULL,
  `nama_part` varchar(250) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_detail_brg_masuk`
--

INSERT INTO `tb_detail_brg_masuk` (`tanggal`, `id_detail_bm`, `id_bm`, `supplier`, `kode_part`, `nama_part`, `jumlah`, `keterangan`) VALUES
('02-01-2024', 'DTBM-0002', 'BM-000002', 'Auto Fashion', 'BM001', 'Baju Polos', 12, ''),
('06-01-2024', 'DTBM-0003', 'BM-000003', 'Auto Fashion', 'BM003', 'Parka', 12, ''),
('06-01-2024', 'DTBM-0004', 'BM-000003', 'Auto Fashion', 'BM001', 'Baju Polos', 20, '');

--
-- Triggers `tb_detail_brg_masuk`
--
DELIMITER $$
CREATE TRIGGER `batal_tambah` AFTER DELETE ON `tb_detail_brg_masuk` FOR EACH ROW BEGIN
	UPDATE tb_barang SET jumlah = jumlah-OLD.jumlah
    WHERE kode_part = OLD.kode_part;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tambah` AFTER INSERT ON `tb_detail_brg_masuk` FOR EACH ROW BEGIN
	UPDATE tb_barang SET jumlah = jumlah+NEW.jumlah
    WHERE kode_part = NEW.kode_part;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_gudang`
--

CREATE TABLE `tb_gudang` (
  `tanggal` varchar(100) NOT NULL,
  `kode_gudang` varchar(100) NOT NULL,
  `nama_gudang` varchar(250) NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_gudang`
--

INSERT INTO `tb_gudang` (`tanggal`, `kode_gudang`, `nama_gudang`, `alamat`, `keterangan`) VALUES
('25-12-2023', 'G001', 'Cabang 1', 'Jalan Sultan Adam', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kategori`
--

CREATE TABLE `tb_kategori` (
  `tanggal` varchar(100) NOT NULL,
  `kode_kategori` varchar(100) NOT NULL,
  `nama_kategori` varchar(250) NOT NULL,
  `keterangan` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kategori`
--

INSERT INTO `tb_kategori` (`tanggal`, `kode_kategori`, `nama_kategori`, `keterangan`) VALUES
('25-12-2023', 'K001', 'Baju', ''),
('25-12-2023', 'K002', 'Celana', ''),
('25-12-2023', 'K003', 'Jaket', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_supplier`
--

CREATE TABLE `tb_supplier` (
  `tanggal` varchar(100) NOT NULL,
  `kode_supplier` varchar(100) NOT NULL,
  `nama_supplier` varchar(250) NOT NULL,
  `notelpon_supplier` varchar(20) NOT NULL,
  `alamat_supplier` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_supplier`
--

INSERT INTO `tb_supplier` (`tanggal`, `kode_supplier`, `nama_supplier`, `notelpon_supplier`, `alamat_supplier`) VALUES
('10-12-2023', 'F01BJM', 'Auto Fashion', '021-6598414', 'Jln. Trans Kalimantan');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(250) NOT NULL,
  `level` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `username`, `password`, `level`) VALUES
(1, 'admin', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`kode_part`);

--
-- Indexes for table `tb_brg_keluar`
--
ALTER TABLE `tb_brg_keluar`
  ADD PRIMARY KEY (`id_bk`);

--
-- Indexes for table `tb_brg_masuk`
--
ALTER TABLE `tb_brg_masuk`
  ADD PRIMARY KEY (`id_bm`);

--
-- Indexes for table `tb_detail_brg_keluar`
--
ALTER TABLE `tb_detail_brg_keluar`
  ADD PRIMARY KEY (`id_detail_bk`);

--
-- Indexes for table `tb_detail_brg_masuk`
--
ALTER TABLE `tb_detail_brg_masuk`
  ADD PRIMARY KEY (`id_detail_bm`);

--
-- Indexes for table `tb_gudang`
--
ALTER TABLE `tb_gudang`
  ADD PRIMARY KEY (`kode_gudang`);

--
-- Indexes for table `tb_kategori`
--
ALTER TABLE `tb_kategori`
  ADD PRIMARY KEY (`kode_kategori`);

--
-- Indexes for table `tb_supplier`
--
ALTER TABLE `tb_supplier`
  ADD PRIMARY KEY (`kode_supplier`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
