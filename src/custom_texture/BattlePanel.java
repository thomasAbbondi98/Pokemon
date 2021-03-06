package custom_texture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import objects.Pokemon;
import screen.Board;

import static objects.Pokemon.Status.*;

/**
 * @author Thomas
 */
public class BattlePanel extends ExpandPanel {
    private final static String PATH = Board.ROOT+"/resources/image/";
    private final static int BAR_DELAY = 30;
    private final static int ICON_SIZE = 20;
    
    private boolean hit = false;
    private int hp, maxhp, damage;
    private final JPanel ExpPanel;
    private JProgressBar ExpBar;
    private Timer barTimer;
    private Pokemon pokemon;
    
    /**
     * Creates new form BattlePanel
     * @param pkmn
     * @param isPlayer
     */
    public BattlePanel(Pokemon pkmn, boolean isPlayer) {
        pokemon = pkmn;
        initComponents();
        ExpPanel = new JPanel();
        setMinimumSize(new Dimension(100, 35));
        int heigth, width = (int) (200*(Board.xDIM));
        if (isPlayer) {
            heigth = (int) (80*(Board.yDIM));
            setPreferredSize(new Dimension(width, heigth));
            setSize(getPreferredSize());
//            ExpPanel.setMaximumSize(new Dimension(400, 20));
            ExpPanel.setMinimumSize(new Dimension(100, 5));
            heigth = (int) (10*(Board.yDIM));
            ExpPanel.setPreferredSize(new Dimension(width, heigth));
            ExpPanel.setLayout(new BorderLayout());
            
            ExpBar = new JProgressBar();
            
            ExpPanel.add(ExpBar);
            GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            add(ExpPanel, gridBagConstraints);
        } else {
            heigth = (int) (70*(Board.yDIM));
            setPreferredSize(new Dimension(width, heigth));
            setSize(getPreferredSize());
        }
        expandComponent();
        setHit(false);
        setTimer(0, 0, 0);
        startThread();
        
        printName(pokemon.getSurname());
        printHPBar(true);
        printLevel();
        printStat();
        printExpBar(true);
    }
    
    public final void setHit(boolean start) {
        hit = start;
    }
    public final boolean getHit() {
        return hit;
    }
    public final void setTimer(int hpTemp, int maxhpTemp, int damageTemp) {
        hp = hpTemp;
        maxhp = maxhpTemp;
        damage = damageTemp;
    }
    
    public final void startThread() {
        barTimer = new Timer(BAR_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (hit) {
                    if (damage > 0) {
                        if (hp > 0) {
                            --damage;
                            getHealtBar().setValue(getHealtBar().getValue()-1);
                            --hp;
                            getHealt().setText(hp+"/"+maxhp);
                        } else {
                            getHealtBar().setValue(0);
                            hit = false;
                        }
                    } else {
                        hit = false;
                    }
                }
            }
        });
        barTimer.start();
    }
    
    public final void printHPBar(boolean change) {
        if (change) {
            HealtBar.setMaximum(pokemon.getStat("MaxHP"));
        }
        HealtBar.setValue(pokemon.getStat("HP"));
        Healt.setText(pokemon.getStat("HP")+"/"+pokemon.getStat("MaxHP"));
    }
    
    public final void printName(String name) {
        Name.setText(name.toUpperCase());
    }
    
    public final void printLevel() {
        if (!pokemon.getIfAsessual()) {
            String sex;
            if (pokemon.getIfMale()) { sex = "Male"; } else { sex = "Female"; }
            int sizex = (int) (ICON_SIZE*(Board.xDIM));
            int sizey = (int) (ICON_SIZE*(Board.yDIM));
            Gender.setIcon(ImageConverter.getScaledImage(
                    ImageConverter.getImage(PATH+sex+".png"), sizex, sizey));
        }
        Level.setText("Lv. "+pokemon.getStat("Level")+"");
    }
    
    public final void printStat() {
        Status.setForeground(Color.black);
        switch (pokemon.getStatus()) {
            case OK:
                Status.setText("");
                Status.setBackground(new Color(0, 0, 0, 0));
                break;
            case Asleep:
                Status.setText("SLP");
                Status.setBackground(Color.gray);
                break;
            case BadPoison:
                Status.setText("BPSN");
                Status.setBackground(Color.magenta);
                break;
            case Burn:
                Status.setText("BRN");
                Status.setBackground(Color.orange);
                break;
            case Freeze:
                Status.setText("FRZ");
                Status.setBackground(Color.cyan);
                break;
            case Paralysis:
                Status.setText("PAR");
                Status.setBackground(Color.yellow);
                break;
            case Poison:
                Status.setText("PSN");
                Status.setBackground(Color.pink);
                break;
            default:
                break;
        }
    }
    
    public final void printExpBar(boolean change) {
        if (ExpBar != null) {
            if (change) {
                ExpBar.setMinimum(pokemon.getLevelExperience(pokemon.getStat("Level")));
                ExpBar.setMaximum(pokemon.getLevelExperience(pokemon.getStat("Level")+1));
            }
            ExpBar.setValue(pokemon.getStat("Exp"));
            ExpBar.repaint();
        }
    }

    public JLabel getGender() {
        return Gender;
    }
    public JLabel getHealt() {
        return Healt;
    }
    public JLabel getLevel() {
        return Level;
    }
    public JLabel getNameLabel() {
        return Name;
    }
    public JLabel getStatus() {
        return Status;
    }
    public JProgressBar getHealtBar() {
        return HealtBar;
    }
    public JProgressBar getExpBar() {
        return ExpBar;
    }
    
    public void setPokemon(Pokemon pkmn) {
        pokemon = pkmn;
        printHPBar(true);
        printLevel();
        printStat();
        printExpBar(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        NamePanel = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        Level = new javax.swing.JLabel();
        HPpanel = new javax.swing.JPanel();
        HPlbl = new javax.swing.JLabel();
        HealtBar = new javax.swing.JProgressBar();
        StatusPanel = new javax.swing.JPanel();
        Status = new javax.swing.JLabel();
        Healt = new javax.swing.JLabel();

        setBackground(new java.awt.Color(75, 75, 75));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMinimumSize(new java.awt.Dimension(100, 35));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(200, 70));
        setLayout(new java.awt.GridBagLayout());

        NamePanel.setMinimumSize(new java.awt.Dimension(200, 25));
        NamePanel.setName(""); // NOI18N
        NamePanel.setOpaque(false);
        NamePanel.setPreferredSize(new java.awt.Dimension(200, 25));
        NamePanel.setLayout(new java.awt.GridBagLayout());

        Name.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Name.setText("NAME");
        Name.setInheritsPopupMenu(false);
        Name.setMaximumSize(null);
        Name.setMinimumSize(new java.awt.Dimension(130, 25));
        Name.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        NamePanel.add(Name, gridBagConstraints);

        Gender.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Gender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Gender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Gender.setInheritsPopupMenu(false);
        Gender.setMaximumSize(null);
        Gender.setMinimumSize(new java.awt.Dimension(25, 25));
        Gender.setName(""); // NOI18N
        Gender.setPreferredSize(new java.awt.Dimension(25, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        NamePanel.add(Gender, gridBagConstraints);

        Level.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Level.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Level.setText("LVL");
        Level.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Level.setInheritsPopupMenu(false);
        Level.setMaximumSize(null);
        Level.setMinimumSize(new java.awt.Dimension(45, 25));
        Level.setPreferredSize(new java.awt.Dimension(45, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        NamePanel.add(Level, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(NamePanel, gridBagConstraints);

        HPpanel.setBackground(new java.awt.Color(51, 51, 51));
        HPpanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        HPpanel.setMaximumSize(null);
        HPpanel.setMinimumSize(new java.awt.Dimension(200, 25));
        HPpanel.setPreferredSize(new java.awt.Dimension(200, 25));
        HPpanel.setLayout(new java.awt.GridBagLayout());

        HPlbl.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        HPlbl.setForeground(new java.awt.Color(255, 102, 0));
        HPlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HPlbl.setText("HP");
        HPlbl.setInheritsPopupMenu(false);
        HPlbl.setMaximumSize(null);
        HPlbl.setMinimumSize(new java.awt.Dimension(40, 20));
        HPlbl.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        HPpanel.add(HPlbl, gridBagConstraints);

        HealtBar.setBackground(new java.awt.Color(51, 51, 51));
        HealtBar.setForeground(new java.awt.Color(51, 255, 51));
        HealtBar.setValue(100);
        HealtBar.setBorderPainted(false);
        HealtBar.setMaximumSize(null);
        HealtBar.setMinimumSize(new java.awt.Dimension(150, 15));
        HealtBar.setPreferredSize(new java.awt.Dimension(150, 15));
        HealtBar.setString("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        HPpanel.add(HealtBar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(HPpanel, gridBagConstraints);

        StatusPanel.setMinimumSize(new java.awt.Dimension(200, 20));
        StatusPanel.setName(""); // NOI18N
        StatusPanel.setOpaque(false);
        StatusPanel.setLayout(new java.awt.GridBagLayout());

        Status.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Status.setText("STATUS");
        Status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        Status.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Status.setInheritsPopupMenu(false);
        Status.setMaximumSize(null);
        Status.setMinimumSize(new java.awt.Dimension(50, 20));
        Status.setName(""); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(50, 20));
        Status.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        StatusPanel.add(Status, gridBagConstraints);

        Healt.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Healt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Healt.setText("HP Point");
        Healt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Healt.setInheritsPopupMenu(false);
        Healt.setMaximumSize(null);
        Healt.setMinimumSize(new java.awt.Dimension(150, 20));
        Healt.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        StatusPanel.add(Healt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(StatusPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Gender;
    private javax.swing.JLabel HPlbl;
    private javax.swing.JPanel HPpanel;
    private javax.swing.JLabel Healt;
    private javax.swing.JProgressBar HealtBar;
    private javax.swing.JLabel Level;
    private javax.swing.JLabel Name;
    private javax.swing.JPanel NamePanel;
    private javax.swing.JLabel Status;
    private javax.swing.JPanel StatusPanel;
    // End of variables declaration//GEN-END:variables
}
