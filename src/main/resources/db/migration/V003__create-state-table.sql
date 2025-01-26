CREATE TABLE tb_state(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(80) NOT NULL,


	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_state (name)
SELECT DISTINCT state_name FROM tb_city;

ALTER TABLE tb_city ADD COLUMN state_id BIGINT NOT NULL;

UPDATE tb_city c SET c.state_id = (
SELECT s.id FROM tb_state s WHERE s.name = c.state_name);

ALTER TABLE tb_city  ADD CONSTRAINT fk_city_state
FOREIGN KEY (state_id) REFERENCES tb_state(id);


ALTER TABLE tb_city DROP COLUMN state_name;

ALTER TABLE tb_city CHANGE city_name name VARCHAR(80) NOT NULL;