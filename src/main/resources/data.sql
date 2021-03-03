DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50),
  phone VARCHAR(30)
);

--INSERT INTO employee (name, phone) VALUES
--  ('Test User', '0810011'),
--  ('Test User 2', '0810012');