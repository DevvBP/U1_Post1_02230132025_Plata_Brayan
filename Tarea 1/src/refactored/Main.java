package refactored;

public class Main {
    public static void main(String[] args) {
        // Configuramos las dependencias comunes
        OrderRepository repo = new FileOrderRepository();
        NotificationService notifier = new EmailNotificationService();
        
        System.out.println("=== INICIANDO SISTEMA DE PEDIDOS (3 CASOS) ===\n");

        OrderService service1 = new OrderService(repo, notifier, new NoDiscount());
        service1.createOrder("Andres", "Teclado", 50.0, 1);
        
        OrderService service2 = new OrderService(repo, notifier, new StandardDiscount());
        service2.createOrder("Juan", "Laptop", 1200.0, 1);
        
        OrderService service3 = new OrderService(repo, notifier, new VIPDiscount());
        service3.createOrder("Maria", "Monitor Pro", 800.0, 2);

        System.out.println("\n-------------------------------------------");
        System.out.println("PROCESO FINALIZADO CON ÉXITO");
        System.out.println("Total de pedidos en repositorio: " + repo.findAll().size());
        System.out.println("-------------------------------------------");
    }
}
