import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

export const authGuard: CanActivateFn = (route, state) => {
  const usuarioService = inject(UsuarioService);
  const router = inject(Router);

  // Verificamos si el usuario tiene el token en el localStorage
  if (usuarioService.estarlogueado()) {
    return true; // Si está logueado, lo deja pasar
  } else {
    // Si no tiene token, lo manda de patitas a la calle (al login)
    router.navigate(['/login']);
    return false;
  }
};
