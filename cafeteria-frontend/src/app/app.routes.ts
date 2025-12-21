// app.routes.ts
import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { UsuarioFormComponent } from './usuario/usuario-form/usuario-form.component';
import { MesasComponent } from './mesas/mesas.component';
import { authGuard } from './interceptor/auth.guard';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import {ProductoFormComponent} from './producto/producto-form/producto-form.component.spec';
import {ProductosComponent} from './producto/productos.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  // 🔐 RUTAS PROTEGIDAS CON LAYOUT
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'mesas', component: MesasComponent },

      { path: 'productos', component: ProductosComponent },
      // 👤 EMPLEADOS (como ya lo tienes)
      { path: 'empleado/form', component: UsuarioFormComponent },
      { path: 'empleado/form/:id', component: UsuarioFormComponent },

      // 🛒 PRODUCTOS (MISMO PATRÓN)
      { path: 'producto/form', component: ProductoFormComponent },
      { path: 'producto/form/:id', component: ProductoFormComponent },
    ]
  },

  { path: '**', redirectTo: 'login' }
];
