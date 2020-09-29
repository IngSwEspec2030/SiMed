import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';
import { Permisos, PermissionCheckerService } from './permission-checker.service';


@Injectable({
    providedIn:'root'
})
export class AuthRouteGuard implements CanActivate{

    /**
     *
     */
    constructor(
        private authService:AuthService,
        private permission:PermissionCheckerService,
        private router:Router
          ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
       if(this.authService.isAuthenticated()){
           this.authService.logout();
           return false;
       }

       if(this.permission.isGranted(route.data['permission'])){
           return true;
       }

       if(!route.data || !route.data['permission']){
           return true;
       }

       this.router.navigate([this.selectDefaultRoute()]);
    }//Fin canActivate

    canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.canActivate(route,state);
    }

    selectDefaultRoute(): string {
        if(this.permission.isGranted(Permisos.ConsultarLugaresCercanos)){
            return '/site';
        }

        return '/login';
    }
}