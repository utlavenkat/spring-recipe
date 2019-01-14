INSERT into category(id,category_name) values (1,'AMERICAN');
INSERT into category(id,category_name) values (2,'MEXICAN');
INSERT into category(id,category_name) values (3,'CHINESE');
INSERT into category(id,category_name) values (4,'FAST FOOD');

INSERT into UNIT_OF_MEASURE(id,uom) values (1,'Teaspoon');
INSERT into UNIT_OF_MEASURE(id,uom) values (2,'Tablespoon');
INSERT into UNIT_OF_MEASURE(id,uom) values (3,'Ounce');
INSERT into UNIT_OF_MEASURE(id,uom) values (4,'Cup');
INSERT into UNIT_OF_MEASURE(id,uom) values (5,'Pinch');
INSERT into UNIT_OF_MEASURE(id,uom) values (6,'Ripe');
INSERT into UNIT_OF_MEASURE(id,uom) values (7,'Each');
INSERT into UNIT_OF_MEASURE(id,uom) values (8,'Dash');
INSERT into UNIT_OF_MEASURE(id,uom) values (9,'Pint');

INSERT  into RECIPE(id,description) values (1,'Test Recipe');

INSERT into Ingredient(id,description,amount,UNIT_OF_MEASURE_ID) values (1,'Test Ingredient',2.0,1);