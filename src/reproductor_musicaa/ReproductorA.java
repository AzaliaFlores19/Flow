/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor_musicaa;

import java.io.*;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javazoom.jl.decoder.JavaLayerException;


/**
 *
 * @author GPS
 */
public class ReproductorA extends javax.swing.JFrame {
    
    SongList canciones=new SongList();
    ArrayList actualizaCanciones= new ArrayList();
    
    javazoom.jl.player.Player player;
    File repro;
    boolean paused = false;
    String nombreCancion="";
    int currentTime = 0;
    

    /**
     * Creates new form PlayerMemo
     */
    public ReproductorA() {
        initComponents();
        this.setLocationRelativeTo(null);
        actualizarList();
    }
    
    
    void actualizarList(){
    
       actualizaCanciones = canciones.getPlayList();
    DefaultListModel<String> model = new DefaultListModel<>();

    for (int i = 0; i < actualizaCanciones.size(); i++) {
        int j = i + 1;
        File file = (File) actualizaCanciones.get(i);
        model.addElement(j + " | " + file.getName());
    }
    jPlaylist.setModel(model);     
    }
    
    
    void agregarCancion(){
     canciones.crearCarpeta();
    canciones.escojerF.setMultiSelectionEnabled(true);
    int fileValid = canciones.escojerF.showOpenDialog(this);

    if (fileValid == javax.swing.JFileChooser.CANCEL_OPTION) {
        return;
    } else if (fileValid == javax.swing.JFileChooser.APPROVE_OPTION) {
        File[] files = canciones.escojerF.getSelectedFiles();

        for (File cancionF : files) {
            canciones.agregarCancion(cancionF);
        }
    }
    actualizarList();
    }
   
      void GuardarCancion(){
       canciones.guardarCanciones();
       actualizarList();
    }
      File play1;
      static int inicio=0; 
     void play() {
         if (inicio == 0) {
        int indiceSeleccionado = jPlaylist.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            File selectedSongFile = (File) actualizaCanciones.get(indiceSeleccionado);
            String nombreCancion = selectedSongFile.getName().replaceAll("\\.(mp3|wav)$", "");
            nombreCancion = nombreCancion; 
            nombreSong.setText("Reproduciendo: " + nombreCancion); // Actualiza nombre song
            try {
                FileInputStream fis = new FileInputStream(selectedSongFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                inicio = 1;
                
                // Inicia new song
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            player.play();
                            nombreSong.setText(""); 
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Problema al tocar msica");
                            System.out.println(e);
                        }
                    }
                }.start();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problema al tocar msica");
                   System.out.println(e);
            }
        }
    } else {
        player.close();
        inicio = 0;
        nombreCancion = ""; 
        nombreSong.setText(""); 
        play();
    }
          
      }
     
      File songF;
    void next() throws FileNotFoundException, JavaLayerException{
        if (actualizaCanciones.isEmpty()) {
        // La lista está vacía, no hay canciones que reproducir, puedes mostrar un mensaje o simplemente salir de la función.
        return;
    }
     if (inicio == 0) {
        int firstSong = jPlaylist.getSelectedIndex() + 1;
        if (firstSong >= actualizaCanciones.size()) {
            firstSong = 0; //Recorrido Circular
        }
        songF = (File) this.canciones.listaS.get(firstSong);
        FileInputStream fis = new FileInputStream(songF);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new javazoom.jl.player.Player(bis);
        inicio = 1;
        jPlaylist.setSelectedIndex(firstSong);
        
        //  nombre song
        String nombreCancion = songF.getName().replaceAll("\\.(mp3|wav)$", "");
        nombreCancion = nombreCancion;

        // Actualizar el nombre de la canción en la etiqueta
        nombreSong.setText("Reproduciendo: " + nombreCancion);

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                }
            }
        }.start();
    } else {
        player.close();
        inicio = 0;
        next();
    }
          
      }
    //i
     void previous() throws FileNotFoundException, JavaLayerException{
      if (actualizaCanciones.isEmpty()) {
        // La lista está vacía, no hay canciones que reproducir, puedes mostrar un mensaje o simplemente salir de la función.
        return;
    }
           if (inicio == 0) {
        int firstSong = jPlaylist.getSelectedIndex() - 1;
        if (firstSong < 0) {
            firstSong = actualizaCanciones.size() - 1; // Recorrido circular
        }
        songF = (File) this.canciones.listaS.get(firstSong);
        FileInputStream fis = new FileInputStream(songF);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new javazoom.jl.player.Player(bis);
        inicio = 1;
        jPlaylist.setSelectedIndex(firstSong);

        // Obtener el nombre de la canción
        String nombreCancion = songF.getName().replaceAll("\\.(mp3|wav)$", "");
        nombreCancion= nombreCancion;

        // nombre song
        nombreSong.setText("Reproduciendo: " + nombreCancion);

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                }
            }
        }.start();
    } else {
        player.close();
        inicio = 0;
        previous();
    }
      }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPlaylist = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        btnprevius = new javax.swing.JButton();
        btnplay = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        nombreSong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPlaylist.setBackground(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportView(jPlaylist);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Reproductor De Musica ");

        btnprevius.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nextF.png"))); // NOI18N
        btnprevius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviusActionPerformed(evt);
            }
        });

        btnplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/playF1.png"))); // NOI18N
        btnplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplayActionPerformed(evt);
            }
        });

        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/previousF.png"))); // NOI18N
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnadd.setText("ADD");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnstop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Stop.png"))); // NOI18N
        btnstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstopActionPerformed(evt);
            }
        });

        btnPause.setBackground(new java.awt.Color(204, 0, 255));
        btnPause.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        nombreSong.setFont(new java.awt.Font("SimSun", 3, 18)); // NOI18N
        nombreSong.setForeground(new java.awt.Color(0, 51, 51));
        nombreSong.setText("Reproduciendo: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(nombreSong, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnprevius, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnplay, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(470, 470, 470)
                .addComponent(btnPause))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)))
                .addGap(20, 20, 20)
                .addComponent(nombreSong)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnprevius, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnplay)
                    .addComponent(btnstop)
                    .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(btnPause))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnpreviusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreviusActionPerformed
        try {
            previous();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReproductorA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(ReproductorA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnpreviusActionPerformed

    private void btnplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnplayActionPerformed
        play();
    }//GEN-LAST:event_btnplayActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        try {
            next();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReproductorA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(ReproductorA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        agregarCancion();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstopActionPerformed
        if (player != null) {
            player.close();
            inicio = 0;
            nombreSong.setText("Reproduciendo: ");
        }
    }//GEN-LAST:event_btnstopActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        if (player != null) {
            player.close();
            inicio = 0;
            nombreSong.setText("Reproduciendo: ");
        }
    }//GEN-LAST:event_btnPauseActionPerformed

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
            java.util.logging.Logger.getLogger(ReproductorA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReproductorA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReproductorA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReproductorA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReproductorA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnplay;
    private javax.swing.JButton btnprevius;
    private javax.swing.JButton btnstop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jPlaylist;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreSong;
    // End of variables declaration//GEN-END:variables

    private DefaultListModel DefaultListModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
