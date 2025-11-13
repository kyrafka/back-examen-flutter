# 🔧 Configuración de IntelliJ IDEA y Base de Datos

## Paso 1: Crear la Base de Datos en MySQL

Abre MySQL Workbench o la consola de MySQL y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS medical_records_db;
```

O ejecuta este comando en tu terminal:

```bash
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS medical_records_db;"
```

## Paso 2: Configurar la Conexión en IntelliJ

En la ventana que tienes abierta de "Data Sources and Drivers", completa los campos:

### Campos a llenar:

1. **Name**: `@localhost` (ya está)
2. **Host**: `localhost` (ya está)
3. **Port**: `3306` (ya está)
4. **User**: `rot` (ya está, pero debería ser `root`)
5. **Password**: `123456` (la que configuraste)
6. **Database**: `medical_records_db` ⬅️ **ESCRIBE ESTO AQUÍ**

### Pasos:

1. En el campo **Database** (que está vacío y resaltado en azul), escribe:
   ```
   medical_records_db
   ```

2. Haz clic en **"Test Connection"** para verificar que funciona

3. Si la conexión es exitosa, haz clic en **"OK"**

## Paso 3: Verificar la Configuración

Tu URL final debería verse así:
```
jdbc:mysql://localhost:3306/medical_records_db
```

## Paso 4: Ejecutar la Aplicación

Una vez configurada la base de datos, ejecuta:

```bash
mvnw.cmd spring-boot:run
```

O desde IntelliJ:
- Busca la clase `BackExamenApplication.java`
- Haz clic derecho → Run 'BackExamenApplication'

## 🎯 Endpoints Disponibles

Una vez que la aplicación esté corriendo, accede a:

### Swagger UI (Documentación Interactiva)
```
http://localhost:8080/api/swagger-ui.html
```

### Endpoints Principales

#### Pacientes
- `GET    /api/api/pacientes` - Listar pacientes
- `POST   /api/api/pacientes` - Crear paciente
- `GET    /api/api/pacientes/{id}` - Obtener por ID
- `GET    /api/api/pacientes/dni/{dni}` - Obtener por DNI
- `PUT    /api/api/pacientes/{id}` - Actualizar
- `DELETE /api/api/pacientes/{id}` - Eliminar

#### Médicos
- `GET    /api/api/medicos` - Listar médicos
- `POST   /api/api/medicos` - Crear médico
- `GET    /api/api/medicos/{id}` - Obtener por ID
- `GET    /api/api/medicos/cmp/{cmp}` - Obtener por CMP
- `GET    /api/api/medicos/especialidad/{especialidad}` - Por especialidad
- `GET    /api/api/medicos/activos` - Médicos activos
- `PUT    /api/api/medicos/{id}` - Actualizar
- `DELETE /api/api/medicos/{id}` - Eliminar

#### Historias Clínicas
- `GET    /api/api/historias-clinicas` - Listar historias
- `POST   /api/api/historias-clinicas` - Crear historia
- `GET    /api/api/historias-clinicas/{id}` - Obtener por ID
- `GET    /api/api/historias-clinicas/paciente/{dni}` - Por paciente
- `GET    /api/api/historias-clinicas/medico/{cmp}` - Por médico
- `GET    /api/api/historias-clinicas/fecha-rango` - Por rango de fechas
- `PUT    /api/api/historias-clinicas/{id}` - Actualizar
- `DELETE /api/api/historias-clinicas/{id}` - Eliminar

## 🐛 Solución de Problemas

### Error: "Access denied for user 'rot'@'localhost'"
- El usuario debería ser `root`, no `rot`
- Verifica tu contraseña de MySQL

### Error: "Unknown database 'medical_records_db'"
- Ejecuta el comando SQL del Paso 1 para crear la base de datos

### Error: "Communications link failure"
- Verifica que MySQL esté corriendo
- Verifica el puerto (debe ser 3306)

### Las tablas no se crean automáticamente
- Verifica que `spring.jpa.hibernate.ddl-auto=update` esté en `application.yml`
- La primera vez que ejecutes la app, Hibernate creará las tablas automáticamente
