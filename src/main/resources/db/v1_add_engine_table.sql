CREATE TABLE IF NOT EXISTS `engine` (
  `id` int(11) NOT NULL,
  `horse_power` int(11) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
