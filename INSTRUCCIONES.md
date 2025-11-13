# 🏥 API de Gestión de Pacientes - Instrucciones de Uso

## ✅ Archivos Creados

### Entidades
- `BaseEntity.java` - Entidad base con campos comunes (id, createdAt, updatedAt)
- `Paciente.java` - Entidad de paciente con validaciones
- `HistoriaClinica.java` - Entidad temporal (para completar después)

### DTOs
- `BaseDTO.java` - DTO base con campos comunes
- `PacienteDTO.java` - DTO de paciente con validaciones

### Repositorios
- `PacienteRepository.java` - Repositorio JPA con métodos personalizados

### Servicios
- `PacienteService.java` - Interfaz del servicio
- `PacienteServiceImpl.java` - Implementación del servicio con lógica de negocio

### Controladores
- `PacienteController.java` - API REST con documentación Swagger

### Configuración
- `ModelMapperConfig.java` - Configuración de ModelMapper
- `GlobalExceptionHandler.java` - Manejo global de excepciones

### Excepciones
- `ResourceNotFoundException.java` - Excepción para recursos no encontrados
- `ResourceAlreadyExistsException.java` - Excepción para recursos duplicados
- `ErrorResponse.java` - Clase para respuestas de error

### Archivos de Configuración
- `application.yml` - Configuración de la aplicación
- `pom.xml` - Dependencias actualizadas (Lombok, ModelMapper, Swagger)

### Archivos Adicionales
- `database-setup.sql` - Script para crear la base de datos
- `ejemplos-api.http` - Ejemplos de peticiones HTTP
- `README-PACIENTES.md` - Documentación completa

## 🚀 Pasos para Ejecutar

### 1. Crear la Base de Datos

```sql
CREATE DATABASE IF NOT EXISTS medical_records_db;
```

### 2. Verificar Configuración

Edita `src/main/resources/application.yml` si necesitas cambiar:
- Puerto (por defecto: 8080)
- Usuario de MySQL (por defecto: root)
- Contraseña de MySQL (por defecto: 123456)

### 3. Ejecutar la Aplicación

```bash
mvnw.cmd spring-boot:run
```

### 4. Acceder a Swagger

Abre tu navegador en:
```
http://localhost:8080/api/swagger-ui.html
```

## 📝 Endpoints Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/api/pacientes` | Listar pacientes (paginado) |
| GET | `/api/api/pacientes/{id}` | Obtener paciente por ID |
| GET | `/api/api/pacientes/dni/{dni}` | Obtener paciente por DNI |
| POST | `/api/api/pacientes` | Crear nuevo paciente |
| PUT | `/api/api/pacientes/{id}` | Actualizar paciente |
| DELETE | `/api/api/pacientes/{id}` | Eliminar paciente |

## 🧪 Probar la API

Usa el archivo `ejemplos-api.http` con extensiones como REST Client en VS Code, o usa Swagger UI.

### Ejemplo de Creación:

```json
POST http://localhost:8080/api/api/pacientes
Content-Type: application/json

{
  "pacDni": "12345678",
  "nombre": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "García",
  "direccion": "Av. Principal 123, Lima",
  "telefono": "+51987654321"
}
```

## ✨ Características Implementadas

- ✅ CRUD completo de pacientes
- ✅ Validaciones de datos (DNI, teléfono, campos obligatorios)
- ✅ Paginación de resultados
- ✅ Búsqueda por ID y DNI
- ✅ Manejo de excepciones personalizado
- ✅ Documentación automática con Swagger
- ✅ Timestamps automáticos (createdAt, updatedAt)
- ✅ Prevención de DNI duplicados

## 📦 Dependencias Agregadas

- Lombok - Reducir código boilerplate
- ModelMapper - Mapeo entre entidades y DTOs
- SpringDoc OpenAPI - Documentación Swagger
- MySQL Connector - Conexión a base de datos
- Spring Validation - Validaciones

## ✅ Sistema Completo Implementado

El sistema está 100% funcional con las siguientes entidades:

### 1. Pacientes ✅
- CRUD completo
- Validaciones de DNI único
- Búsqueda por DNI

### 2. Médicos ✅
- CRUD completo
- Validaciones de CMP, DNI y email únicos
- Búsqueda por CMP, DNI y especialidad
- Filtro de médicos activos
- Campos adicionales: universidad, años de experiencia, horario, consultorio

### 3. Historias Clínicas ✅
- CRUD completo
- Relaciones con Paciente y Médico
- Campos completos: diagnóstico, análisis, tratamiento, signos vitales, etc.
- Búsquedas por paciente, médico, rango de fechas
- Estados: ACTIVA, CERRADA, EN_SEGUIMIENTO
- Tipos de consulta: PRIMERA_VEZ, CONTROL, EMERGENCIA, SEGUIMIENTO

## 📋 Archivos Adicionales Creados

- `CONFIGURACION-INTELLIJ.md` - Guía para configurar IntelliJ y la base de datos
- `ejemplos-completos.http` - 28 ejemplos de peticiones HTTP para probar toda la API
- `database-setup.sql` - Script SQL para crear la base de datos

## 🎯 Próximos Pasos

1. **Configura la base de datos** siguiendo `CONFIGURACION-INTELLIJ.md`
2. **Ejecuta la aplicación** con `mvnw.cmd spring-boot:run`
3. **Prueba los endpoints** usando `ejemplos-completos.http` o Swagger UI
4. **Opcional**: Agrega autenticación con Spring Security
5. **Opcional**: Implementa reportes y estadísticas
