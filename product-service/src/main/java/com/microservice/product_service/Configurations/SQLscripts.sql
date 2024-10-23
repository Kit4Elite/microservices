/*SELECT sequence_name
FROM information_schema.sequences
WHERE sequence_schema = 'public';
*/
ALTER TABLE "attribute_values"
    ALTER COLUMN attribute_values_id SET DEFAULT nextval('attribute_values_seq');

ALTER TABLE "attributes"
    ALTER COLUMN attribute_id SET DEFAULT nextval('attributes_seq');

ALTER TABLE "categories"
    ALTER COLUMN categorie_id SET DEFAULT nextval('categories_seq');

ALTER TABLE "galleries"
    ALTER COLUMN gallerie_id SET DEFAULT nextval('galleries_seq');

ALTER TABLE "reviews"
    ALTER COLUMN reviews_id SET DEFAULT nextval('reviews_seq');

ALTER TABLE "tags"
    ALTER COLUMN tag_id SET DEFAULT nextval('tags_seq');

ALTER TABLE "variant_values"
    ALTER COLUMN variant_values_id SET DEFAULT nextval('variant_values_seq');

ALTER TABLE "variants"
    ALTER COLUMN variants_id SET DEFAULT nextval('variants_seq');

INSERT INTO products (product_name, sku, regular_price, discount_price, product_description, published, created_at, updated_at, created_by, updated_by)
VALUES
    ('Running Shoes 2', 'SKU-212345', 99.99, 89.99, 'High-quality running shoes1', true, NOW(), NOW(), 'admin', 'admin');

INSERT INTO tags (tag_name,created_at,updated_at)
VALUES
    ('Shoes',NOW(), NOW());