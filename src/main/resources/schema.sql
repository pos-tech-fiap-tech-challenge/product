-- Criar o schema se ainda não existir
USE product;

-- Criar a tabela de categorias
CREATE TABLE IF NOT EXISTS product_category (
    product_category_id VARCHAR(36) PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

-- Criar a tabela de produtos
CREATE TABLE IF NOT EXISTS product (
    product_id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    category_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES product_category (product_category_id) ON DELETE CASCADE
);

-- Inserir categorias se ainda não existirem
INSERT INTO product_category (product_category_id, description)
    SELECT '11111111-1111-1111-1111-111111111111', 'Bebida'
    WHERE NOT EXISTS (SELECT 1 FROM product_category WHERE product_category_id = '11111111-1111-1111-1111-111111111111');

INSERT INTO product_category (product_category_id, description)
    SELECT '22222222-2222-2222-2222-222222222222', 'Lanche'
    WHERE NOT EXISTS (SELECT 1 FROM product_category WHERE product_category_id = '22222222-2222-2222-2222-222222222222');

INSERT INTO product_category (product_category_id, description)
    SELECT '33333333-3333-3333-3333-333333333333', 'Acompanhamento'
    WHERE NOT EXISTS (SELECT 1 FROM product_category WHERE product_category_id = '33333333-3333-3333-3333-333333333333');

-- Inserir produtos se ainda não existirem
INSERT INTO product (product_id, name, description, price, category_id)
    SELECT 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Coca-Cola', 'Refrigerante lata 350ml', 5.99, '11111111-1111-1111-1111-111111111111'
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_id = 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa');

INSERT INTO product (product_id, name, description, price, category_id)
    SELECT 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Suco de Laranja', 'Suco natural 500ml', 7.50, '11111111-1111-1111-1111-111111111111'
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_id = 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb');

INSERT INTO product (product_id, name, description, price, category_id)
    SELECT 'ccccccc3-cccc-cccc-cccc-cccccccccccc', 'Hambúrguer', 'Pão, carne, queijo e salada', 15.99, '22222222-2222-2222-2222-222222222222'
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_id = 'ccccccc3-cccc-cccc-cccc-cccccccccccc');

INSERT INTO product (product_id, name, description, price, category_id)
    SELECT 'ddddddd4-dddd-dddd-dddd-dddddddddddd', 'Batata Frita', 'Porção pequena', 8.99, '33333333-3333-3333-3333-333333333333'
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_id = 'ddddddd4-dddd-dddd-dddd-dddddddddddd');

INSERT INTO product (product_id, name, description, price, category_id)
    SELECT 'eeeeeee5-eeee-eeee-eeee-eeeeeeeeeeee', 'Nuggets', '6 unidades de nuggets de frango', 9.99, '33333333-3333-3333-3333-333333333333'
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_id = 'eeeeeee5-eeee-eeee-eeee-eeeeeeeeeeee');
