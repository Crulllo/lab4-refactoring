public class OrdersWriter {
    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuffer sb = new StringBuffer("{\"orders\": [");
        writeOrderTo(sb);

        if (orders.getOrdersCount() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append("]}").toString();
    }

    private void writeOrderTo(StringBuffer sb)
    {
        for (int i = 0; i < orders.getOrdersCount(); i++)
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

            sb.append("{\"code\": \""
                    + product.getCode()
                    + "\", \"color\": \""
                    + getColorFor(product)
                    + "\", ");

            if (product.getSize() != Product.SIZE_NOT_APPLICABLE) {
                sb.append("\"size\": \""
                        + getSizeFor(product)
                        + "\", ");
            }

            writePriceTo(sb, product);
        }
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
        switch (product.getSize()) {
            case 1:
                return "XS";
            case 2:
                return "S";
            case 3:
                return "M";
            case 4:
                return "L";
            case 5:
                return "XL";
            case 6:
                return "XXL";
            default:
                return "Invalid Size";
        }
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