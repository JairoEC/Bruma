import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // 1. Obtener el token
  const token = localStorage.getItem('token');

  // 2. Clonar y agregar cabecera si existe el token
  if (token) {
    const cloneRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloneRequest);
  }

  // 3. Pasar sin tocar si no hay token
  return next(req);
};
