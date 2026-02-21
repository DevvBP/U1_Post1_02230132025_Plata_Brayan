package refactored;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class FileOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        try (FileWriter fw = new FileWriter("orders.txt", true)) {
            fw.write(order.getId() + "," + order.getCustomer() + "," + order.getTotal() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
