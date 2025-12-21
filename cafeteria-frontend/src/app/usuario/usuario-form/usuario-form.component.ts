import { Component, OnInit } from '@angular/core'; // <--- Agrega OnInit
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { Router, RouterModule, ActivatedRoute } from '@angular/router'; // <--- Agrega ActivatedRoute

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.scss'
})
export class UsuarioFormComponent implements OnInit { // <--- Implementa OnInit

  nuevoUsuario: any = {
    nombre: '',
    apellidos: '',
    dni: '',
    email: '',
    celular: '',
    fechaNacimiento: '',
    password: '',
    roles: [1]
  };

  usuarioId: number | null = null; // Variable para saber si estamos editando

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute // <--- Inyectamos esto para leer la URL
  ) {}

  ngOnInit(): void {
    // Leemos el parámetro 'id' de la URL (si existe)
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      // MODO EDICIÓN
      this.usuarioId = +id; // El '+' convierte el string a numero
      console.log('Modo Edición detectado. ID:', this.usuarioId);

      // Cargamos los datos del empleado en el formulario
      this.usuarioService.getEmpleadoById(this.usuarioId).subscribe({
        next: (data) => {
          this.nuevoUsuario = data;
          // Ojo: La contraseña suele venir nula o encriptada,
          // a veces es mejor limpiarla para no re-enviarla si no se cambia
          this.nuevoUsuario.password = '';
        },
        error: (err) => console.error('Error cargando empleado', err)
      });
    } else {
      console.log('Modo Creación');
    }
  }

  guardar() {
    // 1. Validaciones básicas de formulario
    if (!this.nuevoUsuario.dni || this.nuevoUsuario.dni.length !== 8) {
      alert("El DNI debe tener exactamente 8 dígitos.");
      return;
    }

    // 2. PREPARAR LOS DATOS (Mapeo Manual)
    // Creamos un objeto que coincida EXACTAMENTE con tu UsuarioUpdateRequestDto
    const datosParaEnviar = {
      nombre: this.nuevoUsuario.nombre,
      apellidos: this.nuevoUsuario.apellidos,
      dni: this.nuevoUsuario.dni,
      email: this.nuevoUsuario.email,

      // TRUCO 1: Si el celular está vacío, enviamos null (para que pase la validación @Size)
      celular: this.nuevoUsuario.celular ? this.nuevoUsuario.celular : null,

      fechaNacimiento: this.nuevoUsuario.fechaNacimiento,

      // TRUCO 2: Extraemos el ID del Rol de la lista de roles
      // Si nuevoUsuario.roles es una lista de objetos, tomamos el id del primero.
      // Si no hay roles, mandamos 1 (default) o 2 (empleado)
      rolId: (this.nuevoUsuario.roles && this.nuevoUsuario.roles.length > 0)
        ? (this.nuevoUsuario.roles[0].rolId || this.nuevoUsuario.roles[0].id || 1)
        : 1,

      estado: this.nuevoUsuario.estado || 'ACTIVO',

      // Enviamos la password solo si el usuario escribió algo
      password: this.nuevoUsuario.password || null
    };

    console.log('Enviando DTO limpio:', datosParaEnviar);

    if (this.usuarioId) {
      // --- ACTUALIZAR ---
      this.usuarioService.actualizarEmpleado(this.usuarioId, datosParaEnviar).subscribe({
        next: () => {
          alert('¡Empleado actualizado con éxito!');
          this.router.navigate(['/home']);
        },
        error: (e: any) => {
          console.error('Error al actualizar:', e);
          // Muestra los errores de validación si vienen del backend
          if(e.error && e.error.errors) {
            const mensajes = Object.values(e.error.errors).join('\n');
            alert('Errores de validación:\n' + mensajes);
          } else {
            alert('Error al actualizar: ' + (e.error?.message || 'Revisa la consola'));
          }
        }
      });

    } else {
      // --- CREAR ---
      // Para crear usamos la misma lógica o el objeto original si el DTO de crear es diferente
      this.usuarioService.registrarEmpleado(this.nuevoUsuario).subscribe({
        next: () => {
          alert('¡Empleado registrado con éxito!');
          this.router.navigate(['/home']);
        },
        error: (e: any) => alert('Error: ' + e.message)
      });
    }
  }
}
