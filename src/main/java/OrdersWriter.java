import java.util.Arrays;
import java.util.List;
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
            writeProductCodeAndColor(sb, product.getCode(), getColorFor(product));

            if (product.getSize() != Product.SIZE_NOT_APPLICABLE) {
                writeSizeProduct(sb, getSizeFor(product));
            }

            writePriceTo(sb, product);
        }
    }

    private void writeSizeProduct(StringBuffer sb, String size)
    {
        sb.append("\"size\": \""
                + size
                + "\", ");
    }

    private void writeProductCodeAndColor(StringBuffer sb, String codeProduct, String colorProduct)
    {
        sb.append("{\"code\": \""
                + codeProduct
                + "\", \"color\": \""
                + colorProduct
                + "\", ");
    }

    private void writePriceTo(StringBuffer sb, Product product)
    {
        sb.append("\"price\": "
                + product.getPrice()
                + ", \"currency\": \""
                + product.getCurrency()
                + "\"}, ");
    }

    private String getSizeFor(Product product) {
        final int sizeNb = product.getSize();
        Map<Integer, String> sizes = new HashMap<Integer, String>();
        sizes.put(1, "XS");
        sizes.put(2, "S");
        sizes.put(3, "M");
        sizes.put(4, "L");
        sizes.put(5, "XL");
        sizes.put(6, "XXL");
        sizes.put(7, "Invalid Size");

        return (String)sizes.get(sizeNb);
    }

    private String getColorFor(Product product) {
        switch (product.getColor()) {
            case 1:
                return "blue";
            case 2:
                return "red";
            case 3:
                return "yellow";
            default:
                return "no color";
        }
    }
}
