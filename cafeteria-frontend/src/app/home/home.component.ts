import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

import { UsuarioService } from '../services/usuario.service';
import { ProductoService } from '../services/producto.service';

import { Usuario } from '../models/usuario.model';
import { Producto } from '../models/producto.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  // 👤 Empleados
  empleados: Usuario[] = [];

  // 🛒 Productos
  productos: Producto[] = [];

  constructor(
    private usuarioService: UsuarioService,
    private productoService: ProductoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarEmpleados();
    this.cargarProductos(); // 👈 NUEVO
  }

  // =========================
  // EMPLEADOS
  // =========================
  cargarEmpleados() {
    this.usuarioService.getAllEmpleados().subscribe({
      next: (data) => {
        this.empleados = data;
        console.log('Empleados cargados:', data);
      },
      error: (e) => {
        console.error('Error cargando empleados:', e);
        alert('Error de conexión con Java (Empleados)');
      }
    });
  }

  // =========================
  // PRODUCTOS
  // =========================
  cargarProductos() {
    this.productoService.getAllProductos().subscribe({
      next: (data) => {
        this.productos = data;
        console.log('Productos cargados:', data);
      },
      error: (e) => {
        console.error('Error cargando productos:', e);
        alert('Error de conexión con Java (Productos)');
      }
    });
  }

  // =========================
  // LOGOUT
  // =========================
  cerrarSesion() {
    console.log('¡Click detectado! Cerrando sesión...');
    this.usuarioService.logout();
    this.router.navigate(['login'])
      .then(() => console.log('Navegación exitosa'))
      .catch(err => console.error('Error al navegar:', err));
  }
}
