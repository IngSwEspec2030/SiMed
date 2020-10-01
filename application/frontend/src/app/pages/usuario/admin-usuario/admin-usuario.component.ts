import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Eps } from 'src/app/dto/eps';
import { TipoIdentificacion } from 'src/app/dto/tipo_identificacion';
import { TipoUsuario } from 'src/app/dto/tipo_usuario';
import { Usuario } from 'src/app/dto/usuario';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ConfigService } from 'src/app/services/config.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ButtonType, DisplayMessage, IconType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-admin-usuario',
  templateUrl: './admin-usuario.component.html',
  styleUrls: ['./admin-usuario.component.css']
})
export class AdminUsuarioComponent implements OnInit {

  currentUser:Usuario;
  message = new DisplayMessage();
  editForm:FormGroup;
  submitted:boolean=false;
  loading:boolean;
  epsList: Eps[]=[];
  public tipoDocumento:TipoIdentificacion[] = [
    {
      idTipoIdentificacion: 1,
      tipoIdentificacion:1,
      nombreTipoIdentificacion: "CC",
    },
    {
      idTipoIdentificacion: 2,
      tipoIdentificacion:2,
      nombreTipoIdentificacion: "CE"
    },
  ]
  constructor(    
    private formBuilder: FormBuilder,
    private http: UtilHttpService,
    private config: ConfigService,
    private auth:AuthService) { }

  ngOnInit(): void {
    this.currentUser=this.auth.getUserRegistered();
    console.log('currentUser', this.currentUser );
    
    this.onBuildForm();
    this.getEps();
  }  

  getEps() {
    this.epsList.splice(0,this.epsList.length)
    this.http.showBusy();
    this.http.get(this.config.prop.urllistAllEps, null).subscribe((resp:Eps[]) => {
      resp.map(element=>{
        if(element.estadoEps==true){
          this.epsList.push(element);
        }
      });
      this.http.closeBusy();
    }, error => {
      this.http.closeBusy();
    })
  }

  onBuildForm(){
    this.editForm = this.formBuilder.group({
      nombreUsuario: ['', Validators.required],
      apellidosUsuario: ['', Validators.required],
      numeroIdentificacionUsuario: ['', Validators.required],
      correoUsuario: ['', [Validators.required, Validators.email]],
      tipoIdentificacion: ['', [Validators.required]],
      username: [],
      idEps: ['', [Validators.required]],
      idTipoUsuario: 3
    })

    this.onLoadInfo();
  }

    // convenience getter for easy access to form fields
    get f() { return this.editForm.controls; }

  onLoadInfo(){
    this.editForm.setValue({
      nombreUsuario:this.currentUser.nombreUsuario,
      apellidosUsuario:this.currentUser.apellidosUsuario,
      numeroIdentificacionUsuario:this.currentUser.numeroIdentificacionUsuario,
      correoUsuario:this.currentUser.correoUsuario,
      tipoIdentificacion:this.currentUser.tipoIdentificacion.tipoIdentificacion,
      username:this.currentUser.username,
      idEps:this.currentUser.eps.idEps,
      idTipoUsuario:this.currentUser.tipoUsuario,
    })
  }

  /**
   * Actualiza la información
   */
  onConfirm(){

    this.submitted = true;
    if (this.editForm.invalid) {
      return;
    }
    let user = this.obtenerObjetoUsuario();
    this.http.put(this.config.prop.urlUsuario,user, this.currentUser.idUsuario)
    .subscribe(result=>{
      this.auth.updateUsuario(user)
      this.message.displayInfoMessage('Usuario', 'Se ha actualizado correctamente', IconType.info, ButtonType.Ok);
    },  error => {
      console.error(error);      
      this.message.displayInfoMessage('Usuario', 'Error al intentar actualizar la información', IconType.error, ButtonType.Ok);
    });
  }

  onNoClick(){
    this.submitted=false;
    this.editForm.reset();
    this.ngOnInit();
  }

  obtenerObjetoUsuario() : Usuario{
    let usuario:Usuario = {
      idUsuario:this.currentUser.idUsuario,
      username:this.currentUser.username,
      nombreUsuario:this.editForm.get("nombreUsuario").value,
      apellidosUsuario:this.editForm.get("apellidosUsuario").value,
      numeroIdentificacionUsuario:this.editForm.get("numeroIdentificacionUsuario").value,
      correoUsuario:this.editForm.get("correoUsuario").value
    }

    if(this.editForm.get("idEps").value!=this.currentUser.eps.idEps){
      let eps = new Eps();
      eps.idEps=this.editForm.get("idEps").value;
      usuario.eps=eps;
    }

    if(this.editForm.get("tipoIdentificacion").value!=this.currentUser.tipoIdentificacion.tipoIdentificacion){
      let tipoIdentificacion = new TipoIdentificacion();
      tipoIdentificacion.tipoIdentificacion=this.editForm.get("tipoIdentificacion").value
      usuario.tipoIdentificacion=tipoIdentificacion;
    }

    return usuario;
  }

}
