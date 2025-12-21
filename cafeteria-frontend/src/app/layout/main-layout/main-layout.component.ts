import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.scss'
})
export class MainLayoutComponent {

  menuAbierto = false; // Variable para controlar el sándwich

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
  }

  // Al hacer clic en un link, cerramos el menú (buena práctica en móviles)
  cerrarMenu() {
    this.menuAbierto = false;
  }

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  onLogout() {
    this.usuarioService.logout();
    this.router.navigate(['/login']);
  }
}
