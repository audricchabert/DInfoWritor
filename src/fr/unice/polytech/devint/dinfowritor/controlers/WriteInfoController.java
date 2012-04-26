package fr.unice.polytech.devint.dinfowritor.controlers;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import fr.unice.polytech.devint.dinfowritor.models.GameState;
import fr.unice.polytech.devint.dinfowritor.views.FillInfosView;

@SuppressWarnings("serial")
public class WriteInfoController extends JFrame {

    private FillInfosView fiw;

    public WriteInfoController() {
        this.fiw = new FillInfosView(this);
        this.init();
    }

    public void init() {
        this.setContentPane(fiw);

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
                .getScreenSize();

        this.setMinimumSize(new Dimension(1024, 768));
        this.setPreferredSize(new Dimension(1024, 768));
        this.setMaximumSize(screenSize);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setLocation((screenSize.width - this.getWidth()) / 2,
                (screenSize.height - this.getHeight()) / 2);

        this.setVisible(true);
    }

    public void generate(String year, String title, ArrayList<String> authors,
            ArrayList<String> categories, String shortDescription,
            String dpublic, String age, ArrayList<String> notes, String gamePlay,
            String gameRules) {
        Element xmlRoot = new Element("infos");
        Element xmlYear = new Element("year");
        Element xmlCategories = new Element("gamecategories");
        Element xmlShortDescription = new Element("shortdescription");
        Element xmlPublic = new Element("public");
        Element xmlGameState = new Element("gamestate");
        Element xmlAge = new Element("age");
        Element xmlTitle = new Element("title");
        Element xmlAuthors = new Element("authors");
        Element xmlNotes = new Element("notes");
        Element xmlGameplay = new Element("gameplay");
        Element xmlGamerules = new Element("gamerules");

        xmlYear.addContent(new CDATA(year));
        for (String category : categories) {
            Element xmlCategory = new Element("gamecategory");
            xmlCategory.addContent(new CDATA(category));
            xmlCategories.addContent(xmlCategory);
        }
        xmlShortDescription.addContent(new CDATA(shortDescription));
        xmlPublic.addContent(new CDATA(dpublic));
        xmlGameState.addContent(new CDATA(GameState.OK.toString()));
        xmlAge.addContent(new CDATA(age));
        xmlTitle.addContent(new CDATA(title));
        for (String author : authors) {
            Element xmlAuthor = new Element("author");
            xmlAuthor.addContent(new CDATA(author));
            xmlAuthors.addContent(xmlAuthor);
        }
        for (String note : notes) {
            Element xmlNote = new Element("note");
            xmlNote.addContent(new CDATA(note));
            xmlNotes.addContent(xmlNote);
        }
        xmlGameplay.addContent(new CDATA(gamePlay));
        xmlGamerules.addContent(new CDATA(gameRules));
        
        xmlRoot.addContent(xmlYear);
        xmlRoot.addContent(xmlCategories);
        xmlRoot.addContent(xmlShortDescription);
        xmlRoot.addContent(xmlPublic);
        xmlRoot.addContent(xmlGameState);
        xmlRoot.addContent(xmlAge);
        xmlRoot.addContent(xmlTitle);
        xmlRoot.addContent(xmlAuthors);
        xmlRoot.addContent(xmlNotes);
        xmlRoot.addContent(xmlGameplay);
        xmlRoot.addContent(xmlGamerules);
        
        Document xmlDocument = new Document(xmlRoot);
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        try {
            File outputFile = new File("infos.xml");
            outputter.output(xmlDocument, new FileOutputStream(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
