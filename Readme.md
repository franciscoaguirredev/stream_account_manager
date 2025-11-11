# 🎬 Stream Account Manager

## 📌 Descripción del proyecto
Sistema de administración de cuentas de plataformas de streaming que permite gestionar cuentas compartidas, perfiles de usuarios, suscripciones y pagos de manera organizada y eficiente.

---

## 🎯 ESPECIFICACIÓN DEL PROYECTO

### 🔴 Necesidad
Las plataformas de streaming (Netflix, Disney+, HBO Max, Prime Video, etc.) tienen costos elevados para usuarios individuales. Muchas personas buscan compartir cuentas para reducir gastos mensuales, pero actualmente no existe una herramienta eficiente y organizada para gestionar este modelo de compartición de manera segura, controlada y rentable.

### ⚠️ Problema
En la actualidad, la administración de cuentas compartidas de streaming se realiza de manera **manual y desorganizada** (hojas de cálculo, notas, recordatorios), lo que genera:

- ❌ **Pérdida de información** sobre quiénes tienen acceso a cada cuenta
- ❌ **Dificultad para controlar fechas** de inicio y vencimiento de suscripciones
- ❌ **Confusión en los pagos**: no hay claridad sobre quién pagó, cuánto y cuándo
- ❌ **Falta de control de espacios**: no se valida el límite de perfiles por cuenta (máximo 4)
- ❌ **Imposibilidad de generar reportes financieros** de ingresos y egresos
- ❌ **Ausencia de seguridad**: cualquiera puede acceder a la información

Esto provoca **pérdidas económicas, conflictos entre usuarios y desorganización operativa**.

### 🎯 Objetivo General
**Desarrollar un sistema de información web** que permita a administradores gestionar de manera eficiente y centralizada las cuentas de plataformas de streaming, sus perfiles, suscriptores, suscripciones y pagos asociados, garantizando control, seguridad y trazabilidad de la información financiera antes del **15 de diciembre de 2025**.

### ✅ Objetivos Específicos (SMART)

#### 1️⃣ Realizar el análisis del sistema de software
- Identificar requisitos funcionales y no funcionales
- Definir actores del sistema (Administrador, Suscriptor)
- Crear casos de uso principales
- Diseñar el modelo conceptual de datos
- **Fecha límite:** 30 de septiembre de 2025

#### 2️⃣ Diseñar la arquitectura del sistema
- Definir arquitectura de **n capas** (Controller, Service, Repository, DTO, Mapper, Model)
- Crear diagrama de clases completo con todas las entidades
- Diseñar modelo entidad-relación normalizado
- Definir API RESTful siguiendo convenciones REST
- **Fecha límite:** 15 de octubre de 2025

#### 3️⃣ Implementar el backend del sistema
- Desarrollar API REST con **Spring Boot 3 y Java 21**
- Implementar lógica de negocio en capa de servicios
- Crear repositorios JPA para acceso a datos
- Implementar validaciones críticas:
  - ✓ Máximo 4 perfiles por cuenta
  - ✓ Validación de fechas de suscripción
  - ✓ Control de estados (Activo/Inactivo)
- **Fecha límite:** 10 de noviembre de 2025

#### 4️⃣ Implementar el frontend del sistema
- Desarrollar interfaz gráfica de usuario en **Java Swing**
- Implementar arquitectura por capas en el cliente
- Conectar con API REST del backend mediante HTTP
- Crear formularios CRUD para todas las entidades
- **Fecha límite:** 25 de noviembre de 2025

#### 5️⃣ Realizar pruebas del sistema
- Probar todos los endpoints con **Swagger UI**
- Validar reglas de negocio implementadas
- Realizar pruebas de integración entre capas
- Documentar casos de prueba y resultados
- **Fecha límite:** 5 de diciembre de 2025

#### 6️⃣ Documentar el sistema completo
- Documentar API REST con **OpenAPI/Swagger**
- Crear manual de usuario del sistema
- Documentar código fuente con Javadoc
- Generar guía de instalación y despliegue
- **Fecha límite:** 15 de diciembre de 2025

---

## 🗂️ MODELO DE DATOS

### 📊 Diagrama de Clases
```
                    ┌─────────────────────┐
                    │   Administrador     │
                    ├─────────────────────┤
                    │ - idAdministrador   │
                    │ - nombre            │
                    │ - correo            │
                    │ - contrasena        │
                    │ - rol               │
                    └──────────┬──────────┘
                               │
                               │ 1
                               │
                               │ gestiona
                               │
                               │ N
                               │
        ┌──────────────────────┴──────────────────────┐
        │                                             │
        │                                             │
        ▼                                             ▼
┌───────────────┐                            ┌───────────────┐
│  Plataforma   │                            │    Cuenta     │
├───────────────┤                            ├───────────────┤
│ - idPlataforma│                            │ - idCuenta    │
│ - nombre      │                            │ - correo      │
│ - urlOficial  │────────────────────────────│ - contrasena  │
│ - estado      │           1           N    │ - fechaInicio │
└───────┬───────┘                            │ - fechaFin    │
        │                                    │ - estado      │
        │                                    └───────┬───────┘
        │                                            │
        │ 1                                          │ 1
        │                                            │
        │                                            │ contiene
        │                                            │
        │                                            │ N (máx 4)
        │                                            │
        │                                    ┌───────▼───────┐
        │                                    │    Perfil     │
        │                                    ├───────────────┤
        │                                    │ - idPerfil    │
        │                         ┌──────────│ - nombrePerfil│
        │                         │          │ - pin         │
        │                         │          │ - estado      │
        │                         │          └───────────────┘
        │                         │
        │ N                       │ 1-1
        │                         │
        ▼                         │
┌───────────────┐                 │
│  Suscripcion  │◄────────────────┘
├───────────────┤
│- idSuscripcion│
│- fechaInicio  │
│- fechaFin     │
│- estado       │
│- montoMensual │
└───────┬───────┘
        │
        │ N
        │
        │ pertenece a
        │
        │ 1
        │
        ▼
┌───────────────┐
│  Suscriptor   │
├───────────────┤
│- idSuscriptor │
│- nombre       │
│- correo       │
└───────────────┘


┌───────────────┐
│  Suscripcion  │
├───────────────┤
│- idSuscripcion│
│- fechaInicio  │
│- fechaFin     │
│- estado       │
│- montoMensual │
└───────┬───────┘
        │
        │ 1
        │
        │ registra
        │
        │ N
        │
        ▼
┌───────────────┐
│     Pago      │
├───────────────┤
│ - idPago      │
│ - fechaPago   │
│ - montoPagado │
│ - metodoPago  │
└───────────────┘
```

### 🗃️ Diagrama Entidad-Relación
```
┌──────────────────┐              ┌──────────────────┐
│  ADMINISTRADOR   │              │   PLATAFORMA     │
├──────────────────┤              ├──────────────────┤
│ PK id_admin      │              │ PK id_plataforma │
│    nombre        │              │    nombre        │
│    correo        │              │    url_oficial   │
│    contrasena    │              │    estado        │
│    rol           │              └────────┬─────────┘
└────────┬─────────┘                       │
         │                                 │
         │ 1                               │ 1
         │                                 │
         │ gestiona                        │ contiene
         │                                 │
         │ N                               │ N
         │                                 │
         └─────────────┬───────────────────┘
                       │
                       ▼
              ┌─────────────────┐
              │    CUENTA       │
              ├─────────────────┤
              │ PK id_cuenta    │
              │    correo       │
              │    contrasena   │
              │    fecha_inicio │
              │    fecha_fin    │
              │    estado       │
              │ FK id_plataforma│
              │ FK id_admin     │
              └────────┬────────┘
                       │
                       │ 1
                       │
                       │ contiene
                       │
                       │ N (máx 4)
                       │
                       ▼
              ┌────────────────┐
              │    PERFIL      │
              ├────────────────┤
              │ PK id_perfil   │
              │    nombre      │
              │    pin         │
              │    estado      │
              │ FK id_cuenta   │
              └────────┬───────┘
                       │
                       │ 1-1
                       │
                       │ se asigna
                       │
                       ▼
┌────────────────┐  ┌─────────────────┐   ┌─────────────────┐
│    SUSCRIPTOR  │  │  SUSCRIPCION    │   │       PAGO      │
├────────────────┤  ├─────────────────┤   ├─────────────────┤
│PK id_suscriptor│  │PK id_suscripcion│   │ PK id_pago      │
│   nombre       │──│    fecha_inicio │   │  fecha_pago     │
│   correo       │ 1│    fecha_fin    │   │  monto_pagado   │
└────────────────┘  │    estado       │   │  metodo_pago    │
                    │   monto_mensual │   │FK id_suscripcion│
                    │FK id_suscriptor │───└─────────────────┘
                    │FK id_plataforma │ 1        ▲
                    │FK id_perfil     │          │
                    └─────────────────┘          │ N
                           │                     │
                           └─────────────────────┘
                                   1
                               registra
```

---

## 🏗️ ARQUITECTURA DEL SISTEMA

### Arquitectura de N Capas
```
┌────────────────────────────────────────────────────────────┐
│              CAPA DE PRESENTACIÓN                          │
│         (Controllers - API REST Endpoints)                 │
│  - AdministradorController                                 │
│  - PlataformaController                                    │
│  - CuentaController                                        │
│  - PerfilController                                        │
│  - SuscriptorController                                    │
│  - SuscripcionController                                   │
│  - PagoController                                          │
└───────────────────────────┬────────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│            CAPA DE TRANSFERENCIA (DTO)                     │
│         - AdministradorDTO                                 │
│         - PlataformaDTO                                    │
│         - CuentaDTO                                        │
│         - PerfilDTO                                        │
│         - SuscriptorDTO                                    │
│         - SuscripcionDTO                                   │
│         - PagoDTO                                          │
└───────────────────────────┬────────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│            CAPA DE MAPEO (Mappers)                         │
│    - AdministradorMapper                                   │
│    - PlataformaMapper                                      │
│    - CuentaMapper                                          │
│    - PerfilMapper                                          │
│    - SuscriptorMapper                                      │
│    - SuscripcionMapper                                     │
│    - PagoMapper                                            │
└───────────────────────────┬────────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│         CAPA DE LÓGICA DE NEGOCIO (Services)               │
│  - AdministradorService                                    │
│  - PlataformaService                                       │
│  - CuentaService                                           │
│  - PerfilService (validación máximo 4 perfiles)            │
│  - SuscriptorService                                       │
│  - SuscripcionService                                      │
│  - PagoService                                             │
└───────────────────────────┬────────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│         CAPA DE ACCESO A DATOS (Repositories)              │
│  - AdministradorRepository                                 │
│  - PlataformaRepository                                    │
│  - CuentaRepository                                        │
│  - PerfilRepository                                        │
│  - SuscriptorRepository                                    │
│  - SuscripcionRepository                                   │
│  - PagoRepository                                          │
└───────────────────────────┬────────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│         CAPA DE PERSISTENCIA (Entidades JPA)               │
│  - Administrador                                           │
│  - Plataforma                                              │
│  - Cuenta                                                  │
│  - Perfil                                                  │
│  - Suscriptor                                              │
│  - Suscripcion                                             │
│  - Pago                                                    │
└───────────────────────────┬────────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│                  BASE DE DATOS                             │
│              PostgreSQL / H2 Database                      │
└────────────────────────────────────────────────────────────┘
```

---

## 📡 API REST - ENDPOINTS

### **Administradores**
- `POST   /api/administradores` - Crear administrador
- `GET    /api/administradores` - Listar todos
- `GET    /api/administradores/{id}` - Obtener por ID
- `PUT    /api/administradores/{id}` - Actualizar
- `DELETE /api/administradores/{id}` - Eliminar

### **Plataformas**
- `POST   /api/plataformas` - Crear plataforma
- `GET    /api/plataformas` - Listar todas
- `GET    /api/plataformas/{id}` - Obtener por ID
- `PUT    /api/plataformas/{id}` - Actualizar
- `DELETE /api/plataformas/{id}` - Eliminar

### **Cuentas**
- `POST   /api/cuentas` - Crear cuenta
- `GET    /api/cuentas` - Listar todas
- `GET    /api/cuentas/{id}` - Obtener por ID
- `GET    /api/cuentas/plataforma/{idPlataforma}` - Por plataforma
- `GET    /api/cuentas/administrador/{idAdmin}` - Por administrador
- `PUT    /api/cuentas/{id}` - Actualizar
- `DELETE /api/cuentas/{id}` - Eliminar

### **Perfiles** ⭐
- `POST   /api/perfiles` - Crear perfil
- `GET    /api/perfiles` - Listar todos
- `GET    /api/perfiles/{id}` - Obtener por ID
- `GET    /api/perfiles/cuenta/{idCuenta}` - Perfiles de una cuenta
- `GET    /api/perfiles/cuenta/{idCuenta}/disponibilidad` - Validar espacios
- `PUT    /api/perfiles/{id}` - Actualizar
- `DELETE /api/perfiles/{id}` - Eliminar

### **Suscriptores**
- `POST   /api/v1/suscriptores` - Crear suscriptor
- `GET    /api/v1/suscriptores` - Listar todos
- `GET    /api/v1/suscriptores/{id}` - Obtener por ID
- `PUT    /api/v1/suscriptores/{id}` - Actualizar
- `DELETE /api/v1/suscriptores/{id}` - Eliminar

### **Suscripciones**
- `POST   /api/v1/suscripciones` - Crear suscripción
- `GET    /api/v1/suscripciones` - Listar todas
- `GET    /api/v1/suscripciones/{id}` - Obtener por ID
- `PUT    /api/v1/suscripciones/{id}` - Actualizar
- `DELETE /api/v1/suscripciones/{id}` - Eliminar

### **Pagos**
- `POST   /api/v1/pagos` - Registrar pago
- `GET    /api/v1/pagos` - Listar todos
- `GET    /api/v1/pagos/{id}` - Obtener por ID
- `PUT    /api/v1/pagos/{id}` - Actualizar
- `DELETE /api/v1/pagos/{id}` - Eliminar

---

## 🛠️ Tecnologías Utilizadas

| Tecnología      | Versión |         Uso              |
|-----------------|---------|--------------------------|
| Java            |    21   | Lenguaje principal       |
| Spring Boot     |  3.5.6  | Framework backend        |
| Spring Data JPA |  3.5.6  | ORM y repositorios       |
| Hibernate       |  6.6.29 | Implementación JPA       |
| PostgreSQL      |    16+  | Base de datos producción |
| H2 Database     |    2.x  | Base de datos desarrollo |
| Swagger/OpenAPI |    3.0  | Documentación API        |
| Maven           |   3.9+  | Gestión de dependencias  |

---

## ⚙️ Instalación y Ejecución

### 1️⃣ Clonar repositorio
```bash
git clone https://github.com/franciscoaguirredev/stream_account_manager.git
cd stream_account_manager
```

### 2️⃣ Configurar base de datos

**Opción A: PostgreSQL**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/stream_db
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

**Opción B: H2 (para desarrollo)**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

### 3️⃣ Compilar y ejecutar
```bash
./mvnw clean install
./mvnw spring-boot:run
```

### 4️⃣ Acceder a Swagger
```
http://localhost:8080/swagger-ui.html
```

---

## ✅ Reglas de Negocio Implementadas

1. ✓ **Máximo 4 perfiles por cuenta** de streaming
2. ✓ **Validación de disponibilidad** antes de crear perfil
3. ✓ **Control de estados**: Activo/Inactivo
4. ✓ **Validación de fechas**: fecha_fin > fecha_inicio
5. ✓ **Correos únicos** para suscriptores
6. ✓ **Desacoplamiento con DTOs**: no se exponen entidades

---

## 👥 Equipo de Desarrollo

- **Francisco Aguirre** - Perfiles y Cuentas
- **Samuel Stiven Diaz Isaza** - Administradores y Plataformas  
- **Bladimir Trespalacios** - Suscriptores, Suscripciones y Pagos

---

## 📝 Licencia
Proyecto académico - Instituto Tecnológico Metropolitano (ITM)
