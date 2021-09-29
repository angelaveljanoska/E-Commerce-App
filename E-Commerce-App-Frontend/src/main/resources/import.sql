INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO users(username, email, password) VALUES ('admin', 'admin@armedia.com', '$2a$10$oGOZukgkYaHghXyMqt9DquwzwzRBpzhD706WlZJcDseNukHLFgZF2');
INSERT INTO user_roles(user_id, role_id) VALUES('1', '1');