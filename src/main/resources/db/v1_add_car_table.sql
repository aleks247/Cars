CREATE TABLE IF NOT EXISTS`car` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `year_of_creation` int(11) NOT NULL,
  `engine_id` int(11) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
