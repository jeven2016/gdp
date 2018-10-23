drop TABLE admin_user;
CREATE TABLE admin_user
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    password VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    pwd_changed BOOLEAN DEFAULT FALSE  NOT NULL,
    last_login DATETIME,
    deprecated BOOLEAN NOT NULL
);
ALTER TABLE super_admin ADD CONSTRAINT unique_name UNIQUE (name);