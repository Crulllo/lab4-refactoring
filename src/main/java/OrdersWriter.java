import Colors.Color;
import Sizes.Size;

import java.util.*;

public class OrdersWriter {
    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuffer sb = new StringBuffer("{\"orders\": [");
        writeOrderTo(sb);

        if (orders.getOrdersCount() > 0)
        {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append("]}").toString();
    }

    private void writeOrderTo(StringBuffer sb)
    {
        final int nbOrders = orders.getOrdersCount();

        for (int i = 0; i < nbOrders; i++)
        {
            Order order = orders.getOrder(i);

            writeOrderIdTo(sb, order.getOrderId());
            writeProductsTo(sb, order);

            if (order.getProductsCount() > 0) {
                sb.delete(sb.length() - 2, sb.length());
            }

            sb.append("]}, ");
        }
    }

    private void writeOrderIdTo(StringBuffer sb, int orderId)
    {
        sb.append("{\"id\": " + orderId);
    }

    private void writeProductsTo(StringBuffer sb, Order order)
    {
        sb.append(", \"products\": [");

        for (int j = 0; j < order.getProductsCount(); j++)
        {
            Product product = order.getProduct(j);
            writeProductCode(sb, product.getCode());
            writeProductColor(sb, product.getColor());

            if(product.getSize().getClass() != Size.class)
            {
                writeSizeProduct(sb, product.getSize());
            }

            writePriceTo(sb, product);
        }
    }

    private void writeSizeProduct(StringBuffer sb, Size size)
    {
        sb.append("\"size\": \"" + size + "\", ");
    }

    private void writeProductCode(StringBuffer sb, String codeProduct)
    {
        sb.append("{\"code\": \"" + codeProduct);
    }

    private void writeProductColor(StringBuffer sb, Color color)
    {
        sb.append("\", \"color\": \"" + color + "\", ");
    }

    private void writePriceTo(StringBuffer sb, Product product)
    {
        sb.append("\"price\": "
                + product.getPrice()
                + ", \"currency\": \""
                + product.getCurrency()
                + "\"}, ");
    }
}
