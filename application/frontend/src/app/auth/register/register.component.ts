import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators,  ReactiveFormsModule } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';
import { UserService } from 'src/app/services/usuario.service';
import { first } from 'rxjs/internal/operators/first';
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  alert: DisplayMessage; 
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService
  ) {
    this.alert = new DisplayMessage();

  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      nombreUsuario: ['', Validators.required],
      apellidosUsuario: ['', Validators.required],
      numeroIdentificacionUsuario: ['', Validators.required],
      correoUsuario: ['', [Validators.required, Validators.email]],
      passwordUsuario: ['', [Validators.required, Validators.minLength(6)]],
      tipoIdentificacion: ['', [Validators.required, Validators.required]],
      idEps: ['', [Validators.required, Validators.required]],
      estadoUsuario: 1
    });


  }

  // convenience getter for easy access to form fields
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
    this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data)
          this.alert.displayInfoMessage('Usuario','Se ha registrado correctamente', IconType.info, ButtonType.Ok);
          //this.alertService.success('Se ha registrado correctamente', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alert.displayInfoMessage('Usuario','No se ha registrado correctamente', IconType.error, ButtonType.Ok);

          //this.alertService.error('No se ha registrado correctamente', true);
          this.alertService.error(error);
          this.loading = false;
        });
  }

}
