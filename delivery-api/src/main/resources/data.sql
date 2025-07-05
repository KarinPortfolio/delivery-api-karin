-- data.sql

-- Inserindo Clientes
-- Change 'ativo' to 'ATIVO' (uppercase) here
INSERT INTO cliente (nome, email, telefone, endereco, ATIVO) VALUES
('João Silva', 'joao.silva@email.com', '(11) 98765-4321', 'Rua das Flores, 123, São Paulo', TRUE),
('Maria Oliveira', 'maria.o@email.com', '(21) 91234-5678', 'Avenida Copacabana, 456, Rio de Janeiro', TRUE);

-- ... (and if you have 'ativo' or other potentially case-sensitive columns in other INSERTs like 'produto', change them to uppercase there too) ...

-- Inserindo Restaurantes
-- 'ativo' is boolean not null, 'avaliacao' and 'taxa_entrega' are numeric.
INSERT INTO restaurante (nome, cnpj, endereco, ATIVO, avaliacao, taxa_entrega, categoria, telefone) VALUES
('Pizzaria do Zé', '12.345.678/0001-99', 'Rua da Pizza, 10, São Paulo', TRUE, 4.5, 5.00, 'Pizzaria', '(11) 2345-6789'),
('Hamburgueria Top', '98.765.432/0001-11', 'Avenida do Burger, 20, Rio de Janeiro', TRUE, 4.8, 7.50, 'Hamburgueria', '(21) 8765-4321');

-- Inserindo Produtos (assuming active and available are boolean not null)
INSERT INTO produto (nome, descricao, preco, restaurante_id, ATIVO, disponivel, categoria) VALUES
('Pizza Margherita', 'Molho, mussarela e manjericão', 45.00, 1, TRUE, TRUE, 'Pizza'),
('Pizza Calabresa', 'Molho, mussarela e calabresa fatiada', 50.00, 1, TRUE, TRUE, 'Pizza'),
('Refrigerante Lata', 'Coca-Cola, Guaraná ou Fanta', 5.00, 1, TRUE, TRUE, 'Bebida');

-- Inserindo Produtos para a Hamburgueria Top (ID=2)
INSERT INTO produto (nome, descricao, preco, restaurante_id, ATIVO, disponivel, categoria) VALUES
('X-Burger Clássico', 'Pão, carne, queijo, alface e tomate', 30.00, 2, TRUE, TRUE, 'Hamburguer'),
('X-Bacon Especial', 'Pão, carne, queijo, bacon crocante, cebola caramelizada', 35.50, 2, TRUE, TRUE, 'Hamburguer'),
('Batata Frita', 'Porção individual de batata frita', 12.00, 2, TRUE, TRUE, 'Acompanhamento');

-- Inserindo um Pedido de exemplo
INSERT INTO pedido (data_pedido, status, subtotal, taxa_entrega, valor_total, cliente_id, restaurante_id, endereco_entrega, observacoes, numero_pedido) VALUES
(CURRENT_TIMESTAMP, 'PENDENTE', 90.00, 5.00, 95.00, 1, 1, 'Rua das Flores, 123, São Paulo', 'Sem cebola extra', 'PED001');

-- Associando os produtos ao pedido (ID=1) via ItemPedido
INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
(1, 1, 1, 45.00),
(1, 2, 1, 50.00);

ALTER TABLE item_pedido ALTER COLUMN pedido_id SET NULL;