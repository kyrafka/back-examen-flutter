# API de Gestión de Pacientes

## Configuración Inicial

### 1. Requisitos Previos
- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### 2. Configurar la Base de Datos

Ejecuta el siguiente comando en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS medical_records_db;
```

O ejecuta el archivo `database-setup.sql`:

```bash
mysql -u root -p < database-setup.sql
```

### 3. Configuración de Credenciales

Edita `src/main/resources/application.yml` si necesitas cambiar las credenciales de MySQL:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/medical_records_db?useSSL=false&serverTimezone=UTC
    username: root
    password: 123456
```

### 4. Ejecutar la Aplicación

```bash
mvnw spring-boot:run
```

O en Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Acceder a la Documentación

Una vez iniciada la aplicación, accede a:

- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **API Docs**: http://localhost:8080/api/api-docs

## Endpoints Disponibles

### Pacientes

- `GET /api/api/pacientes` - Obtener todos los pacientes (paginado)
- `GET /api/api/pacientes/{id}` - Obtener paciente por ID
- `GET /api/api/pacientes/dni/{dni}` - Obtener paciente por DNI
- `POST /api/api/pacientes` - Crear nuevo paciente
- `PUT /api/api/pacientes/{id}` - Actualizar paciente
- `DELETE /api/api/pacientes/{id}` - Eliminar paciente

## Ejemplo de Petición

### Crear Paciente

```bash
curl -X POST http://localhost:8080/api/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "pacDni": "12345678",
    "nombre": "Juan",
    "apellidoPaterno": "Pérez",
    "apellidoMaterno": "García",
    "direccion": "Av. Principal 123",
    "telefono": "+51987654321",
    "imagen": "base64_encoded_image"
  }'
```

## Estructura del Proyecto

```
src/main/java/com/example/backexamen/
├── config/              # Configuraciones (ModelMapper, Exception Handler)
├── controller/          # Controladores REST
├── dto/                 # Data Transfer Objects
├── entity/              # Entidades JPA
├── exception/           # Excepciones personalizadas
├── repository/          # Repositorios JPA
└── service/             # Lógica de negocio
    └── impl/            # Implementaciones de servicios
```

## Tecnologías Utilizadas

- Spring Boot 3.5.7
- Spring Data JPA
- MySQL 8
- Lombok
- ModelMapper
- SpringDoc OpenAPI (Swagger)
- Bean Validation
