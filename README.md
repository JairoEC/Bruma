# Bruma 

Bruma es una aplicación web diseñada para optimizar la atención en cafeterías, facilitando la generación, envío y gestión de pedidos directamente hacia el área de cocina en tiempo real.

## Características

- **Gestión de Pedidos**: Toma de órdenes rápida desde las mesas.
- **Panel de Cocina**: Visualización y cambio de estado de pedidos en tiempo real.
- **Seguridad**: Autenticación y autorización basada en roles (Mesero, Cocinero, Administrador).

## Tecnologías Utilizadas

### Backend
- **Java** (Versión 17 o superior)
- **Spring Boot** (Core, Web)
- **Spring Security** & **JWT (JSON Web Tokens)** (Seguridad y sesiones)
- **Spring Data JPA** (Persistencia de datos)

### Frontend
- **Angular** (Framework SPA)
- **TypeScript**, HTML y CSS

### Base de Datos & Herramientas
- **MySQL** (Motor de base de datos relacional)
- **Maven** (Gestor de dependencias backend)
- **npm** (Gestor de dependencias frontend)

## Configuración e Instalación

### Requisitos Previos
Asegúrate de tener instalado:
- JDK 17 o superior
- Node.js (v18+) y Angular CLI
- MySQL Server

### 1. Base de Datos
1. Crea una base de datos en MySQL llamada `cafeteria_bd`.
2. Configura tus credenciales en el archivo `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bruma_db
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_CONTRASEÑA
   ```

### 2. Levantar el Backend (Spring Boot)
1. Navega a la carpeta raiz del proyecto:

2. Ejecuta la aplicación con Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

### 3. Levantar el Frontend (Angular)
1. Navega a la carpeta raiz del proyecto:
   ```bash
   cd cafeteria-frontend
   ```
2. Instala las dependencias:
   ```bash
   npm install
   ```
3. Inicia el servidor de desarrollo:
   ```bash
   ng serve
   ```
4. Abre tu navegador en `http://localhost:4200`.

## Seguridad
La API está protegida con **Spring Security** y **JWT**. Para realizar peticiones a los endpoints protegidos, debes incluir el token en las cabeceras:
`Authorization: Bearer <tu_token_jwt>`

## Contribuciones
Las contribuciones son bienvenidas. Si deseas mejorar Bruma, por favor abre un Pull Request o reporta un Issue.
