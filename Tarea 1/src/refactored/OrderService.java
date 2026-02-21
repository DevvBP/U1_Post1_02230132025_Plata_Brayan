package refactored;

public class OrderService {
    private final OrderRepository repository;
    private final NotificationService notifier;
    private final DiscountStrategy discountStrategy;

    public OrderService(OrderRepository repository, NotificationService notifier, DiscountStrategy discountStrategy) {
        this.repository = repository;
        this.notifier = notifier;
        this.discountStrategy = discountStrategy;
    }

    public void createOrder(String customer, String product, double price, int quantity) {
        double total = discountStrategy.applyDiscount(price * quantity);
        String id = "ORD-" + System.currentTimeMillis();
        Order order = new Order(id, customer, product, total);
        repository.save(order);
        notifier.notify(customer, "Pedido " + id + " creado.");
    }
}
