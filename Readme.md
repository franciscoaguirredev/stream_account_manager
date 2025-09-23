# 🎬 Streaming Account Manager

## 📌 Descripción del proyecto

En la actualidad, el uso de plataformas de streaming como **Netflix, HBO Max, Disney+, Prime Video**, entre otras, se ha convertido en parte esencial del entretenimiento digital.  
Sin embargo, administrar de manera organizada las cuentas compartidas, los usuarios vinculados, las fechas de vigencia y los pagos correspondientes puede resultar un proceso complejo y propenso a errores.

Este proyecto busca desarrollar un **sistema de administración de cuentas de streaming**, diseñado para:

- Centralizar la gestión de cuentas.
- Controlar los usuarios que acceden a cada cuenta (con un máximo de 4).
- Registrar pagos asociados a los usuarios.
- Generar reportes básicos de ingresos y egresos.

El sistema está orientado para ser utilizado únicamente por **administradores o vendedores autorizados**, garantizando control, eficiencia y seguridad en el manejo de la información.

---

## 🎯 Visión y Justificación

### 🔹 Justificación

El crecimiento del consumo de plataformas de streaming ha impulsado a muchas personas a compartir cuentas con familiares, amigos o clientes.  
Este modelo compartido genera la necesidad de contar con un **sistema que permita gestionar de forma centralizada** las cuentas, los usuarios vinculados y los pagos realizados.

Actualmente, este proceso suele realizarse de manera manual (hojas de cálculo, notas o recordatorios), lo que provoca:

- Pérdida de información.
- Dificultad en el control de fechas.
- Confusión en los pagos.
- Falta de claridad en los balances económicos.

El sistema propuesto surge como una **solución práctica** para automatizar y organizar esta administración, ofreciendo una herramienta confiable y fácil de usar.

---

## 🛠️ Objetivos

### 🎯 Objetivo principal
Diseñar e implementar un sistema que permita a los administradores **gestionar cuentas de streaming, usuarios vinculados y pagos asociados**, facilitando el control de fechas, ingresos y egresos, garantizando la seguridad en el acceso.

### ✅ Objetivos específicos
- Desarrollar un módulo de gestión de cuentas de streaming que registre:
    - Nombre del servicio.
    - Valor.
    - Fecha de inicio.
    - Fecha de caducidad.
- Implementar la administración de usuarios vinculados, limitando un **máximo de 4 por cuenta**.
- Controlar fechas de vinculación y expiración de cada usuario.
- Registrar y consultar los pagos realizados por los usuarios, relacionándolos con las cuentas correspondientes.
- Generar **reportes financieros básicos** que permitan conocer:
    - Cuánto se ha pagado por cada cuenta.
    - Cuánto se ha recibido de los usuarios.
- Establecer un sistema de roles y seguridad que restrinja el acceso únicamente a administradores y vendedores autorizados.

---

## 🏗️ Tecnologías utilizadas
- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL** como base de datos
- **Postman** para pruebas de la API
- **IntelliJ IDEA** como IDE de desarrollo

---

# 🚀 Próximos pasos del desarrollo
1. Implementar CRUD para cada entidad principal:
    - **CuentaStreaming**
    - **Usuario**
    - **UsuarioCuentaStreaming** (relación intermedia)
    - **Pago**
    - **Administrador**
2. Validaciones de negocio:
    - Máximo de 4 usuarios por cuenta de streaming.
    - Control de fechas de vinculación y expiración.
3. Módulo de reportes financieros.
4. Seguridad y autenticación de administradores.

---

## 👨‍💻 Autores
- Proyecto académico desarrollado como práctica de **Java + Spring Boot + PostgreSQL**.
- Enfocado en **aprendizaje de programación orientada a objetos, arquitectura MVC y APIs REST**.


