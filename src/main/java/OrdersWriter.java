import Colors.Color;
import Sizes.Size;

public class OrdersWriter {
    private Orders orders;
    private StringBuffer content;

    public String getContents() {
        content = new StringBuffer("{\"orders\": [");
        writeOrderTo();
        displayProduct(orders.getOrdersCount());

        return content.append("]}").toString();
    }

    public void  displayProduct(int numberOfOrders)
    {
        if (numberOfOrders > 0)
        {
            content.delete(content.length() - 2, content.length());
        }
    }

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    private void writeOrderTo()
    {
        final int numberOfOrders = orders.getOrdersCount();

        for (int i = 0; i < numberOfOrders; i++)
        {
            Order order = orders.getOrder(i);

            writeOrderIdTo(order.getOrderId());
            writeProductsTo(order);

            displayProduct(order.getProductsCount());

            content.append("]}, ");
        }
    }

    private void writeOrderIdTo(int orderId)
    {
        content.append("{\"id\": ").append(orderId);
    }

    private void writeProductsTo(Order order)
    {
        content.append(", \"products\": [");

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
        content.append("\"size\": \"")
                .append(size)
                .append("\", ");
    }

    private void writeProductCode(String codeProduct)
    {
        content.append("{\"code\": \"")
                .append(codeProduct);
    }

    private void writeProductColor(Color color)
    {
        content.append("\", \"color\": \"")
                .append(color)
                .append("\", ");
    }

    private void writePriceTo(Product product)
    {
        content.append("\"price\": ")
                .append(product.getPrice())
                .append(", \"currency\": \"")
                .append(product.getCurrency())
                .append("\"}, ");
    }
}
