import Product from './product';
export default class Order {
  id!: number;
  status: string = "";
  date!: Date;
  user!: {
    id: number;
    email: string;
  };
  products: {
    product: Product,
    quantity: number,
  }[] = [];
}
