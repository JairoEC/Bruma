import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { UsuarioFormComponent} from './usuario/usuario-form/usuario-form.component';
import {MesasComponent} from './mesas/mesas.component';
import { authGuard } from './interceptor/auth.guard';
import { MainLayoutComponent} from './layout/main-layout/main-layout.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  // RUTAS PROTEGIDAS CON LAYOUT
  {
    path: '',
    component: MainLayoutComponent, // El Layout envuelve a los de abajo
    canActivate: [authGuard],
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'mesas', component: MesasComponent },
      { path: 'empleado/form', component: UsuarioFormComponent },
      { path: 'empleado/form/:id', component: UsuarioFormComponent },
    ]
  },

  { path: '**', redirectTo: 'login' } // Manejo de rutas no encontradas
];
