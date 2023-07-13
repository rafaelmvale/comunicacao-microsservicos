import Order from '../../modules/sales/model/Order.js';

export async function createInitialData() {
  await Order.collection.drop();
  let firstOrder = await Order.create({
    products: [
      {
        productId: 1001,
        quantity: 2,
      },
      {
        productId: 1002,
        quantity: 1,
      },
      {
        productId: 1001,
        quantity: 1,
      },
    ],
    user: {
      id: 'ad21wfc2334ges5g6s',
      name: 'John Doe',
      email: 'userTest@gmail.com',
    },
    status: 'APPROVED',
    createdAt: new Date(),
    updatedAt: new Date()
});
await Order.create({
  products: [
    {
      productId: 1001,
      quantity: 4,
    },
    {
      productId: 1003,
      quantity: 2,
    },
  ],
  user: {
    id: 'ad21wfc233wf32',
    name: 'John Doe 2 ',
    email: 'userTest2@gmail.com',
  },
  status: 'REJECTED',
  createdAt: new Date(),
  updatedAt: new Date()
});
let initialData = await Order.find();
console.info('Initial data created successfully', initialData);
}