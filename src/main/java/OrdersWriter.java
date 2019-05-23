import Colors.Color;
import Sizes.Size;

public class OrdersWriter {
    private Orders orders;
    private StringBuffer sb;

    public String getContents() {
        sb = new StringBuffer("{\"orders\": [");
        writeOrderTo();
        displayProduct(orders.getOrdersCount());

        return sb.append("]}").toString();
    }

    public void  displayProduct(int numberOfOrders)
    {
        if (numberOfOrders > 0)
        {
            sb.delete(sb.length() - 2, sb.length());
        }
    }

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    private void writeOrderTo()
    {
        final int nbOrders = orders.getOrdersCount();

        for (int i = 0; i < nbOrders; i++)
        {
            Order order = orders.getOrder(i);

            writeOrderIdTo(order.getOrderId());
            writeProductsTo(order);

            displayProduct(order.getProductsCount());

            sb.append("]}, ");
        }
    }

    private void writeOrderIdTo(int orderId)
    {
        sb.append("{\"id\": " + orderId);
    }

    private void writeProductsTo(Order order)
    {
        sb.append(", \"products\": [");

        for (int j = 0; j < order.getProductsCount(); j++)
        {
            Product product = order.getProduct(j);
            writeProductCode(product.getCode());
            writeProductColor(product.getColor());

            if(product.getSize().getClass() != Size.class)
            {
                writeSizeProduct(product.getSize());
            }

            writePriceTo(product);
        }
    }

    private void writeSizeProduct(Size size)
    {
        sb.append("\"size\": \"" + size + "\", ");
    }

    private void writeProductCode(String codeProduct)
    {
        sb.append("{\"code\": \"" + codeProduct);
    }

    private void writeProductColor(Color color)
    {
        sb.append("\", \"color\": \"" + color + "\", ");
    }

    private void writePriceTo(Product product)
    {
        sb.append("\"price\": "
                + product.getPrice()
                + ", \"currency\": \""
                + product.getCurrency()
                + "\"}, ");
    }
}
