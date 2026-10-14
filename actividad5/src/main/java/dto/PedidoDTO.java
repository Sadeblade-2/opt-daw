package dto;

import java.util.List;

public class PedidoDTO {

    private ClienteDTO cliente;
    private List<ProductoDTO> productos;

    public PedidoDTO() {
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}