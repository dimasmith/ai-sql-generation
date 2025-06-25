-- Categories
INSERT INTO category (id, name)
VALUES ('11111111-1111-1111-1111-111111111111', 'Electronics'),
       ('22222222-2222-2222-2222-222222222222', 'Books'),
       ('33333333-3333-3333-3333-333333333333', 'Clothing');

-- Params
INSERT INTO param (id, name, category_id, unit_measurement)
VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Voltage', '11111111-1111-1111-1111-111111111111', 'V'),
       ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Power', '11111111-1111-1111-1111-111111111111', 'W'),

       ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Pages', '22222222-2222-2222-2222-222222222222', 'pages'),
       ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Author', '22222222-2222-2222-2222-222222222222', NULL),

       ('ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'Size', '33333333-3333-3333-3333-333333333333', 'cm'),
       ('ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Material', '33333333-3333-3333-3333-333333333333', NULL);

-- Products
INSERT INTO product (id, name, category_id, price, available_amount, created_at, updated_at)
VALUES ('ddddddd1-dddd-dddd-dddd-dddddddddddd', 'Smartphone', '11111111-1111-1111-1111-111111111111', '699.99', 100, now(),
        now()),
       ('ddddddd2-dddd-dddd-dddd-dddddddddddd', 'Laptop', '11111111-1111-1111-1111-111111111111', '1299.99', 50, now(), now()),

       ('eeeeeee1-eeee-eeee-eeee-eeeeeeeeeeee', 'Fantasy Novel', '22222222-2222-2222-2222-222222222222', '19.99', 200, now(),
        now()),
       ('eeeeeee2-eeee-eeee-eeee-eeeeeeeeeeee', 'Textbook', '22222222-2222-2222-2222-222222222222', '59.99', 150, now(), now()),

       ('fffffff1-ffff-ffff-ffff-ffffffffffff', 'T-shirt', '33333333-3333-3333-3333-333333333333', '14.99', 300, now(), now()),
       ('fffffff2-ffff-ffff-ffff-ffffffffffff', 'Jacket', '33333333-3333-3333-3333-333333333333', '89.99', 80, now(), now());

-- Product Params (2 per product)
INSERT INTO product_param (product_id, param_id, value, created_at, updated_at)
VALUES
    -- Smartphone (Electronics)
    ('ddddddd1-dddd-dddd-dddd-dddddddddddd', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '220', now(), now()),
    ('ddddddd1-dddd-dddd-dddd-dddddddddddd', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '15', now(), now()),

    -- Laptop (Electronics)
    ('ddddddd2-dddd-dddd-dddd-dddddddddddd', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '60', now(), now()),
    ('ddddddd2-dddd-dddd-dddd-dddddddddddd', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '110', now(), now()),

    -- Fantasy Novel (Books)
    ('eeeeeee1-eeee-eeee-eeee-eeeeeeeeeeee', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', '350', now(), now()),
    ('eeeeeee1-eeee-eeee-eeee-eeeeeeeeeeee', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'J.K. Rowling', now(), now()),

    -- Textbook (Books)
    ('eeeeeee2-eeee-eeee-eeee-eeeeeeeeeeee', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'John Smith', now(), now()),
    ('eeeeeee2-eeee-eeee-eeee-eeeeeeeeeeee', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', '480', now(), now()),

    -- T-shirt (Clothing)
    ('fffffff1-ffff-ffff-ffff-ffffffffffff', 'ccccccc1-cccc-cccc-cccc-ccccccccccc1', '42', now(), now()),
    ('fffffff1-ffff-ffff-ffff-ffffffffffff', 'ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Cotton', now(), now()),

    -- Jacket (Clothing)
    ('fffffff2-ffff-ffff-ffff-ffffffffffff', 'ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Leather', now(), now()),
    ('fffffff2-ffff-ffff-ffff-ffffffffffff', 'ccccccc1-cccc-cccc-cccc-ccccccccccc1', '60', now(), now());
-- Additional Categories (adding 2 more)
INSERT INTO category (id, name)
VALUES ('44444444-4444-4444-4444-444444444444', 'Furniture'),
       ('55555555-5555-5555-5555-555555555555', 'Toys');

-- Additional Params for new categories
INSERT INTO param (id, name, category_id, unit_measurement)
VALUES
    -- Furniture
    ('ddddddd3-dddd-dddd-dddd-dddddddddddd', 'Material', '44444444-4444-4444-4444-444444444444', NULL),
    ('ddddddd4-dddd-dddd-dddd-dddddddddddd', 'Weight', '44444444-4444-4444-4444-444444444444', 'kg'),

    -- Toys
    ('eeeeeee3-eeee-eeee-eeee-eeeeeeeeeeee', 'Age Range', '55555555-5555-5555-5555-555555555555', 'years'),
    ('eeeeeee4-eeee-eeee-eeee-eeeeeeeeeeee', 'Battery Included', '55555555-5555-5555-5555-555555555555', NULL);

-- Additional Products for new categories
INSERT INTO product (id, name, category_id, price, available_amount, created_at, updated_at)
VALUES
    -- Furniture
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'Office Chair', '44444444-4444-4444-4444-444444444444', '150.00', 20, now(), now()),
    ('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'Dining Table', '44444444-4444-4444-4444-444444444444', '350.00', 10, now(), now()),

    -- Toys
    ('bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'Action Figure', '55555555-5555-5555-5555-555555555555', '25.00', 100, now(), now()),
    ('bbbbbbb4-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'Building Blocks', '55555555-5555-5555-5555-555555555555', '40.00', 150, now(),
     now());

-- product_param entries (2 params per product)

INSERT INTO product_param (product_id, param_id, value, created_at, updated_at)
VALUES
    -- Office Chair (Furniture)
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'ddddddd3-dddd-dddd-dddd-dddddddddddd', 'Leather', now(), now()),
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'ddddddd4-dddd-dddd-dddd-dddddddddddd', '15', now(), now()),

    -- Dining Table (Furniture)
    ('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'ddddddd4-dddd-dddd-dddd-dddddddddddd', '50', now(), now()),
    ('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'ddddddd3-dddd-dddd-dddd-dddddddddddd', 'Wood', now(), now()),

    -- Action Figure (Toys)
    ('bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'eeeeeee3-eeee-eeee-eeee-eeeeeeeeeeee', '5+', now(), now()),
    ('bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'eeeeeee4-eeee-eeee-eeee-eeeeeeeeeeee', 'Yes', now(), now()),

    -- Building Blocks (Toys)
    ('bbbbbbb4-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'eeeeeee4-eeee-eeee-eeee-eeeeeeeeeeee', 'No', now(), now()),
    ('bbbbbbb4-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 'eeeeeee3-eeee-eeee-eeee-eeeeeeeeeeee', '3+', now(), now());
