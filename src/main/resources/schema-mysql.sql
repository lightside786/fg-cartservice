use fgcart;
drop table CART_ITEM;
drop table CART_DISCOUNT;
drop table DISCOUNT;
drop table CART;


CREATE TABLE `CART` (
  `id`               BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `record_id`        VARCHAR(36)    NOT NULL,
  `user_id`          VARCHAR(20),
  `item_count`       INT  			    NOT NULL DEFAULT 0,
  `total`            DECIMAL(12,2)  NOT NULL DEFAULT 0,
  `currency_code`    VARCHAR(3)     NOT NULL DEFAULT 'AED',
  `created_on`       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`       TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed_on` TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ship_address_id`  VARCHAR(36),
  `bill_address_id`  VARCHAR(36),
  `payment_id`       VARCHAR(36),
  CONSTRAINT UNIQUE `UK_CART_USRID` (user_id),
  CONSTRAINT UNIQUE `UK_CART_RECORD_ID` (record_id),
  CONSTRAINT UNIQUE `UK_PAYMENT_RECORD_ID` (payment_id)
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
  `ship_address_id`  VARCHAR(36)    NOT NULL,
  `bill_address_id`  VARCHAR(36)    NOT NULL,
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
  `id`               BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `record_id`        VARCHAR(36),
  `cart_id`          BIGINT       NOT NULL,
  `product_id`       VARCHAR(36)  NOT NULL,
  `quantity`         DECIMAL(4,2) NOT NULL DEFAULT 1.00,
  `price`            DECIMAL(8,2) NOT NULL,
  `total`            DECIMAL(12,2)  NOT NULL DEFAULT 0,
  `created_on`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed_on` TIMESTAMP		  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT UNIQUE `UK_CART_ITEM_ID` (record_id),
  CONSTRAINT UNIQUE `UK_CART_ITEM_PROD_ID` (product_id),
  FOREIGN KEY (cart_id)
  REFERENCES CART (id)
    ON DELETE CASCADE
);
