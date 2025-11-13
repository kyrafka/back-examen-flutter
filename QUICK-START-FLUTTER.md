# 🚀 Quick Start - Flutter con Backend

## ⚡ Información Esencial

### URLs Base
```
Backend Local: http://localhost:8080/api
Android Emulator: http://10.0.2.2:8080/api
Swagger UI: http://localhost:8080/api/swagger-ui.html
```

### Ejecutar Backend
```bash
cd BACK-EXAMEN
mvn spring-boot:run
```

---

## 📋 Endpoints Resumidos

### PACIENTES
```
GET    /api/pacientes                  - Listar (paginado)
GET    /api/pacientes/{id}             - Por ID
GET    /api/pacientes/dni/{dni}        - Por DNI
POST   /api/pacientes                  - Crear
PUT    /api/pacientes/{id}             - Actualizar
DELETE /api/pacientes/{id}             - Eliminar
```

### MÉDICOS
```
GET    /api/medicos                    - Listar (paginado)
GET    /api/medicos/{id}               - Por ID
GET    /api/medicos/cmp/{cmp}          - Por CMP
GET    /api/medicos/dni/{dni}          - Por DNI
GET    /api/medicos/especialidad/{esp} - Por especialidad
GET    /api/medicos/activos            - Solo activos
POST   /api/medicos                    - Crear
PUT    /api/medicos/{id}               - Actualizar
DELETE /api/medicos/{id}               - Eliminar
```

### HISTORIAS CLÍNICAS
```
GET    /api/historias-clinicas                      - Listar (paginado)
GET    /api/historias-clinicas/{id}                 - Por ID
GET    /api/historias-clinicas/paciente/{dni}       - Por paciente
GET    /api/historias-clinicas/medico/{cmp}         - Por médico
GET    /api/historias-clinicas/fecha-rango          - Por fechas
GET    /api/historias-clinicas/paciente/{dni}/medico/{cmp} - Específico
POST   /api/historias-clinicas                      - Crear
PUT    /api/historias-clinicas/{id}                 - Actualizar
DELETE /api/historias-clinicas/{id}                 - Eliminar
```

---

## 🎯 Ejemplos de Peticiones

### Crear Paciente
```json
POST /api/pacientes

{
  "pacDni": "12345678",
  "nombre": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "García",
  "direccion": "Av. Principal 123",
  "telefono": "+51987654321"
}
```

### Crear Médico
```json
POST /api/medicos

{
  "medCmp": "CMP12345",
  "nombre": "Roberto",
  "apellidos": "Sánchez Torres",
  "especialidad": "Cardiología",
  "dni": "45678912",
  "email": "roberto@hospital.com",
  "telefono": "+51998877665",
  "activo": true
}
```

### Crear Historia Clínica
```json
POST /api/historias-clinicas

{
  "pacienteDni": "12345678",
  "medicoCmp": "CMP12345",
  "fechaAtencion": "2024-11-13T10:30:00",
  "diagnostico": "Hipertensión arterial",
  "analisis": "Presión arterial elevada",
  "tratamiento": "Enalapril 10mg",
  "peso": 78.5,
  "altura": 172.0,
  "temperatura": 36.5,
  "presionArterial": "145/95",
  "frecuenciaCardiaca": 78,
  "estado": "ACTIVA",
  "tipoConsulta": "PRIMERA_VEZ"
}
```

---

## 🔑 Campos Obligatorios

### Paciente
- `pacDni` (8 dígitos, único)
- `nombre`
- `apellidoPaterno`
- `apellidoMaterno`
- `direccion`
- `telefono`

### Médico
- `medCmp` (único)
- `nombre`
- `apellidos`
- `especialidad`
- `dni` (8 dígitos, único)

### Historia Clínica
- `pacienteDni` (debe existir)
- `medicoCmp` (debe existir)
- `fechaAtencion`
- `diagnostico`
- `analisis`
- `tratamiento`

---

## 📊 Enums

### Estado Historia
- `ACTIVA`
- `CERRADA`
- `EN_SEGUIMIENTO`

### Tipo Consulta
- `PRIMERA_VEZ`
- `CONTROL`
- `EMERGENCIA`
- `SEGUIMIENTO`

---

## ⚠️ Códigos de Error

- `200` - OK
- `201` - Creado
- `204` - Eliminado
- `400` - Validación fallida
- `404` - No encontrado
- `409` - Duplicado (DNI, CMP, Email)
- `500` - Error servidor

---

## 🛠️ Configuración Flutter

### pubspec.yaml
```yaml
dependencies:
  http: ^1.1.0
  provider: ^6.1.1
  intl: ^0.18.1
```

### Servicio API Básico
```dart
class ApiService {
  static const baseUrl = 'http://10.0.2.2:8080/api';
  
  Future<List<Paciente>> getPacientes() async {
    final response = await http.get(
      Uri.parse('$baseUrl/pacientes?page=0&size=100')
    );
    
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      return (data['content'] as List)
          .map((json) => Paciente.fromJson(json))
          .toList();
    }
    throw Exception('Error');
  }
}
```

---

## 📝 Validaciones Importantes

### DNI
- Exactamente 8 dígitos
- Único en sistema
- Solo números

### CMP
- Único en sistema
- Máximo 20 caracteres

### Email
- Formato válido
- Único en sistema

### Teléfono
- Formato: `+51987654321`
- Puede incluir espacios y guiones

### Signos Vitales
- Peso: 0-500 kg
- Altura: 0-300 cm
- Temperatura: 30-45 °C
- Frecuencia cardíaca: 30-250 ppm

---

## 🎨 Estructura de Respuesta Paginada

```json
{
  "content": [...],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 50,
  "totalPages": 5,
  "last": false,
  "first": true
}
```

---

## 🔍 Búsquedas Útiles

### Buscar paciente por DNI
```
GET /api/pacientes/dni/12345678
```

### Buscar médicos por especialidad
```
GET /api/medicos/especialidad/Cardiología
```

### Buscar historias de un paciente
```
GET /api/historias-clinicas/paciente/12345678
```

### Buscar historias por fecha
```
GET /api/historias-clinicas/fecha-rango?fechaInicio=2024-11-01T00:00:00&fechaFin=2024-11-30T23:59:59
```

---

## 💡 Tips

1. **Usa Swagger** para probar endpoints antes de implementar en Flutter
2. **Valida datos** en Flutter antes de enviar al backend
3. **Maneja errores** con try-catch y muestra mensajes al usuario
4. **Usa paginación** para listas grandes
5. **Guarda tokens** si implementas autenticación después

---

## 🐛 Troubleshooting

### No conecta desde emulador
- Usa `http://10.0.2.2:8080/api` en lugar de `localhost`

### Error CORS
- Agrega configuración CORS en Spring Boot (ver API-REFERENCE-FLUTTER.md)

### Error 404
- Verifica que el backend esté corriendo
- Verifica la URL completa con `/api` al inicio

### Error 400 (Validación)
- Revisa que todos los campos obligatorios estén presentes
- Verifica formato de DNI (8 dígitos)
- Verifica formato de fechas

---

## 📚 Archivos de Referencia

- `API-REFERENCE-FLUTTER.md` - Documentación completa de la API
- `ejemplos-completos.http` - 28 ejemplos de peticiones
- `RESUMEN-COMPLETO.md` - Resumen del proyecto backend
- `CONFIGURACION-INTELLIJ.md` - Configuración de base de datos

---

## ✅ Checklist Antes de Empezar Flutter

- [ ] Backend corriendo en puerto 8080
- [ ] Base de datos MySQL creada y conectada
- [ ] Swagger UI accesible
- [ ] Probado al menos un endpoint desde Swagger
- [ ] Conoces la URL base para tu entorno (emulador/físico)
- [ ] Tienes los modelos Dart listos
- [ ] Dependencias Flutter instaladas
