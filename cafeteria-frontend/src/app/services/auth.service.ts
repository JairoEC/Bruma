import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, delay } from 'rxjs/operators';
import {Usuario} from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/auth/login';

  private autenticado = false;
  private usuario = '';
  private clave = '';

  constructor(private http: HttpClient) { }

  //Metodo login
  login(usuario: string, clave: string): Observable<any>{
    // En lugar de llamar a http.post, devolvemos un objeto "fake" usando 'of'
    // El 'delay(1000)' hace que parezca que piensa por 1 segundo (opcional)
    return of({ mensaje: 'Login Simulado Exitoso', token: 'fake-token-123' }).pipe(
      delay(500),
      tap(() => {
        // Guardamos los datos como si hubiera funcionado de verdad
        this.autenticado = true;
        this.usuario = usuario;
        this.clave = clave;
      })
    );
  }

  // Método Logout
  logout(): void {
    this.autenticado = false;
    this.usuario = '';
    this.clave = '';
  }

  estadoAutenticado(): boolean {
    return this.autenticado;
  }

  // Generar cabecera
  getAuthHeader(): string{
    return 'Basic ' + btoa(`${this.usuario}:${this.clave}`);
  }

  // Obtener un solo empleado por su ID (para rellenar el formulario)
  getEmpleadoById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${id}`);
  }

  // Actualizar un empleado existente
  actualizarEmpleado(id: number, usuario: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, usuario);
  }

}
