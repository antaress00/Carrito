
//Punto 1
public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return producto.getPrecio() * cantidad;
    }
}

public class Carrito {
    private List<ItemCarrito> items = new ArrayList<>();
    private Descuento descuento;

    public void addItem(ItemCarrito item) {
        items.add(item);
    }

    public void removeItem(ItemCarrito item) {
        items.remove(item);
    }

    public double getPrecioTotal() {
        double precioTotal = 0;
        for (ItemCarrito item : items) {
            precioTotal += item.getPrecioTotal();
        }
        if (descuento != null) {
            precioTotal = descuento.aplicarDescuento(precioTotal);
        }
        return precioTotal;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }
}

public interface Descuento {
    double aplicarDescuento(double precio);
}

public class DescuentoPorcentaje implements Descuento {
    private double porcentaje;

    public DescuentoPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double aplicarDescuento(double precio) {
        return precio * (1 - porcentaje / 100);
    }
}



//Punto 2


public static void main(String[] args) {
    Producto jabonEnPolvo = new Producto("Jabón en polvo", 40);
    Producto esponjas = new Producto("Esponjas", 10);
    Producto chocolates = new Producto("Chocolates", 100);

    ItemCarrito item1 = new ItemCarrito(jabonEnPolvo, 1);
    ItemCarrito item2 = new ItemCarrito(esponjas, 3);
    ItemCarrito item3 = new ItemCarrito(chocolates, 2);

    Carrito carrito = new Carrito();
    carrito.addItem(item1);
    carrito.addItem(item2);
    carrito.addItem(item3);

    Descuento descuento = new DescuentoPorcentaje(10);
    carrito.setDescuento(descuento);

    System.out.println("El precio total es: " + carrito.getPrecioTotal());
}
