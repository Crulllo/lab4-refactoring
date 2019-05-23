# lab4-refactoring

### Élèves : Crüll Loris, Lagier Elodie

Bad smells noticed :
- Long method -> too many line of code.
For example : writeOrderTo from the OrdersWriter class.

- Switch Statements
For example : colors and sizes.

- Duplicate code -> fragments look almost identical
For example :
if (numberOfOrders > 0)
{
  productContent.delete(productContent.length() - 2, productContent.length());
}

- Message chains
For example : a lot of append ...
