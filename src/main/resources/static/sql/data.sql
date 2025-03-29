INSERT INTO app_user (id, name, email, password, role) VALUES
(1, 'John Doe', 'john.doe@example.com', '$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K', 'BIKE_OWNER'),
(2, 'Jane Smith', 'jane.smith@example.com', '$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K', 'TECHNICIAN'),
(3, 'Alice Johnson', 'alice.johnson@example.com', '$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K', 'ADMIN'),
(4, 'Bob Brown', 'bob.brown@example.com', '$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K', 'SUPER_ADMIN');

INSERT INTO bike_owner (id, phone_number, birth_date) VALUES
(1, '555-1234', '1990-05-20');

INSERT INTO technician (id) VALUES
(2);

INSERT INTO administrator (id, company_name) VALUES
(3, 'E-Bike Corp');

INSERT INTO super_admin (id) VALUES
(4);