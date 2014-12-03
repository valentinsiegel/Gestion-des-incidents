-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Lun 17 Juin 2013 à 01:49
-- Version du serveur: 5.5.31
-- Version de PHP: 5.3.10-1ubuntu3.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `incident`
--

-- --------------------------------------------------------

--
-- Structure de la table `incident`
--

CREATE TABLE IF NOT EXISTS `incident` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text,
  `description` text,
  `date` timestamp NULL DEFAULT NULL,
  `resolu` tinyint(1) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`,`type`),
  KEY `fk_incident_type_incident1_idx` (`type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

CREATE TABLE IF NOT EXISTS `intervention` (
  `technicien_id` int(11) NOT NULL,
  `incident_id` int(11) NOT NULL,
  PRIMARY KEY (`technicien_id`,`incident_id`),
  KEY `fk_technicien_has_incident_incident1_idx` (`incident_id`),
  KEY `fk_technicien_has_incident_technicien1_idx` (`technicien_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `liste_incident`
--

CREATE TABLE IF NOT EXISTS `liste_incident` (
  `poste_id` int(11) NOT NULL,
  `incident_id` int(11) NOT NULL,
  PRIMARY KEY (`poste_id`,`incident_id`),
  KEY `fk_poste_has_incident_incident1_idx` (`incident_id`),
  KEY `fk_poste_has_incident_poste1_idx` (`poste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `poste`
--

CREATE TABLE IF NOT EXISTS `poste` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `poste`
--

INSERT INTO `poste` (`id`, `nom`) VALUES
(3, 'COMPTA'),
(4, 'ACCUEIL');

-- --------------------------------------------------------

--
-- Structure de la table `technicien`
--

CREATE TABLE IF NOT EXISTS `technicien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text,
  `login` text,
  `password` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `technicien`
--

INSERT INTO `technicien` (`id`, `nom`, `login`, `password`) VALUES
(4, 'tech1', 'tech1', 'admin'),
(5, 'tech2', 'tech2', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `type_incident`
--

CREATE TABLE IF NOT EXISTS `type_incident` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `type_incident`
--

INSERT INTO `type_incident` (`id`, `nom`) VALUES
(7, 'virus'),
(8, 'internet'),
(9, 'panne');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `incident`
--
ALTER TABLE `incident`
  ADD CONSTRAINT `fk_incident_type_incident1` FOREIGN KEY (`type`) REFERENCES `type_incident` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD CONSTRAINT `fk_technicien_has_incident_technicien1` FOREIGN KEY (`technicien_id`) REFERENCES `technicien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_technicien_has_incident_incident1` FOREIGN KEY (`incident_id`) REFERENCES `incident` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `liste_incident`
--
ALTER TABLE `liste_incident`
  ADD CONSTRAINT `fk_poste_has_incident_poste1` FOREIGN KEY (`poste_id`) REFERENCES `poste` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_poste_has_incident_incident1` FOREIGN KEY (`incident_id`) REFERENCES `incident` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
