-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2019 at 07:20 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jdbc_demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `first_name`, `last_name`, `email`) VALUES
(1, 'Johnny', 'Bravo', 'johnny@bravo.com'),
(3, 'Adam', 'West', 'batman@returns.us'),
(4, 'Kyle', 'Knapp', 'update1@damn.com'),
(5, 'David', 'Dude', 'ohdude@dude.pl'),
(6, 'Stefano', 'Italiano', 'stefano@macaroni.it'),
(8, 'John', 'Bowner', 'thebowner@bowme.com'),
(9, 'Anna', 'Wanna', 'anna.wanna@armatura.pl');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` float NOT NULL,
  `category` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `category`) VALUES
(1, 'GTA:VC', 19.99, 'games'),
(2, 'Rayman 3', 19.99, 'games'),
(3, 'Half Life 2', 39.99, 'games'),
(4, 'Potato', 1.55, 'cuisine'),
(5, 'GTA:SA', 119.46, 'games'),
(6, 'Tomato', 0.89, 'cuisine'),
(7, 'Test Drive: Unlimited', 99.99, 'games'),
(8, 'Juice Mixer', 79.3, 'cuisine'),
(9, 'Harry Potter 1-7', 19.99, 'books'),
(10, 'Barbie Redhead', 22.5, 'toys'),
(11, 'Wooden spoon', 9.99, 'cuisine'),
(12, 'Potop', 190, 'books'),
(13, 'Marshmallow pack', 10.53, 'cuisine'),
(14, 'Train Station', 73.5, 'toys'),
(15, 'CS:SOURCE', 100, 'games');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
