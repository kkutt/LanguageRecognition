package ssn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.encog.neural.networks.BasicNetwork;
import ssn.LanguageRecognition;
import ssn.file.DataFile;
import ssn.file.NetworkFile;
import ssn.file.TextFile;
import ssn.network.NetworkModes;
import ssn.network.NetworkUtils;

/**
 *
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class Gui extends javax.swing.JFrame {

    /* zmienne */
    String networkFileName;
    String dataFileName;
    String newLang;
    HashSet<TextFile> textFiles;
    BasicNetwork network;
    Mode mode;
    public enum Mode { LEARN, TEST }
    
    /** Creates new form Gui */
    public Gui() {
        initComponents();
        initVariables();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        netNameTextField = new javax.swing.JTextField();
        netOpenButton = new javax.swing.JButton();
        netSaveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        languageList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        addLanguageButton = new javax.swing.JButton();
        dataSaveButton = new javax.swing.JButton();
        dataNameTextField = new javax.swing.JTextField();
        dataOpenButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        addFileButton = new javax.swing.JButton();
        clearLanguagesButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textLanguageList = new javax.swing.JList();
        clearFilesButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        learnRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        testRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Language Recognition");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("Language Recognition");

        jLabel2.setText("Aktualna sieć:");

        startButton.setMnemonic('S');
        startButton.setText("Start!");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        netNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netNameTextFieldActionPerformed(evt);
            }
        });

        netOpenButton.setText("Wczytaj sieć");
        netOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netOpenButtonActionPerformed(evt);
            }
        });

        netSaveButton.setText("Zapisz sieć");
        netSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netSaveButtonActionPerformed(evt);
            }
        });

        languageList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "CZ", "DE", "EN", "ES", "EZ", "FR", "HU", "IT", "LA", "PL", "SK" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(languageList);

        jLabel3.setText("Lista języków:");

        addLanguageButton.setText("Dodaj język");
        addLanguageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLanguageButtonActionPerformed(evt);
            }
        });

        dataSaveButton.setText("Zapisz dane");
        dataSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSaveButtonActionPerformed(evt);
            }
        });

        dataNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNameTextFieldActionPerformed(evt);
            }
        });

        dataOpenButton.setText("Wczytaj dane");
        dataOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataOpenButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Aktualne dane:");

        jLabel5.setText("Pary (język - tekst):");

        exitButton.setText("Wyjdź");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        addFileButton.setText("Dodaj plik");
        addFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileButtonActionPerformed(evt);
            }
        });

        clearLanguagesButton.setText("Wyczyść");
        clearLanguagesButton.setActionCommand("Wyczyść języki");
        clearLanguagesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLanguagesButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(textLanguageList);

        clearFilesButton.setText("Wyczyść");
        clearFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFilesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(netNameTextField))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(netOpenButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(netSaveButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataNameTextField))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dataOpenButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataSaveButton))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addLanguageButton)
                                    .addComponent(addFileButton)))
                            .addComponent(jLabel3)
                            .addComponent(clearLanguagesButton))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(clearFilesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(netNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(netOpenButton)
                            .addComponent(netSaveButton)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dataNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataOpenButton)
                            .addComponent(dataSaveButton))))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addLanguageButton)
                                .addGap(68, 68, 68)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearLanguagesButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startButton)
                            .addComponent(exitButton)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearFilesButton)))
                .addContainerGap())
        );

        jMenu2.setText("Tryb");

        learnRadioButtonMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        modeChooseButtonGroup.add(learnRadioButtonMenuItem);
        learnRadioButtonMenuItem.setSelected(true);
        learnRadioButtonMenuItem.setText("Nauka");
        learnRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learnRadioButtonMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(learnRadioButtonMenuItem);

        testRadioButtonMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        modeChooseButtonGroup.add(testRadioButtonMenuItem);
        testRadioButtonMenuItem.setText("Testy");
        testRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testRadioButtonMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(testRadioButtonMenuItem);

        jMenuBar1.add(jMenu2);

        aboutMenu.setMnemonic('P');
        aboutMenu.setText("Pomoc");

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        aboutMenuItem.setText("O programie");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(aboutMenuItem);

        jMenuBar1.add(aboutMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initVariables() {
        networkFileName = "";
        dataFileName = "";
        textFiles = new HashSet<TextFile>();
        network = null;
        mode = Mode.LEARN;
        newLang = "";
    }
    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        
        String info = "";
        
        if(textFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brak plików", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR: Brak plikow");
            return;
        }
        
        int langsQuantity = languageList.getModel().getSize();
        
        if(langsQuantity == 0) {
            JOptionPane.showMessageDialog(this, "Brak języków", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR: Brak jezykow");
            return;
        }
        
        String langs[] = new String[langsQuantity];
        for(int i = 0; i < langsQuantity; i++) {
            langs[i] = (String) languageList.getModel().getElementAt(i);
        }
        
        if( mode == Mode.LEARN ) {
            network = NetworkModes.learn(textFiles, langs);
            info = NetworkModes.getLearnInfo();
        } else if ( mode == Mode.TEST ) {
            if(network == null) {
                JOptionPane.showMessageDialog(this, "Wczesniej naucz siec!", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println("ERROR: Wczesniej naucz siec!");
                return;
            }
            
            NetworkModes.test(textFiles, langs, network);
            info = NetworkUtils.getTestInfo();
        }
        
        if(!info.isEmpty()){
            String title;
            if(mode == Mode.LEARN)
                title = "Learning";
            else
                title = "Tests results";
            final JDialog resultsDialog = new JDialog(this, title, true);
            JScrollPane infoPane = new JScrollPane(new JLabel(info));
            resultsDialog.add(infoPane);
            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    resultsDialog.setVisible(false);
                }
            });
            
            JButton save = new JButton("Zapisz");
            save.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    FileWriter fw = null;
                    try {
                        fw = new FileWriter("TestResults.html");
                        if(mode == Mode.LEARN)
                            fw.write(NetworkModes.getLearnInfo());
                        else
                            fw.write(NetworkUtils.getTestInfo());
                        fw.close();
                    } catch (IOException ex) {
                        System.out.println("WARN: Blad zapisu do pliku TestResults.html");
                    } finally {
                        try {
                            fw.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            });
        
            JPanel resultsPanel = new JPanel();
            resultsPanel.add(ok);
            resultsPanel.add(save);
            resultsDialog.add(resultsPanel, BorderLayout.SOUTH);
            if(mode == Mode.LEARN)
                resultsDialog.setSize(950,400);
            else
                resultsDialog.setSize(500,800);
        
            resultsDialog.setResizable(false);
            resultsDialog.setVisible(true);
        }
        
    }//GEN-LAST:event_startButtonActionPerformed

    private void netNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netNameTextFieldActionPerformed
        // nothing
    }//GEN-LAST:event_netNameTextFieldActionPerformed

    private void dataNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNameTextFieldActionPerformed
        // nothing
    }//GEN-LAST:event_dataNameTextFieldActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void netOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netOpenButtonActionPerformed
        JFileChooser netChooser = new JFileChooser();
        FileNameExtensionFilter netFilter = new FileNameExtensionFilter("Pliki sieci (.net)", "net");
        netChooser.setFileFilter(netFilter);
        netChooser.setCurrentDirectory(new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"data"));
        int netResult = netChooser.showDialog(this, "Wybierz sieć");
        if(netResult == 0){
            networkFileName = netChooser.getSelectedFile().getPath();
            netNameTextField.setText(networkFileName.substring(networkFileName.lastIndexOf(System.getProperty("file.separator")) + 1));
            network = NetworkFile.loadNetworkFromFile(networkFileName);
        }
    }//GEN-LAST:event_netOpenButtonActionPerformed

    private void addFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileButtonActionPerformed
        JFileChooser addFileChooser = new JFileChooser();
        FileNameExtensionFilter addFileFilter = new FileNameExtensionFilter("Plik txt", "txt");
        addFileChooser.setFileFilter(addFileFilter);
        addFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"data"));
        int addFileResult = addFileChooser.showDialog(this, "Wybierz plik");
        int selectedLanguage = languageList.getSelectedIndex();
        String addFileLanguage;
        if (selectedLanguage == -1)
            addFileLanguage = "PL";
        else
            addFileLanguage = languageList.getSelectedValue().toString();
        if(addFileResult == 0){
            String newTextFileName = addFileChooser.getSelectedFile().getPath();
            TextFile newTextFile = new TextFile(newTextFileName,addFileLanguage);
            textFiles.add(newTextFile);
            refreshPairList();
        }
    }//GEN-LAST:event_addFileButtonActionPerformed

    private void dataOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataOpenButtonActionPerformed
        JFileChooser dataChooser = new JFileChooser();
        FileNameExtensionFilter dataFilter = new FileNameExtensionFilter("Pliki danych (.txt)", "txt");
        dataChooser.setFileFilter(dataFilter);
        dataChooser.setCurrentDirectory(new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"data"));
        int dataResult = dataChooser.showDialog(this, "Wybierz dane");
        if(dataResult == 0){
            dataFileName = dataChooser.getSelectedFile().getPath();
            dataNameTextField.setText(dataFileName.substring(dataFileName.lastIndexOf(System.getProperty("file.separator")) + 1));
            textFiles.clear();
            DataFile.loadData(dataFileName, textFiles);
            refreshLanguageList();
            refreshPairList();
        }
    }//GEN-LAST:event_dataOpenButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                              
        final JDialog about = new JDialog(this, "O programie", true);
        about.add(new JLabel("<html><h1>LanguageRecognition</h1><hr>"
                + "<h3>Projekt przygotowany na przedmiot Sztuczne Sieci Neuronowe 2011/2012."
                + "<br>"
                + "Autorzy: Krzysztof Kutt & Michał Nowak (wtorek 10:00)."
                + "<br>"
                + "<br>"
                + "Aplikacja ma na celu rozpoznawanie języka zadanego tekstu na podstawie częstotliwości"
                + "występowania liter. Wykorzystano sieć neuronową."
                + "<br>"
                + "<br>"
                + "Przygotowano przykładowy zbiór tekstów do nauki i testowania sieci przygotowano w jedynastu językach: "
                + "<ul>"
                + "<li>CZ - czeski</li>"
                + "<li>DE - niemiecki</li>"
                + "<li>EN - angielski</li>"
                + "<li>ES - hiszpański</li>"
                + "<li>EZ - esperanto</li>"
                + "<li>FR - francuski</li>"
                + "<li>HU - węgierski</li>"
                + "<li>IT - włoski</li>"
                + "<li>LA - łacina</li>"
                + "<li>PL - polski</li>"
                + "<li>SK - słowacki</li>"
                + "</ul>"
                + "<br>"
                + "Nic nie stoi na przeszkodzie, aby program rozszerzać "
                + "o dowolną ilość rozpoznawanych języków - "
                + "wymagany jest tylko odpowiedni materiał do nauki dla sieci.</html>"));
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                about.setVisible(false);
            }
        });
        
        JPanel aboutPanel = new JPanel();
        aboutPanel.add(ok);
        about.add(aboutPanel, BorderLayout.SOUTH);
        about.setSize(500,600);
        about.setResizable(false);
        about.setVisible(true);
    }                                         

    private void learnRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learnRadioButtonMenuItemActionPerformed
        mode = Mode.LEARN;
    }//GEN-LAST:event_learnRadioButtonMenuItemActionPerformed

    private void testRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testRadioButtonMenuItemActionPerformed
        mode = Mode.TEST;
    }//GEN-LAST:event_testRadioButtonMenuItemActionPerformed

    private void clearLanguagesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLanguagesButtonActionPerformed
        languageList.setListData(new Vector<String>());
    }//GEN-LAST:event_clearLanguagesButtonActionPerformed

    private void clearFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilesButtonActionPerformed
         textLanguageList.setListData(new Vector<String>());
         textFiles.clear();
    }//GEN-LAST:event_clearFilesButtonActionPerformed

    private void dataSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSaveButtonActionPerformed
        if (dataNameTextField.getText().isEmpty())
            dataFileName = LanguageRecognition.DEFAULT_DATA_FILENAME;
        else
            dataFileName = dataNameTextField.getText();
        dataFileName = "data" + System.getProperty("file.separator") + dataFileName + ".txt";
        DataFile.saveData(dataFileName, textFiles);
    }//GEN-LAST:event_dataSaveButtonActionPerformed

    private void netSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netSaveButtonActionPerformed
        if(netNameTextField.getText().isEmpty())
            networkFileName = LanguageRecognition.DEFAULT_NETWORK_FILENAME;
        else
            networkFileName = netNameTextField.getText();
        networkFileName = "data" + System.getProperty("file.separator") + networkFileName + ".net";
        NetworkFile.saveNetworkToFile(network, networkFileName);
    }//GEN-LAST:event_netSaveButtonActionPerformed

    private void addLanguageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLanguageButtonActionPerformed
        final JDialog langDialog = new JDialog(this, "Dodaj język", true);
        JLabel title = new JLabel("Język:");
        final JTextField lang = new JTextField();
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newLang = lang.getText();
                sortLanguageList(true);
                langDialog.setVisible(false);
            }
        });
        
        JButton cancel = new JButton("Anuluj");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                langDialog.setVisible(false);
            }
        });
        
        
        JPanel langPanel = new JPanel();
        langPanel.setLayout(new GridLayout(2,2,5,5));
        langPanel.add(title);
        langPanel.add(lang);
        langPanel.add(ok);
        langPanel.add(cancel);
        
        langDialog.add(langPanel, BorderLayout.CENTER );
        langDialog.setSize(150,100);
        langDialog.setResizable(false);
        langDialog.setVisible(true);
    }//GEN-LAST:event_addLanguageButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
    
    
    private void refreshPairList() {
        
        final String model[] = new String[ textFiles.size() ];
        int index = 0;
        for( TextFile text : textFiles ) {
            model[index] = text.getLanguage() + " - " + text.getFilename();
            index++;
        }
        Arrays.sort(model);
        
        textLanguageList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = model;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }
    
    
    /** 
     * przeglada liste plikow i sprawdza jakie sa jezyki (do wykorzystywania przy wczytywaniu zestawu danych
     */
    private void refreshLanguageList() {
        HashSet<String> modelSet = new HashSet<String>();
        for( TextFile text : textFiles ) {
            modelSet.add(text.getLanguage());
        }
        final String[] model = new String[modelSet.size()];
        modelSet.toArray(model);
        Arrays.sort(model);
        languageList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = model;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }
    
    
    /** 
     * sortuje aktualna liste jezykow (do wykorzystania przy dodawaniu nowego jezyka)
     * 
     * @param newElement czy do listy zostal dodany nowy element i znajduje sie w zmiennej
     *                   globalnej String newLang?
     */
    private void sortLanguageList(boolean newElement) {
        int elem = 0;
        if(newElement)
            elem = 1;
        final String[] model = new String[languageList.getModel().getSize() + elem];
        for( int i = 0; i < languageList.getModel().getSize(); i++) {
            model[i] = (String) languageList.getModel().getElementAt(i);
        }
        if(newElement)
            model[model.length-1] = newLang;
        Arrays.sort(model);
        languageList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = model;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }
    
    
    private javax.swing.ButtonGroup modeChooseButtonGroup = new ButtonGroup();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addFileButton;
    private javax.swing.JButton addLanguageButton;
    private javax.swing.JButton clearFilesButton;
    private javax.swing.JButton clearLanguagesButton;
    private javax.swing.JTextField dataNameTextField;
    private javax.swing.JButton dataOpenButton;
    private javax.swing.JButton dataSaveButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList languageList;
    private javax.swing.JRadioButtonMenuItem learnRadioButtonMenuItem;
    private javax.swing.JTextField netNameTextField;
    private javax.swing.JButton netOpenButton;
    private javax.swing.JButton netSaveButton;
    private javax.swing.JButton startButton;
    private javax.swing.JRadioButtonMenuItem testRadioButtonMenuItem;
    private javax.swing.JList textLanguageList;
    // End of variables declaration//GEN-END:variables
}
