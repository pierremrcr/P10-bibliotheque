package com.bibliotheque.batch.mail;

import livres.wsdl.LivreType;
import livres.wsdl.MembreType;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
public class MessagesMail {

    public String textMailRelance(String text){

        return  "<html>"
                + "<body>"
                + "<h1>Demande de restitution d'un livre</h1>"
                + "<hr/>"
                + "<div id=\"conteneur\" style=\" display:flex; width:100%; margin:auto\">"
                +                ""+ text + ""
                + "</div>"
                + "<hr/>"
                + "<div style=\"margin:auto; text-align:center; width:70%\">"
                + "<h4><a href=\"http://localhost:8080/\">Bibliothèque de La Rochelle</a></h4>"
                + "<small>Adresse : 7 Avenue Michel Crépeau, 17000 La Rochelle</small></br>"
                + "<small>La bibliothéque est ouverte du lundi au samedi de 9h00 à 18h00</small></br>"
                + "<small>Téléphone : 05 17 67 58 27</small></br>"
                + "<small>Email : mediathequedelarochelle@gmail.com</small></br>"
                + "</div>"
                + "</body>"
                + "</html>";

    }

    public String textMailLivreDisponible(Date dateLivreDisponible, MembreType membreType, LivreType livreType){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return textMail().get(0)
                + "<div style=\"padding-left: 20px\">"
                + "<p>Bonjour, " + membreType.getPrenom() + "</p>"
                + "<p>Votre livre est disponible !</p>"
                + "<p>Vous pouvez vous présenter à l'accueil de la bibliothèque pour venir le récupérer !</p>"
                + "<p>Titre de l'ouvrage :  " + livreType.getTitre() + "</p>"
                + "<p>Rappel : vous avez 48 heures pour venir récupérer votre livre, passé ce délai votre réservation sera annulé.</p>"
                + "</div>"
                + textMail().get(1);
    }


    public String textMailDelaiExpirer(MembreType membreType, LivreType livreType) {

        return textMail().get(0)
                + "<div style=\"padding-left: 20px\">"
                + "<p>Bonjour, " + membreType.getPrenom() + "</p>"
                + "<p>Nous vous informons que votre réservation a expiré le délai de 48h00 pour venir retirer votre livre en bibliothèque.</p>"
                + "<p>Vous pouvez faire une nouvelle demande de réservation. Vous serez averti par mail lorsque l'ouvrage sera à nouveau disponible.</p>"
                + "<p>Titre de l'ouvrage :  " + livreType.getTitre() + "</p>"
                + "</div>"
                + textMail().get(1);
    }


    public List<String> textMail(){

        Properties properties = new Properties();
        InputStream stream = null;
        List<String> pathMessage = new ArrayList<>();

        try{
            String filename = "messagesMail.properties";
            stream = getClass().getClassLoader().getResourceAsStream(filename);
            properties.load(stream);
            pathMessage.add(properties.getProperty("mailPartie1"));
            pathMessage.add(properties.getProperty("adresse_bibliothèque"));
        } catch (Exception pEX){
            pEX.printStackTrace();
        }

        return pathMessage;
    }


}
