package org.example.Handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.example.repositorios.RepositorioNotificaciones;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GetUploadCSV implements  Handler {
  @Override
  public void handle(@NotNull Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    RepositorioNotificaciones repositorioNotificaciones = RepositorioNotificaciones.getInstance();
    String usuarioAVerificar = context.sessionAttribute("username");
    if (repositorioNotificaciones.obtenerNotificacionesActivasXUsuario(usuarioAVerificar).isEmpty()){
      model.put("notificacionActiva","<i class=\"bi bi-bell\"></i>");
    }else {
      model.put("notificacionActiva","<i class=\"bi bi-bell-fill\"></i>");
    }
    String tipoPersona = context.sessionAttribute("tipo_persona");
    model.put("tipoPersona",tipoPersona);

    context.render("/templates/uploadCSV.mustache",model);
  }
}
