import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';
import { UserService } from 'src/app/services/usuario.service';
import { first } from 'rxjs/internal/operators/first';
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';
import { Eps } from 'src/app/dto/eps';
import { TipoIdentificacion } from 'src/app/dto/tipo_identificacion';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ConfigService } from 'src/app/services/config.service';
import { TipoUsuario } from 'src/app/dto/tipo_usuario';
import { Usuario } from 'src/app/dto/usuario';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  epsList: any = []
/*
  epsList: any = [
    {
      "idEps": "1",
      "nombreEps": "Sanitas"
    },
    {
      "idEps": "2",
      "nombreEps": "Salud Total"
    },
    {
      "idEps": "1",
      "nombreEps": "Nueva Eps"
    }]
* */
  tipoDocumento = [
    {
      "idTipoDocumento": 1,
      "tipoDocumento": "CC"
    },
    {
      "idTipoDocumento": 2,
      "tipoDocumento": "CE"
    },
  ]
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  alert: DisplayMessage;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private http: UtilHttpService,
    private userService: UserService,
    private alertService: AlertService,
    private config: ConfigService,
  ) {
    this.alert = new DisplayMessage();

  }

  ngOnInit(): void {
    console.log("Entró a Registrate");
    this.getEps();
    this.registerForm = this.formBuilder.group({
      nombreUsuario: ['', Validators.required],
      apellidosUsuario: ['', Validators.required],
      numeroIdentificacionUsuario: ['', Validators.required],
      correoUsuario: ['', [Validators.required, Validators.email]],
      passwordUsuario: ['', [Validators.required, Validators.minLength(6)]],
      tipoIdentificacion: ['', [Validators.required, Validators.required]],
      username: ['', [Validators.required, Validators.required]],
      idEps: ['', [Validators.required, Validators.required]],
      estadoUsuario: true,
      idTipoUsuario: 3
    });


    


  }

  getEps() {
    console.log("Entro a obtener EPS: "+this.config.prop.urllistAllEps);
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEps, null).subscribe((resp) => {
      console.log("EPS Recibidas: ", resp);
      this.epsList = resp;
      this.http.closeBusy();
    }, error => {
      
      console.error("Error EPS: "+error);
      this.http.closeBusy();
    })
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  crearObjetoUsuario() : Usuario{
    let eps = new Eps();
    eps.idEps=this.registerForm.get("idEps").value
    let tipoIdentificacion = new TipoIdentificacion();
    tipoIdentificacion.tipoIdentificacion=this.registerForm.get("tipoIdentificacion").value
    let tipoUsuario = new TipoUsuario();
    tipoUsuario.idTipoUsuario=this.registerForm.get("idTipoUsuario").value

    let usuario:Usuario = {
      nombreUsuario:this.registerForm.get("nombreUsuario").value,
      apellidosUsuario:this.registerForm.get("apellidosUsuario").value,
      numeroIdentificacionUsuario:this.registerForm.get("numeroIdentificacionUsuario").value,
      correoUsuario:this.registerForm.get("correoUsuario").value,
      passwordUsuario:this.registerForm.get("passwordUsuario").value,
      username:this.registerForm.get("username").value,      
      eps:eps,
      tipoUsuario:tipoUsuario,
      tipoIdentificacion:tipoIdentificacion,
      estadoUsuario: true
    }
    return usuario;
  }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.userService.register(this.crearObjetoUsuario())
      .pipe(first())
      .subscribe(
        data => {
          console.log(data)
          this.alert.displayInfoMessage('Usuario', 'Se ha registrado correctamente', IconType.info, ButtonType.Ok);
          //this.alertService.success('Se ha registrado correctamente', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alert.displayInfoMessage('Usuario', 'No se ha registrado correctamente', IconType.error, ButtonType.Ok);

          //this.alertService.error('No se ha registrado correctamente', true);
          this.alertService.error(error);
          this.loading = false;
        });
  }

}
