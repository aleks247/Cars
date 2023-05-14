CREATE TABLE IF NOT EXISTS `manufacturer` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year_of_creation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
