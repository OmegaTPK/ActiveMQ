CREATE SEQUENCE  IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS product_entity (
  id BIGINT NOT NULL,
   name VARCHAR(255),
   price DOUBLE PRECISION,
   migrated BOOLEAN,
   CONSTRAINT pk_productentity PRIMARY KEY (id)
);
INSERT INTO public.product_entity(
  	id, name, price, migrated)
  	VALUES (0,'Axe', 10, false)
  	, (1,'Hummer', 20, false)
  	, (2,'Chainsaw', 20, false)
  	, (3,'Drill', 20, false)
  	, (4,'Screwdriver', 20, false);