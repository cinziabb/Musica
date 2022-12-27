-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 27, 2022 alle 23:24
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musica2122`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `album`
--

CREATE TABLE `album` (
  `id` int(11) NOT NULL,
  `nome` varchar(190) NOT NULL,
  `dataUscita` date DEFAULT NULL,
  `numTracce` int(2) DEFAULT NULL,
  `idArtista` int(11) DEFAULT NULL COMMENT 'foreign key'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `album`
--

INSERT INTO `album` (`id`, `nome`, `dataUscita`, `numTracce`, `idArtista`) VALUES
(1, 'Purple Rain', '1984-06-25', 9, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `artista`
--

CREATE TABLE `artista` (
  `id` int(11) NOT NULL,
  `nome` varchar(190) NOT NULL,
  `dataNascita` date NOT NULL,
  `annoInizio` int(4) NOT NULL,
  `annoFine` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `artista`
--

INSERT INTO `artista` (`id`, `nome`, `dataNascita`, `annoInizio`, `annoFine`) VALUES
(1, 'David Guetta', '1967-11-07', 1984, 0),
(2, 'Kygo', '1991-07-11', 2011, 0),
(3, 'Lenny Kravits', '1964-05-24', 1983, 0),
(4, 'Tupac Shakur', '1971-06-16', 1987, 1996),
(5, 'Prince', '1958-06-07', 1975, 2016),
(6, 'John Frusciante', '1970-03-05', 1988, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `cognome` varchar(250) NOT NULL,
  `nome` varchar(250) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(600) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `cliente`
--

INSERT INTO `cliente` (`id`, `cognome`, `nome`, `telefono`, `email`, `password`) VALUES
(1, 'Rossi', 'Mario', NULL, 'rossi.mario@gmail.it', '123456789');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`),
  ADD KEY `fk_artista` (`idArtista`);

--
-- Indici per le tabelle `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Indici per le tabelle `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `album`
--
ALTER TABLE `album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `artista`
--
ALTER TABLE `artista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `fk_artista` FOREIGN KEY (`idArtista`) REFERENCES `artista` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
