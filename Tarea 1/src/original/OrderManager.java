package original;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<String[]> orders = new ArrayList<>();

    public void createOrder(String customer, String product, double price, int quantity) {
        double total = price * quantity;
        if (total > 1000) total *= 0.9; // 10% descuento [cite: 59]
        if (total > 5000) total *= 0.85; // 15% descuento adicional [cite: 60]
        
        String orderId = "ORD-" + System.currentTimeMillis();
        orders.add(new String[] {orderId, customer, product, String.valueOf(total), "PENDING"});
        System.out.println("Orden creada: " + orderId);

        // Guardar en archivo [cite: 63]
        try {
            java.io.FileWriter fw = new java.io.FileWriter("orders.txt", true);
            fw.write(orderId + "," + customer + "," + total + "\n");
            fw.close();
        } catch (Exception e) { e.printStackTrace(); }

        // Enviar notificación [cite: 68]
        System.out.println("EMAIL a " + customer + ": Su pedido " + orderId + " ha sido creado.");
    }

    public double calculateTax(String orderId) {
        for (String[] o : orders) {
            if (o[0].equals(orderId)) {
                double total = Double.parseDouble(o[3]);
                return total * 0.19; // IVA 19% [cite: 85]
            }
        }
        return 0;
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE PEDIDOS ===\n");
        double grandTotal = 0;
        for (String[] o : orders) {
            sb.append(o[0] + " | " + o[1] + " | $" + o[3] + "\n");
            grandTotal += Double.parseDouble(o[3]);
        }
        sb.append("TOTAL: $" + grandTotal);
        System.out.println(sb.toString());
        return sb.toString();
    }
}