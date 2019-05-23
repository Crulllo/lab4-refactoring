import Colors.Color;
import Sizes.Size;

public class OrdersWriter {
    private Orders orders;
    private StringBuffer productContent;

    public String getContents() {
        productContent = new StringBuffer("{\"orders\": [");
        writeOrderTo();
        displayProduct(orders.getOrdersCount());

        return productContent.append("]}").toString();
    }

    public void  displayProduct(int numberOfOrders)
    {
        if (numberOfOrders > 0)
        {
            productContent.delete(productContent.length() - 2, productContent.length());
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

            productContent.append("]}, ");
        }
    }

    private void writeOrderIdTo(int orderId)
    {
        productContent.append("{\"id\": ").append(orderId);
    }

    private void writeProductsTo(Order order)
    {
        final int numberOfProducts = order.getProductsCount();
        productContent.append(", \"products\": [");

        for (int j = 0; j < numberOfProducts; j++)
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
        productContent.append("\"size\": \"")
                .append(size)
                .append("\", ");
    }

    private void writeProductCode(String codeProduct)
    {
        productContent.append("{\"code\": \"")
                .append(codeProduct);
    }

    private void writeProductColor(Color color)
    {
        productContent.append("\", \"color\": \"")
                .append(color)
                .append("\", ");
    }

    private void writePriceTo(Product product)
    {
        productContent.append("\"price\": ")
                .append(product.getPrice())
                .append(", \"currency\": \"")
                .append(product.getCurrency())
                .append("\"}, ");
    }
}
