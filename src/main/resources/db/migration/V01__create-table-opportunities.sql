CREATE TABLE opportunity(
	id BIGSERIAL PRIMARY KEY,
	name_prospect VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	value DECIMAL(10,6)
);