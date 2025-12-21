import { Component, OnInit } from '@angular/core';
import {CommonModule} from '@angular/common';

import { UsuarioService } from '../services/usuario.service';
import { Usuario } from '../models/usuario.model'
import {Router, RouterModule} from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  // Aquí guardaremos la lista que viene de Java
  empleados: Usuario[] = [];

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  // Este método se ejecuta automáticamente al iniciar la página
  ngOnInit(): void {
    this.cargarEmpleados();
  }

  cargarEmpleados() {
    this.usuarioService.getAllEmpleados().subscribe({
      next: (data) => {
        this.empleados = data;
        console.log('Datos cargados:', data); // Para ver en la consola si llegaron
      },
      error: (e) => {
        console.error('Error cargando datos:', e);
        alert('Error de conexión con Java');
      }
    });
  }

  cerrarSesion(){
    console.log('¡Click detectado! Cerrando sesión...');
    this.usuarioService.logout();
    this.router.navigate(['login'])
      .then(() => console.log('Navegación exitosa'))
      .catch(err => console.error('Error al navegar:', err)); // <--- Y ESTO
  }
}
