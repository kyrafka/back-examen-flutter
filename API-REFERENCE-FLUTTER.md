# 📱 API Reference para Flutter Frontend

## 🌐 Configuración Base

```dart
// Base URL de la API
const String BASE_URL = "http://localhost:8080/api";

// Para Android Emulator usa:
// const String BASE_URL = "http://10.0.2.2:8080/api";

// Para dispositivo físico usa tu IP local:
// const String BASE_URL = "http://192.168.1.X:8080/api";
```

## 📋 Endpoints Completos

### 🏥 PACIENTES

#### 1. Listar Pacientes (Paginado)
```http
GET /api/pacientes?page=0&size=10
```

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "pacDni": "12345678",
      "nombre": "Juan Carlos",
      "apellidoPaterno": "Pérez",
      "apellidoMaterno": "García",
      "direccion": "Av. Principal 123, Lima",
      "telefono": "+51987654321",
      "imagen": null,
      "createdAt": "2024-11-13 10:30:00",
      "updatedAt": "2024-11-13 10:30:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 1,
  "totalPages": 1
}
```

#### 2. Obtener Paciente por ID
```http
GET /api/pacientes/{id}
```

#### 3. Obtener Paciente por DNI
```http
GET /api/pacientes/dni/{dni}
```

#### 4. Crear Paciente
```http
POST /api/pacientes
Content-Type: application/json

{
  "pacDni": "12345678",
  "nombre": "Juan Carlos",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "García",
  "direccion": "Av. Principal 123, Lima",
  "telefono": "+51987654321",
  "imagen": null
}
```

#### 5. Actualizar Paciente
```http
PUT /api/pacientes/{id}
Content-Type: application/json

{
  "pacDni": "12345678",
  "nombre": "Juan Carlos",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "García",
  "direccion": "Av. Principal 456, Lima",
  "telefono": "+51987654321",
  "imagen": null
}
```

#### 6. Eliminar Paciente
```http
DELETE /api/pacientes/{id}
```

---

### 👨‍⚕️ MÉDICOS

#### 1. Listar Médicos (Paginado)
```http
GET /api/medicos?page=0&size=10
```

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "medCmp": "CMP12345",
      "nombre": "Roberto",
      "apellidos": "Sánchez Torres",
      "especialidad": "Cardiología",
      "dni": "45678912",
      "email": "roberto.sanchez@hospital.com",
      "telefono": "+51998877665",
      "direccion": "Consultorio 301, Hospital Central",
      "universidad": "Universidad Nacional Mayor de San Marcos",
      "aniosExperiencia": 15,
      "horarioAtencion": "Lun-Vie 9:00-17:00",
      "consultorio": "301",
      "activo": true,
      "imagen": null,
      "observaciones": "Especialista en enfermedades cardiovasculares",
      "nombreCompleto": "Roberto Sánchez Torres",
      "createdAt": "2024-11-13 10:30:00",
      "updatedAt": "2024-11-13 10:30:00"
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

#### 2. Obtener Médico por ID
```http
GET /api/medicos/{id}
```

#### 3. Obtener Médico por CMP
```http
GET /api/medicos/cmp/{cmp}
```

#### 4. Obtener Médico por DNI
```http
GET /api/medicos/dni/{dni}
```

#### 5. Obtener Médicos por Especialidad
```http
GET /api/medicos/especialidad/{especialidad}
```

**Response:** Array de médicos (sin paginación)

#### 6. Obtener Médicos Activos
```http
GET /api/medicos/activos
```

**Response:** Array de médicos activos (sin paginación)

#### 7. Crear Médico
```http
POST /api/medicos
Content-Type: application/json

{
  "medCmp": "CMP12345",
  "nombre": "Roberto",
  "apellidos": "Sánchez Torres",
  "especialidad": "Cardiología",
  "dni": "45678912",
  "email": "roberto.sanchez@hospital.com",
  "telefono": "+51998877665",
  "direccion": "Consultorio 301, Hospital Central",
  "universidad": "Universidad Nacional Mayor de San Marcos",
  "aniosExperiencia": 15,
  "horarioAtencion": "Lun-Vie 9:00-17:00",
  "consultorio": "301",
  "activo": true,
  "observaciones": "Especialista en enfermedades cardiovasculares"
}
```

#### 8. Actualizar Médico
```http
PUT /api/medicos/{id}
Content-Type: application/json
```

#### 9. Eliminar Médico
```http
DELETE /api/medicos/{id}
```

---

### 📋 HISTORIAS CLÍNICAS

#### 1. Listar Historias Clínicas (Paginado)
```http
GET /api/historias-clinicas?page=0&size=10
```

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "pacienteDni": "12345678",
      "medicoCmp": "CMP12345",
      "fechaAtencion": "2024-11-13 10:30:00",
      "diagnostico": "Hipertensión arterial leve",
      "analisis": "Paciente presenta presión arterial elevada",
      "tratamiento": "Enalapril 10mg cada 12 horas",
      "motivoConsulta": "Dolor de cabeza frecuente",
      "sintomas": "Cefalea, mareos ocasionales",
      "signosVitales": "PA: 145/95, FC: 78, Temp: 36.5°C",
      "peso": 78.5,
      "altura": 172.0,
      "temperatura": 36.5,
      "presionArterial": "145/95",
      "frecuenciaCardiaca": 78,
      "examenesSolicitados": "Electrocardiograma, Perfil lipídico",
      "medicamentosRecetados": "Enalapril 10mg (30 tabletas)",
      "observaciones": "Control en 15 días",
      "proximaCita": "2024-11-28",
      "estado": "ACTIVA",
      "tipoConsulta": "PRIMERA_VEZ",
      "pacienteNombre": "Juan Carlos Pérez",
      "medicoNombre": "Roberto Sánchez Torres",
      "createdAt": "2024-11-13 10:30:00",
      "updatedAt": "2024-11-13 10:30:00"
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

#### 2. Obtener Historia Clínica por ID
```http
GET /api/historias-clinicas/{id}
```

#### 3. Obtener Historias de un Paciente
```http
GET /api/historias-clinicas/paciente/{dni}?page=0&size=10
```

#### 4. Obtener Historias de un Médico
```http
GET /api/historias-clinicas/medico/{cmp}?page=0&size=10
```

#### 5. Obtener Historias por Rango de Fechas
```http
GET /api/historias-clinicas/fecha-rango?fechaInicio=2024-11-01T00:00:00&fechaFin=2024-11-30T23:59:59
```

**Response:** Array de historias (sin paginación)

#### 6. Obtener Historias de Paciente con Médico Específico
```http
GET /api/historias-clinicas/paciente/{dni}/medico/{cmp}
```

**Response:** Array de historias (sin paginación)

#### 7. Crear Historia Clínica
```http
POST /api/historias-clinicas
Content-Type: application/json

{
  "pacienteDni": "12345678",
  "medicoCmp": "CMP12345",
  "fechaAtencion": "2024-11-13T10:30:00",
  "diagnostico": "Hipertensión arterial leve",
  "analisis": "Paciente presenta presión arterial elevada",
  "tratamiento": "Enalapril 10mg cada 12 horas",
  "motivoConsulta": "Dolor de cabeza frecuente",
  "sintomas": "Cefalea, mareos ocasionales",
  "signosVitales": "PA: 145/95, FC: 78, Temp: 36.5°C",
  "peso": 78.5,
  "altura": 172.0,
  "temperatura": 36.5,
  "presionArterial": "145/95",
  "frecuenciaCardiaca": 78,
  "examenesSolicitados": "Electrocardiograma, Perfil lipídico",
  "medicamentosRecetados": "Enalapril 10mg (30 tabletas)",
  "observaciones": "Control en 15 días",
  "proximaCita": "2024-11-28",
  "estado": "ACTIVA",
  "tipoConsulta": "PRIMERA_VEZ"
}
```

#### 8. Actualizar Historia Clínica
```http
PUT /api/historias-clinicas/{id}
Content-Type: application/json
```

#### 9. Eliminar Historia Clínica
```http
DELETE /api/historias-clinicas/{id}
```

---

## 🎨 Modelos Dart para Flutter

### Paciente Model
```dart
class Paciente {
  final int? id;
  final String pacDni;
  final String nombre;
  final String apellidoPaterno;
  final String apellidoMaterno;
  final String direccion;
  final String telefono;
  final String? imagen;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  Paciente({
    this.id,
    required this.pacDni,
    required this.nombre,
    required this.apellidoPaterno,
    required this.apellidoMaterno,
    required this.direccion,
    required this.telefono,
    this.imagen,
    this.createdAt,
    this.updatedAt,
  });

  factory Paciente.fromJson(Map<String, dynamic> json) {
    return Paciente(
      id: json['id'],
      pacDni: json['pacDni'],
      nombre: json['nombre'],
      apellidoPaterno: json['apellidoPaterno'],
      apellidoMaterno: json['apellidoMaterno'],
      direccion: json['direccion'],
      telefono: json['telefono'],
      imagen: json['imagen'],
      createdAt: json['createdAt'] != null 
          ? DateTime.parse(json['createdAt']) 
          : null,
      updatedAt: json['updatedAt'] != null 
          ? DateTime.parse(json['updatedAt']) 
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'pacDni': pacDni,
      'nombre': nombre,
      'apellidoPaterno': apellidoPaterno,
      'apellidoMaterno': apellidoMaterno,
      'direccion': direccion,
      'telefono': telefono,
      'imagen': imagen,
    };
  }

  String get nombreCompleto => '$nombre $apellidoPaterno $apellidoMaterno';
}
```

### Médico Model
```dart
class Medico {
  final int? id;
  final String medCmp;
  final String nombre;
  final String apellidos;
  final String especialidad;
  final String dni;
  final String? email;
  final String? telefono;
  final String? direccion;
  final String? universidad;
  final int? aniosExperiencia;
  final String? horarioAtencion;
  final String? consultorio;
  final bool? activo;
  final String? imagen;
  final String? observaciones;
  final String? nombreCompleto;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  Medico({
    this.id,
    required this.medCmp,
    required this.nombre,
    required this.apellidos,
    required this.especialidad,
    required this.dni,
    this.email,
    this.telefono,
    this.direccion,
    this.universidad,
    this.aniosExperiencia,
    this.horarioAtencion,
    this.consultorio,
    this.activo,
    this.imagen,
    this.observaciones,
    this.nombreCompleto,
    this.createdAt,
    this.updatedAt,
  });

  factory Medico.fromJson(Map<String, dynamic> json) {
    return Medico(
      id: json['id'],
      medCmp: json['medCmp'],
      nombre: json['nombre'],
      apellidos: json['apellidos'],
      especialidad: json['especialidad'],
      dni: json['dni'],
      email: json['email'],
      telefono: json['telefono'],
      direccion: json['direccion'],
      universidad: json['universidad'],
      aniosExperiencia: json['aniosExperiencia'],
      horarioAtencion: json['horarioAtencion'],
      consultorio: json['consultorio'],
      activo: json['activo'],
      imagen: json['imagen'],
      observaciones: json['observaciones'],
      nombreCompleto: json['nombreCompleto'],
      createdAt: json['createdAt'] != null 
          ? DateTime.parse(json['createdAt']) 
          : null,
      updatedAt: json['updatedAt'] != null 
          ? DateTime.parse(json['updatedAt']) 
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'medCmp': medCmp,
      'nombre': nombre,
      'apellidos': apellidos,
      'especialidad': especialidad,
      'dni': dni,
      'email': email,
      'telefono': telefono,
      'direccion': direccion,
      'universidad': universidad,
      'aniosExperiencia': aniosExperiencia,
      'horarioAtencion': horarioAtencion,
      'consultorio': consultorio,
      'activo': activo,
      'imagen': imagen,
      'observaciones': observaciones,
    };
  }
}
```

### Historia Clínica Model
```dart
class HistoriaClinica {
  final int? id;
  final String pacienteDni;
  final String medicoCmp;
  final DateTime fechaAtencion;
  final String diagnostico;
  final String analisis;
  final String tratamiento;
  final String? motivoConsulta;
  final String? sintomas;
  final String? signosVitales;
  final double? peso;
  final double? altura;
  final double? temperatura;
  final String? presionArterial;
  final int? frecuenciaCardiaca;
  final String? examenesSolicitados;
  final String? medicamentosRecetados;
  final String? observaciones;
  final DateTime? proximaCita;
  final String? estado;
  final String? tipoConsulta;
  final String? pacienteNombre;
  final String? medicoNombre;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  HistoriaClinica({
    this.id,
    required this.pacienteDni,
    required this.medicoCmp,
    required this.fechaAtencion,
    required this.diagnostico,
    required this.analisis,
    required this.tratamiento,
    this.motivoConsulta,
    this.sintomas,
    this.signosVitales,
    this.peso,
    this.altura,
    this.temperatura,
    this.presionArterial,
    this.frecuenciaCardiaca,
    this.examenesSolicitados,
    this.medicamentosRecetados,
    this.observaciones,
    this.proximaCita,
    this.estado,
    this.tipoConsulta,
    this.pacienteNombre,
    this.medicoNombre,
    this.createdAt,
    this.updatedAt,
  });

  factory HistoriaClinica.fromJson(Map<String, dynamic> json) {
    return HistoriaClinica(
      id: json['id'],
      pacienteDni: json['pacienteDni'],
      medicoCmp: json['medicoCmp'],
      fechaAtencion: DateTime.parse(json['fechaAtencion']),
      diagnostico: json['diagnostico'],
      analisis: json['analisis'],
      tratamiento: json['tratamiento'],
      motivoConsulta: json['motivoConsulta'],
      sintomas: json['sintomas'],
      signosVitales: json['signosVitales'],
      peso: json['peso']?.toDouble(),
      altura: json['altura']?.toDouble(),
      temperatura: json['temperatura']?.toDouble(),
      presionArterial: json['presionArterial'],
      frecuenciaCardiaca: json['frecuenciaCardiaca'],
      examenesSolicitados: json['examenesSolicitados'],
      medicamentosRecetados: json['medicamentosRecetados'],
      observaciones: json['observaciones'],
      proximaCita: json['proximaCita'] != null 
          ? DateTime.parse(json['proximaCita']) 
          : null,
      estado: json['estado'],
      tipoConsulta: json['tipoConsulta'],
      pacienteNombre: json['pacienteNombre'],
      medicoNombre: json['medicoNombre'],
      createdAt: json['createdAt'] != null 
          ? DateTime.parse(json['createdAt']) 
          : null,
      updatedAt: json['updatedAt'] != null 
          ? DateTime.parse(json['updatedAt']) 
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'pacienteDni': pacienteDni,
      'medicoCmp': medicoCmp,
      'fechaAtencion': fechaAtencion.toIso8601String(),
      'diagnostico': diagnostico,
      'analisis': analisis,
      'tratamiento': tratamiento,
      'motivoConsulta': motivoConsulta,
      'sintomas': sintomas,
      'signosVitales': signosVitales,
      'peso': peso,
      'altura': altura,
      'temperatura': temperatura,
      'presionArterial': presionArterial,
      'frecuenciaCardiaca': frecuenciaCardiaca,
      'examenesSolicitados': examenesSolicitados,
      'medicamentosRecetados': medicamentosRecetados,
      'observaciones': observaciones,
      'proximaCita': proximaCita?.toIso8601String().split('T')[0],
      'estado': estado,
      'tipoConsulta': tipoConsulta,
    };
  }
}
```

---

## 🔧 Enums

### Estados de Historia Clínica
```dart
enum EstadoHistoria {
  ACTIVA,
  CERRADA,
  EN_SEGUIMIENTO
}
```

### Tipos de Consulta
```dart
enum TipoConsulta {
  PRIMERA_VEZ,
  CONTROL,
  EMERGENCIA,
  SEGUIMIENTO
}
```

---

## ⚠️ Manejo de Errores

### Estructura de Error
```json
{
  "error": "Recurso no encontrado",
  "message": "Paciente no encontrado con DNI: 12345678"
}
```

### Error de Validación
```json
{
  "error": "Error de validación",
  "message": "Error en los datos proporcionados",
  "details": {
    "pacDni": "El DNI debe tener 8 dígitos",
    "nombre": "El nombre es obligatorio"
  }
}
```

### Códigos de Estado HTTP
- `200 OK` - Operación exitosa
- `201 Created` - Recurso creado
- `204 No Content` - Eliminación exitosa
- `400 Bad Request` - Error de validación
- `404 Not Found` - Recurso no encontrado
- `409 Conflict` - Recurso duplicado
- `500 Internal Server Error` - Error del servidor

---

## 📦 Dependencias Flutter Recomendadas

```yaml
dependencies:
  flutter:
    sdk: flutter
  http: ^1.1.0              # Para peticiones HTTP
  provider: ^6.1.1          # State management
  intl: ^0.18.1             # Formateo de fechas
  shared_preferences: ^2.2.2 # Almacenamiento local
```

---

## 🚀 Ejemplo de Servicio API en Flutter

```dart
import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {
  static const String baseUrl = 'http://localhost:8080/api';
  
  // GET Pacientes
  Future<List<Paciente>> getPacientes({int page = 0, int size = 10}) async {
    final response = await http.get(
      Uri.parse('$baseUrl/pacientes?page=$page&size=$size'),
    );
    
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      final List content = data['content'];
      return content.map((json) => Paciente.fromJson(json)).toList();
    } else {
      throw Exception('Error al cargar pacientes');
    }
  }
  
  // POST Paciente
  Future<Paciente> createPaciente(Paciente paciente) async {
    final response = await http.post(
      Uri.parse('$baseUrl/pacientes'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(paciente.toJson()),
    );
    
    if (response.statusCode == 201) {
      return Paciente.fromJson(json.decode(response.body));
    } else {
      throw Exception('Error al crear paciente');
    }
  }
  
  // PUT Paciente
  Future<Paciente> updatePaciente(int id, Paciente paciente) async {
    final response = await http.put(
      Uri.parse('$baseUrl/pacientes/$id'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(paciente.toJson()),
    );
    
    if (response.statusCode == 200) {
      return Paciente.fromJson(json.decode(response.body));
    } else {
      throw Exception('Error al actualizar paciente');
    }
  }
  
  // DELETE Paciente
  Future<void> deletePaciente(int id) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/pacientes/$id'),
    );
    
    if (response.statusCode != 204) {
      throw Exception('Error al eliminar paciente');
    }
  }
}
```

---

## 📝 Notas Importantes

1. **CORS**: Si tienes problemas de CORS, agrega esta configuración en Spring Boot:
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
```

2. **Formato de Fechas**: 
   - `fechaAtencion`: `"yyyy-MM-ddTHH:mm:ss"` (ej: `"2024-11-13T10:30:00"`)
   - `proximaCita`: `"yyyy-MM-dd"` (ej: `"2024-11-28"`)

3. **Paginación**: Los endpoints que retornan listas paginadas incluyen:
   - `content`: Array de elementos
   - `totalElements`: Total de registros
   - `totalPages`: Total de páginas
   - `pageable.pageNumber`: Página actual
   - `pageable.pageSize`: Tamaño de página

4. **Validaciones**: Todos los campos marcados como `@NotBlank` o `@NotNull` son obligatorios

---

## 🎯 Swagger UI

Documentación interactiva disponible en:
```
http://localhost:8080/api/swagger-ui.html
```

Aquí puedes probar todos los endpoints directamente desde el navegador.
