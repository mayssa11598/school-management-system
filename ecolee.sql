-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le : Mar 16 Décembre 2025 à 09:33
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ecolee`
--

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `filiere_id` int(11) DEFAULT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `eleve_ibfk_1` (`filiere_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id`, `nom`, `prenom`, `filiere_id`, `sexe`) VALUES
(1, 'Martin', 'Luc', 1, 'M'),
(2, 'Dubois', 'Emma', 1, 'F'),
(3, 'Bernard', 'Hugo', 2, 'M'),
(4, 'Petit', 'Léa', 3, 'F'),
(5, 'Durand', 'Louis', 4, 'M'),
(6, 'Moreau', 'Chloé', 1, 'F'),
(7, 'Lefebvre', 'Gabriel', 2, 'M'),
(8, 'Garcia', 'Inès', 3, 'F'),
(9, 'amira', 'ben chaabene', 4, 'F');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE IF NOT EXISTS `enseignant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `enseignant`
--

INSERT INTO `enseignant` (`id`, `nom`, `prenom`, `email`, `telephone`) VALUES
(1, 'Rousseau', 'Pierre', 'p.rousseau@ecole.fr', '01 23 45 67 89'),
(2, 'Mercier', 'Sophie', 's.mercier@ecole.fr', '01 98 76 54 32'),
(3, 'Fournier', 'Antoine', 'a.fournier@ecole.fr', '06 12 34 56 78'),
(4, 'Lemoine', 'Marie', 'm.lemoine@ecole.fr', '06 87 65 43 21');

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE IF NOT EXISTS `filiere` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `filiere`
--

INSERT INTO `filiere` (`id`, `nom`) VALUES
(1, 'Informatique'),
(2, 'Mathématiques'),
(3, 'Physique-Chimie'),
(4, 'Sciences Economiques');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE IF NOT EXISTS `matiere` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `coeff` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`id`, `nom`, `coeff`) VALUES
(1, 'Algorithmique', 3),
(2, 'Base de données', 2.5),
(3, 'Analyse Mathématique', 4),
(4, 'Physique Quantique', 3.5),
(5, 'Économétrie', 3),
(6, 'Programmation Web', 2);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE IF NOT EXISTS `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eleve_id` int(11) DEFAULT NULL,
  `matiere_id` int(11) DEFAULT NULL,
  `note` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `note_ibfk_1` (`eleve_id`),
  KEY `note_ibfk_2` (`matiere_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `note`
--

INSERT INTO `note` (`id`, `eleve_id`, `matiere_id`, `note`) VALUES
(1, 1, 1, 15.5),
(2, 1, 2, 14),
(3, 1, 6, 16.5),
(4, 2, 1, 18),
(5, 2, 2, 17.5),
(6, 2, 6, 19),
(7, 3, 3, 12.5),
(8, 3, 1, 13),
(9, 4, 4, 15),
(10, 4, 3, 16.5),
(11, 5, 5, 14.5),
(12, 5, 3, 13.5),
(13, 6, 1, 17),
(14, 6, 2, 16),
(15, 7, 3, 18.5),
(16, 7, 1, 15),
(17, 8, 4, 14),
(18, 8, 3, 16);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `username`, `password`) VALUES
(1, 'admin', '1234');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `eleve_ibfk_1` FOREIGN KEY (`filiere_id`) REFERENCES `filiere` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`eleve_id`) REFERENCES `eleve` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
