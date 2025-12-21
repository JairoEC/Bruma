import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { ProductoService } from '../../services/producto.service';
import { Producto, CATEGORIAS_PRODUCTO } from '../../models/producto.model';

@Component({
  selector: 'app-producto-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './producto-form.component.html',
  styleUrl: './producto-form.component.scss'
})
export class ProductoFormComponent implements OnInit {

  // 🔹 Objeto del formulario
  nuevoProducto: Producto = {
    id: 0,
    nombre: '',
    categoria: '',
    precio: 0,
    estado: 'ACTIVO'
  };

  categorias = CATEGORIAS_PRODUCTO;

  productoId: number | null = null; // Saber si editamos

  constructor(
    private productoService: ProductoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      // 🟡 MODO EDICIÓN
      this.productoId = +id;
      console.log('Modo Edición Producto. ID:', this.productoId);

      this.productoService.getProductoById(this.productoId).subscribe({
        next: (data) => {
          this.nuevoProducto = data;
        },
        error: (err) => console.error('Error cargando producto', err)
      });
    } else {
      console.log('Modo Creación Producto');
    }
  }

  guardar() {
    if (!this.nuevoProducto.nombre.trim()) {
      alert('El nombre es obligatorio');
      return;
    }

    if (this.nuevoProducto.precio <= 0) {
      alert('El precio debe ser mayor a 0');
      return;
    }

    // 🧼 DTO LIMPIO (igual que en usuarios)
    const datosParaEnviar = {
      nombre: this.nuevoProducto.nombre,
      categoria: this.nuevoProducto.categoria,
      precio: this.nuevoProducto.precio,
      estado: this.nuevoProducto.estado || 'ACTIVO'
    };

    console.log('Enviando Producto:', datosParaEnviar);

    if (this.productoId) {
      // 🟠 ACTUALIZAR
      this.productoService.actualizarProducto(this.productoId, datosParaEnviar).subscribe({
        next: () => {
          alert('¡Producto actualizado con éxito!');
          this.router.navigate(['/productos']);
        },
        error: (e) => {
          console.error('Error al actualizar', e);
          alert('Error al actualizar producto');
        }
      });
    } else {
      // 🟢 CREAR
      this.productoService.registrarProducto(datosParaEnviar).subscribe({
        next: () => {
          alert('¡Producto registrado con éxito!');
          this.router.navigate(['/productos']);
        },
        error: (e) => {
          console.error('Error al registrar', e);
          alert('Error al registrar producto');
        }
      });
    }
  }

  cancelar() {
    this.router.navigate(['/productos']);
  }
}
