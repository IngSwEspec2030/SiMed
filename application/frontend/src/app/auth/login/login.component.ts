import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';
import { LoginService } from 'src/app/services/login.service';
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';
import { first } from 'rxjs/internal/operators/first';
import { userReturned } from 'src/app/dto/userReturn';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  alert: DisplayMessage; 
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private alertService: AlertService,
    private authService:AuthService
  ) {
    this.alert = new DisplayMessage();

  }


  ngOnInit(): void {

    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]

    });

  }
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.loginService.login(this.registerForm.value)
      .pipe(first())
      .subscribe(
        (data:userReturned) => {
          this.authService.setUser(data);//inscripción de datos en servicio de autorización
          this.router.navigate(['/site']);
        },
        error => {
          this.alert.displayInfoMessage('Usuario','Las credenciales ingresadas no son válidas', IconType.error, ButtonType.Ok);

          //this.alertService.error('No se ha registrado correctamente', true);
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
