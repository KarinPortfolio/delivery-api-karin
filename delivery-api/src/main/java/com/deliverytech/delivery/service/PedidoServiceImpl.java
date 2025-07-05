package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.entity.ItemPedido;
import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.entity.Produto;
import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.enums.StatusPedido;
import com.deliverytech.delivery.repository.ClienteRepository;
import com.deliverytech.delivery.repository.PedidoRepository;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Certifique-se de importar corretamente o DTO
import com.deliverytech.delivery.dto.ItemPedidoDTO;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Pedido criarPedido(Long clienteId, Long restauranteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + clienteId));
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado com ID: " + restauranteId));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setSubtotal(BigDecimal.ZERO);
        pedido.setTaxaEntrega(new BigDecimal("5.00"));
        pedido.setValorTotal(pedido.getTaxaEntrega());
        pedido.setNumeroPedido(UUID.randomUUID().toString().substring(0, 8).toUpperCase()); // Gera um número de pedido simples
        pedido.setItens(new ArrayList<>()); // Inicializa a lista de itens

        recalcularValoresPedido(pedido); // Recalcula valores após adicionar todos os itens
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public Pedido criarPedidoComItens(Long clienteId, Long restauranteId, String enderecoEntrega, java.util.List<ItemPedidoDTO> itensDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + clienteId));
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado com ID: " + restauranteId));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setEnderecoEntrega(enderecoEntrega);
        pedido.setSubtotal(BigDecimal.ZERO);
        pedido.setTaxaEntrega(new BigDecimal("5.00"));
        pedido.setNumeroPedido(UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoDTO itemDTO : itensDTO) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + itemDTO.getProdutoId()));
            if (itemDTO.getQuantidade() <= 0) {
                throw new IllegalArgumentException("A quantidade do item " + produto.getNome() + " deve ser maior que zero.");
            }
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDTO.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());
            itens.add(itemPedido);
        }
        pedido.setItens(itens);

        recalcularValoresPedido(pedido); // Recalcula valores após adicionar todos os itens
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + pedidoId));
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + produtoId));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Optional<ItemPedido> itemExistente = pedido.getItens().stream()
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemPedido item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
            item.setPrecoUnitario(produto.getPreco());

        } else {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setPrecoUnitario(produto.getPreco());
            pedido.adicionarItem(itemPedido);
        }

        recalcularValoresPedido(pedido);

        return pedidoRepository.save(pedido);
    }

    private void recalcularValoresPedido(Pedido pedido) {
        BigDecimal subtotal = pedido.getItens().stream()
                .map(item -> item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setSubtotal(subtotal);
        pedido.setValorTotal(subtotal.add(pedido.getTaxaEntrega()));
    }


    @Override
    @Transactional
    public Pedido confirmarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + pedidoId));
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new IllegalArgumentException("Pedido não pode ser confirmado pois não está no status PENDENTE.");
        }
        pedido.confirmar();
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> listarPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + clienteId));
        return pedidoRepository.findByClienteOrderByDataPedidoDesc(cliente);
    }

    @Override
    public Optional<Pedido> buscarPorNumero(String numeroPedido) {
        return Optional.ofNullable(pedidoRepository.findByNumeroPedido(numeroPedido));
    }

    @Override
    @Transactional
    public Pedido atualizarStatus(Long pedidoId, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + pedidoId));
        pedido.setStatus(status);

        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public Pedido cancelarPedido(Long pedidoId, String motivo) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + pedidoId));

        if (pedido.getStatus() == StatusPedido.ENTREGUE || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new IllegalArgumentException("Pedido já foi entregue ou cancelado e não pode ser cancelado novamente.");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.setObservacoes("Cancelado: " + (motivo != null ? motivo : "Motivo não especificado."));
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> listarPedidosDoDia() {
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);
        return pedidoRepository.findByDataPedidoBetweenOrderByDataPedidoDesc(inicioDoDia, fimDoDia);
    }
}