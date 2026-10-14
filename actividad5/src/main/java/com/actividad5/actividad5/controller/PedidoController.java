package com.actividad5.actividad5.controller;

import com.example.pedidos.dto.PedidoDTO;
import com.example.pedidos.dto.ProductoDTO;
import com.example.pedidos.dto.ResumenPedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @PostMapping
    public ResponseEntity<ResumenPedidoDTO> procesarPedido(@RequestBody PedidoDTO pedido) {

        BigDecimal precioTotal = BigDecimal.ZERO;
        int totalProductos = 0;

        for (ProductoDTO producto : pedido.getProductos()) {
            BigDecimal subtotal = producto.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(producto.getCantidad()));

            precioTotal = precioTotal.add(subtotal);
            totalProductos += producto.getCantidad();
        }

        ResumenPedidoDTO respuesta = new ResumenPedidoDTO(
                pedido.getCliente().getNombre(),
                totalProductos,
                precioTotal,
                "Pedido recibido correctamente"
        );

        return ResponseEntity.ok(respuesta);
    }
}