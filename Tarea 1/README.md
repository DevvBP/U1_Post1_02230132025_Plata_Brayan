# U1_Post1_02230132025_Plata_Brayan.zip
Buenas_Practicas_Patrones


# Laboratorio: Refactorización con SOLID - UDES

## Identificación de Violaciones SOLID (Paso 3)

He identificado las siguientes 4 violaciones en la clase `OrderManager`:

1. **Single Responsibility Principle (SRP):** La clase `OrderManager` tiene múltiples responsabilidades: gestiona la lógica de pedidos, calcula descuentos, realiza persistencia en archivos físicos (`FileWriter`) y envía notificaciones por consola.

2. **Open/Closed Principle (OCP):** Si se desea agregar un nuevo tipo de descuento o un nuevo impuesto, es necesario modificar el código existente mediante condicionales `if`, en lugar de extender la funcionalidad.

3. **Dependency Inversion Principle (DIP):** La clase depende directamente de implementaciones concretas como `java.io.FileWriter`, lo que dificulta realizar pruebas unitarias y acopla el código al sistema de archivos.

4. **Falta de Cohesión / Modelado Pobre:** El uso de `List<String[]>` para representar pedidos dificulta el mantenimiento y la legibilidad. No existe una entidad de dominio `Order` que encapsule los datos.

## Decisiones de Diseño

Para resolver las violaciones identificadas, se aplicaron las siguientes soluciones:

- **Patrón Strategy:** Se implementó para el cálculo de precios, creando la interfaz `DiscountStrategy`. Esto permite que el sistema soporte múltiples lógicas de negocio (`NoDiscount`, `StandardDiscount`, `VIPDiscount`) sin modificar el código de `OrderService`, cumpliendo con el principio **Open/Closed**.
- **Inyección de Dependencias (DIP):** El `OrderService` no instancia sus herramientas, sino que las recibe por constructor. Esto permite cambiar fácilmente entre un repositorio de archivos o uno de base de datos, o cambiar el método de notificación sin afectar la lógica principal.
- **Patrón Repository:** Se separó la persistencia en `OrderRepository`, eliminando el acoplamiento directo con `FileWriter` que existía en el código original.
- **Single Responsibility (SRP):** Se fragmentó la clase "Dios" (`OrderManager`) en clases pequeñas y especializadas: una entidad de datos (`Order`), servicios de lógica (`OrderService`) y servicios de infraestructura (`Notification`, `Repository`).