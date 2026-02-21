# Ejercicio 1: Refactorización y Principios SOLID

## Descripción
En este ejercicio se realizó la refactorización de un sistema de procesamiento de pedidos que violaba varios principios de diseño. Se aplicaron los principios SOLID para mejorar la mantenibilidad y escalabilidad del código.

## Patrones y Principios Aplicados

1. **Single Responsibility Principle (SRP):**
   - Se separó la lógica de cálculo de impuestos de la clase principal de pedidos.
   - Se crearon clases independientes para la persistencia de datos (Repository) y para el envío de notificaciones.

2. **Open/Closed Principle (OCP):**
   - Se implementó una interfaz para los métodos de pago, permitiendo agregar nuevos métodos (PayPal, Tarjeta, Cripto) sin modificar el código existente.

3. **Dependency Inversion Principle (DIP):**
   - El sistema ahora depende de abstracciones (interfaces) en lugar de implementaciones concretas, facilitando las pruebas unitarias.

## Estructura del Proyecto
- `model/`: Contiene las entidades básicas como `Order` y `Product`.
- `service/`: Contiene la lógica de negocio refactorizada.
- `repository/`: Encargado del manejo de datos.

# Ejercicio 1: Refactorización y Principios SOLID

## Descripción
Este proyecto consistió en transformar un sistema monolítico de procesamiento de pedidos en una arquitectura desacoplada, siguiendo los estándares de Clean Code.

## Patrones y Principios Aplicados

### 1. Single Responsibility Principle (SRP)
- **Cambio:** Se eliminó la lógica de persistencia de la clase `Order`.
- **Justificación:** Una clase de negocio no debe saber cómo guardarse en una base de datos. Se creó `OrderRepository` para esta tarea.

### 2. Open/Closed Principle (OCP)
- **Cambio:** Implementación de la interfaz `IPaymentMethod`.
- **Justificación:** Ahora el sistema es abierto a la extensión (podemos agregar 'BitcoinPayment' sin tocar el código core) pero cerrado a la modificación.

### 3. Dependency Inversion Principle (DIP)
- **Cambio:** El `OrderService` ahora recibe interfaces en su constructor.
- **Justificación:** Esto permite realizar Inyección de Dependencias, facilitando el intercambio de componentes y la creación de Mock Tests.

### 4. Liskov Substitution Principle (LSP)
- **Cambio:** Se aseguró que todas las subclases de `Product` respeten el contrato de la clase padre sin lanzar excepciones inesperadas.

## Secciones Adicionales Agregadas
- **Manejo de Errores:** Se implementaron validaciones previas (como las que hiciste en SQL con los Triggers) para evitar datos inconsistentes antes de procesar el pedido.
- [cite_start]**Documentación de Vistas:** Se incluyó un resumen de las consultas complejas realizadas (como el Top 5 de países por área) para demostrar la capacidad de análisis de datos[cite: 14].

## Estructura Final
- `src/model/`: Entidades puras.
- `src/service/`: Lógica de negocio y reglas SOLID.
- `src/repository/`: Capa de datos.
- `src/exception/`: Excepciones personalizadas.