import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router'; // Importante Router
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  credenciales = {
    email: '',
    password: ''
  };

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  login() {
    console.log('Intentando loguear...', this.credenciales);

    this.usuarioService.login(this.credenciales).subscribe({
      next: (resp: any) => {
        console.log('Login exitoso. Token recibido:', resp.token);

        // 1. GUARDAR EL TOKEN (La llave maestra)
        localStorage.setItem('token', resp.token);

        // 2. REDIRIGIR AL HOME
        this.router.navigate(['/home']);
      },
      error: (e: any) => {
        console.error('Error de login:', e);
        alert('Credenciales incorrectas o error en el servidor.');
      }
    });
  }
}
