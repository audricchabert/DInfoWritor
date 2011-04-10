package fr.unice.polytech.devint.dinfowritor.controlers;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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
            String dpublic, ArrayList<String> notes, String gamePlay,
            String gameRules) {
        Element xmlRoot = new Element("infos");
        Element xmlYear = new Element("year");
        Element xmlCategories = new Element("gamecategories");
        Element xmlShortDescription = new Element("shortdescription");
        Element xmlPublic = new Element("public");
        Element xmlTitle = new Element("title");
        Element xmlAuthors = new Element("authors");
        Element xmlNotes = new Element("notes");
        Element xmlGameplay = new Element("gameplay");
        Element xmlGamerules = new Element("gamerules");

        xmlYear.setText(year);
        for (String category : categories) {
            Element xmlCategory = new Element("gamecategory");
            xmlCategory.setText(category);
            xmlCategories.addContent(xmlCategory);
        }
        xmlShortDescription.setText(shortDescription);
        xmlPublic.setText(dpublic);
        xmlTitle.setText(title);
        for (String author : authors) {
            Element xmlAuthor = new Element("author");
            xmlAuthor.setText(author);
            xmlAuthors.addContent(xmlAuthor);
        }
        for (String note : notes) {
            Element xmlNote = new Element("note");
            xmlNote.setText(note);
            xmlNotes.addContent(xmlNote);
        }
        xmlGameplay.setText(gamePlay);
        xmlGamerules.setText(gameRules);
        
        xmlRoot.addContent(xmlYear);
        xmlRoot.addContent(xmlCategories);
        xmlRoot.addContent(xmlShortDescription);
        xmlRoot.addContent(xmlPublic);
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
