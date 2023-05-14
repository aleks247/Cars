CREATE TABLE IF NOT EXISTS `worker` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
