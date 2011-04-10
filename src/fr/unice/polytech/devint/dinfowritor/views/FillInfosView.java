package fr.unice.polytech.devint.dinfowritor.views;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.unice.polytech.devint.dinfowritor.controlers.WriteInfoController;
import fr.unice.polytech.devint.dinfowritor.models.GameCategory;
import fr.unice.polytech.devint.dinfowritor.models.Public;

@SuppressWarnings("serial")
public class FillInfosView extends JPanel {

    private WriteInfoController controller;
    private JTextField yearField;
    private JTextField titleField;
    private ArrayList<JTextField> authorsField;
    private ArrayList<JComboBox> categoriesField;
    private JTextField shortDescriptionField;
    private JComboBox publicField;
    private ArrayList<JTextField> notesField;
    private JTextField gameplayField;
    private JTextField gameRulesField;
    private JPanel form;
    private JScrollPane formScrollPane;

    public FillInfosView(WriteInfoController controller) {
        this.controller = controller;

        this.yearField = new JTextField();
        this.titleField = new JTextField();
        this.authorsField = new ArrayList<JTextField>();
        this.authorsField.add(new JTextField());
        this.categoriesField = new ArrayList<JComboBox>();
        this.categoriesField.add(new JComboBox(GameCategory.validCategory
                .keySet().toArray()));
        this.shortDescriptionField = new JTextField();
        this.publicField = new JComboBox(Public.validPublic.keySet().toArray());
        this.notesField = new ArrayList<JTextField>();
        this.notesField.add(new JTextField());
        this.gameplayField = new JTextField();
        this.gameRulesField = new JTextField();

        draw();

    }

    void draw() {
        this.setLayout(new GridBagLayout());

        JLabel titre = new JLabel(
                "Interface de création des fichiers infos.xml");

        this.form = createForm();
        this.formScrollPane = new JScrollPane(this.form);

        Button generate = new Button("Générer");
        generate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                generate();

            }
        });

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1.0;
        c.weighty = 0.02;
        c.gridx = c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;

        this.add(titre, c);

        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = 1;

        this.add(formScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0.05;
        c.gridheight = GridBagConstraints.REMAINDER;

        this.add(generate, c);
    }

    protected void generate() {
        String year = this.yearField.getText();
        String title = this.titleField.getText();

        ArrayList<String> authors = new ArrayList<String>();
        for (JTextField textField : this.authorsField) {
            authors.add(textField.getText());
        }

        ArrayList<String> categories = new ArrayList<String>();
        for (JComboBox comboBox : this.categoriesField) {
            categories.add((String) comboBox.getSelectedItem());
        }

        String shortDescription = this.shortDescriptionField.getText();
        String dpublic = (String) this.publicField.getSelectedItem();

        ArrayList<String> notes = new ArrayList<String>();
        for (JTextField textField : this.notesField) {
            notes.add(textField.getText());
        }

        String gamePlay = this.gameplayField.getText();
        String gameRules = this.gameRulesField.getText();

        this.controller.generate(year, title, authors, categories,
                shortDescription, dpublic, notes, gamePlay, gameRules);

    }

    private JPanel createForm() {

        JPanel form = new JPanel();

        form.setLayout(new GridBagLayout());

        JLabel yearLabel = new JLabel("Année: ");
        JLabel titleLabel = new JLabel("Nom du jeu: ");
        JLabel authorsLabel = new JLabel("Auteurs: ");
        JLabel categoriesLabel = new JLabel("Type de jeu: ");
        JLabel shortdescriptionLabel = new JLabel("Courte description: ");
        JLabel publicLabel = new JLabel("Public : ");
        JLabel notesLabel = new JLabel("Notes :");
        JLabel gameplayLabel = new JLabel("Gameplay :");
        JLabel gamerulesLabel = new JLabel("Règles du jeu: ");
        Button addAuthorButton = new Button("Ajouter un auteur");
        addAuthorButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addAuthor();
            }
        });

        Button removeAuthorButton = new Button("Retirer un auteur");
        removeAuthorButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                removeAuthor();
            }
        });

        Button addCategoryButton = new Button("Ajouter une catégorie");
        addCategoryButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addCategory();
            }
        });

        Button removeCategoryButton = new Button("Retirer une Categorie");
        removeCategoryButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                removeCategory();
            }
        });

        Button addNoteButton = new Button("Ajouter une note");
        addNoteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addNote();
            }
        });

        Button removeNoteButton = new Button("Retirer une note");
        removeNoteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                removeNote();
            }
        });

        GridBagConstraints c = new GridBagConstraints();

        int gridy = 0;

        gridy = createEntry(yearLabel, this.yearField, gridy, form);
        gridy = createEntry(titleLabel, this.titleField, gridy, form);
        gridy = createEntries(authorsLabel, this.authorsField, gridy, form);

        c.gridx = 1;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.insets = new Insets(3, 5, 0, 5);
        form.add(addAuthorButton, c);
        c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        form.add(removeAuthorButton, c);
        gridy++;

        gridy = createEntriesCBox(categoriesLabel, this.categoriesField, gridy,
                form);

        c.gridx = 1;
        c.gridy = gridy;
        c.insets = new Insets(3, 5, 0, 5);
        c.gridwidth = 1;
        form.add(addCategoryButton, c);
        c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        form.add(removeCategoryButton, c);
        gridy++;

        gridy = createEntry(shortdescriptionLabel, this.shortDescriptionField,
                gridy, form);
        gridy = createEntry(publicLabel, this.publicField, gridy, form);
        gridy = createEntries(notesLabel, this.notesField, gridy, form);

        c.gridx = 1;
        c.gridy = gridy;
        c.insets = new Insets(3, 5, 0, 5);
        c.gridwidth = 1;
        form.add(addNoteButton, c);
        c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        form.add(removeNoteButton, c);
        gridy++;

        gridy = createEntry(gameplayLabel, this.gameplayField, gridy, form);
        gridy = createEntry(gamerulesLabel, this.gameRulesField, gridy, form);

        return form;
    }

    protected void addAuthor() {
        this.authorsField.add(new JTextField());
        this.removeAll();
        this.draw();
        this.validate();
        this.repaint();

    }

    protected void removeAuthor() {
        if (authorsField.size() > 0) {
            this.authorsField.remove(authorsField.size() - 1);
        }
        this.removeAll();
        this.draw();
        this.validate();
        this.repaint();
    }

    protected void addNote() {
        this.notesField.add(new JTextField());
        this.removeAll();
        this.draw();
        this.validate();
        this.repaint();

    }

    protected void removeNote() {
        if (notesField.size() > 0) {
            this.notesField.remove(notesField.size() - 1);
        }
        this.removeAll();
        this.draw();
        this.validate();
        this.repaint();
    }

    protected void addCategory() {
        this.categoriesField.add(new JComboBox(GameCategory.validCategory
                .keySet().toArray()));
        this.removeAll();
        this.draw();
        this.validate();
        this.repaint();

    }

    protected void removeCategory() {
        if (categoriesField.size() > 0) {
            this.categoriesField.remove(categoriesField.size() - 1);
        }
        this.removeAll();
        this.draw();
        this.validate();
        this.repaint();
    }

    private int createEntry(JLabel label, JComponent component, int gridy,
            JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.02;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = gridy++;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.BASELINE_LEADING;
        c.insets = new Insets(3, 5, 0, 0);

        panel.add(label, c);

        c.insets = new Insets(3, 5, 0, 5);
        c.weightx = 1.0;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.BASELINE;

        panel.add(component, c);

        return gridy;
    }

    private int createEntries(JLabel label, ArrayList<JTextField> textFields,
            int gridy, JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.02;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.insets = new Insets(3, 5, 0, 0);
        c.anchor = GridBagConstraints.BASELINE_LEADING;

        panel.add(label, c);

        c.insets = new Insets(3, 5, 0, 5);
        c.weightx = 1.0;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.BASELINE;
        for (JTextField textField : textFields) {
            c.gridy = gridy++;
            panel.add(textField, c);
        }

        return gridy;
    }

    private int createEntriesCBox(JLabel label, ArrayList<JComboBox> cboxes,
            int gridy, JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.02;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.insets = new Insets(3, 5, 0, 0);
        c.anchor = GridBagConstraints.BASELINE_LEADING;

        panel.add(label, c);

        c.insets = new Insets(3, 5, 0, 5);
        c.weightx = 1.0;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.BASELINE;
        for (JComboBox cbox : cboxes) {
            c.gridy = gridy++;
            panel.add(cbox, c);
        }

        return gridy;
    }
}
