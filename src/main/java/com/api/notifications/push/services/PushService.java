package com.api.notifications.push.services;

import com.api.notifications.push.utils.JWTUtil;
import dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushService {

    @Autowired
    private JWTUtil jwtUtil;

    public void send(NotificationDTO notification) throws ErrorService{
        boolean validado = validaToken(notification);
        if (validado) {
            formateaPayload(notification);
            registraEnvio(notification);
        } else throw new ErrorService("Token de dispositivo inválido");
    }

    private void formateaPayload(NotificationDTO notificacion) {
        String nuevoToken = jwtUtil.create("nuevoId", "nuevoSubject");
        notificacion.setTokenDevice(nuevoToken);
    }

    private void registraEnvio(NotificationDTO notificacion) {
        notificacion.setSendState(true);
    }

    public boolean validaToken(NotificationDTO notificacion) {
        if (notificacion.getTokenDevice() != null) { //solo valida que el token no sea nulo
            return true;
        } else {
            return false;
        }
    }
}
