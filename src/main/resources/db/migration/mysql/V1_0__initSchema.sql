

CREATE TABLE `CART` (
  `id`               BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `record_id`        VARCHAR(36)    NOT NULL,
  `user_id`          VARCHAR(20),
  `total`            DECIMAL(12,2)  NOT NULL DEFAULT 0,
  `created_on`       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`       TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed_on` TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT UNIQUE `UK_CART_USRID` (user_id),
  CONSTRAINT UNIQUE `UK_CART_RECORD_ID` (record_id)
);

CREATE TABLE `DISCOUNT` (
  `id`               BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `record_id`        VARCHAR(36)    NOT NULL,
  `type`             VARCHAR(10)    NOT NULL,
  `amount`           DECIMAL(4,2)   NOT NULL,
  `description`      VARCHAR(120)   NOT NULL,
  `code`             VARCHAR(36)    NOT NULL,
  `created_on`       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`       TIMESTAMP		  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date`       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed_on` TIMESTAMP		  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date`         TIMESTAMP		  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT UNIQUE `UK_DISC_ID` (record_id),
  CONSTRAINT UNIQUE `UK_CODE_ID` (code)
);


CREATE TABLE `CART_DISCOUNT` (
  `id`               BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `record_id`        VARCHAR(36)    NOT NULL,
  `cart_id`          BIGINT         NOT NULL,
  `discount_id`      BIGINT         NOT NULL,
  `created_on`       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`       TIMESTAMP		  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed_on` TIMESTAMP		  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT UNIQUE `UK_CART_DISC_ID` (record_id),
  CONSTRAINT UNIQUE `UK_CART_ID_DISC_ID` (cart_id, discount_id),
  FOREIGN KEY (cart_id)
  REFERENCES CART (id)
    ON DELETE CASCADE,
  FOREIGN KEY (discount_id)
  REFERENCES DISCOUNT (id)
    ON DELETE CASCADE
);

CREATE TABLE `CART_ITEM` (
  `id`                  BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `record_id`           VARCHAR(36),
  `cart_id`             BIGINT       NOT NULL,
  `product_id`          VARCHAR(36)  NOT NULL,
  `primary_quantity`    DECIMAL(5,2) NOT NULL,
  `secondary_quantity`  DECIMAL(6,2) ,
  `primary_uom`         VARCHAR(36)  NOT NULL DEFAULT "KG",
  `secondary_uom`       VARCHAR(36)  ,
  `price`               DECIMAL(8,2) NOT NULL,
  `total`               DECIMAL(12,2)  NOT NULL DEFAULT 0,
  `created_on`          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed_on`    TIMESTAMP		 NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT UNIQUE     `UK_CART_ITEM_ID` (record_id),
  CONSTRAINT UNIQUE     `UK_CART_ITEM_PROD_ID` (cart_id ,product_id),
  FOREIGN KEY (cart_id)
  REFERENCES CART (id)
    ON DELETE CASCADE
);


CREATE TRIGGER before_insert_cart
BEFORE INSERT ON CART
FOR EACH ROW
  SET NEW.record_id = uuid();



CREATE TRIGGER before_insert_cartItem
BEFORE INSERT ON CART_ITEM
FOR EACH ROW
  SET NEW.record_id = uuid();
