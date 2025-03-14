INSERT INTO users (username, password, enabled) VALUES
('api_call', '$2a$10$2vYaxcOyMq/H9JAZ643pQecTk80NebqK.c0EGidrYzRF8whd6ovca', true),
('scrap', '$2a$10$2vYaxcOyMq/H9JAZ643pQecTk80NebqK.c0EGidrYzRF8whd6ovca',  true),
('crud', '$2a$10$2vYaxcOyMq/H9JAZ643pQecTk80NebqK.c0EGidrYzRF8whd6ovca', true);


INSERT INTO users_roles (users_id, roles_id) VALUES
(1, 1),
(2, 2),
(3, 3)
;
