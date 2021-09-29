import Order from "./order";
import Product from "./product";

export default class ProductOrder {
  order!: Order;
  product!: Product;
  quantity!: number;
}
