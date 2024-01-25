
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;


public class ControleurP4 implements ActionListener{
	
	private enum EtatControleur {
		ATTENTE_CLIC_PION_ROUGE, ATTENTE_CLIC_PION_JAUNE, FIN_PARTIE
	}
	
	private VueP4 vue;
	private ModeleP4 modele;
	private EtatControleur etat;
	
	
	public ControleurP4(VueP4 vue) {
		this.vue = vue;
		this.modele = new ModeleP4();
		this.etat = EtatControleur.ATTENTE_CLIC_PION_ROUGE;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().contentEquals("Rejouer")) {
			this.vue.initialiser();
			this.modele.initialiser();
			System.out.println(this.modele);
			this.etat = EtatControleur.ATTENTE_CLIC_PION_ROUGE;
		} else {
			
			switch (this.etat) {
			case ATTENTE_CLIC_PION_ROUGE :
				try {			
					this.modele.jouer(CouleurP4.ROUGE, ((PionP4) b).getColonne());
					this.vue.modifCase(this.modele);
					this.vue.modifPion(CouleurP4.ROUGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(this.modele.estPartieFinie()) {
					this.etat = EtatControleur.FIN_PARTIE;
					this.vue.modifPion();
					if(this.modele.partieNulle()) {
						this.vue.afficherResultat(0);
					}else {
						this.vue.afficherResultat(1);
					}
				}else {
					this.etat = EtatControleur.ATTENTE_CLIC_PION_JAUNE;
				}
				break;
			case ATTENTE_CLIC_PION_JAUNE :
				try {
					modele.jouer(CouleurP4.JAUNE, ((PionP4) b).getColonne());
					this.vue.modifCase(this.modele);
					this.vue.modifPion(CouleurP4.JAUNE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(modele.estPartieFinie()) {
					
					this.etat = EtatControleur.FIN_PARTIE;
					this.vue.modifPion();
					if(this.modele.partieNulle()) {
						this.vue.afficherResultat(0);
					}else {
						this.vue.afficherResultat(2);
					}
					
				}else {
					this.etat = EtatControleur.ATTENTE_CLIC_PION_ROUGE;
				}		
				break;
			default:
				break;
			}
		}
	}

}
