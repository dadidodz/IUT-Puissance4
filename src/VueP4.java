import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VueP4 extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaseP4 [][] cases;
	private PionP4 [] pions;
	private JTextField ptsRouge;
	private JTextField ptsJaune;
	private JTextField ptsNulle;
	
	public VueP4() {
				
		this.cases = new CaseP4 [6][7];
		this.pions = new PionP4 [7];
		//NORTH
		ControleurP4 controleur = new ControleurP4(this);
		this.setLayout(new BorderLayout());
		JPanel haut = new JPanel();
		haut.setLayout(new GridLayout(1, 2));
		JPanel hautGauche = new JPanel();
		hautGauche.setLayout(new GridLayout(3,2));
		
		hautGauche.add(new JLabel("Victoire jaune"));
		this.ptsJaune = new JTextField("0");
		this.ptsJaune.setEditable(false);
		hautGauche.add(this.ptsJaune);
		
		hautGauche.add(new JLabel("Victoire rouge"));
		this.ptsRouge = new JTextField("0");
		this.ptsRouge.setEditable(false);
		hautGauche.add(this.ptsRouge);
		
		hautGauche.add(new JLabel("Match nul"));
		this.ptsNulle = new JTextField("0");
		this.ptsNulle.setEditable(false);
		hautGauche.add(this.ptsNulle);
		
		JButton b = new JButton("Rejouer");
		b.addActionListener(controleur);

		haut.add(hautGauche);
		haut.add(b);

		this.add(haut, BorderLayout.NORTH);
		
		//CENTER
		JPanel bas = new JPanel();
		bas.setLayout(new GridLayout(7, 7));
		for (int i=0 ; i<7 ; i++) {
			this.pions[i] = new PionP4(i);
			try {
				Image img = ImageIO.read(getClass().getResource("images/pionRouge.jpg"));
				this.pions[i].setIcon(new ImageIcon(img));
			} catch (Exception ex) {
				System.out.println(ex);
			}
			this.pions[i].addActionListener(controleur);
			bas.add(this.pions[i]); 
		}
		for(int i=0;i<6;i++) {
			for(int j=0; j<7; j++) {
				this.cases[i][j]= new CaseP4(i,j);
				try {
					Image img = ImageIO.read(getClass().getResource("images/caseVide.jpg"));
					this.cases[i][j].setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
				bas.add(this.cases[i][j]);
			}
		}
		this.add(bas, BorderLayout.CENTER);

	}
	
	public void modifCase(ModeleP4 modele) {
		for(int i=0;i<6;i++) {
			for(int j=0; j<7; j++) {
				try {
					CouleurP4 couleur = modele.getCouleur(i, j);
					switch (couleur) {
					case ROUGE:
						try {
							Image img = ImageIO.read(getClass().getResource("images/caseRouge.jpg"));
							this.cases[i][j].setIcon(new ImageIcon(img));
						} catch (Exception ex) {
							System.out.println(ex);
						}
						break;
					case JAUNE:
						try {
							Image img = ImageIO.read(getClass().getResource("images/caseJaune.jpg"));
							this.cases[i][j].setIcon(new ImageIcon(img));
						} catch (Exception ex) {
							System.out.println(ex);
						}
						break;
					default:
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void modifPion(CouleurP4 couleur) {
		for(int i=0; i<7; i++) {
			switch (couleur) {
			case ROUGE:
				try {
					Image img = ImageIO.read(getClass().getResource("images/pionJaune.jpg"));
					this.pions[i].setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
				break;
			case JAUNE:
				try {
					Image img = ImageIO.read(getClass().getResource("images/pionRouge.jpg"));
					this.pions[i].setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void modifPion() {
		for(int i=0; i<7; i++) {
			this.pions[i].setIcon(null);
		}
	}
	
	public void afficherResultat(int i) {
		switch(i) {
			case 0:
				String text1 = this.ptsNulle.getText();
				int a1 = Integer.parseInt(text1) + 1;
				this.ptsNulle.setText(a1 + "");
				break;	
			case 1:
				String text2 = this.ptsRouge.getText();
				int a2 = Integer.parseInt(text2) + 1;
				this.ptsRouge.setText(a2 + "");
				break;	
			case 2:
				String text3 = this.ptsJaune.getText();
				int a3 = Integer.parseInt(text3) + 1;
				this.ptsJaune.setText(a3 + "");
				break;
		}
	}
	
	public void initialiser () {
		for (int i=0 ; i<7 ; i++) {
			try {
				Image img = ImageIO.read(getClass().getResource("images/pionRouge.jpg"));
				this.pions[i].setIcon(new ImageIcon(img));
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		for(int i=0;i<6;i++) {
			for(int j=0; j<7; j++) {				
				try {
					Image img = ImageIO.read(getClass().getResource("images/caseVide.jpg"));
					this.cases[i][j].setIcon(new ImageIcon(img));
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
	}
}
