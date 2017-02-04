insert into CART (id, record_id, user_id, total, created_on, updated_on, last_accessed_on) values
(11, 'init-cart-record-recordId-1000001', 'ummerstest', 39, '2016-08-08', '2016-10-24', '2016-10-10');


insert into cart_item (id, record_id, cart_id, product_id, primary_quantity, secondary_quantity, primary_uom, secondary_uom, price, total, created_on, updated_on, last_accessed_on) values
 (11, 'init-cart-item-recordId-1000001', 11, '111690ab-a981-4122-b2f3-2467647c47fc', 75,  NULL , 'EACH', NULL, 1.72, 83.45, '2016-01-25', '2016-01-23', '2016-09-12');
