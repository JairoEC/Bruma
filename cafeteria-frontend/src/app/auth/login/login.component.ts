import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // <--- Importante para los inputs
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule], // <--- Agregamos los módulos necesarios
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(private router: Router) { }

  login() {
    console.log('Datos capturados:', this.username, this.password);

    // Prueba rápida de simulación
    if(this.username === '12345678' && this.password === '123456') {
      alert("¡LOGIN EXITOSO! 🎉 Bienvenido Admin");
    } else {
      alert("Error: Credenciales incorrectas ❌");
    }
  }
}
