insert into CART (id, record_id, user_id, total, created_on, updated_on, last_accessed_on) values
(11, 'init-cart-record-recordId-1000001', 'ummerstest', 39, '2016-08-08', '2016-10-24', '2016-10-10');


insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (11, 'init-cart-item-recordId-1000001', 11, 'init-cart-product-id-100001', 75,  NULL , 'EACH', NULL, 1.72, 83.45, '2016-01-25', '2016-01-23', '2016-09-12');

insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (12, 'init-cart-item-recordId-1000002', 11, 'init-cart-product-id-100002', 10,  NULL , 'EACH', NULL, 10, 100.0, '2016-01-25', '2016-01-23', '2016-09-12');



insert into CART (id, record_id, user_id, total, created_on, updated_on, last_accessed_on) values
 (12, 'init-cart-record-recordId-1000002', 'inittest', 0, '2016-08-08', '2016-10-24', '2016-10-10');


insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (13, 'init-cart-item-recordId-1000003', 12, 'init-cart-product-id-100003', 100,  NULL , 'KG', NULL, 12.23, 0.00, '2016-01-25', '2016-01-23', '2016-09-12');

insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (14, 'init-cart-item-recordId-1000004', 12, 'init-cart-product-id-100004', 100,  900 , 'KG', 'GMS', 15, 0.00, '2016-01-25', '2016-01-23', '2016-09-12');


insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (15, 'init-cart-item-recordId-1000005', 12, 'init-cart-product-id-100005', 100,  NULL , 'EACH', NULL, 15, 0.00, '2016-01-25', '2016-01-23', '2016-09-12');

insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (16, 'init-cart-item-recordId-1000006', 12, 'init-cart-product-id-100006', 100,  NULL , 'BOX', NULL, 15, 0.00, '2016-01-25', '2016-01-23', '2016-09-12');

insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (17, 'init-cart-item-recordId-1000007', 12, 'init-cart-product-id-100007', 100,  NULL , 'DOZEN', NULL, 15, 0.00, '2016-01-25', '2016-01-23', '2016-09-12');