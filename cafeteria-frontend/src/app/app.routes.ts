import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirigir raíz a login
  { path: 'login', component: LoginComponent }           // Ruta del login
];
